package dao;

import java.sql.*;

public abstract class BaseDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/persons?user=admin&password=admin&useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);

        return DriverManager.getConnection(CONNECTION_URL);
    }

    protected ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection con = null;
        PreparedStatement stmnt = null;
        ResultSet resultSet = null;

        try {
            con = createConnection();
            stmnt = con.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmnt.setObject(i + 1, params[i]);
            }

            resultSet = stmnt.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

            throw e;
        }

        return resultSet;
    }
}
