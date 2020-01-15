package com.totvs.sl.school.core;

import org.mockito.ArgumentCaptor;
import org.springframework.messaging.Message;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.totvs.tjf.core.common.security.SecurityPrincipal;
import com.totvs.tjf.core.message.TOTVSMessage;

public final class TestUtils {

	public static Long externalId = 1L;

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.registerModule(new JavaTimeModule());
	}

	public static String objectToJson(Object value) throws Exception {
		return mapper.writeValueAsString(value);
	}

	public static <T> T getMessagePayLoad(ArgumentCaptor<Message<?>> argument, Class<T> event) {
		return event.cast(((TOTVSMessage<?>) argument.getValue().getPayload()).getContent());
	}

	public static void setAuthentication(String tenant) {
		SecurityPrincipal principal = new SecurityPrincipal("", tenant, tenant, tenant);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}