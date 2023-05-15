package es.ieslavereda.demospring.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.datasource.impl.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource {

    public static DataSource getMySQLDataSource() throws SQLException {
        MysqlDataSource mysqlDS = new MysqlDataSource();

        mysqlDS.setURL("jdbc:mysql://10.13.0.3:/java");
        mysqlDS.setUser("jalonso");
        mysqlDS.setPassword("1111");
        return mysqlDS;
    }
}
