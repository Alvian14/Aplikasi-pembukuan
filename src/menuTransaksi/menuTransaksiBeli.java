/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuTransaksi;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.Koneksi;
import login.Login;
import static menuTransaksi.menutransaksijual.txthargajual;
import static menuTransaksi.menutransaksijual.txtidbarang;

/**
 *
 * @author Administrator
 */
public class menuTransaksiBeli extends javax.swing.JFrame {
        DefaultTableModel tabModel;
        ResultSet RsSupplier=null;
    /**
     * Creates new form menuTransaksiBeli
     */
    public menuTransaksiBeli() {
        initComponents();
//        loadData();
        logo();
        tanggal();
        Hapus();
        createID();
          
        this.lbNama.setText(Login.getNamaUser().toUpperCase());
        this.lbNama.setHorizontalAlignment(JLabel.CENTER);
        this.lbNama.setVerticalAlignment(JLabel.CENTER);
        this.txtIdbeli.setEditable(false);
           this.btnBarang.setBackground(new Color (0,0,0,0));
         this.btnSimpan.setBackground(new Color (0,0,0,0));
           this.btnHapus.setBackground(new Color (0,0,0,0));
             this.btnTambah.setBackground(new Color (0,0,0,0));
                this.btnHome.setBackground(new Color (0,0,0,0));
                   this.btnLogout.setBackground(new Color (0,0,0,0));
                     this.btnTransaksi.setBackground(new Color (0,0,0,0));
                       this.btnSetting.setBackground(new Color (0,0,0,0));
                         this.btnSupplier.setBackground(new Color (0,0,0,0));
                           this.btnBack.setBackground(new Color (0,0,0,0)); 
                            this.txtIdbeli.setBackground(new Color (0,0,0,0));
                             this.txtTgl.setBackground(new Color (0,0,0,0)); 
                             this.txtIdsupp.setBackground(new Color (0,0,0,0));
                             this.txtIdbarang.setBackground(new Color (0,0,0,0)); 
                             this.txtJumlah.setBackground(new Color (0,0,0,0)); 
                             this.txtHarga.setBackground(new Color (0,0,0,0)); 
                             this.txtTotalharga.setBackground(new Color (0,0,0,0)); 
        

        
                   
        tabModel = new DefaultTableModel();                                   
        tblTransbel.setModel(tabModel);
         //menampilan title pada table
        tabModel.addColumn("Id Beli");
        tabModel.addColumn("Tgl Beli");
        tabModel.addColumn("Id Supplier");
        tabModel.addColumn("Id Barang");
        tabModel.addColumn("Jumlah");
        tabModel.addColumn("Harga ");
        tabModel.addColumn("Total Harga");          
                            
                                     
    }    
     private void logo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
                
     private void loadData(){
          DefaultTableModel model = (DefaultTableModel) tblTransbel.getModel();
          model.addRow(new Object[]{
           
            txtIdbeli.getText(),
            txtTgl.getText(),
            txtIdsupp.getText(),
            txtIdbarang.getText(),
            txtJumlah.getText(),
            txtHarga.getText(),
            txtTotalharga.getText(),
    });
      }
           
       private void Hapus(){
           txtIdsupp.setText(null);
           txtIdbarang.setText(null);
           txtJumlah.setText(null);
           txtHarga.setText(null);
           txtTotalharga.setText(null); 
       }
    
     
    
      private void Hapus1(){
           txtIdsupp.setText("");
           txtIdbarang.setText("");
           txtJumlah.setText("");
           txtHarga.setText("");
           txtTotalharga.setText("");
      }
      
       private void Hapustbl(){
        DefaultTableModel model = (DefaultTableModel) tblTransbel.getModel();
        while (model.getRowCount()>0) {
        model.removeRow(0);
    }
    }
          
           
            
     
     private void createID(){
           try{
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM transaksi_beli ORDER BY id_beli DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("id_beli").substring(2);
                String B = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(B.length()==1)
                {Nol = "000";}
                else if(B.length()==2)
                {Nol = "00";}
                else if(B.length()==3)
                {Nol = "0";}
                else if(B.length()==4)
                {Nol = "";}
                txtIdbeli.setText("BT" + Nol + B);
            }else{      
                txtIdbeli.setText("BT0001");
            } 
            r.close();
            s.close();  
        }catch (Exception e) { 
            System.out.println("autonumber eror");
        }
       }
      
     private void totalBiaya(){
        int jumlahBaris = tblTransbel.getRowCount();
        int totalBiaya = 0;
        int jumlah, hargaJual;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlah = Integer.parseInt(tblTransbel.getValueAt(i, 4).toString());
            hargaJual = Integer.parseInt(tblTransbel.getValueAt(i, 5).toString());
            totalBiaya = totalBiaya + (jumlah * hargaJual);
        }
        txtTotalharga.setText(String.valueOf(totalBiaya));
//        txttotalharga.setText("Rp"+totalBiaya +",00");
    }
         
         
     public void tanggal(){
        DateFormat tgl = new SimpleDateFormat("yyyy/MM/dd");
        String tglk = tgl.format(Calendar.getInstance().getTime());
         txtTgl.setText(tglk);
    }
      
     public void tambahtransaksi(){
        int jumlah, harga, total;
        jumlah = Integer.valueOf(txtJumlah.getText());
        harga = Integer.valueOf(txtHarga.getText());
        total = jumlah * harga;
        txtTotalharga.setText(String.valueOf(total));
        loadData();
        Hapus1();
        tanggal();
        totalBiaya();
        txtIdsupp.requestFocus();
        
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
        btnSupplier = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        txtIdbarang = new javax.swing.JTextField();
        txtIdbeli = new javax.swing.JTextField();
        txtTgl = new javax.swing.JTextField();
        txtTotalharga = new javax.swing.JTextField();
        txtIdsupp = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransbel = new javax.swing.JTable();
        txtHarga = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbNama = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSupplier.setBorder(null);
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 170, 40));

        btnBarang.setBorder(null);
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });
        jPanel1.add(btnBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 170, 40));

        btnTransaksi.setBorder(null);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 170, 40));

        btnSetting.setBorder(null);
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        jPanel1.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 170, 40));

        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 170, 40));

        btnBack.setBorder(null);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 40, 30));

        btnSimpan.setBorder(null);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 90, 40));

        btnHapus.setBorder(null);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 90, 40));

        btnHome.setBorder(null);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 170, 40));

        txtIdbarang.setBorder(null);
        jPanel1.add(txtIdbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 110, 20));

        txtIdbeli.setBorder(null);
        txtIdbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdbeliActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 120, 20));

        txtTgl.setBorder(null);
        txtTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglActionPerformed(evt);
            }
        });
        jPanel1.add(txtTgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 74, 120, 20));

        txtTotalharga.setBorder(null);
        txtTotalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalhargaActionPerformed(evt);
            }
        });
        txtTotalharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalhargaKeyReleased(evt);
            }
        });
        jPanel1.add(txtTotalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 120, 30));

        txtIdsupp.setBorder(null);
        txtIdsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdsuppActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdsupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 90, 20));

        btnTambah.setBorder(null);
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 90, 40));

        tblTransbel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Beli", "Tgl Beli", "Id Supplier", "Id Barang", "Jumlah", "Harga Beli", "Total Harga"
            }
        ));
        tblTransbel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransbelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransbel);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 500, 200));

        txtHarga.setBorder(null);
        jPanel1.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 90, 20));

        txtJumlah.setBorder(null);
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        jPanel1.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 74, 110, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/Search.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 30, 20));

        lbNama.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(lbNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/Transaksi beli final.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalhargaActionPerformed

    private void txtIdsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdsuppActionPerformed
        
    }//GEN-LAST:event_txtIdsuppActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
      tambahtransaksi();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        int status;
        status = JOptionPane.showConfirmDialog(this, "Simpan data yang telah di input ?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (status){
             case JOptionPane.YES_OPTION:
        
        try {
            if (tblTransbel.getRowCount() <0) {           
                JOptionPane.showMessageDialog(rootPane, "Data belum lengkap");
            } else {
                java.sql.Connection conn = (Connection) Koneksi.configDB();
                String sql = "insert into  transaksi_beli values (?,?,?)";
                java.sql.PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtIdbeli.getText());
                stat.setString(2, txtTgl.getText());
                stat.setString(3, txtTotalharga.getText());
                stat.executeUpdate();
                System.out.println("data 1 berhasil ditambahkan");
                String idSupp,idBarang,jumlah,harga;

                // Data ke 2
               
                for (int i = 0; i < tblTransbel.getRowCount(); i++) {
                    idSupp = tblTransbel.getValueAt(i, 2).toString();
                    idBarang = tblTransbel.getValueAt(i, 3).toString();
                    jumlah = tblTransbel.getValueAt(i, 4).toString();
                    harga = tblTransbel.getValueAt(i, 5).toString();
//                    totalHarga = tblTransbel.getValueAt(i, 6).toString();
                    String sql1 = "insert into  detail_transaksi_beli values (?,?,?,?,?,?,?)";
                    java.sql.PreparedStatement stat1 = conn.prepareStatement(sql1);
                    stat1.setString(1, txtIdbeli.getText());
                    stat1.setString(2, txtTgl.getText());
                    stat1.setString(3, idSupp);
                    stat1.setString(4, idBarang);
                    stat1.setString(5, jumlah);
                    stat1.setString(6, harga);
                    stat1.setString(7, txtTotalharga.getText());
                    stat1.executeUpdate();
                }
                System.out.println("data 2 berhasil ditambahkan");
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
                loadData();
                Hapus1();
                Hapus();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data failed to save" + e);
        }
        tanggal();
        createID();
        Hapustbl();
        break;
        }
                
       
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
 int status;
        status = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menhapus data?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (status){
            case JOptionPane.YES_OPTION:
                
        DefaultTableModel model = (DefaultTableModel) tblTransbel.getModel();
        int row = tblTransbel.getSelectedRow();
        model.removeRow(row);
        totalBiaya();      
                break;
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tblTransbelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransbelMouseClicked
     
    }//GEN-LAST:event_tblTransbelMouseClicked

    private void txtTotalhargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalhargaKeyReleased
      
        
    }//GEN-LAST:event_txtTotalhargaKeyReleased

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
       this.setVisible(false);
       new menuUtama.dasboard().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
         this.setVisible(false);
       new menuSupplier.menusupplier().setVisible(true);
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
     this.setVisible(false);
     new Barang.menubarang().setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void txtTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglActionPerformed
       
    }//GEN-LAST:event_txtTglActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        tambahtransaksi();
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtIdbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdbeliActionPerformed
        // TODO add your handling code here:
        createID();
    }//GEN-LAST:event_txtIdbeliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(true);
        new ListSupplier().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        this.setVisible(false);
        new TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingActionPerformed
        this.setVisible(false);
        new setting.Setting().setVisible(true);
    }//GEN-LAST:event_btnSettingActionPerformed

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
            java.util.logging.Logger.getLogger(menuTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuTransaksiBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuTransaksiBeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNama;
    private javax.swing.JTable tblTransbel;
    public static javax.swing.JTextField txtHarga;
    public static javax.swing.JTextField txtIdbarang;
    private javax.swing.JTextField txtIdbeli;
    public static javax.swing.JTextField txtIdsupp;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtTgl;
    private javax.swing.JTextField txtTotalharga;
    // End of variables declaration//GEN-END:variables
}
