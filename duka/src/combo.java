/*public class combo {
    //Read/View a specific entry in tbl_stock by using the item_id
    public void displayStockRecord(JComboBox comboBox, JTextField txtName, JTextField txtQuantity, JTextField txtPrice, String sql){
        try{
            String itemID = comboBox.getSelectedItem().toString();

            pst = connection.prepareStatement(sql);
            pst.setString(1, itemID);
            resultSet = pst.executeQuery();

            if(resultSet.next()){
                txtName.setText(resultSet.getString(2));
                txtQuantity.setText(resultSet.getString(3));
                txtPrice.setText(resultSet.getString(4));
            } else{
                JOptionPane.showMessageDialog(null, "No record found", "Record Missing", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e){
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //View the item_id or name of a certain item by using a dropdown list
    public void viewColumn(JComboBox comboBox, String sql) throws SQLException {
        try{
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            comboBox.removeAllItems();
            while(resultSet.next())
            {
                comboBox.addItem(resultSet.getString(1));
            }
        } catch (SQLException e){
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    //Update data from the table
    public void updateRecord(String itemName, String itemQuantity, String itemPrice, JComboBox comboBox) throws SQLException {
        try{
            String itemID = comboBox.getSelectedItem().toString();
            String sql = "UPDATE tbl_stock SET Item_Name=?, Item_Quantity=?, Item_Price=? WHERE  Item_ID=?";
            pst = connection.prepareStatement(sql);

            pst.setString(1, itemName);
            pst.setString(2, itemQuantity);
            pst.setString(3, itemPrice);
            pst.setString(4, itemID);
            int k = pst.executeUpdate();
            if(k==1){
                JOptionPane.showMessageDialog(null, "Data has been changed successfully.", "Modification Successful", JOptionPane.INFORMATION_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null, "Error: Record has not been changed.");
            }

        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: Record has not been changed. Please try again." , "Insert Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/

