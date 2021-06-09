/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import perpustakaan.helper.ConnectionHelper;
import perpustakaan.model.Buku;

/**
 *
 * @author CodeVector
 */
public class AddBook extends javax.swing.JFrame {

    String penerbit = "";
    String formTitle = "";
    int bukuId = 0;
    Buku bukuGlobal;

    /**
     * Creates new form AddBook
     */
    public AddBook() {
        initComponents();

        validateExit();
    }

    public AddBook(String title, int id) {
        initComponents();
        addBookLable.setText(title);
        formTitle = title;
        bukuId = id;

        //Set Form Element Value
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            System.out.println(id);
            ResultSet rs = stm.executeQuery("SELECT * FROM buku WHERE id = " + id);
            while (rs.next()) {
                bookTextField.setText(rs.getString("judul_buku"));
                authorTextField.setText(rs.getString("pengarang"));
                switch (rs.getString("penerbit")) {
                    case "Jember Pustaka" ->
                        jbrPustakaRadBtn.setSelected(true);
                    case "Gramedia" ->
                        gramediaRadBtn.setSelected(true);
                    case "Polije Press" ->
                        polijePressRadBtn.setSelected(true);
                    case "RRI Library" ->
                        rriLibraryBtn.setSelected(true);
                    default ->
                        System.out.println("HMMMMM");
                }
//                thnTerbitComboBox.addItem(rs.getString("tahun_terbit"));
                thnTerbitComboBox.setSelectedItem(rs.getString("tahun_terbit"));
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        validateExit();
    }

    private void validateExit() {

        AddBook.this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AddBook.this.setVisible(false);

            }

        });
    }

    public void showMessage(String message, int type) {
        if (type == 1) {
            JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void radioPenerbit() {
        if (jbrPustakaRadBtn.isSelected()) {
            penerbit = "Jember Pustaka";
        } else if (gramediaRadBtn.isSelected()) {
            penerbit = "Gramedia";
        } else if (polijePressRadBtn.isSelected()) {
            penerbit = "Polije Press";
        } else if (rriLibraryBtn.isSelected()) {
            penerbit = "RRI Library";
        }
    }

    void updateBuku(int id) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            radioPenerbit();
            System.out.println(authorTextField.getText());
            String query = "UPDATE buku SET judul_buku = '" + bookTextField.getText() + "',"
                    + "tahun_terbit = '" + thnTerbitComboBox.getSelectedItem() + "',"
                    + "pengarang = '" + authorTextField.getText() + "',"
                    + "penerbit = '" + penerbit + "' WHERE id = " + id + " ";

            stm.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Data sudah di update ", ""
                    + "infomasi", JOptionPane.INFORMATION_MESSAGE);
//                      AddBook.this.setVisible(false);
            AddBook.this.dispose();
            new ShowBook().setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak terupdate"
                    + e.getMessage(), "infomasi",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

        void tambahBuku() {
        try {

            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            radioPenerbit();
            String query = "INSERT INTO `buku` (`judul_buku`, `tahun_terbit`, "
                    + "`pengarang`, `penerbit`) "
                    + "VALUES ('" + bookTextField.getText() + "', "
                    + "'" + thnTerbitComboBox.getSelectedItem() + "', '" + authorTextField.getText()
                    + "', '" + penerbit + "');";

            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data sudah di tertambah ", 
                    "infomasi", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak tertambah", 
                    "infomasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
    /**
A     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        addBookLable = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bookTextField = new javax.swing.JTextField();
        authorTextField = new javax.swing.JTextField();
        jbrPustakaRadBtn = new javax.swing.JRadioButton();
        gramediaRadBtn = new javax.swing.JRadioButton();
        polijePressRadBtn = new javax.swing.JRadioButton();
        rriLibraryBtn = new javax.swing.JRadioButton();
        thnTerbitComboBox = new javax.swing.JComboBox<>();
        submitBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        addBookLable.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        addBookLable.setText("Tambah Buku");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Judul");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Pengarang");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Penerbit");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tahun Terbit");

        bookTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bookTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookTextFieldActionPerformed(evt);
            }
        });

        authorTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        buttonGroup1.add(jbrPustakaRadBtn);
        jbrPustakaRadBtn.setText("Jember Pustaka");

        buttonGroup1.add(gramediaRadBtn);
        gramediaRadBtn.setText("Gramedia");

        buttonGroup1.add(polijePressRadBtn);
        polijePressRadBtn.setText("Poije Press");

        buttonGroup1.add(rriLibraryBtn);
        rriLibraryBtn.setText("RRI Library");

        thnTerbitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2020", "2019", "2018", "2017", "2016" }));

        submitBtn.setText("Simpan");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Batal");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(59, 59, 59)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thnTerbitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbrPustakaRadBtn)
                                    .addComponent(gramediaRadBtn)
                                    .addComponent(submitBtn))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelBtn)
                                    .addComponent(rriLibraryBtn)
                                    .addComponent(polijePressRadBtn))))))
                .addGap(103, 103, 103))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBookLable)
                .addGap(144, 144, 144))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addBookLable)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(bookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbrPustakaRadBtn)
                            .addComponent(polijePressRadBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gramediaRadBtn)
                            .addComponent(rriLibraryBtn))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(thnTerbitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (bukuId != 0) {
            updateBuku(bukuId);
        } else {
            tambahBuku();
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void bookTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookTextFieldActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        new ShowBook().setVisible(true);

    }//GEN-LAST:event_cancelBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBookLable;
    private javax.swing.JTextField authorTextField;
    private javax.swing.JTextField bookTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JRadioButton gramediaRadBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jbrPustakaRadBtn;
    private javax.swing.JRadioButton polijePressRadBtn;
    private javax.swing.JRadioButton rriLibraryBtn;
    private javax.swing.JButton submitBtn;
    private javax.swing.JComboBox<String> thnTerbitComboBox;
    // End of variables declaration//GEN-END:variables
}
