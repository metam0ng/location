package com.location.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(value = {"com.location.repository.entity"})
@EnableJpaRepositories(value = {"com.location.repository"})
@ComponentScan(basePackages = {"com.location.repository"})
public class RepositoryConfig {

}
