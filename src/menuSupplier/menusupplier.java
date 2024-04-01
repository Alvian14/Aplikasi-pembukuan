/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuSupplier;

import menuUtama.dasboard;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.Koneksi;
import login.Login;
/**
 *
 * @author Administrator
 */
public class menusupplier extends javax.swing.JFrame {
       DefaultTableModel tabModel;
        ResultSet RsSupplier=null;
       
    /**
     * Creates new form 
     */
    public menusupplier() {
        initComponents();
        createID();
        loadData();
        Hapus();
        logo();
        this.txtidsupp.setEditable(false);
        this.txtidsupp.setText(this.createID());
        
        
        this.lbNama.setText(Login.getNamaUser().toUpperCase());
        this.lbNama.setHorizontalAlignment(JLabel.CENTER);
        this.lbNama.setVerticalAlignment(JLabel.CENTER);
        
        
        
       this.btnbarang.setBackground(new Color (0,0,0,0));
         this.btnEdit.setBackground(new Color (0,0,0,0));
           this.btnHapus.setBackground(new Color (0,0,0,0));
             this.btnSimpan.setBackground(new Color (0,0,0,0));
               this.btnbarang.setBackground(new Color (0,0,0,0));
                 this.btnhome.setBackground(new Color (0,0,0,0));
                   this.btnLogout.setBackground(new Color (0,0,0,0));
                     this.btnsupp.setBackground(new Color (0,0,0,0));
                       this.btntransaksi.setBackground(new Color (0,0,0,0));
                         this.txtalamat.setBackground(new Color (0,0,0,0));
                           this.txtcari.setBackground(new Color (0,0,0,0));
                             this.txtidsupp.setBackground(new Color (0,0,0,0));
                               this.txtcari.setBackground(new Color (0,0,0,0));
                                 this.txtnama.setBackground(new Color (0,0,0,0));
                                   this.txtnotlpn.setBackground(new Color (0,0,0,0));
                                     this.btnSetting.setBackground(new Color (0,0,0,0));
                                      this.btnClear.setBackground(new Color (0,0,0,0));
                                     
                                     
                                     
    }
     private void logo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
     private void loadData(){
        DefaultTableModel model = new DefaultTableModel ();
        model.addColumn("Id supplier");
        model.addColumn("Nama");
        model.addColumn("No Telpn");
        model.addColumn("Alamat");
        
        // menampilkan data pada tabel
        
        try  {
             
            String sql = "select * from supplier " ;
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.Statement smk=conn.createStatement();
            java.sql.ResultSet res=smk.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[] {res.getString(1), res.getString(2),res.getString(3),res.getString(4) });
            }
            tblSupplier.setModel(model);
        }catch (Exception e){
            
        }
     }
     // untuk mengkosongkan txtfield
     private void Hapus(){
//      txtidsupp.setText(null);
         txtnama.setText(null);
           txtnotlpn.setText(null);
             txtalamat.setText(null);
     }
     //untuk pencarian data
     private void cariData(String key){
         try{
            Object[] judul_kolom = {"Id supplier", "Nama","No Telpn", "Alamat"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            tblSupplier.setModel(tabModel);
            
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            Statement pst=conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            RsSupplier=pst.executeQuery("SELECT * from supplier WHERE id_supplier LIKE '%"+key+"%' OR nama_supplier LIKE '%"+key+"%' OR no_hp LIKE '%"+key+"%' OR alamat LIKE '%"+key+"%'");  
            while(RsSupplier.next()){
                Object[] data={
                    RsSupplier.getString("id_supplier"),
                    RsSupplier.getString("nama_supplier"),
                    RsSupplier.getString("no_hp"),
                    RsSupplier.getString("alamat"),
                };
               tabModel.addRow(data);
            }                
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
     
    // untuk id otomatis
  private String createID(){
        try{
            // menyiapkan query untuk mendapatkan id terakhir
            String query = "SELECT * FROM supplier ORDER BY id_supplier DESC LIMIT 0,1", lastID, nomor;
            Connection conn = (Connection)Koneksi.configDB();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(query);
            
            // cek apakah query berhasil dieksekusi
            if(res.next()){
                // mendapatkan id terakhir
                lastID =  res.getString("id_supplier");
                if(lastID != null){
                    // mendapatkan nomor id
                    nomor = lastID.substring(3);
                }else{
                    nomor = "0000";
                }
                conn.close();
            
                // jika id barang belum exist maka id akan dibuat
                return String.format("SP%03d", Integer.parseInt(nomor)+1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
   
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        txtnotlpn = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        txtidsupp = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        btnhome = new javax.swing.JButton();
        btnsupp = new javax.swing.JButton();
        btntransaksi = new javax.swing.JButton();
        btnbarang = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        lbNama = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 100, 40));

        btnHapus.setBorder(null);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 100, 40));

        txtcari.setBorder(null);
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 190, 30));

        txtnotlpn.setBorder(null);
        jPanel1.add(txtnotlpn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 130, 20));

        txtalamat.setBorder(null);
        txtalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtalamatActionPerformed(evt);
            }
        });
        txtalamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtalamatKeyPressed(evt);
            }
        });
        jPanel1.add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 130, 20));

        txtidsupp.setBorder(null);
        txtidsupp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtidsuppMouseClicked(evt);
            }
        });
        txtidsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidsuppActionPerformed(evt);
            }
        });
        jPanel1.add(txtidsupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 130, 20));

        txtnama.setBorder(null);
        jPanel1.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 130, 30));

        btnhome.setBorder(null);
        btnhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 170, 50));

        btnsupp.setBorder(null);
        btnsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuppActionPerformed(evt);
            }
        });
        jPanel1.add(btnsupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 170, 50));

        btntransaksi.setBorder(null);
        btntransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(btntransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, 50));

        btnbarang.setBorder(null);
        btnbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbarangActionPerformed(evt);
            }
        });
        jPanel1.add(btnbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 170, 50));

        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 170, 50));

        btnSimpan.setBorder(null);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 100, 40));

        btnSetting.setBorder(null);
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        jPanel1.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 170, 50));

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id supplier", "Nama", "No Telpn", "alamat"
            }
        ));
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 187, 490, 260));

        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 50, 30));

        lbNama.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(lbNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 150, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/Menu Supplier final2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
       int status;
       
       status = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
       
       switch(status){
           case JOptionPane.YES_OPTION : 
        try {
           
           String sql = "delete from supplier where id_supplier ='"+txtidsupp.getText()+"'";
           java.sql.Connection conn = (Connection)Koneksi.configDB();
           java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            
           pst.execute();
           JOptionPane.showMessageDialog(this, "Data Berhasil Di Hapus");
       } catch (Exception e) {
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
       loadData();
       Hapus();
       txtidsupp.setText(createID()); 
               break;
       }
        

      
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbarangActionPerformed
         this.setVisible(false);
        new menuTransaksi.TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_btnbarangActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if(txtnama.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Isi Nama Supplier");
        }else if(txtnotlpn.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Isi Harga Barang");
        } else if (txtalamat.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Isi Jumlah Barang");
        } else {
        
         String sql = "insert into supplier values (?,?,?,?)";
         try {
            java.sql.Connection conn=(Connection) Koneksi.configDB();
            java.sql.PreparedStatement stat=conn.prepareStatement(sql);
            stat.setString(1, txtidsupp.getText());
            stat.setString(2, txtnama.getText());
            stat.setString(3, txtnotlpn.getText ());
            stat.setString(4, txtalamat.getText());
            if (JOptionPane.showConfirmDialog(null, "Simpan data yang telah di input ?", "WARNING",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {     
                 stat.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
                 Hapus();
                  txtidsupp.setText(createID()); 
                 loadData();
            } else {
        } 
            txtidsupp.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data failed to save"+e);
        }
     }
        
    }//GEN-LAST:event_btnSimpanActionPerformed
       
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
         int status;
       
       status = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
       
       switch(status){
           case JOptionPane.YES_OPTION : 
        try {
            String sql ="UPDATE supplier SET id_supplier = '"+txtidsupp.getText()+"', nama_supplier = '"+txtnama.getText()+"', no_hp = '"+txtnotlpn.getText()+"', alamat = '"+txtalamat.getText()+"' WHERE id_supplier = '"+txtidsupp.getText()+"'";
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        loadData();
        Hapus();
        txtidsupp.setText(createID()); 
          break;
       }
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtidsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidsuppActionPerformed
      txtidsupp.setText(createID());   
      
    }//GEN-LAST:event_txtidsuppActionPerformed

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
     int baris = tblSupplier.rowAtPoint(evt.getPoint());
        String id_supplier =tblSupplier.getValueAt(baris,0).toString();
        txtidsupp.setText(id_supplier);
        String nama = tblSupplier.getValueAt(baris,1).toString();
        txtnama.setText(nama);
        String alamat = tblSupplier.getValueAt(baris, 3).toString();
        txtalamat.setText(alamat);
        String no_hp = tblSupplier.getValueAt(baris, 2).toString();
        txtnotlpn.setText(no_hp);
        
        
    }//GEN-LAST:event_tblSupplierMouseClicked

    private void btntransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransaksiActionPerformed
        this.setVisible(false);
        new Barang.menubarang().setVisible(true);
    }//GEN-LAST:event_btntransaksiActionPerformed

    private void btnsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuppActionPerformed
        
    }//GEN-LAST:event_btnsuppActionPerformed

    private void btnhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhomeActionPerformed
        this.setVisible(false);
        new dasboard().setVisible(true);
    }//GEN-LAST:event_btnhomeActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
         int status;
        status = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin keluar ?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (status){
            case JOptionPane.YES_OPTION:
                this.setVisible(false);
                new login.Login().setVisible(true);
                break;
        }
        
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingActionPerformed
       this.setVisible(false);
        new setting.Setting().setVisible(true);
    }//GEN-LAST:event_btnSettingActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
       String key = txtcari.getText();
        System.out.println(key);
        
        if(key!=""){
            cariData(key);
        }else{
            loadData();
        }
    }//GEN-LAST:event_txtcariKeyReleased

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        
    }//GEN-LAST:event_txtcariKeyPressed

    private void txtidsuppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidsuppMouseClicked
        
    }//GEN-LAST:event_txtidsuppMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
       Hapus();
       txtidsupp.setText(createID());   
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtalamatActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_txtalamatActionPerformed

    private void txtalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtalamatKeyPressed
       if (evt.getKeyCode()== KeyEvent.VK_ENTER){
          btnSimpanActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Data Berhasil di tambahkan"));
        }
        
    }//GEN-LAST:event_txtalamatKeyPressed

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
            java.util.logging.Logger.getLogger(menusupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menusupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menusupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menusupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menusupplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnbarang;
    private javax.swing.JButton btnhome;
    private javax.swing.JButton btnsupp;
    private javax.swing.JButton btntransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNama;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtidsupp;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnotlpn;
    // End of variables declaration//GEN-END:variables
}
