package com.kitaplik.book_service;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Bean
	public GrpcServerConfigurer keepAliveServerConfigurer(){
		return serverBuilder -> {
			if (serverBuilder instanceof NettyServerBuilder) {
				((NettyServerBuilder) serverBuilder).keepAliveTime(30, TimeUnit.SECONDS)
						.keepAliveTimeout(50, TimeUnit.SECONDS).permitKeepAliveWithoutCalls(true);
			}
		};
	}
}
