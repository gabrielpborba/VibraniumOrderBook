package br.com.orderbookmanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class OrderBookManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(OrderBookManagerApplication.class, args);

	}

}
