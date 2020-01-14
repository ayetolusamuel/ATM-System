package model;

import daoFactory.DaoFactory;
import daoFactory.DatabaseConnection;
import daoFactory.UserDao;

import java.sql.SQLException;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String accountNumber;
    private String accountType;
    private int secretPin;

    public int getSecretPin() {
        return secretPin;
    }

    public void setSecretPin(int secretPin) {
        this.secretPin = secretPin;
    }

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, String accountNumber, String accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public  String getFullName(){
        return lastName+ " "+firstName;
    }

//
//
//    public void saveUser() throws SQLException{
//       userDao().saveUser(this);
//    }
//    public List<User> getAllUser()throws SQLException{
//        return userDao().getUser();
//    }
//
//
//    public User getUserByPhoneNumber(String phoneNumber) throws SQLException{
//        return userDao().findByPhoneNumber(phoneNumber);
//    }
//
//    private UserDao userDao(){
//        DaoFactory daoFactory = DaoFactory.getDatabase();
//        return daoFactory.getUserDao();
//    }
}
