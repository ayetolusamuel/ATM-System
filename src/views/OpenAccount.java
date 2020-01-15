package views;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import daoFactory.DaoFactory;
import daoFactory.DatabaseConnection;
import daoFactory.UserController;
import listener.WindowEventHandler;
import model.User;
import utils.Utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author DELL
 */
public class OpenAccount extends JFrame {

    private JPanel jPanel;
    private JButton btnBackToMain, btnSave;
    protected JLabel lblPhoneNumber;
    private JTextField txtFirstName, txtLastName, txtPhoneNumber;
    private JComboBox<String> jcmbAccountType;
    private String[] account = {"Saving", "Current", "Fixed"};
    private JTextField txtAccountNumber;
    private JButton btnClear;
    private JButton btnSearch;
    private JPasswordField txtPin;
    private JButton btnCancel;
    //private WindowEventHandler mHandler = new WindowEventHandler();


    public OpenAccount() throws HeadlessException {
        displayGUI();
        registerListener();
    }





    private void displayGUI() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //event to happen when x button
                System.out.println("close frame!!!");
            }
        });


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
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setSize(550, 430);
        init();

    }

    private void init() {
        JLabel lblWelcome = new JLabel("<html><b>Account Registration Portal");
        lblWelcome.setForeground(Color.MAGENTA);
        lblWelcome.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        jPanel.add(lblWelcome).setBounds(180, 10, 290, 20);

        btnBackToMain = new JButton(new ImageIcon("images//backtomainpage1.jpg"));
        btnBackToMain.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        jPanel.add(btnBackToMain).setBounds(0, 50, 160, 40);


        btnSearch = new JButton(new ImageIcon("images//search.jpg"));
        btnSearch.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        btnSearch.setToolTipText("Search customer with phone Number");
        jPanel.add(btnSearch).setBounds(480, 50, 60, 50);

        JLabel lblPin = new JLabel("<html><b>Enter four digit pin....");
        lblPin.setForeground(Color.red);
        lblPin.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        jPanel.add(lblPin).setBounds(180, 45, 180, 50);

        txtPin = new JPasswordField();
        jPanel.add(txtPin).setBounds(360, 64, 40, 20);
        txtPin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                String pin = new String(txtPin.getPassword());
                if (!(c == KeyEvent.VK_BACK_SPACE)) {

                    if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
                            c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {
                        getToolkit().beep();
                        ke.consume();
                    } else if (pin.length() > 3) {
                        getToolkit().beep();
                        ke.consume();
                    }
                }
            }
        });


        JLabel lblFirstName = new JLabel("<html><b>First Name");
        lblFirstName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblFirstName.setForeground(Color.white);
        jPanel.add(lblFirstName).setBounds(10, 100, 160, 40);


        txtFirstName = new JTextField();
        txtFirstName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        txtFirstName.setForeground(Color.black);
        jPanel.add(txtFirstName).setBounds(160, 105, 300, 25);


        JLabel lblLastName = new JLabel("<html><b>Last Name|Surname");
        lblLastName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblLastName.setForeground(Color.white);
        jPanel.add(lblLastName).setBounds(10, 140, 160, 40);

        txtLastName = new JTextField();
        txtLastName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        txtLastName.setForeground(Color.black);
        jPanel.add(txtLastName).setBounds(160, 145, 300, 25);


        lblPhoneNumber = new JLabel("<html><b>Phone Number");
        lblPhoneNumber.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblPhoneNumber.setForeground(Color.white);
        jPanel.add(lblPhoneNumber).setBounds(10, 180, 160, 40);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        txtPhoneNumber.setForeground(Color.black);
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

        jPanel.add(txtPhoneNumber).setBounds(160, 185, 300, 25);


        JLabel lblAccountNumber = new JLabel("<html><b>Account Number");
        lblAccountNumber.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblAccountNumber.setForeground(Color.white);
        jPanel.add(lblAccountNumber).setBounds(10, 220, 160, 40);

        txtAccountNumber = new JTextField();
        txtAccountNumber.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        txtAccountNumber.setEditable(false);
        txtAccountNumber.setForeground(Color.black);
        jPanel.add(txtAccountNumber).setBounds(160, 225, 300, 25);


        JLabel lblAccountType = new JLabel("<html><b>Account Type");
        lblAccountType.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblAccountType.setForeground(Color.white);
        jPanel.add(lblAccountType).setBounds(10, 260, 160, 40);


        jcmbAccountType = new JComboBox<>(account);
        jcmbAccountType.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        jcmbAccountType.setForeground(Color.MAGENTA);
        jPanel.add(jcmbAccountType).setBounds(160, 265, 160, 25);


        btnSave = new JButton("Save");
        btnSave.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        btnSave.setForeground(Color.MAGENTA);
        jPanel.add(btnSave).setBounds(100, 315, 120, 25);

        btnClear = new JButton("Clear Fields");
        btnClear.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        btnClear.setForeground(Color.MAGENTA);
        jPanel.add(btnClear).setBounds(250, 315, 120, 25);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        btnCancel.setForeground(Color.MAGENTA);
        jPanel.add(btnCancel).setBounds(390, 315, 120, 25);
        getContentPane().add(jPanel, BorderLayout.CENTER);
    }


    private boolean textValidation() {
        boolean flag = true;
        String pin = new String(txtPin.getPassword());
        if (pin.length() <= 0) {
            JOptionPane.showMessageDialog(this, "pin can't be empty!!");
            flag = false;
        } else if (pin.length() != 4) {
            JOptionPane.showMessageDialog(this, "Invalid!, pin must be 4 digits.");
            flag = false;
        } else if (txtFirstName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First name can't be empty!!!");
            flag = false;
        } else if (txtLastName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last name can't be empty!!!");
            flag = false;
        } else if (txtPhoneNumber.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number can't be empty!!!");

            flag = false;
        } else if (txtPhoneNumber.getText().length() != 11) {
            JOptionPane.showMessageDialog(this, "Invalid! your phone number must be 11 digits!!!");

            flag = false;
        }
        return flag;
    }

    private void registerListener() {
        btnSave.addActionListener(e -> {

            String buttonName = e.getActionCommand();

            if (!buttonName.equalsIgnoreCase("save")) {
                btnSave.setEnabled(true);
                System.out.println("update");
                try {
                    String result = UserController.getInstance().updateOpenAccount(txtFirstName.getText(),txtLastName.getText(),
                    Objects.requireNonNull(jcmbAccountType.getSelectedItem()).toString(),txtPhoneNumber.getText());
                    System.out.println(result);
                    clearTextImpl();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                return;
            }
            if (textValidation()) {
                try {


                    String pin = new String(txtPin.getPassword());
                    String firstName = txtFirstName.getText();
                    String lastName = txtLastName.getText();
                    String phoneNumber = txtPhoneNumber.getText();
                    String accountType = Objects.requireNonNull(jcmbAccountType.getSelectedItem()).toString();

                    User user = new User();
                    user.setSecretPin(Integer.parseInt(pin));
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPhoneNumber(phoneNumber);
                    user.setAccountNumber(Utils.getInstance().generateAccountNumber());
                    user.setAccountType(accountType);

                    UserController.getInstance().save(user);
                    if(saveCopyToAmountDepositedTable(user.getPhoneNumber(), String.valueOf(user.getSecretPin()))){
                        JOptionPane.showMessageDialog(this, "Save Successfully!\nYour Account Number: " + user.getAccountNumber());
                        clearTextImpl();
                    }

                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "" + e1.getMessage());
                    e1.printStackTrace();
                }
            }

        });
        btnCancel.addActionListener(e -> {
            super.setVisible(false);
            WindowEventHandler.getInstance().homePageWindowOpen();
        });
        btnClear.addActionListener(e -> {
            clearTextImpl();
        });
        btnBackToMain.addActionListener(e -> {
            super.setVisible(false);
          WindowEventHandler.getInstance().homePageWindowOpen();
        });
        btnSearch.addActionListener(e -> {
            searchUser();
        });


    }

    private boolean saveCopyToAmountDepositedTable(String number, String pin) {
        PreparedStatement ps;
        boolean flag;
        try {

            String pAmount = "0.0", aDeposited = "0.0", balance = "0.0";
            String sql = "INSERT INTO  amountdeposited(pnumber,pin,pamount,adeposited,balance)values(?,?,?,?,?)";

            Connection connection = DatabaseConnection.getDatabase().openConnection();

            ps = connection.prepareStatement(sql);
            ps.setString(1, number);
            ps.setString(2, pin);
            ps.setString(3, pAmount);
            ps.setString(4, aDeposited);
            ps.setString(5, balance);
            ps.executeUpdate();
            flag = true;

        } catch (Exception ex) {

            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }




    private void clearTextImpl() {
        Utils.getInstance().clearTextOpenAccount(txtPin, txtFirstName,
                txtLastName, txtPhoneNumber, txtAccountNumber, jcmbAccountType);
    }

    private void searchUser() {
        String option = JOptionPane.showInputDialog(rootPane,
                "Enter Phone Number", "Search model.User via Phone Number", JOptionPane.DEFAULT_OPTION);
        try {
            if (!option.equals(JOptionPane.OK_OPTION)) {

                if (option.length() != 11) {
                    JOptionPane.showMessageDialog(this, "Invalid! phone number must be 11 digits,");
                    clearTextImpl();
                    return;
                }
                User user = UserController.getInstance().findByPhoneNumber(option);
                if (user != null) {
                    txtPhoneNumber.setText(user.getPhoneNumber());
                    txtFirstName.setText(user.getFirstName());
                    txtLastName.setText(user.getLastName());
                    txtAccountNumber.setText(user.getAccountNumber());
                    jcmbAccountType.setSelectedItem(user.getAccountType());
                    txtPhoneNumber.setEditable(false);
                    btnSave.setText("Update");
                    btnSave.setEnabled(true);
                    txtPin.setEditable(false);

                } else {
                    JOptionPane.showMessageDialog(this, "No data for this user " + option);
                    clearTextImpl();
                }

            } else {
                clearTextImpl();
                dispose();
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }}