package com.totvs.sl.school.core;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ImplicitGrantBuilder;
import springfox.documentation.builders.LoginEndpointBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private SchoolProperties properties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()                                            
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(apiInfo())
                .enable(properties.getSwagger()
                .isEnabled());
    }

    
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Serviço School: API REST")
                .description("Interface de apresentação das API REST disponíveis para o Serviço School").build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().clientId(properties.getSwagger().getOauth2().getClientId())
                .clientSecret(properties.getSwagger().getOauth2().getClientSecret()).scopeSeparator(" ").build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new ImplicitGrantBuilder()
                .loginEndpoint(new LoginEndpointBuilder()
                        .url(properties.getSwagger().getOauth2().getAuthServer() + "/connect/authorize").build())
                .build();

        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth").grantTypes(Arrays.asList(grantType))
                .scopes(Arrays.asList(scopes())).build();
        return oauth;
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = { new AuthorizationScope("authorization_api", "Authorization API"),
                new AuthorizationScope("offline_access", "Access Offline") };
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/api.*")).build();
    }
 
}