package daoFactory;


import model.User;

import java.sql.SQLException;
import java.util.List;
public interface UserDao {
    void saveUser(User user)throws SQLException;
    List<User> getUser()throws SQLException;
    User findByPhoneNumber(String phone)throws SQLException;
    String AmountDeposited(double previousAmount,double deposited,double balance,String phone) throws SQLException;
    String getAmountDeposited(String phoneNumber) throws SQLException;
    String getBalance(String phoneNumber) throws SQLException;
    String retrieveCustomerBalance(String phone, int pin) throws SQLException;
    String updateOpenAccount(String firstName,String lastName, String accountType, String phoneNumber) throws  SQLException;

}
