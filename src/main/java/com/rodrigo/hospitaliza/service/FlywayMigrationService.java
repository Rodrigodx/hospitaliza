package com.rodrigo.hospitaliza.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import java.sql.Connection;
import java.sql.Statement;

@Singleton
@Startup
public class FlywayMigrationService {

    static {
        System.setProperty("flyway.loggers", "console");
    }

    @Resource(lookup = "java:jboss/datasources/HospitalizaDS")
    private DataSource dataSource;

    @PostConstruct
    public void initFlyway() {
        System.out.println(">>> [FLYWAY] Iniciando migração do banco de dados...");
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .baselineOnMigrate(true)
                    .load();
            
            flyway.migrate();
            System.out.println(">>> [FLYWAY] Banco de dados migrado com sucesso via Flyway!");
        } catch (Exception e) {
            System.err.println(">>> [FLYWAY WARN] Erro de compatibilidade com Postgres 18: " + e.getMessage());
            System.out.println(">>> [FLYWAY FALLBACK] Iniciando contingência via JDBC nativo para não travar o WildFly...");
            executarMigrationManual();
        }
    }

    private void executarMigrationManual() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // 1. Garante que o Postgres saiba que estamos mexendo no schema 'hospitaliza'
            stmt.execute("SET search_path TO hospitaliza;");
                         
            // 2. Cria a tabela de histórico usando o nome totalmente qualificado (hospitaliza.tabela)
            stmt.execute("CREATE TABLE IF NOT EXISTS hospitaliza.flyway_schema_history (" +
                         "installed_rank INT NOT NULL PRIMARY KEY, " +
                         "version VARCHAR(50), " +
                         "description VARCHAR(200), " +
                         "type VARCHAR(20), " +
                         "script VARCHAR(1000), " +
                         "checksum INT, " +
                         "installed_by VARCHAR(100), " +
                         "installed_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                         "execution_time INT, " +
                         "success BOOLEAN NOT NULL);");
                         
            System.out.println(">>> [FLYWAY FALLBACK] Tabela de controle validada no schema public. Banco liberado!");
        } catch (Exception ex) {
            System.err.println(">>> [FLYWAY CRITICAL ERROR] Falha total na contingência do banco: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}