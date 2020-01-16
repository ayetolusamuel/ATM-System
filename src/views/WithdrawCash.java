package views;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daoFactory.DatabaseConnection;
import daoFactory.UserController;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author DELL
 */
public class WithdrawCash extends JFrame {
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

    public WithdrawCash() throws HeadlessException {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                setVisible(false);
//                Homepage homepage = new Homepage();
//                homepage.setSize(560, 430);
//                homepage.setResizable(false);
//                homepage.setVisible(true);
            }
        });
        displayGUI();
        registerListener();


    }

    private void registerListener() {
        btnCheck.addActionListener(e -> {
            String result = fetchDataBaseOnPhoneNumber(txtPhoneNumber.getText());
            if (!result.equals("")) {
                JOptionPane.showMessageDialog(this, "welcome!," + result);
            }

        });
        btnSave.addActionListener(e -> {
            deposit();
        });
        btnClear.addActionListener(e -> {
            clearTextImpl();
        });

        btnCancel.addActionListener(e -> {
            super.setVisible(false);
            Homepage homepage = new Homepage();
            homepage.setSize(560, 430);
            homepage.setResizable(false);
            homepage.setVisible(true);
        });

    }

    private void clearTextImpl() {
        Utils.getInstance().clearTextDeposit(txtPhoneNumber, txtFullName,
                txtPreviousAmount, txtAmountDeposited, txtBalance);
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

        jPanel.add(txtPhoneNumber).setBounds(150, 51, 90, 20);

        // btnCheck = new JButton(new ImageIcon("images//sam.gif"));
        btnCheck = new JButton("Search");
        jPanel.add(btnCheck).setBounds(410, 50, 50, 50);


        JLabel lblPin = new JLabel("Pin ");
        jPanel.add(lblPin).setBounds(260, 50, 100, 20);
        lblPin.setForeground(Color.white);
        lblPin.setFont(new Font("Times New Roman", Font.ITALIC, 15));

        JTextField txtPin = new JTextField();
        txtPin.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        jPanel.add(txtPin).setBounds(290, 50, 50, 20);



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

    private String fetchDataBaseOnPhoneNumber(String number) {
        String fullNameResult = null;
        try {
            if (number.length() == 0) {
                JOptionPane.showMessageDialog(this, "phone number can't be empty!!!!");
                return fullNameResult;

            } else if (number.length() != 11) {
                JOptionPane.showMessageDialog(this, "Invalid phone number number!!!!");
                return fullNameResult;
            } else {

                String query = "SELECT * FROM accountopening where pnumber like '" + number + "'";
                Connection connection = DatabaseConnection.getDatabase().openConnection();
                PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "No Record found this user!!!: " + number);
                    clearTextImpl();
                } else {
                    pNumber = resultSet.getString("pnumber").trim();
                    pin = resultSet.getString("pin").trim();
                    String fname = resultSet.getString("fname").trim();
                    String lname = resultSet.getString("lname").trim();
                    fullNameResult = fname + " " + lname;
                    txtFullName.setText(fullNameResult);
                    txtAmountDeposited.setEditable(true);
                    txtPreviousAmount.setText(UserController.getInstance().getBalance(txtPhoneNumber.getText()));
                    btnSave.setEnabled(true);
                    btnClear.setEnabled(true);
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


    private void deposit() {
        try {

            if (txtAmountDeposited.getText().matches("")) {
                Icon error = new ImageIcon("images//error.jpg");
                JOptionPane.showMessageDialog(null, "<html><i>Amount Deposited Can't Be Empty!!!!.", "ERROR", JOptionPane.PLAIN_MESSAGE, error);


            } else {
                String phoneNumber = txtPhoneNumber.getText();
                double previousAmount = Double.parseDouble(UserController.getInstance().getBalance(phoneNumber));
                double amountDeposited = Double.parseDouble(txtAmountDeposited.getText());
                double balance = Utils.getInstance().
                        balanceAmountDeposited(previousAmount, amountDeposited);
                String result = UserController.getInstance()
                        .updateAmountDeposited(previousAmount, amountDeposited, balance, phoneNumber);
                if (!result.equals("")) {
                    JOptionPane.showMessageDialog(this, result);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WithdrawCash withdrawCash = new WithdrawCash();
        withdrawCash.setVisible(true);
        withdrawCash.setLocationRelativeTo(null);

    }

}
