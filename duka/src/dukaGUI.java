import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel lblAttendantIDLA;
    private JLabel lblPasswordLA;
    private JTextField txtBuyerIDLB;
    private JPasswordField pwdPasswordLB;

    private JTextField txtItemNameA;
    private JTextField txtItemIDA;
    private JTextField txtItemPriceA;
    private JTextField txtItemDescriptionA;
    private JButton btnCancelA;
    private JButton btnUploadA;
    private JButton btnSearchB;
    private JLabel lblItemNameA;
    private JLabel lblItemPriceA;
    private JLabel lblItemDescriptionA;
    private JLabel lblSearchB;
    private JComboBox comboBox1;
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
    private JLabel lblCPasswordR;
    private JTable table1;
    private JButton btnPlaceOrderB;
    private JButton btnCancelW;

    //Linking to my database file that contains the database functions
    database db = new database();

    public dukaGUI() {
        this.setTitle("DUKA");
        this.setSize(400, 400);
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
        btnSearchB.addActionListener((this));
        btnRegisterR.addActionListener(this);
        btnGoBackLA.addActionListener(this);
        btnGoBackR.addActionListener(this);
        btnPlaceOrderB.addActionListener(this);
        btnRegisterLA.addActionListener(this);
        btnCancelW.addActionListener(this);

    }

    @Override
    //Listening for actions performed on buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAttendantW) {
            //moving to the login form for the attendant
            welcome.setVisible(false);
            loginA.setVisible(true);
        }
        if (e.getSource() == btnBuyerW) {
            //Moving to the buyer panel
            welcome.setVisible(false);
            buyer.setVisible(true);
        }
        if(e.getSource() == btnCancelW){
            //exiting the program from the welcome page
        }
        if (e.getSource() == btnLoginLA) {
            //Login to attendant form
            try {
                String userName = txtUsernameLA.getText();
                String passwordA = pwdPasswordLA.getText();
                if (db.login(userName,passwordA)) {
                    loginA.setVisible(false);
                    attendant.setVisible(true);
                }
            } catch (Exception em) {
                JOptionPane.showMessageDialog(null, "Please enter all the fields");
                String userName = txtUsernameLA.getText();
                String passwordA = pwdPasswordLA.getText();
                if (db.login(userName,passwordA)) {
                    loginA.setVisible(false);
                    attendant.setVisible(true);
                }
            }
        }
        if (e.getSource() == btnUploadA) {
            //Uploading new items in the database
            try {
                String ItemName = txtItemNameA.getText();
                int ItemPrice = Integer.parseInt(txtItemPriceA.getText());
                String ItemDescription = txtItemDescriptionA.getText();
                JOptionPane.showMessageDialog(this, ItemName + " has been added successfully!");
                //if(!(ItemName.isEmpty() || ItemID==0 || ItemPrice==0 || ItemDescription.isEmpty())) {
                    //JOptionPane.showMessageDialog(null, ItemName + " has been added successfully!");
                //}
            } catch (Exception el){
                JOptionPane.showMessageDialog(null,"Please Try again");
                String ItemName = txtItemNameA.getText();
                int ItemID = Integer.parseInt(txtItemIDA.getText());
                int ItemPrice = Integer.parseInt(txtItemPriceA.getText());
                String ItemDescription = txtItemDescriptionA.getText();
            }

        }
        if(e.getSource() == btnCancelA){
            //exiting the program from the attendant form
        }

        if(e.getSource() == btnRegisterLA){
            //moving from login to register
            loginA.setVisible(false);
            register.setVisible(true);
        }
        if(e.getSource() == btnRegisterR){
            //Registering and going back to login using the registered details
            String name=txtNameR.getName();
            String username=txtUsernameR.getName();
            String password=psdPasswordR.getText();
            String cpassword=psdCPasswordR.getText();
            if(password==cpassword){
                if(db.register(name,username,password)){
                    JOptionPane.showMessageDialog(null,"Welcome "+username+". Use "+password+" to login now");
                    register.setVisible(false);
                    loginA.setVisible(true);
                }
            }
            register.setVisible(false);
            loginA.setVisible(true);
        }
        if(e.getSource() == btnGoBackR){
            //takes you back from the register page to the login page
            register.setVisible(false);
            loginA.setVisible(true);
        }
        // search place order
        if(e.getSource()==btnSearchB){
            //Searching for items in the database

        }
        if(e.getSource()==btnPlaceOrderB){
            //Placing orders from the items displayed

        }


        }
}
