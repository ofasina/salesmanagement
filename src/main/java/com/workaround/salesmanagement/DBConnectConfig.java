/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.salesmanagement;

import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Olawale
 */
@Component
@ConfigurationProperties()
@Slf4j
public class DBConnectConfig {
    
     @Autowired
    Environment env;
    
    public JdbcTemplate jdbcConnect() {
        final DriverManagerDataSource datasource = new DriverManagerDataSource();
        try {
            
            datasource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
            datasource.setUrl(env.getProperty("spring.datasource.url"));
            datasource.setUsername(env.getProperty("spring.datasource.username"));
            datasource.setPassword(env.getProperty("spring.datasource.password"));

            log.debug("JDBC Database Connection: {} ", datasource.getConnection().getSchema());
        } catch (SQLException e) {
            log.error("Unable to connect: {} ", e.getMessage());
        }
        return new JdbcTemplate(datasource);
    }
}
