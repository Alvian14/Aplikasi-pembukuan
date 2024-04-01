/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuTransaksi;

import menuUtama.dasboard;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.Koneksi;
import login.Login;

/**
 *
 * @author Administrator
 */
public class menutransaksijual extends javax.swing.JFrame {
    DefaultTableModel tabModel;
        ResultSet RsSupplier=null;
//    private HashMap<String, Integer> tempStokBarang, tempStokMenu;
    /**
     * Creates new form
     */
    
        
    public menutransaksijual() {
        initComponents();
        tanggal();
        loadData();
        logo();
        Hapus();
        createID();
        
          
        this.lbNama.setText(Login.getNamaUser().toUpperCase());
        this.lbNama.setHorizontalAlignment(JLabel.CENTER);
        this.lbNama.setVerticalAlignment(JLabel.CENTER);
        
        this.txtidjual.setEditable(false);
        this.txthargajual.setEditable(false);
        this.txtidbarang.setEditable(false);
        this.txtStok.setEditable(false);
        this.txttgljual.setEditable(false);
        tanggal();
        
        this.btnbarang.setBackground(new Color (0,0,0,0));
          this.txtStok.setBackground(new Color (0,0,0,0));
           this.btnhapus.setBackground(new Color (0,0,0,0));
             this.btntambah.setBackground(new Color (0,0,0,0));
               this.btnbarang.setBackground(new Color (0,0,0,0));
                 this.btnhome.setBackground(new Color (0,0,0,0));
                   this.btnLogout.setBackground(new Color (0,0,0,0));
                     this.btnsupp.setBackground(new Color (0,0,0,0));
                       this.btntransaksi.setBackground(new Color (0,0,0,0));
                         this.txtidjual.setBackground(new Color (0,0,0,0));
                           
                             this.txtidbarang.setBackground(new Color (0,0,0,0));
                               this.txtJumlah.setBackground(new Color (0,0,0,0));
                                 this.txthargajual.setBackground(new Color (0,0,0,0));
                                   this.txttotalharga.setBackground(new Color (0,0,0,0));
                                     this.txttgljual.setBackground(new Color (0,0,0,0));
                                       this.btnsetting.setBackground(new Color (0,0,0,0));
                                        this.jButton2.setBackground(new Color (0,0,0,0));
                                         this.btnsimpan.setBackground(new Color (0,0,0,0));
       
        tabModel = new DefaultTableModel();                                   
        tblTransjul.setModel(tabModel);
         //menampilan title pada table
        tabModel.addColumn("Id Jual");
        tabModel.addColumn("Tgl Jual");
        tabModel.addColumn("Id Barng");
        tabModel.addColumn("Jumlah");
        tabModel.addColumn("Harga Jual");
        tabModel.addColumn("Total Harga");                                   
                                     
        
    }
    
     private void logo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
     private void loadData(){
          DefaultTableModel model = (DefaultTableModel) tblTransjul.getModel();
          model.addRow(new Object[]{
           
            txtidjual.getText(),
            txttgljual.getText(),
            txtidbarang.getText(),
            txtJumlah.getText(),
            txthargajual.getText(),
            txttotalharga.getText(),
    });
      }
         
    public void tanggal(){
        DateFormat tgl = new SimpleDateFormat("yyyy/MM/dd");
        String tglk = tgl.format(Calendar.getInstance().getTime());
        txttgljual.setText(tglk);
    }
      
    
      private void totalBiaya(){
        int jumlahBaris = tblTransjul.getRowCount();
        int totalBiaya = 0;
        int jumlah, hargaJual;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlah = Integer.parseInt(tblTransjul.getValueAt(i, 3).toString());
            hargaJual = Integer.parseInt(tblTransjul.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlah * hargaJual);
        }
        txttotalharga.setText(String.valueOf(totalBiaya));

    }
         
     
     
     private void Hapustbl(){
        DefaultTableModel model = (DefaultTableModel) tblTransjul.getModel();
        while (model.getRowCount()>0) {
        model.removeRow(0);
    }
    }
       
        
        
      private void Hapus(){
           txttgljual.setText(null);
           txtidbarang.setText(null);
             txtJumlah.setText(null);
              txthargajual.setText(null);
              txttotalharga.setText(null);
     }
      private void Hapus1(){
           txttgljual.setText("");
           txtidbarang.setText("");
           txtJumlah.setText("");
           txthargajual.setText("");
           txttotalharga.setText("");
      }
        
      
       private void createID(){
           try{
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM transaksi_jual ORDER BY id_jual DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("id_jual").substring(2);
                String T = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(T.length()==1)
                {Nol = "000";}
                else if(T.length()==2)
                {Nol = "00";}
                else if(T.length()==3)
                {Nol = "0";}
                else if(T.length()==4)
                {Nol = "";}
                txtidjual.setText("T" + Nol + T);
            }else{      
                txtidjual.setText("T0001");
            } 
            r.close();
            s.close();  
        }catch (Exception e) { 
            System.out.println("autonumber eror");
        }
       }
         
        public void tambahtransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.valueOf(txtJumlah.getText());
        harga = Integer.valueOf(txthargajual.getText());
        total = jumlah * harga;
        txttotalharga.setText(String.valueOf(total));
        loadData();
        Hapus1();
        tanggal();
        totalBiaya();
        txtidbarang.requestFocus();
        
    }
        
        public void jual(){
        int jumlah, stok;
        
        jumlah = Integer.valueOf(txtJumlah.getText());
        stok = Integer.valueOf(txtStok.getText());
        
//        bayar = Integer.valueOf(txt_Jumlah.getText());
        if (jumlah > stok) {
            JOptionPane.showMessageDialog(null, "MOHON MAAF STOK KURANG !!");
            Hapus1();
            tanggal();
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
        btnsupp = new javax.swing.JButton();
        btntransaksi = new javax.swing.JButton();
        btnbarang = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnhome = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        txttgljual = new javax.swing.JTextField();
        txtidbarang = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        txthargajual = new javax.swing.JTextField();
        txttotalharga = new javax.swing.JTextField();
        txtidjual = new javax.swing.JTextField();
        btnsetting = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransjul = new javax.swing.JTable();
        lbNama = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsupp.setBorder(null);
        btnsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuppActionPerformed(evt);
            }
        });
        jPanel1.add(btnsupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 170, 50));

        btntransaksi.setBorder(null);
        btntransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(btntransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 170, 50));

        btnbarang.setBorder(null);
        btnbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbarangActionPerformed(evt);
            }
        });
        jPanel1.add(btnbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, 50));

        btntambah.setBorder(null);
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        jPanel1.add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 90, 40));

        btnhapus.setBorder(null);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 90, 40));

        btnhome.setBorder(null);
        btnhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 170, 50));

        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 170, 50));

        txttgljual.setBorder(null);
        txttgljual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttgljualActionPerformed(evt);
            }
        });
        jPanel1.add(txttgljual, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 130, 20));

        txtidbarang.setBorder(null);
        txtidbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidbarangActionPerformed(evt);
            }
        });
        jPanel1.add(txtidbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 100, 30));

        txtJumlah.setBorder(null);
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahKeyReleased(evt);
            }
        });
        jPanel1.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 130, 30));

        txthargajual.setBorder(null);
        txthargajual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargajualActionPerformed(evt);
            }
        });
        jPanel1.add(txthargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 130, 20));

        txttotalharga.setBorder(null);
        txttotalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalhargaActionPerformed(evt);
            }
        });
        jPanel1.add(txttotalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 130, 30));

        txtidjual.setBorder(null);
        txtidjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidjualActionPerformed(evt);
            }
        });
        jPanel1.add(txtidjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 130, 30));

        btnsetting.setBorder(null);
        btnsetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsettingActionPerformed(evt);
            }
        });
        jPanel1.add(btnsetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 170, 50));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/Search.png"))); // NOI18N
        btnSearch.setBorder(null);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 30, 30));

        btnsimpan.setBorder(null);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 90, 40));

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 40, 40));

        tblTransjul.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblTransjul.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblTransjulComponentShown(evt);
            }
        });
        jScrollPane2.setViewportView(tblTransjul);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 197, 470, 230));

        lbNama.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(lbNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/Transaksi jual final.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txtStok.setBorder(null);
        jPanel1.add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 10, -1));

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

    private void txtidbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidbarangActionPerformed
        
    }//GEN-LAST:event_txtidbarangActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
           
           jual();
           tambahtransaksi();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
         int status;
        status = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus data ?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (status){
            case JOptionPane.YES_OPTION:
                
        DefaultTableModel model = (DefaultTableModel) tblTransjul.getModel();
        int row = tblTransjul.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
                break;

        }
    }//GEN-LAST:event_btnhapusActionPerformed
    
    private void btnsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuppActionPerformed
       this.setVisible(false);
        new menuSupplier.menusupplier().setVisible(true);
    }//GEN-LAST:event_btnsuppActionPerformed

    private void btnbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbarangActionPerformed
        this.setVisible(false);
        new Barang.menubarang().setVisible(true);
    }//GEN-LAST:event_btnbarangActionPerformed

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

    private void btnsettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsettingActionPerformed
        this.setVisible(false);
        new setting.Setting().setVisible(true);
    }//GEN-LAST:event_btnsettingActionPerformed

    private void txtidjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidjualActionPerformed
        createID();
    }//GEN-LAST:event_txtidjualActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        this.setVisible(true);
        new Listbarang().setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txthargajualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargajualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargajualActionPerformed

    private void txttgljualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttgljualActionPerformed
        
    }//GEN-LAST:event_txttgljualActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        
        jual();
        tambahtransaksi();
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txttotalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalhargaActionPerformed
        jual();
        tambahtransaksi();
        
        
    }//GEN-LAST:event_txttotalhargaActionPerformed
//
//    private int hitungStok(){
//        int jumlah = 0;
//        for(int i = 0; i < this.tblTransjul.getRowCount(); i++){
//            jumlah += Integer.parseInt(this.tblTransjul.getValueAt(i, 3).toString());
//        }
//        return jumlah;
//    }
//    
//    private int hitungHarga(){
//        int jumlah = 0;
//        for(int i = 0; i < this.tblTransjul.getRowCount(); i++){
//            jumlah += Integer.parseInt(this.tblTransjul.getValueAt(i, 5).toString());
//        }
//        return jumlah;
//    }
    
    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
           int status;
        status = JOptionPane.showConfirmDialog(this, "Simpan data yang telah di input ?","confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (status){
             case JOptionPane.YES_OPTION:
        
        
        try {
            if (tblTransjul.getRowCount() <0) {           // or bla bla bla is empty, must be has data
                JOptionPane.showMessageDialog(rootPane, "Data belum lengkap");
            } else {
                java.sql.Connection conn = (Connection) Koneksi.configDB();
                String sql = "insert into  transaksi_jual values (?,?,?)";
                java.sql.PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtidjual.getText());
                stat.setString(2, txttgljual.getText());
                stat.setString(3, txttotalharga.getText());
                stat.executeUpdate();
                System.out.println("data 1 berhasil ditambahkan");
                String idBarang,jumlah,hargaJual,totalHarga;

                // Data ke 2
               
                for (int i = 0; i < tblTransjul.getRowCount(); i++) {
                    
                    idBarang = tblTransjul.getValueAt(i, 2).toString();
                    jumlah = tblTransjul.getValueAt(i, 3).toString();
                    hargaJual = tblTransjul.getValueAt(i, 4).toString();
                    totalHarga = tblTransjul.getValueAt(i, 5).toString();
                    String sql1 = "insert into  detail_transaksi_jual values (?,?,?,?,?,?)";
                    java.sql.PreparedStatement stat1 = conn.prepareStatement(sql1);
                    stat1.setString(1, txtidjual.getText());
                    stat1.setString(2, txttgljual.getText());
                    stat1.setString(3, idBarang);
                    stat1.setString(4, jumlah);
                    stat1.setString(5, hargaJual);
                    stat1.setString(6, totalHarga);
                    stat1.executeUpdate();
                }
                System.out.println("data 2 berhasil ditambahkan");
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
                loadData();
                Hapus();
                Hapus1();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data failed to save" + e);
        }
//        jual();
        tanggal();
        createID();
        Hapustbl();
                 break;
        }
                

          
                
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void txtJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyPressed

    }//GEN-LAST:event_txtJumlahKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblTransjulComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblTransjulComponentShown
        this.setVisible(false);
        new TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_tblTransjulComponentShown

    private void btntransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransaksiActionPerformed
        this.setVisible(false);
        new TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_btntransaksiActionPerformed

    private void btnhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhomeActionPerformed
        this.setVisible(false);
        new dasboard().setVisible(true);
    }//GEN-LAST:event_btnhomeActionPerformed

    private void txtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahKeyReleased

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
            java.util.logging.Logger.getLogger(menutransaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menutransaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menutransaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menutransaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menutransaksijual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnbarang;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnhome;
    private javax.swing.JButton btnsetting;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnsupp;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btntransaksi;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNama;
    private javax.swing.JTable tblTransjul;
    private javax.swing.JTextField txtJumlah;
    public static transient javax.swing.JTextField txtStok;
    public static javax.swing.JTextField txthargajual;
    public static javax.swing.JTextField txtidbarang;
    private javax.swing.JTextField txtidjual;
    private javax.swing.JTextField txttgljual;
    private javax.swing.JTextField txttotalharga;
    // End of variables declaration//GEN-END:variables
}
