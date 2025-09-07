package com.utp.infrastructure.config;

import com.utp.application.annotation.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.utp.application.usecase",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION, value = UseCase.class
        )
)
public class DomainConfig {
}
