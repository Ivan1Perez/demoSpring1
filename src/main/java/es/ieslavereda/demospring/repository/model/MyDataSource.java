package es.ieslavereda.demospring.repository.model;

import oracle.jdbc.datasource.impl.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource {

    public static DataSource getMySQLDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@//10.0.0.21:1521/xe");
        dataSource.setUser("C##1DAWPEREZ");
        dataSource.setPassword("1234");
        return dataSource;
    }
}
