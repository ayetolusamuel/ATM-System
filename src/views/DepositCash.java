package views;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daoFactory.DatabaseConnection;
import daoFactory.UserController;
import model.User;
import utils.Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author DELL
 */
public class DepositCash extends JFrame {
    private JPanel jPanel;
    private JLabel lblPhoneNumber, lblFullName, lblPreviousAmount, lblAmountDeposited, lblBalance;
    private JTextField txtPhoneNumber, txtFullName, txtPreviousAmount, txtAmountDeposited, txtBalance;
    private JButton btnCheck, btnSave;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    private double result;
    private String preAmount;

    private String amtDeposited;
    private String customerBalance;
    private String pin;
    private String pNumber;
    private JButton btnCancel;
    private JButton btnClear;

    public DepositCash() throws HeadlessException {
        //    databaseConnection.open();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                                    views.Homepage bankSystem2 = new views.Homepage();
//                                    bankSystem2.setLocation(300, 100);
//                                    bankSystem2.setResizable(false);
//                                    bankSystem2.setSize(550, 430);
//                                    bankSystem2.setVisible(true);
            }
        });
        displayGUI();
        registerListener();


    }

    private void registerListener() {
        btnCheck.addActionListener(e -> {
            String result = fetchDataBaseOnPhoneNumber(txtPhoneNumber.getText());
            System.out.println(result);

        });
        btnSave.addActionListener(e -> {
            deposit();
        });
    }

    private void displayGUI() {
        setTitle("Deposit Cash!!!!!!!!!!!");
        setSize(500, 330);
        jPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.getImage("images//background.jpg");
                MediaTracker t = new MediaTracker(this);
                t.addImage(img, 1);
                while (true) {
                    try {
                        t.waitForID(1);
                        break;
                    } catch (Exception e) {
                    }
                }
                g.drawImage(img, 0, 0, 600, 600, null);
            }

        };

        Image img = Toolkit.getDefaultToolkit().getImage("images//codelagos.jpg");
        setIconImage(img);
        jPanel.setLayout(null);
        lblPhoneNumber = new JLabel("Phone Number ");
        jPanel.add(lblPhoneNumber).setBounds(15, 50, 100, 20);
        lblPhoneNumber.setForeground(Color.white);
        lblPhoneNumber.setFont(new Font("Times New Roman", Font.ITALIC, 15));

        txtPhoneNumber = new JTextField();
        //  txtPhoneNumber.setText("08167137007");
        txtPhoneNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!((c == KeyEvent.VK_BACK_SPACE))) {
                    if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
                            c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {
                        getToolkit().beep();
                        ke.consume();
                    } else if (txtPhoneNumber.getText().length() > 10) {
                        getToolkit().beep();
                        ke.consume();
                    }
                }
            }
        });

        jPanel.add(txtPhoneNumber).setBounds(150, 51, 250, 20);

        // btnCheck = new JButton(new ImageIcon("images//sam.gif"));
        btnCheck = new JButton("Search");
        // btnCheck.addActionListener(this);
        jPanel.add(btnCheck).setBounds(410, 50, 50, 50);


        lblFullName = new JLabel("Full Name ");
        jPanel.add(lblFullName).setBounds(15, 90, 100, 20);
        lblFullName.setForeground(Color.white);
        lblFullName.setFont(new Font("Times New Roman", Font.ITALIC, 15));

        txtFullName = new JTextField();
        txtFullName.setEditable(false);
        txtFullName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        jPanel.add(txtFullName).setBounds(150, 91, 250, 20);

        lblPreviousAmount = new JLabel("Previous Balance ");
        jPanel.add(lblPreviousAmount).setBounds(15, 130, 140, 20);
        lblPreviousAmount.setForeground(Color.white);

        lblPreviousAmount.setFont(new Font("Times New Roman", Font.ITALIC, 15));

        txtPreviousAmount = new JTextField();
        txtPreviousAmount.setText("0.00");
        jPanel.add(txtPreviousAmount).setBounds(150, 131, 250, 20);

        lblAmountDeposited = new JLabel("Amount Deposited ");
        jPanel.add(lblAmountDeposited).setBounds(15, 170, 140, 20);
        lblAmountDeposited.setForeground(Color.white);
        lblAmountDeposited.setFont(new Font("Times New Roman", Font.ITALIC, 15));

        txtAmountDeposited = new JTextField();
        jPanel.add(txtAmountDeposited).setBounds(150, 171, 250, 20);
        txtAmountDeposited.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!((c == KeyEvent.VK_BACK_SPACE))) {
                    if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
                            c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {
                        getToolkit().beep();
                        ke.consume();
                    }
                }
            }
        });


        lblBalance = new JLabel("Balance ");
        // jPanel.add(lblBalance).setBounds(15, 210, 100, 20);
        lblBalance.setForeground(Color.white);
        lblBalance.setFont(new Font("Times New Roman", Font.ITALIC, 15));

        txtBalance = new JTextField();
        // jPanel.add(txtBalance).setBounds(150, 211, 250, 20);

        btnSave = new JButton(new ImageIcon("images//save.png"));
        btnSave.setForeground(Color.MAGENTA);
        btnSave.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        jPanel.add(btnSave).setBounds(120, 260, 50, 30);
        // btnSave.addActionListener(this);

        btnClear = new JButton(new ImageIcon("images//clear.png"));
        btnClear.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        btnClear.setForeground(Color.MAGENTA);
        jPanel.add(btnClear).setBounds(190, 260, 60, 30);
        // btnClear.addActionListener(this);


        btnCancel = new JButton(new ImageIcon("images//cancel.png"));
        btnCancel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        btnCancel.setForeground(Color.MAGENTA);
        jPanel.add(btnCancel).setBounds(270, 260, 80, 30);
        // btnCancel.addActionListener(this);

        setInvisible();

        getContentPane().add(jPanel, BorderLayout.CENTER);

    }

    private void setInvisible() {
        lblFullName.setEnabled(false);
        txtFullName.setEnabled(false);
        lblPreviousAmount.setEnabled(false);
        txtPreviousAmount.setEditable(false);
        lblAmountDeposited.setEnabled(false);
        txtAmountDeposited.setEditable(false);
        lblBalance.setEnabled(false);
        txtBalance.setEditable(false);
        btnSave.setEnabled(false);
        // btnCancel.setEnabled(false);
        btnClear.setEnabled(false);

    }

    private void setVisible() {
        btnCancel.setEnabled(true);
        lblFullName.setEnabled(true);
        txtFullName.setEnabled(true);
        lblPreviousAmount.setEnabled(true);
        txtPreviousAmount.setEditable(false);
        txtPreviousAmount.setEnabled(false);
        lblAmountDeposited.setEnabled(true);
        txtAmountDeposited.setEditable(true);
        lblBalance.setEnabled(true);
        txtBalance.setEditable(false);
        btnSave.setEnabled(true);
        btnClear.setEnabled(true);


    }

    private void clearText() {
        txtFullName.setText("");
        txtAmountDeposited.setText("");
        txtPreviousAmount.setText("");
        txtPhoneNumber.setText("");
        txtBalance.setText("");
    }


    private String fetchDataBaseOnPhoneNumber(String number) {
        String fullNameResult = null;
        try {


            if (number.length() == 0) {
                JOptionPane.showMessageDialog(this, "phone number can't be empty!!!!");

            } else if (number.length() != 11) {
                JOptionPane.showMessageDialog(this, "Invalid phone number number!!!!");
                //System.out.println("Invalid phone number number!!!!");
            } else {

                String query = "SELECT * FROM accountopening where pnumber like '" + number + "'";

                Connection connection = DatabaseConnection.getDatabase().openConnection();
                PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "No Record found this user!!!: " + number);
                    clearText();

                } else {
                    pNumber = resultSet.getString("pnumber").trim();
                    pin = resultSet.getString("pin").trim();
                    String fname = resultSet.getString("fname").trim();
                    String lname = resultSet.getString("lname").trim();
                    fullNameResult = lname + " " + fname;
                    txtFullName.setText(fullNameResult);
                    txtAmountDeposited.setEditable(true);
                    txtPreviousAmount.setText(getPreviousAmount(number));
                    btnSave.setEnabled(true);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fullNameResult;

    }

    private String getPreviousAmount(String number) {
        String previousAmount = null;
        try {


            String query = "SELECT pamount FROM amountdeposited where pnumber like '" + number + "'";

            Connection connection = DatabaseConnection.getDatabase().openConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                previousAmount = resultSet.getString("pamount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return previousAmount;

    }
//


    public String returnUserAmountBasedOnPhone(String pNumber) {
        String result;
        String previousAmount = txtPreviousAmount.getText();
        try {


            // String query = "SELECT * FROM amountdeposited where pnumber like '" + pNumber + "'AND pin like '"+pin+"'";
            String query = "SELECT * FROM amountdeposited where pnumber like '" + pNumber + "'";
            ResultSet resultSet;
            Connection connection = DatabaseConnection.getDatabase().openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {


            } else {
                String preAmount;
                preAmount = resultSet.getString("pamount").trim();
                //  System.out.println("Pre Amount "+preAmount);


//                  int  preAmountInt = Integer.parseInt(preAmount);


                // String amtDeposited = resultSet.getString("adeposited").trim();
                // String customerBalance = resultSet.getString("balance").trim();

                double preAmountDouble = Double.parseDouble(preAmount);

                if (!preAmount.equals("0")) {

                    double amtDepositedtDouble = Double.parseDouble(amtDeposited);
                    double customerBalanceDouble = Double.parseDouble(customerBalance);


//
//
//
//
                    // updateToDatabase(preAmountDouble, amtDepositedtDouble, customerBalanceDouble);
                    clearText();

                } else {
                    System.out.println("Save to database!!!!!");
                    //saveToDatabase();
                    // updateToDatabase(preAmountDouble, amtDepositedtDouble, customerBalanceDouble);
                    clearText();

                }
////
////
////
            }

            // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return preAmount;
    }

    //
    private double balanceCompute(double previousAmount, double amountDeposited) {
        double myResult = previousAmount + amountDeposited;
        return myResult;
    }

    private double returnBalanceFromDatabase(String pNumber) {

        try {
            String query = "SELECT balance FROM amountdeposited where pnumber like '" + pNumber + "'";
            Connection connection = DatabaseConnection.getDatabase().openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String balance = resultSet.getString("balance").trim();
                result = Double.parseDouble(balance);
            }
        } catch (Exception ex) {

        }


        return result;
    }

    //
    private void saveToDatabase(String pAmount, String aDeposited, String custBalance) {
        PreparedStatement ps;
        try {

            pAmount = txtPreviousAmount.getText();
            aDeposited = txtAmountDeposited.getText();
            custBalance = txtBalance.getText();
            System.out.println("Pin!!!!" + pin);
            System.out.println("Number " + pNumber);

//
//               ps = databaseConnection.getConnection().prepareStatement("UPDATE amountdeposited SET  pamount = ?, adeposited = ?,  balance = ? WHERE pnumber = ?");
//
//             ps.setString(1, String.valueOf(pAmount));
//             ps.setString(2, String.valueOf(aDeposited));
//             ps.setString(3, String.valueOf(custBalance));
//             ps.setString(4, pNumber);
//
//
//
//	ps.executeUpdate();
            JOptionPane.showMessageDialog(null, aDeposited + " Was Deposited To Your Account Successfully!!!!");

            clearText();

        } catch (Exception e) {
            // e.printStackTrace();
            System.err.println("Error in creating account!!!!!");
        }


    }

    private void cleatText() {
        txtAmountDeposited.setText("");
        txtFullName.setText("");
        txtPhoneNumber.setText("");
        txtPreviousAmount.setText("");


    }


    private static void openMainPage() {
        views.Homepage bankSystem2 = new views.Homepage();
        bankSystem2.setLocation(300, 100);
        bankSystem2.setResizable(false);
        bankSystem2.setSize(550, 300);
        bankSystem2.setVisible(true);
    }

    private void updateToDatabase(double pAmount, double pAmountDeposited) {
        try {

            String phoneNumber = txtPhoneNumber.getText();

            UserController.getInstance().updateAmountDeposited(pAmount, pAmountDeposited, phoneNumber);

            Icon info = new ImageIcon("images//info.jpg");
            JOptionPane.showMessageDialog(null, "<html><i>Account Updated Successfully!!!!\nNew Balance is  "
                    + Utils.getInstance().balanceAmountDeposited(pAmount, pAmountDeposited), "Information", JOptionPane.PLAIN_MESSAGE, info);

            clearText();
            btnCancel.setEnabled(true);
            btnSave.setEnabled(false);
            btnClear.setEnabled(false);
            txtAmountDeposited.setEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        DepositCash cash = new DepositCash();
        cash.setVisible(true);
        cash.setSize(500, 330);
        cash.setLocation(300, 100);
    }

//    @Override
//    public void actionPerformed(ActionEvent event) {
//        Object source = event.getSource();
//        if (source.equals(btnCheck)) {
//            String number = null, result;
//            try {
//
//                number = txtPhoneNumber.getText();
//                result = fetchDataBaseOnPhoneNumber(number);
//                if (result != null) {
//                    txtFullName.setText(result);
//
//                    setVisible();
//                }
//
//
//            } catch (Exception ex) {
//
//            }
//
//        }
//
//        if (source.equals(btnCancel)) {
//            setVisible(false);
////            OpenAccount account = new OpenAccount();
////            account.mainPage();
//
//        }
//        if (source.equals(btnClear)) {
//            clearText();
//            setInvisible();
//        }
//
//        if (source.equals(btnSave)) {
//
//
//
//
//
//    }

    private double sumPreviousAndAmountDeposited(double returnBalance, double parseDouble) {
        return returnBalance + parseDouble;
    }

    private void deposit() {
       try {

            double returnBalance = returnBalanceFromDatabase(pNumber);
            System.out.println("return Balance " + returnBalance);
            if (txtAmountDeposited.getText().matches("")) {
                Icon error = new ImageIcon("images//error.jpg");
                JOptionPane.showMessageDialog(null, "<html><i>Amount Deposited Can't Be Empty!!!!.", "ERROR", JOptionPane.PLAIN_MESSAGE, error);


            } else {

              double balance =  Utils.getInstance().balanceAmountDeposited(Double.parseDouble(txtAmountDeposited.getText()),
                      Double.parseDouble(txtPreviousAmount.getText()));

                System.out.println("balance "+balance);


                //update the previous, amtdep,balance
                String pAmount = txtPreviousAmount.getText();
                if (pAmount.equals("0.00")){
                    pAmount = "0";
                    UserController.getInstance().updateAmountDeposited(Double.parseDouble(txtBalance.getText()),
                            Double.parseDouble(pAmount),
                            txtPhoneNumber.getText());

                }else{
                    UserController.getInstance().updateAmountDeposited(Double.parseDouble(txtBalance.getText()),
                            Double.parseDouble(pAmount),
                            txtPhoneNumber.getText());
                }

            }
        } catch (Exception ex) {
             ex.printStackTrace();
        }


    }

    private double sumPrice(String previousAmount, String amountDeposited) {
        return Double.parseDouble(previousAmount) + Double.parseDouble(amountDeposited);
    }

}
