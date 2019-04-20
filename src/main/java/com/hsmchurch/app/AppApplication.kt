package com.hsmchurch.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableJpaAuditing
@SpringBootApplication
open class AppApplication

fun main(args: Array<String>) {
    runApplication<AppApplication>(*args)
}