package com.example.jwt;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class JwtImplementationApplication implements DisposableBean {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {

		SpringApplication.run(JwtImplementationApplication.class, args);
	}

	@Override
	public void destroy() throws Exception {
		if (dataSource instanceof HikariDataSource) {
			((HikariDataSource) dataSource).close();
		}

	}
}
