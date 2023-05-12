package es.ieslavereda.demospring.repository.model;

import oracle.jdbc.datasource.impl.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource {

    public static DataSource getMySQLDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@//192.168.1.38:1539/xe");
        dataSource.setUser("sys as sysdba");
        dataSource.setPassword("oracle");
        return dataSource;
    }
}
