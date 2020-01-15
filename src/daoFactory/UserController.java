package daoFactory;

import model.User;
import utils.Utils;
import views.OpenAccount;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private static UserController mController;
    private User mUser;
    UserDaoQueryImpl mUserDaoQuery = new UserDaoQueryImpl();
    private UserController(User user) {
        mUser = user;
    }

    public UserController() {
    }

//    public static UserController getInstance(User user) {
//        if (mController == null) {
//            mController = new UserController(user);
//        }
//        return mController;
//    }

    public static UserController getInstance() {
        if (mController == null) {
            mController = new UserController();
        }
        return mController;
    }


    public void save(User user) throws SQLException {

        if (user != null) {
           mUserDaoQuery.saveUser(user);
        }
    }

    public List<User> getAllUser() throws SQLException {
       // return mUser.getAllUser();
        return null;
    }

    public User findByPhoneNumber(String phoneNumber) throws SQLException{
        return mUserDaoQuery.findByPhone(phoneNumber);
    }
    public String updateAmountDeposited(double previousAmount,double amountDeposted,double balance, String phone) throws  SQLException{
        return mUserDaoQuery.AmountDeposited(previousAmount,amountDeposted,balance,phone);
    }
    public String getAmountDeposited(String phoneNumber) throws SQLException{
        return mUserDaoQuery.getAmountDeposited(phoneNumber);
    }
    public String getBalance(String phoneNumber) throws SQLException{
        return mUserDaoQuery.balance(phoneNumber);
    }
    public String checkBalance(String phone, int pin) throws SQLException{
        return mUserDaoQuery.checkBalance(phone,pin);
    }

    public String updateOpenAccount(String firstName,String lastName, String accountType, String phoneNumber) throws SQLException{
        return mUserDaoQuery.updateOpenAccount(firstName,lastName,accountType,phoneNumber);
    }

}
