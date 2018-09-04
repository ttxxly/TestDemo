package com.skm.demo;

import com.skm.common.mybatis.config.MybatisConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.util.Arrays;

@SpringBootApplication
@Import({MybatisConfiguration.class, Swagger2DocumentationConfiguration.class})
@ComponentScan(basePackages = {"com.skm.common.spring", "com.skm.demo"})
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext env = SpringApplication.run(Application.class, args);
        String[] activeProfiles = env.getEnvironment().getActiveProfiles();
        LOG.info("active profile: {}", Arrays.toString(activeProfiles));
    }
}
