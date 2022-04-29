/*
    Configuring AppConfig class to enable Thread(Asyn), ScheduleLock and
    Scheduling with a touch of LockProvider Bean creation and consumed by
    Spring container to create the explicit bean
*/

package com.example.schedulingtasks;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import javax.sql.DataSource;

@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "15m")
@EnableAsync
@Configuration
public class AppConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                        .withJdbcTemplate(new JdbcTemplate(dataSource))
                        .withTableName("Tradeshedlock")
                        .build());
    }

}
