/*
 *Lance Munyao
 * 150772
 * ICS 1.2D
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dukaGUI extends JFrame implements ActionListener {
    private JPanel panel1;
    private JPanel welcome;
    private JPanel loginA;
    private JPanel register;
    private JPanel attendant;
    private JPanel buyer;
    private JButton btnAttendantW;
    private JButton btnBuyerW;
    private JTextField txtUsernameLA;
    private JPasswordField pwdPasswordLA;
    private JButton btnGoBackLA;
    private JButton btnLoginLA;
    private JLabel lblUsernameLA;
    private JLabel lblPasswordLA;
    private JTextField txtBuyerIDLB;
    private JPasswordField pwdPasswordLB;

    private JTextField txtItemNameA;
    private JTextField txtItemIDA;
    private JTextField txtItemPriceA;
    private JTextField txtItemDescriptionA;
    private JButton btnCancelA;
    private JButton btnUploadA;
    private JButton btnOrderB;
    private JLabel lblItemNameA;
    private JLabel lblItemPriceA;
    private JLabel lblItemDescriptionA;
    private JLabel lblSearchB;
    private JComboBox cmbSearchB;
    private JButton btnRegisterLA;
    private JTextField txtNameR;
    private JTextField txtUsernameR;
    private JPasswordField psdPasswordR;
    private JPasswordField psdCPasswordR;
    private JButton btnRegisterR;
    private JButton btnGoBackR;
    private JLabel lblNameR;
    private JLabel lblUsernameR;
    private JLabel lblPasswordR;
    private JButton btnPlaceOrderB;
    private JButton btnCancelW;
    private JButton btnResetLA;
    private JButton btnResetA;
    private JButton btnResetR;
    private JTable table1;
    private JButton btnDeleteA;

    //Linking to my database file that contains the database functions
    database db = new database();

    public dukaGUI() {
        this.setTitle("DUKA");
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add the container panel
        this.add(panel1);

        //Add action listeners to buttons
        btnAttendantW.addActionListener(this);
        btnBuyerW.addActionListener((this));
        btnLoginLA.addActionListener((this));
        btnGoBackLA.addActionListener((this));
        btnUploadA.addActionListener((this));
        btnCancelA.addActionListener((this));
        btnOrderB.addActionListener((this));
        btnRegisterR.addActionListener(this);
        btnGoBackLA.addActionListener(this);
        btnGoBackR.addActionListener(this);
        btnRegisterLA.addActionListener(this);
        btnCancelW.addActionListener(this);
        btnResetA.addActionListener(this);
        btnResetLA.addActionListener(this);
        btnResetR.addActionListener(this);
        cmbSearchB.addActionListener(this);
        btnDeleteA.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbSearchB) {
            //We can use this to search for items in the database after the combobox item is selected
            String item = (String) cmbSearchB.getSelectedItem();

        }
        if (e.getSource() == btnAttendantW) {
            //moving to the login form for the attendant
            welcome.setVisible(false);
            loginA.setVisible(true);
        }
        if (e.getSource() == btnBuyerW) {
            //Moving to the buyer panel
            welcome.setVisible(false);
            buyer.setVisible(true);
            //Preload the item_id dropdown list
            try {
                db.viewColumn(cmbSearchB);
                db.table_update(table1);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }

        }
        if (e.getSource() == btnCancelW) {
            //exiting the program from the welcome page
            dispose();
        }
        if (e.getSource() == btnLoginLA) {
            //Login to attendant form
            try {
                String userName = txtUsernameLA.getText();
                String passwordA = pwdPasswordLA.getText();
                if (!(userName.isEmpty() || passwordA.isEmpty())) {
                    if (db.login(userName, passwordA)) {
                        loginA.setVisible(false);
                        attendant.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
                }
            } catch (Exception em) {
                JOptionPane.showMessageDialog(null, "Please enter all the fields");
                String userName = txtUsernameLA.getText();
                String passwordA = pwdPasswordLA.getText();
                if (db.login(userName, passwordA)) {
                    loginA.setVisible(false);
                    attendant.setVisible(true);
                }
            }
        }
        if (e.getSource() == btnGoBackLA) {
            //Going back from Login to the welcome page
            loginA.setVisible(false);
            welcome.setVisible(true);
        }
        if (e.getSource() == btnResetLA) {
            txtUsernameLA.setText("");
            pwdPasswordLA.setText("");
        }
        if (e.getSource() == btnUploadA) {
            //Uploading new items in the database
            try {
                String ItemName = txtItemNameA.getText();
                int ItemPrice = Integer.parseInt(txtItemPriceA.getText());
                String ItemDescription = txtItemDescriptionA.getText();
                db.upload(ItemName, ItemPrice, ItemDescription);
            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "Please Try again");
                String ItemName = txtItemNameA.getText();
                int ItemPrice = Integer.parseInt(txtItemPriceA.getText());
                String ItemDescription = txtItemDescriptionA.getText();
                db.upload(ItemName, ItemPrice, ItemDescription);
            }

        }
        if (e.getSource() == btnResetA) {
            txtItemNameA.setText("");
            txtItemPriceA.setText("");
            txtItemDescriptionA.setText("");

        }
        if (e.getSource() == btnCancelA) {
            //exiting the program from the attendant form
            dispose();
        }

        if (e.getSource() == btnRegisterLA) {
            //moving from login to register
            loginA.setVisible(false);
            register.setVisible(true);
        }
        if (e.getSource() == btnRegisterR) {
            //Registering and going back to login using the registered details
            String name = txtNameR.getText();
            String username = txtUsernameR.getText();
            String password = psdPasswordR.getText();

            if (db.register(name, username, password)) {
                register.setVisible(false);
                loginA.setVisible(true);
            } else {
                register.setVisible(false);
                loginA.setVisible(true);
            }
        }
        if (e.getSource() == btnResetR) {
            txtNameR.setText("");
            txtUsernameR.setText("");
            psdPasswordR.setText("");
            psdCPasswordR.setText("");
        }
        if (e.getSource() == btnGoBackR) {
            //takes you back from the register page to the login page
            register.setVisible(false);
            loginA.setVisible(true);
        }
        // search place order
        /*if(e.getSource()==btnOrderB){
            //Searching for items in the database
            db.viewStockTable(table1);

        }*/
        if (e.getSource() == btnOrderB) {
            //Placing orders from the items displayed
            //getting the value from the combobox
            cmbSearchB.getSelectedItem();
            int choice = JOptionPane.showOptionDialog(this,
                    "Do you want to save changes?",
                    "Save changes?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Save", "Quit", "Don't Save"},
                    "Save");

            // Handle user choice
            if (choice == JOptionPane.YES_OPTION) {
                // Save changes
                JOptionPane.showMessageDialog(null,"Order for "+cmbSearchB.getSelectedItem()+" has been placed successfully");
            } else if (choice == JOptionPane.NO_OPTION) {
                // Discard changes
                dispose();
            } else {
                // Cancel
            }
        }
        if (e.getSource() == btnDeleteA) {
            try {
                db.deleteRecord(txtItemNameA);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
