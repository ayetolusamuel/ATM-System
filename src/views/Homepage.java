package views;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daoFactory.DaoFactory;
import daoFactory.DatabaseConnection;
import daoFactory.UserController;
import listener.WindowEventHandler;
import model.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author DELL
 */
public class Homepage extends JFrame {

    private JPanel jPanel;
    private JButton btnOpenAccount;
    private JButton btnDepositCash;
    private JButton btnTransferFunds;
    private JButton btnWithdrawCash;
    private JButton btnCheckBalance;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    private JTextField txtPhoneNumber;
    private JPasswordField txtpin;
    private JTextField txtWithdraw;
    private JButton btnLogout;
    Connection mConnection;

    public Homepage() {
        getGUI();
       // mConnection = DatabaseConnection.getDatabase().openConnection();
        registerListener();
    }

    private void registerListener() {

        btnOpenAccount.addActionListener(e -> {
            super.setVisible(false);
            OpenAccount openAccount = new OpenAccount();
            openAccount.setLocationRelativeTo(null);
            openAccount.setResizable(false);
            openAccount.setVisible(true);

        });
        btnLogout.addActionListener(e -> {
            setVisible(false);
            LoginFirst loginFirst = new LoginFirst();
            loginFirst.setVisible(true);
            loginFirst.setLocationRelativeTo(null);
            loginFirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });
        btnCheckBalance.addActionListener(e -> {
            balancepopUp();
        });
        btnDepositCash.addActionListener(e -> {
            setVisible(false);
            DepositCash depositCash = new DepositCash();
            depositCash.setLocationRelativeTo(null);
            depositCash.setVisible(true);
        });
    }

    private void getGUI() {

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                //	logout();


            }
        });

        Image img = Toolkit.getDefaultToolkit().getImage("images//codelagos.jpg");
        setIconImage(img);
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
        setTitle("CodeLagos Bank System Version 1.0");
        jPanel.setLayout(null);
        setSize(560, 430);
        setLocationRelativeTo(null);
        setVisible(true);


        //System.out.print("1.\tOPEN ACCOUNT\n2.\tDEPOSIT CASH\n3.\tWITHDRAW CASH\n4.\tTRANSFER FUNDS\n5.\tCHECK BALANCE\n6.\tEXIT APPLICATION\n\n");
        JLabel lblWelcome = new JLabel("WELCOME TO CODE LAGOS BANK PLC");
        lblWelcome.setForeground(Color.white);
        lblWelcome.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        jPanel.add(lblWelcome).setBounds(120, 10, 290, 20);


        JLabel lblMenuList = new JLabel("Menu List");
        lblMenuList.setForeground(Color.PINK);
        lblMenuList.setFont(new Font("Times New Roman", Font.ITALIC, 40));
        jPanel.add(lblMenuList).setBounds(180, 60, 290, 30);


        btnLogout = new JButton(new ImageIcon("images/logout.png"));
        btnLogout.setToolTipText("Click to Logout!!!");
        btnLogout.setForeground(Color.red);
        jPanel.add(btnLogout).setBounds(480, 10, 50, 50);
        //   btnLogout.addActionListener(this);


        btnOpenAccount = new JButton("Open Account");
        btnOpenAccount.setToolTipText("Click to open account with us!!!");
        btnOpenAccount.setForeground(Color.red);
        jPanel.add(btnOpenAccount).setBounds(10, 100, 130, 100);
        //  btnOpenAccount.addActionListener(this);

        btnDepositCash = new JButton("Deposit Cash");
        btnDepositCash.setToolTipText("deposit cash to your account!!!");
        btnDepositCash.setForeground(Color.red);
        jPanel.add(btnDepositCash).setBounds(400, 100, 130, 100);
        //btnDepositCash.addActionListener(this);

        btnWithdrawCash = new JButton("Withdraw Cash");
        btnWithdrawCash.setToolTipText("Feel free to withdraw from your account!!!!!!!!!");
        btnWithdrawCash.setForeground(Color.red);
        jPanel.add(btnWithdrawCash).setBounds(10, 220, 130, 100);
        // btnWithdrawCash.addActionListener(this);

        btnTransferFunds = new JButton("Transfer Funds");
        btnTransferFunds.setToolTipText("Transfer Funds to your Love ones!!!!!!!!!");
        btnTransferFunds.setForeground(Color.red);
        jPanel.add(btnTransferFunds).setBounds(400, 220, 130, 100);
        //  btnTransferFunds.addActionListener(this);
//
        btnCheckBalance = new JButton("check Balance");
        btnCheckBalance.setToolTipText("Feel free to check your Balance!!!!!!!!!!!");
        btnCheckBalance.setForeground(Color.red);
        jPanel.add(btnCheckBalance).setBounds(10, 340, 510, 50);
        // btnCheckBalance.addActionListener(this);
//
//
//


        getContentPane().add(jPanel, BorderLayout.CENTER);


    }


    //
//    /////////////////////////////////////////////////////////
    private void balancepopUp() {
        txtPhoneNumber = new JTextField();
        txtpin = new JPasswordField();
        txtPhoneNumber.setToolTipText("Enter your phone Here!!!!!!!!!!!!");
        txtPhoneNumber.addKeyListener(new KeyAdapter() {
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


        txtpin.addKeyListener(new KeyAdapter() {
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


        txtpin.setToolTipText("Enter your four digits pin number!!!!!!!!!!!");
        Object[] message = {"Phone Number", txtPhoneNumber, "Pin Number", txtpin};


        Icon error = new ImageIcon("images//info.jpg");
        //JOptionPane.showMessageDialog(this,"<html><font size=4 color=red>Invalid Password \n\t\t Please enter valid password","Login",JOptionPane.ERROR_MESSAGE,error);


        int option = JOptionPane.showConfirmDialog(null, message, "Customer Information", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, error);

        if (option == JOptionPane.OK_OPTION) {
            String phoneNumber = txtPhoneNumber.getText();
            char[] pinChar = txtpin.getPassword();
            String pin = new String(pinChar);

            // int pin = Integer.parseInt(txtpin.getText());
            try {
                if (phoneNumber.length() == 0) {
                    JOptionPane.showMessageDialog(this, "phone number can't be empty!");
                    return;
                } else if (pin.length() == 0) {
                    JOptionPane.showMessageDialog(this, "pin can't be empty!");
                    return;
                } else if (pin.length() != 4) {
                    JOptionPane.showMessageDialog(this, "Invalid!, phone number must be 4digits");
                    return;
                } else if (phoneNumber.length() != 11) {
                    JOptionPane.showMessageDialog(this, "Invalid!, phone number must be 11 digits");
                    return;
                }

                String result = UserController.getInstance().checkBalance(phoneNumber, Integer.parseInt(pin));
                if (!result.equals("")){
                    JOptionPane.showMessageDialog(this,"Your balance is: "+result);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //
//     private void withdrawpopUp(){
//        txtPhoneNumber = new JTextField();
//        txtpin = new JPasswordField();
//        txtPhoneNumber.setToolTipText("Enter your phone Here!!!!!!!!!!!!");
//          txtPhoneNumber.addKeyListener(new KeyAdapter () {
//                                @Override
//				public void keyTyped (KeyEvent ke) {
//					char c = ke.getKeyChar ();
//					if (! ( (c == KeyEvent.VK_BACK_SPACE))) {
//						if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
//					            c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {
//								getToolkit().beep ();
//								ke.consume ();}}}});
//
//       txtWithdraw = new JTextField();
//        txtWithdraw.addKeyListener(new KeyAdapter () {
//                                @Override
//				public void keyTyped (KeyEvent ke) {
//					char c = ke.getKeyChar ();
//					if (! ( (c == KeyEvent.VK_BACK_SPACE))) {
//						if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
//					            c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {
//								getToolkit().beep ();
//								ke.consume ();}}}});
//        Object[] message = {"Phone Number", txtPhoneNumber, "Amount to be withdraw",txtWithdraw};
//
//
//   Icon error=new ImageIcon("images//info.jpg");
//    //JOptionPane.showMessageDialog(this,"<html><font size=4 color=red>Invalid Password \n\t\t Please enter valid password","Login",JOptionPane.ERROR_MESSAGE,error);
//
//
//        int option = JOptionPane.showConfirmDialog(null, message, "Customer Information",JOptionPane.PLAIN_MESSAGE,JOptionPane.INFORMATION_MESSAGE,error);
//
//        if (option == JOptionPane.OK_OPTION) {
//            String phoneNumber = txtPhoneNumber.getText();
//
//            // int pin = Integer.parseInt(txtpin.getText());
//           try{
//
//
//
//
//             if ((phoneNumber.length() != 0)) {
//                    returnBalance();
////System.out.println("Setonji");
//
//
//             }else{
//                    System.out.println("Exit");
//                }
//
//    }
//            catch(Exception ex){
//
//            }
//        }}
//
//
//    private void updateAccount(String number,String preAmount,String withdraw,String balance){
//         try{
//             PreparedStatement ps;
//
//
//             //databaseConnection.open();
//
//                //  ps = databaseConnection.getConnection().prepareStatement("UPDATE amountdeposited SET pamount = ?, adeposited = ?, balance = ? WHERE pnumber = ?");
//
////             ps.setString(1, String.valueOf(preAmount));
////             ps.setString(2, String.valueOf(withdraw));
////             ps.setString(3, String.valueOf(balance));
////             ps.setString(4, number);
//
//
//
//
//
//	//ps.executeUpdate();
//
//	//JOptionPane.showMessageDialog(null, "Account Updated Successfully!!!!");
//
//         }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void returnBalance(){
//        String number = txtPhoneNumber.getText();
//       double balance =Double.parseDouble(returnBalanceBasePhoneNumber(number));
//       double amountToWithdraw = Double.parseDouble(txtWithdraw.getText());
//
//        double result = withdrawComfirm(balance, amountToWithdraw);
//        if (result == 0) {
//            Icon error=new ImageIcon("images//error.jpg");
//            JOptionPane.showMessageDialog(null, "<html><i>Insufficient balance\n<html><i><b>Your Balance is "+balance,"ERROR",JOptionPane.PLAIN_MESSAGE,error);
//          // System.out.println("Insufficient balance\nYour Balance "+balance);
//        }
//        else{
//            //System.out.println("Before withdraw "+balance);
//            //System.out.println("After withdraw  balance is "+result);
//           // System.out.println((int)amountToWithdraw + " withdraw successfully from your account");
//            //update account
//           // updateAccount(number,String.valueOf(balance),String.valueOf(amountToWithdraw), String.valueOf(result));
//           double pre = updatePreviousAmount(result);
//          //  System.out.println("After Transaction");
//            updateAccount(number,String.valueOf(pre),String.valueOf(amountToWithdraw), String.valueOf(result));
//
//          // System.out.println("Previous "+pre);
//
//           // System.out.println("Amount withdraw is "+txtWithdraw.getText());
//            //show balance
//            JOptionPane.showMessageDialog(null, (int)amountToWithdraw + " withdraw successfully from your account!!!!! "
//                    + "\nYour New Balance is "+result);
//           // System.out.println("After withdraw "+result);
//
//
//
//
//
//        }
//    }
//
//
//      private double updatePreviousAmount(double result) {
//          double preAmount = 0;
//          double deposited = Double.parseDouble(txtWithdraw.getText());
//
//
//
//          if (result !=0 ) {
//              preAmount = result - deposited;
//             }
//          return preAmount;
//      }
//
//
//
//
//    private double withdrawComfirm(double balance, double withdraw){
//        double result = 0;
//
//        if (withdraw>balance) {
//            //System.out.println("Insufficient balance");
//            //returnBalance();
//        }
//        else{
//           result =  balance-withdraw;
//        }
//        return result;
//    }
//
//      private String returnBalanceBasePhoneNumber(String number) {
//         String customerBalance = null;
//        try{
////            String query = "SELECT * FROM amountdeposited where  pnumber like '"+number+"'";
////                    ResultSet resultSet;
////                  //  databaseConnection.open();
////
////                Statement statement = databaseConnection.getStatement();
////                resultSet = statement.executeQuery(query);
////
////                if (!resultSet.next()) {
////                    JOptionPane.showMessageDialog(null, "No Record found this user!!!");
////
////                } else {
////                    customerBalance = resultSet.getString("balance").trim();
////
////                     }
////                }catch(HeadlessException | SQLException ex){
////
////                }
//        return null;
//    }
//
    private String returnDataBasePhoneNumberPin(String number, int pin) {
        String customerBalance = null;
        try {


            //String query = "SELECT * FROM amountdeposited where pnumber like '" + number + "'AND pin  '"+pin+"'";
            String query = "SELECT * FROM amountdeposited where pin like '" + pin + "' AND pnumber like '" + number + "'";
            Connection connection = DaoFactory.getDatabase().openConnection();

            PreparedStatement pstmt = connection.prepareStatement(query);
            User user = null;
            ResultSet resultSet = pstmt.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "No Record found this user!!!");
                //clearTextOpenAccount();

            } else {
                String preAmount;
                preAmount = resultSet.getString("pamount").trim();
                String amtDeposited = resultSet.getString("adeposited").trim();
                customerBalance = resultSet.getString("balance").trim();

                JOptionPane.showMessageDialog(null, "<html><i>Welcome " + number + "\n<html><i>Your Balance is \"" + customerBalance + "\"");

            }

            pstmt.close();
            resultSet.close();
        } catch (Exception ex) {

        }

        return null;
    }

//
//
////
////    private void logout() {
////                setVisible(false);
////                LoginFirst loginFirst =new LoginFirst();
////		loginFirst.setVisible(true);
////		loginFirst.setDefaultCloseOperation(3);
////
////    }
//
//
//    ////////////////////////////////
//
//
//
//
//
//
//
////
////    @Override
////    public void actionPerformed(ActionEvent event) {
////        Object source = event.getSource();
////        if (source.equals(btnOpenAccount)) {
////            setVisible(false);
//////            OpenAccount openAccount = new OpenAccount();
//////            openAccount.setLocation(300, 100);
//////             openAccount.setResizable(false);
//////              openAccount.  setSize(550, 430);
//////              openAccount.setVisible(true);
////
////        }
//////        if (source.equals(btnCheckBalance)) {
    //balancepopUp();
//////
//////
//////        }
////        if (source.equals(btnDepositCash)) {
////            setVisible(false);
////        views.DepositCash cash = new views.DepositCash();
////        cash.setVisible(true);
////        cash.setSize(500,350);
////        cash.setLocation(300,100);
////        }
//////        if (source.equals(btnTransferFunds)) {
//////            setVisible(false);
//////             TransferFunds  transferFunds = new TransferFunds();
//////        transferFunds.setVisible(true);
//////        transferFunds.setSize(530,330);
//////        transferFunds.setLocation(300, 100);
//////        }
//////        if (source.equals(btnWithdrawCash)) {
//////        withdrawpopUp();
//////        }
//////        if (source.equals(btnLogout)) {
//////            logout();
//////        }
//////        }
//////
//////
//////
////    }
//

}
