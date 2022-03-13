package com.mostafa.student.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Student.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {}
