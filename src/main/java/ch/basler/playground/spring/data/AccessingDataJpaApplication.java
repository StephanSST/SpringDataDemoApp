package ch.basler.playground.spring.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class AccessingDataJpaApplication {

  private static final Logger LOG = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }

  @Bean
  public CommandLineRunner demo(ApplicationContext ctx) {
    return (args) -> {

      LOG.info("Showing all registered endpoints:");
      ctx.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().forEach((key, value) -> LOG.info("{} {}", key, value));
      LOG.info("Let's get started with some DB tasks");

    };
  }

}