/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Barang;

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
public class menubarang extends javax.swing.JFrame {
       DefaultTableModel tabModel;
        ResultSet RsBarang=null;
    /**
     * Creates new form menubarang
     */
    public menubarang() {
        initComponents();
        loadData();
        logo();
        createID();
        Hapus();
        this.lbNama.setText(Login.getNamaUser().toUpperCase());
        this.lbNama.setHorizontalAlignment(JLabel.CENTER);
        this.lbNama.setVerticalAlignment(JLabel.CENTER);
        this.txtidbarang.setEditable(false);
        this.txtKategori.setEditable(false);
        
        this.btnBarang.setBackground(new Color (0,0,0,0));
         this.btnEdit.setBackground(new Color (0,0,0,0));
          this.btnHapus.setBackground(new Color (0,0,0,0));
           this.btnHome.setBackground(new Color (0,0,0,0));
            this.btnLogout.setBackground(new Color (0,0,0,0));
             this.btnSetting.setBackground(new Color (0,0,0,0));
              this.btnSimpan.setBackground(new Color (0,0,0,0));
               this.btnBarang.setBackground(new Color (0,0,0,0));
                this.btnTransaksi.setBackground(new Color (0,0,0,0));
                 this.btnSupplier.setBackground(new Color (0,0,0,0));
                  this.txtHarjul.setBackground(new Color (0,0,0,0));
                   this.btnClear.setBackground(new Color (0,0,0,0));
                    this.txtHarbel.setBackground(new Color (0,0,0,0));
                     this.kategori.setBackground(new Color (0,0,0,0));
    }
    private void logo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
    
    private void loadData(){
        DefaultTableModel model = new DefaultTableModel ();
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok");
        model.addColumn("Kategori");
        model.addColumn("Harga Jual");
        model.addColumn("Harga Beli");
       
       
        
        // menampilkan data pada tabel
        
        try  {
             
            String sql = "select * from barang " ;
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.Statement pst=conn.createStatement();
            java.sql.ResultSet res=pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[] {res.getString(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)});
            }
            tblBarang.setModel(model);
        }catch (Exception e){
            
        }
     }
     
      private void Hapus(){
//        txtIdbarang.setText(null);
         txtNama.setText(null);
           txtStok.setText(null);
           txtKategori.setText(null);
             txtHarjul.setText(null);
              txtHarbel.setText(null);
     }
      
       private void cariData(String key){
         try{
            Object[] judul_kolom = {"Id Barang", "Nama Barang","Stok","Kategori", "Harga Jual","Harga Beli"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            tblBarang.setModel(tabModel);
            
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            Statement pst=conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            RsBarang=pst.executeQuery("SELECT * from barang WHERE id_barang LIKE '%"+key+"%' OR nama_barang LIKE '%"+key+"%' OR stok LIKE '%"+key+"%' OR kategori LIKE '%"+key+"%' OR harga_jual LIKE '%"+key+"%' OR harga_beli LIKE '%"+key+"%'");  
            while(RsBarang.next()){
                Object[] data={
                    RsBarang.getString("id_barang"),
                    RsBarang.getString("nama_barang"),
                    RsBarang.getString("stok"),
                    RsBarang.getString("kategori"),
                    RsBarang.getString("harga_jual"),
                    RsBarang.getString("harga_beli"),
                };
               tabModel.addRow(data);
            }                
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
    }
     
        private void createID(){
           try{
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM barang ORDER BY id_barang DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("id_barang").substring(2);
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
                txtidbarang.setText("B" + Nol + B);
            }else{      
                txtidbarang.setText("B0001");
            } 
            r.close();
            s.close();  
        }catch (Exception e) { 
            System.out.println("autonumber eror");
        }
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
        btnEdit = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtHarjul = new javax.swing.JTextField();
        txtHarbel = new javax.swing.JTextField();
        txtCari = new javax.swing.JTextField();
        txtidbarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        txtKategori = new javax.swing.JTextField();
        kategori = new javax.swing.JComboBox<>();
        lbNama = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
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
        jPanel1.add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 170, 40));

        btnSetting.setBorder(null);
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        jPanel1.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 170, 40));

        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 170, 40));

        btnEdit.setBorder(null);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 90, 40));

        btnHome.setBorder(null);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 170, 40));

        btnHapus.setBorder(null);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 90, 40));

        btnSimpan.setBorder(null);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 90, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setText("Rp.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setText("Rp.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, 20));

        txtNama.setBorder(null);
        jPanel1.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 120, 20));

        txtStok.setBorder(null);
        jPanel1.add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 114, 120, 20));

        txtHarjul.setBorder(null);
        jPanel1.add(txtHarjul, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 110, 20));

        txtHarbel.setBorder(null);
        txtHarbel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHarbelActionPerformed(evt);
            }
        });
        txtHarbel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHarbelKeyPressed(evt);
            }
        });
        jPanel1.add(txtHarbel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 110, 30));

        txtCari.setBorder(null);
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariKeyPressed(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 180, 20));

        txtidbarang.setBorder(null);
        txtidbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidbarangActionPerformed(evt);
            }
        });
        jPanel1.add(txtidbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 44, 120, 20));

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Barang", "Nama Barang", "Stok", "Harga Jual", "Harga Beli"
            }
        ));
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 177, 500, 260));

        txtKategori.setBorder(null);
        txtKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKategoriActionPerformed(evt);
            }
        });
        jPanel1.add(txtKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 110, 30));

        kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full face", "Half face", "Aksesoris" }));
        kategori.setBorder(null);
        kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriActionPerformed(evt);
            }
        });
        jPanel1.add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 120, 30));

        lbNama.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(lbNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, 20));

        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 140, 40, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/Menu Barang final2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 723, -1));

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

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        this.setVisible(false);
        new menuSupplier.menusupplier().setVisible(true);
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        new menuUtama.dasboard().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        int status;
       status = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
       switch(status){
           case JOptionPane.YES_OPTION : 
        try {
            String sql = "INSERT INTO barang VALUES (' " +txtidbarang.getText ()+"',' " +txtNama.getText ()+"',' " +txtStok.getText ()+"',' " +txtKategori.getText ()+"',' " +txtHarjul.getText ()+"',' " +txtHarbel.getText ()+"')";
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        Hapus();
        createID();
        break;
       }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int status;
       status = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
       switch(status){
           case JOptionPane.YES_OPTION :
        try {
            String sql ="UPDATE barang SET id_barang = '"+txtidbarang.getText()+"', nama_barang = '"+txtNama.getText()+"', stok = '"+txtStok.getText()+"', kategori = '"+txtKategori.getText()+"', harga_jual = '"+txtHarjul.getText()+"', harga_beli = '"+txtHarbel.getText()+"' WHERE id_barang = '"+txtidbarang.getText()+"'";
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        loadData();
        Hapus();
        createID();
        break;
       }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
       int status;
       status = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
       switch(status){
           case JOptionPane.YES_OPTION :
        try {
           
           String sql = "delete from barang where id_barang ='"+txtidbarang.getText()+"'";
           java.sql.Connection conn = (Connection)Koneksi.configDB();
           java.sql.PreparedStatement pst=conn.prepareStatement(sql);
           pst.execute();
           JOptionPane.showMessageDialog(this, "Data Berhasil Di Hapus");
       } catch (Exception e) {
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
       loadData();
       Hapus();
       createID();
         break;
       }   
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
      int baris = tblBarang.rowAtPoint(evt.getPoint());
        String id_barang =tblBarang.getValueAt(baris,0).toString();
        txtidbarang.setText(id_barang);
        String nama_barang = tblBarang.getValueAt(baris,1).toString();
        txtNama.setText(nama_barang);
        String stok = tblBarang.getValueAt(baris, 2).toString();
        txtStok.setText(stok);
        String kategori = tblBarang.getValueAt(baris, 3).toString();
        txtKategori.setText(kategori);
        String harga_jual= tblBarang.getValueAt(baris, 4).toString();
        txtHarjul.setText(harga_jual);
        String harga_beli = tblBarang .getValueAt(baris, 5).toString();
        txtHarbel.setText(harga_beli);
    }//GEN-LAST:event_tblBarangMouseClicked

    private void txtCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyPressed
         String key = txtCari.getText();
        System.out.println(key);
        
        if(key!=""){
            cariData(key);
        }else{
            loadData();
        }
    }//GEN-LAST:event_txtCariKeyPressed

    private void txtidbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidbarangActionPerformed
      createID();
    }//GEN-LAST:event_txtidbarangActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
    
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
         this.setVisible(false);
         new menuTransaksi.TransaksiUtama().setVisible(true);
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

    private void txtHarbelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHarbelKeyPressed
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            btnSimpanActionPerformed(new ActionEvent(evt.getSource(), evt.getID(), "Data Berhasil di tambahkan"));
        }
    }//GEN-LAST:event_txtHarbelKeyPressed

    private void txtHarbelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHarbelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHarbelActionPerformed

    private void txtKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKategoriActionPerformed

    private void kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriActionPerformed
        switch(kategori.getSelectedIndex()){
            case 0:
                txtKategori.setText("Full Face");
                break;
            case 1:
                txtKategori.setText("Half Face");
                break;
            case 2:
                txtKategori.setText("Aksesoris");
                break;
        }
    }//GEN-LAST:event_kategoriActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        Hapus();
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(menubarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menubarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menubarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menubarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menubarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kategori;
    private javax.swing.JLabel lbNama;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarbel;
    private javax.swing.JTextField txtHarjul;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtidbarang;
    // End of variables declaration//GEN-END:variables
}
