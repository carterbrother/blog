package com.spring.story.demo.cache.pre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.spring.story.demo.cache.pre.dao.repository")
public class JpaConfig {
}
