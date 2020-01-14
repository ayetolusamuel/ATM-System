package daoFactory;


import model.User;

import java.sql.SQLException;
import java.util.List;
public interface UserDao {
    void saveUser(User user)throws SQLException;
    List<User> getUser()throws SQLException;
    User findByPhoneNumber(String phone)throws SQLException;
    void AmountDeposited(double previous,double deposited,String phone) throws SQLException;
    String getAmountDeposited(String phoneNumber) throws SQLException;
    String getBalance(String phoneNumber) throws SQLException;

}
