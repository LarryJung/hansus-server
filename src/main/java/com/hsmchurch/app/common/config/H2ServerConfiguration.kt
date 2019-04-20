package com.hsmchurch.app.common.config

import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.sql.SQLException
import javax.sql.DataSource

@Configuration
@Profile("local")
open class H2ServerConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari") // yml의 설정값을 Set한다.
    @Throws(SQLException::class)
    open fun dataSource(): DataSource {
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start()
        return com.zaxxer.hikari.HikariDataSource()
    }
}