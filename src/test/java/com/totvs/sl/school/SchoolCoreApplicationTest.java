package com.totvs.sl.school;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.totvs.sl.school.core.SchoolCoreApplication;

public class SchoolCoreApplicationTest {

	@Test
	public void contextLoads() {
	    SchoolCoreApplication.main(new String[] { "--spring.profiles.active=local" });
		assertThat(true).isTrue();
	}
}
