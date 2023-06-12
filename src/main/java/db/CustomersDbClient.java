package db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.sql.*;

public class CustomersDbClient {

    Connection conn;
    Statement st;
    private static final Logger logger = LoggerFactory.getLogger(CustomersDbClient.class);

    public Connection createConnection() {
        String url
                = "jdbc:sqlite:/Users/nitishbector/Documents/projects/sdet-assignment/customers.db"; // table details

        String query
                = "select * from customers where name='Nitish'"; // query to be run
        try {
            Class.forName(
                    "org.sqlite.JDBC"); // Driver name
            conn = DriverManager.getConnection(
                    url);
        } catch (ClassNotFoundException  | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(
                "Connection Established successfully");
        return conn;
    }

    public void executeEquery(String query) throws SQLException {
        conn = createConnection();
        logger.info(String.format("\nfetching details from query --- %s", query), true);
        Reporter.log(String.format("\nfetching details from query --- %s", query), true);
        try {
            st = conn.createStatement();
            ResultSet rs
                    = st.executeQuery(query); // Execute query
            rs.next();
            String name
                    = rs.getString("phone_number"); // Retrieve name from db

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
                Reporter.log("Closed database successfully", true);
            }
        }
        System.out.println("Connection Closed....");
    }

    public void executeUpdateGenericQuery(String query) throws SQLException {
        conn = createConnection();
        Statement selectStmt = null;
        try {
            logger.info(String.format("\nfetching details from query --- %s", query), true);
            Reporter.log(String.format("\nfetching details from query --- %s", query), true);
            selectStmt = conn.createStatement();
            int rs = selectStmt.executeUpdate(query);
            if (rs == 0) {
                logger.info("\nResultSet is empty", true);
                Reporter.log(String.format("\nResultSet is empty", true));
            } else {
                logger.info("\nExecuted query successfully", true);
                Reporter.log(String.format("\nExecuted query successfully", true));
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (conn != null) {
                conn.close();
                Reporter.log("Closed database successfully", true);

            }
        }
    }

    public static void main(String[] args) throws SQLException {
        CustomersDbClient customersDbClient = new CustomersDbClient();
        customersDbClient.executeEquery("select * from customers where name='Nitish'");
        customersDbClient.executeEquery("select * from customers where name='Nitish'");
    }
}
