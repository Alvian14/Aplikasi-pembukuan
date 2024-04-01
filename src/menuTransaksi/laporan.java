/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuTransaksi;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import login.Koneksi;

/**
 *
 * @author Administrator
 */
public class laporan extends javax.swing.JFrame {

    /**
     * Creates new form laporan
     */
    public laporan() {
        initComponents();
       dataTable();
       dataTable1();
       tampilJam();
       logo();
        
        this.btnBack.setBackground(new Color(0,0,0,0));
        this.txtPendapatan.setBackground(new Color(0,0,0,0));
        this.btnTampil.setBackground(new Color (0,0,0,0));
        this.btnTampil2.setBackground(new Color (0,0,0,0));
        this.txtPendapatan1.setBackground(new Color(0,0,0,0));
    }
    
     private void logo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
    
    public void tampilJam() {
        ActionListener taskPerformer = new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {
                
            String nol_jam = "", nol_menit = "", nol_detik = "";
            
            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
            
            if(nilai_jam <= 9) nol_jam = "0";
            if(nilai_menit <= 9) nol_menit = "0";
            if(nilai_detik <= 9) nol_detik = "0";
            
            String jam = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
            
            lbJam.setText(jam + ":" + menit + ":" + detik + "");
            
        }
        
    };
        new Timer (100, taskPerformer).start();
    }
      public void dataTable(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id Beli");
        tbl.addColumn("Tgl Beli");
        tbl.addColumn("Id Supplier");
        tbl.addColumn("Id Barang");
        tbl.addColumn("Jumlah");
//        tbl.addColumn("harga");
        tbl.addColumn("Total");
        tblPengeluaran.setModel(tbl);
            try {
            Connection conn = (Connection) Koneksi.configDB();
            Statement s = conn.createStatement();

            ResultSet res = s.executeQuery("SELECT Transaksi_beli.tgl_beli,detail_transaksi_beli.id_beli, "
                    + "detail_transaksi_beli.id_supplier,detail_transaksi_beli.id_barang,"
                    + "detail_transaksi_beli.jumlah,detail_transaksi_beli.harga,"
                    + "detail_transaksi_beli.jumlah,detail_transaksi_beli.total_harga "
                    + "FROM transaksi_beli, detail_transaksi_beli "
                    + "WHERE  detail_transaksi_beli.id_beli = transaksi_beli.id_beli");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_beli"),
                    res.getString("tgl_beli"),
                    res.getString("id_supplier"),
                    res.getString("id_barang"),
                    res.getString("jumlah"),
                    res.getString("total_harga")
                });
                tblPengeluaran.setModel(tbl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

                
       public void dataTable1(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id Jual");
        tbl.addColumn("Tgl Jual");
        tbl.addColumn("Id Barang");
        tbl.addColumn("jumlah");
        tbl.addColumn("Harga Jual");
        tbl.addColumn("Total Harga");
        tblPemasukan.setModel(tbl);
        
            try {
            Connection conn = (Connection) Koneksi.configDB();
            Statement s = conn.createStatement();
            ResultSet res = s.executeQuery("SELECT Transaksi_jual.tgl_jual,detail_transaksi_jual.id_jual, detail_transaksi_jual.id_barang,"
                    + "detail_transaksi_jual.jumlah,detail_transaksi_jual.harga_jual,detail_transaksi_jual.total_harga "
                    + "FROM transaksi_jual, detail_transaksi_jual "
                    + "WHERE  detail_transaksi_jual.id_jual = transaksi_jual.id_jual");
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("id_jual"),
                    res.getString("tgl_jual"),
                    res.getString("id_barang"),
                    res.getString("jumlah"),
                    res.getString("harga_jual"),
                    res.getString("total_harga")
                });
                tblPemasukan.setModel(tbl);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
                

        
            


                

                    
                    
      private void pengeluaran() {
         
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(tgl1.getDate()));
        String tanggal2 = String.valueOf(fm.format(tgl2.getDate()));
        
        try {
            String sql = "select sum(jumlah) as barang, sum(total_harga) as harga "
                    + "FROM detail_transaksi_beli "
                    + "WHERE tgl_beli BETWEEN '" + tanggal1 +"' AND '" + tanggal2 + "'";
            Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                
                txtPendapatan.setText(rs.getString("harga"));
                
            }else {
                txtPendapatan.setText("0");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }        
    }

      
       private void pemasukan() {
         
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(tgl3.getDate()));
        String tanggal2 = String.valueOf(fm.format(tgl4.getDate()));
        
        try {
            String sql = "select sum(jumlah) as barang, sum(total_harga) as harga "
                    + "FROM detail_transaksi_jual "
                    + "WHERE tgl_jual BETWEEN '" + tanggal1 +"' AND '" + tanggal2 + "'";
            Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                
                txtPendapatan1.setText(rs.getString("harga"));
                
            }else {
                txtPendapatan1.setText("0");
            }

        } catch (SQLException  e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPengeluaran = new javax.swing.JTable();
        tgl2 = new com.toedter.calendar.JDateChooser();
        tgl1 = new com.toedter.calendar.JDateChooser();
        txtPendapatan = new javax.swing.JTextField();
        btnTampil = new javax.swing.JButton();
        lbDalam1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tgl4 = new com.toedter.calendar.JDateChooser();
        tgl3 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPemasukan = new javax.swing.JTable();
        btnTampil2 = new javax.swing.JButton();
        txtPendapatan1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lbJam = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPengeluaran);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 670, 250));
        jPanel3.add(tgl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 120, 30));
        jPanel3.add(tgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 120, 30));

        txtPendapatan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPendapatan.setBorder(null);
        txtPendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPendapatanActionPerformed(evt);
            }
        });
        jPanel3.add(txtPendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 110, 20));

        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });
        jPanel3.add(btnTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 70, 30));

        lbDalam1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/8. LabelPengeluaran final 1.png"))); // NOI18N
        jPanel3.add(lbDalam1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 370));

        jTabbedPane1.addTab("Laporan Pengeluaran", jPanel3);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(tgl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 120, 30));
        jPanel2.add(tgl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 120, 30));

        tblPemasukan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblPemasukan);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 660, 260));

        btnTampil2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampil2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnTampil2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 70, 30));

        txtPendapatan1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPendapatan1.setBorder(null);
        txtPendapatan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPendapatan1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtPendapatan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 110, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/8. LabelPendapatan final.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 370));

        jTabbedPane1.addTab("Laporan Pendapatan", jPanel2);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 700, 400));

        tgl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 100, 20));
        tgl.setText(new menuUtama.dateTime().getTgl());
        tgl.setPreferredSize(new java.awt.Dimension(120,22));

        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, 40));

        lbJam.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(lbJam, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 100, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/8. L trans final.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 510));

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

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
        // tabel pengeluaran
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id Beli");
        tbl.addColumn("Tgl Beli");
        tbl.addColumn("Id Supplier");
        tbl.addColumn("Id Barang");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Total");
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(tgl1.getDate()));
        String tanggal2 = String.valueOf(fm.format(tgl2.getDate()));
        try{ 
            
        String sql = "SELECT * FROM detail_transaksi_beli WHERE tgl_beli BETWEEN '" + tanggal1 +"' AND '" + tanggal2 + "'" ;
        Connection conn = (Connection) Koneksi.configDB();
        java.sql.Statement st = conn.createStatement();
        java.sql.ResultSet rs = st.executeQuery(sql);
        //System.out.println(sql);
        
            while (rs.next()) {
                tbl.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7)});
            }
            tblPengeluaran.setModel(tbl);
            pengeluaran();
           
       }catch(SQLException  e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
           
        


        
        
        
        
        
        
           
        
       
    }//GEN-LAST:event_btnTampilActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        new menuTransaksi.TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtPendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPendapatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendapatanActionPerformed

    private void txtPendapatan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPendapatan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendapatan1ActionPerformed

    private void btnTampil2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampil2ActionPerformed
         DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id Jual");
        tbl.addColumn("Tgl Jual");
        tbl.addColumn("Id Barang");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Harga Jual");
        tbl.addColumn("Total Harga");
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(tgl3.getDate()));
        String tanggal2 = String.valueOf(fm.format(tgl4.getDate()));
        
        try{ 
        String sql = "SELECT * FROM detail_transaksi_jual WHERE tgl_jual BETWEEN '" + tanggal1 +"' AND '" + tanggal2 + "'" ;
        Connection conn = (Connection) Koneksi.configDB();
        java.sql.Statement st = conn.createStatement();
        java.sql.ResultSet rs = st.executeQuery(sql);
        
            while (rs.next()) {
                tbl.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }
            tblPemasukan.setModel(tbl);
            pemasukan();
       }catch(SQLException  e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       } 
        
    }//GEN-LAST:event_btnTampil2ActionPerformed

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
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnTampil2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDalam1;
    private javax.swing.JLabel lbJam;
    private javax.swing.JTable tblPemasukan;
    private javax.swing.JTable tblPengeluaran;
    private javax.swing.JLabel tgl;
    private com.toedter.calendar.JDateChooser tgl1;
    private com.toedter.calendar.JDateChooser tgl2;
    private com.toedter.calendar.JDateChooser tgl3;
    private com.toedter.calendar.JDateChooser tgl4;
    private javax.swing.JTextField txtPendapatan;
    private javax.swing.JTextField txtPendapatan1;
    // End of variables declaration//GEN-END:variables
}
