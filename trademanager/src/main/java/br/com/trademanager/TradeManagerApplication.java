package br.com.trademanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class TradeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeManagerApplication.class, args);
	}

}
