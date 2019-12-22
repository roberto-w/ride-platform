package pl.wojcikiewicz.util;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionUtil {

    private static final Logger logger = Logger.getLogger(ConnectionUtil.class);

    private static final String CONNECTION_FILE_NAME = "src/main/resources/connection.properties";

    private ConnectionUtil() {
    } // private constructor

    public static Connection getConnection() {
        Properties connectionParams = getConnectionParams();

        String dbHost = connectionParams.getProperty("db_host");
        String dbPort = connectionParams.getProperty("db_port");
        String dbName = connectionParams.getProperty("db_name");
        String dbUser = connectionParams.getProperty("db_user");
        String dbPassword = connectionParams.getProperty("db_password");

        String dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            logger.error(""); // add sensible error msg
        }
        return connection;

    }

    private static Properties getConnectionParams() {
        Properties properties = null;
        try {
            FileReader fileReader = new FileReader(CONNECTION_FILE_NAME);

            properties = new Properties();
            properties.load(fileReader);

        } catch (FileNotFoundException e) {
            logger.error("");
        } catch (IOException e) {
            logger.error("");
        }
        return properties;
    }
}
