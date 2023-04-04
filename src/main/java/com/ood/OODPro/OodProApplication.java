package com.ood.OODPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import lombok.extern.slf4j.Slf4j;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication(scanBasePackages = {"com.ood.OODPro"})
@ComponentScan("com.ood.OODPro")
@EnableAspectJAutoProxy
@Slf4j
public class OodProApplication {

	public static void main(String[] args) {
		SpringApplication.run(OodProApplication.class, args);
	}

}
