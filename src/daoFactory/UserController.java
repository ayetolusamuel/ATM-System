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
           UserController.getInstance().save(user);
        }
    }

    public List<User> getAllUser() throws SQLException {
       // return mUser.getAllUser();
        return null;
    }

    public User findByPhoneNumber(String phoneNumber) throws SQLException{
        return mUserDaoQuery.findByPhone(phoneNumber);
    }
    public void updateAmountDeposited(double balance,double previous, String phone) throws  SQLException{
        mUserDaoQuery.AmountDeposited(balance,previous,phone);
    }
    public String getAmountDeposited(String phoneNumber) throws SQLException{
        return mUserDaoQuery.getAmountDeposited(phoneNumber);
    }
    public String getBalance(String phoneNumber) throws SQLException{
        return mUserDaoQuery.balance(phoneNumber);
    }
//    public void updateAmountDeposited(String phoneNumber,double previous,double amountDeposited)throws SQLException{
//        mUserDaoQuery.updateAmountDeposited(previous,amountDeposited,phoneNumber);
//    }
}
