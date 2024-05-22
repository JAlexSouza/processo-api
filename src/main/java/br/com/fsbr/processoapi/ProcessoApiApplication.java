package br.com.fsbr.processoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ProcessoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessoApiApplication.class, args);
	}

}
