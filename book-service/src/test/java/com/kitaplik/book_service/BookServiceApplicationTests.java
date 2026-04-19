package com.kitaplik.book_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"grpc.server.enabled=false",
		"spring.cloud.vault.enabled=false"
})
class BookServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
