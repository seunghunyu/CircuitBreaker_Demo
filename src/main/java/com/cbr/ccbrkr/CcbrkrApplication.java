package com.cbr.ccbrkr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class CcbrkrApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext ac;

	public static void main(String[] args) {
		SpringApplication.run(CcbrkrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		Arrays.sort(beanDefinitionNames);
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("beanDefinitionName = " + beanDefinitionName + ", bean type = " + bean.getClass().getName());

		}
	}

}
