package com.stuff.minor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinorApplicationTests {

	@Autowired
	private StringController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
}