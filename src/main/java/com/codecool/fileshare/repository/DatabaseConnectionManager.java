package com.codecool.fileshare.repository;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    public DataSource connect() throws SQLException {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setDatabaseName(System.getenv().get("DATABASE_NAME"));
        ds.setUser(System.getenv().get("USERNAME"));
        ds.setPassword(System.getenv().get("PASSWORD"));

        System.out.println("Trying to connect...");
        ds.getConnection().close();
        System.out.println("Connection OK");

        return ds;
    }
}

