package com.olalayeone.audittrailtest;

import com.olaleyeone.audittrail.advice.EntityManagerAdvice;
import com.olaleyeone.audittrail.impl.*;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.olaleyeone.audittrail.repository"})
@EntityScan({"com.olaleyeone.audittrail.entity", "com.olalayeone.audittrailtest.data.entity"})
public class TestApplication {

    @Bean
    public EntityManagerAdvice entityManagerAdvice() {
        return Mockito.mock(EntityManagerAdvice.class);
    }

    @Bean
    public TaskTransactionContextFactory taskTransactionContextFactory() {
        return new TaskTransactionContextFactory(new TaskContextHolder()) {

            @Override
            public TaskTransactionContext createTaskTransactionContext(TaskTransactionLogger taskTransactionLogger) {
                return Mockito.mock(TaskTransactionContext.class);
            }
        };
    }

    @Bean
    public TaskContextImpl taskContext() {
        return Mockito.mock(TaskContextImpl.class);
    }
}
