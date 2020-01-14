package daoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends DaoFactory {

    @Override
    public Connection openConnection() {
        String msAccDB = "database//codelagos_bank_system.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB;
        try {
            Connection connection = DriverManager.getConnection(dbURL
                    + ";jackcessOpener=CryptCodecOpener", "", "codelagos");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoQueryImpl();
    }


}
