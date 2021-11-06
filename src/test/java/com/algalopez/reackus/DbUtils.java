package com.algalopez.reackus;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
@Slf4j
public class DbUtils {

    private Connection connection;

    public Connection connection() {
        return connection;
    }

    @SneakyThrows
    public void executeUpdate(String sql) {
        connection.prepareStatement(sql).executeUpdate();
    }

    @SuppressWarnings("unused")
    void onStart(@Observes StartupEvent ev) {
        log.debug("The database is starting...");

        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:13306/reackus", "user", "pass");
        } catch (SQLException e) {
            log.error("Error creating test connection for database", e);
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings("unused")
    void onStop(@Observes ShutdownEvent ev) {
        log.debug("The database is stopping...");

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error("Error creating test connection for database", e);
            throw new IllegalStateException(e);

        }
    }
}
