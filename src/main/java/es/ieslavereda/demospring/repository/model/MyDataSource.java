package es.ieslavereda.demospring.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.datasource.impl.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource {

    public static DataSource getMySQLDataSource() throws SQLException {
        MysqlDataSource mysqlDS = new MysqlDataSource();

        mysqlDS.setURL("jdbc:mysql://192.168.1.36:/java");
        mysqlDS.setUser("iperez");
        mysqlDS.setPassword("1111");
        return mysqlDS;
    }
}
