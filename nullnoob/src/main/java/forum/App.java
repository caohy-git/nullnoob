package forum;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("forum.module.mapper")
@EnableSwagger2
public class App extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	protected SpringApplicationBuilder config(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(App.class);
    }
}
