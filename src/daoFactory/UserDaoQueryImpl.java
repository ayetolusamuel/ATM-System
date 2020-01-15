package daoFactory;

import model.User;
import utils.Utils;
import views.listener.MySqlListener;

import javax.rmi.CORBA.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoQueryImpl implements UserDao {
    private static final String
            INSERT = "INSERT INTO accountopening (fname, lname,pnumber,anumber,atype,pin) VALUES (?, ?,?,?,?,?)";

    private static final String
            ALL = "SELECT * FROM accountopening";

    private static final String
            UPDATE = "UPDATE amountdeposited SET pamount = '6000' WHERE pnumber = 08167137007";
    private static final String
            GET_AMOUNT_DEPOSITED = "SELECT adeposited FROM amountdeposited WHERE pnumber = ?";
    private static final String
            GET_BALANCE = "SELECT balance FROM amountdeposited WHERE pnumber = ?";
private static final String
            CHECK_BALANCE = "SELECT balance FROM amountdeposited WHERE pnumber = ? AND pin = ?";

    private static final String
            FIND_BY_LOGIN = "SELECT * FROM accountopening WHERE pnumber = ?";

    private static final String
            FIND_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String
            DELETE = "DELETE FROM users where id = ?";

    private static final String
            DELETE_ALL = "DELETE FROM users";
    // String query = "SELECT * FROM accountopening where pnumber like '" + number + "'";


    /**
     * Method to insert a user in the database
     *
     * @param user the user that will be inserted
     * @return user the inserted
     * @throws SQLException
     */
    private User insert(User user) throws SQLException {
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getPhoneNumber());
        pstmt.setString(4, user.getAccountNumber());
        pstmt.setString(5, user.getAccountType());
        pstmt.setInt(6, user.getSecretPin());
        pstmt.executeUpdate();
        pstmt.close();
        c.close();
        return user;
    }

    /**
     * Method to retrieve all users from the database
     *
     * @return users all users in the database
     * @throws SQLException
     */
    private List<User> getAllUser() throws SQLException {

        ArrayList<User> users = new ArrayList<User>();
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(ALL);
        ResultSet rset = pstmt.executeQuery();
        while (rset.next()) {
            User user = new User(rset.getString("fname")
                    , rset.getString("lname"));
            users.add(user);
        }

        pstmt.close();
        c.close();

        return users;
    }

    public String aDeposited(String phone) throws SQLException {
        Connection connection = DatabaseConnection.getDatabase().openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_AMOUNT_DEPOSITED);
        preparedStatement.setString(1, phone);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("adeposited");
        }
        return "No value for this customer!\nPlease register with us.";
    }
    public String balance(String phone) throws SQLException {
        Connection connection = DatabaseConnection.getDatabase().openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BALANCE);
        preparedStatement.setString(1, phone);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("balance");
        }
        return "No value for this customer!\nPlease register with us.";
    }

    public String checkBalance(String phone, int pin) throws SQLException {
        Connection connection = DatabaseConnection.getDatabase().openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_BALANCE);

        preparedStatement.setString(1, phone);
        preparedStatement.setInt(2, pin);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("balance");
        }
        return "No value for this customer!\nPlease register with us.";
    }


    public String updateOpenAccount(String firstName, String lastName,String accountType, String phoneNumber) throws SQLException {
        String update1 = "Update accountopening set fname = '"
                + firstName+ "' where pnumber = '"
                + phoneNumber + "'";
        String update2 = "Update accountopening set lname = '"
                + lastName + "' where pnumber = '"
                + phoneNumber + "'";
        String update3 = "Update accountopening set atype = '"
                + accountType + "' where pnumber = '"
                + phoneNumber + "'";


        Connection c = DaoFactory.getDatabase().openConnection();

        Statement statement = c.createStatement();

        //PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.KEEP_CURRENT_RESULT);

        statement.executeUpdate(update1);
        statement.executeUpdate(update2);
        statement.executeUpdate(update3);
        statement.close();
        c.close();
        return "update successfully.";
    }
  public String updateAmountDeposited(double previousAmount, double amountDeposited,double balance, String phoneNumber) throws SQLException {
        String update1 = "Update amountdeposited set pamount = '"
                + previousAmount+ "' where pnumber = '"
                + phoneNumber + "'";
        String update2 = "Update amountdeposited set adeposited = '"
                + amountDeposited + "' where pnumber = '"
                + phoneNumber + "'";
        String update3 = "Update amountdeposited set balance = '"
                + balance + "' where pnumber = '"
                + phoneNumber + "'";


        Connection c = DaoFactory.getDatabase().openConnection();

        Statement statement = c.createStatement();

        //PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.KEEP_CURRENT_RESULT);

        statement.executeUpdate(update1);
        statement.executeUpdate(update2);
        statement.executeUpdate(update3);
        statement.close();
        c.close();
        return "update successfully.";
    }

    //
//    /**
//     * Method to delete all users in the database
//     * @return rowsAffected the numbers of rows Affected
//     * @throws SQLException
//     */
//    public int deleteAll() throws SQLException {
//        Connection c = DaoFactory.getDatabase().openConnection();
//        PreparedStatement pstmt = c.prepareStatement(DELETE_ALL);
//
//        int rowsAffected = pstmt.executeUpdate();
//
//        pstmt.close();
//        c.close();
//
//        return rowsAffected;
//    }
//
//    /**
//     * Method to delete all users in the database
//     * @return rowsAffected the numbers of rows Affected
//     * @throws SQLException
//     */
//    public int delete(User user) throws SQLException {
//        Connection c = DaoFactory.getDatabase().openConnection();
//        PreparedStatement pstmt = c.prepareStatement(DELETE);
//        pstmt.setLong(1, user.getId());
//
//        int rowsAffected = pstmt.executeUpdate();
//
//        pstmt.close();
//        c.close();
//
//        return rowsAffected;
//    }
//
//
//    /**
//     * Method to find a user by login
//     * @return user User find by the login, otherwise null
//     * @throws SQLException
//     */
    public User findByPhone(String phoneNumber) throws SQLException {


        Connection c = DaoFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(FIND_BY_LOGIN);
        pstmt.setString(1, phoneNumber);

        User user = null;
        ResultSet rset = pstmt.executeQuery();

        while (rset.next()) {
            user = createUser(rset);
        }

        pstmt.close();
        c.close();

        return user;
    }

    private User createUser(ResultSet rset) throws SQLException {
        User user = new User();
        String fName = rset.getString("fname");
        String lName = rset.getString("lname");
        String accountNumber = rset.getString("anumber");
        String actType = rset.getString("atype");
        String pNumber = rset.getString("pnumber");

        user.setPhoneNumber(pNumber);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setAccountNumber(accountNumber);
        user.setAccountType(actType);

        return user;
    }

//    /**
//     * Method to find a user by login
//     * @return user User find by the id, otherwise null
//     * @throws SQLException
//     */
//    public User findById(Long id) throws SQLException {
//        Connection c = DaoFactory.getDatabase().openConnection();
//
//        PreparedStatement pstmt = c.prepareStatement(FIND_BY_ID);
//        pstmt.setLong(1, id);
//
//        User user = null;
//        ResultSet rset = pstmt.executeQuery();
//
//        while (rset.next()){
//            user = createUser(rset);
//        }
//
//        pstmt.close();
//        c.close();
//
//        return user;
//    }

    /* method to create users **/


    @Override
    public void saveUser(User user) throws SQLException {
        insert(user);
    }

    @Override
    public List<User> getUser() throws SQLException {
        return getAllUser();
    }

    @Override
    public User findByPhoneNumber(String phone) throws SQLException {
        return findByPhone(phone);
    }

    @Override
    public String AmountDeposited(double previousAmount, double amountDeposited,double balance, String phone) throws SQLException {
        return updateAmountDeposited(previousAmount, amountDeposited,balance, phone);
    }

    @Override
    public String getAmountDeposited(String phoneNumber) throws SQLException {
        return aDeposited(phoneNumber);
    }

    @Override
    public String getBalance(String phoneNumber) throws SQLException {
        return balance(phoneNumber);
    }

    @Override
    public String retrieveCustomerBalance(String phone, int pin) throws SQLException {
        return checkBalance(phone,pin);
    }



}
