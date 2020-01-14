package utils;

import daoFactory.UserController;
import model.User;

import javax.swing.*;
import java.sql.SQLException;

public class Utils {
    private static Utils mUtils;

    public static Utils getInstance() {
        if (mUtils == null) {
            mUtils = new Utils();
        }
        return mUtils;
    }

    public String generateAccountNumber() {
        long accountNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;  //generate 10 digit Number
        return Long.toString(accountNumber);
    }

    public void clearText(JPasswordField pin,JTextField firstName, JTextField lastName, JTextField phoneNumber,JTextField accountNumber, JComboBox<String> jcmbAccountType) {
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        jcmbAccountType.setSelectedIndex(0);
        accountNumber.setText("");
        pin.setText("");

    }
    public double balanceAmountDeposited(double previous,double deposited){
        return previous + deposited;
    }
    public String getBalance(String phone) throws SQLException {
        return UserController.getInstance().getBalance(phone);
    }public String getAmountDeposited(String phone) throws SQLException {
        return UserController.getInstance().getAmountDeposited(phone);
    }

    public double resultBalanceFromPrevious(double balance, double previous){
        return balance - previous;
    }
}
