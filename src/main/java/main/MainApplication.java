package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = { "main","service" })
public class MainApplication {

	public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(MainApplication.class, args);//init the context
        Main main = app.getBean(Main.class);
        main.doTask();
	}

}
