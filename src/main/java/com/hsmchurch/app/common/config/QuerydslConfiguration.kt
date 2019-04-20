package com.hsmchurch.app.common.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
open class QuerydslConfiguration {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Bean
    open fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(entityManager)
}
