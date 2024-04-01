/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuUtama;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import login.Koneksi;
import login.Login;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author Administrator
 */
public class dasboard extends javax.swing.JFrame {

    

    /**
     * Creates new form dasboard
     */
    public dasboard() {
        initComponents();
        logo();
        tampilJam();
        tampilPendapatan();
        tampilPengeluaran();
        terjual();
        showPieChartPenjualan(panel2,"Barang Terjual Bulan Ini", getBulan()+1, getTahun()+1900);
        
        
        
        this.lbNama.setText(Login.getNamaUser().toUpperCase());
        this.lbNama.setHorizontalAlignment(JLabel.CENTER);
        this.lbNama.setVerticalAlignment(JLabel.CENTER);
        
         this.btnGrafik.setBackground(new Color(0,0,0,0));
        this.btnHome.setBackground(new Color(0,0,0,0));
        this.btnSupplier.setBackground(new Color(0,0,0,0));
        this.btnTransaksi.setBackground(new Color(0,0,0,0));
        this.btnBarang.setBackground(new Color(0,0,0,0));
        this.btnLogout.setBackground(new Color(0,0,0,0));
        this.btnSetting.setBackground(new Color(0,0,0,0));
       
    }
    
    private void logo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
    
    
   public void tampilPendapatan(){
        
       try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            System.out.println(date);
            String tgl = date.toString();
            lbJam.setText(tgl);
            java.sql.Statement statement = Koneksi.configDB().createStatement();
         
            String total = "SELECT SUM(total_harga) AS total FROM detail_transaksi_jual WHERE MONTH (tgl_jual) = '"+lbJam.getText().toString().substring(5,7)+"';";
            System.out.println(total);
            Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(total);
            if (rs.next()) {

             //   int jumlah = Integer.parseInt(txtTgl.getText());
                this.lbPendapatan.setText(String.valueOf(rs.getString("total")));
            }else {
                 this.lbPendapatan.setText(String.valueOf("0"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        } }
   
   public void tampilPengeluaran(){
        
       try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            System.out.println(date);
            String tgl = date.toString();
            lbJam.setText(tgl);
            java.sql.Statement statement = Koneksi.configDB().createStatement();
         
            String total = "SELECT SUM(total_harga) AS total FROM detail_transaksi_beli WHERE MONTH (tgl_beli) = '"+lbJam.getText().toString().substring(5,7)+"';";
            System.out.println(total);
            Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(total);
            if (rs.next()) {

             
                this.lbPengeluaran.setText(String.valueOf(rs.getString("total")));
            }else {
                 this.lbPengeluaran.setText(String.valueOf("0"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        } }
   
    private void terjual() {

        try {
            String sql = "select sum(jumlah) as produk, sum(total_harga) as harga, WEEK(tgl_jual) as tgl "
                    + "FROM detail_transaksi_jual group by tgl "
                    + "having tgl = WEEK(NOW())";
            Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                System.out.println(rs);
                lbTerjual.setText(rs.getString("produk") + " Terjual");
//                txt_pendapatan.setText(rs.getString("harga"));
                
            }else {
                lbTerjual.setText("0");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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
     
   private void showPieChartPenjualan(JPanel panel, String title, int tgl1, int tgl2){
        // membuat query
        String sql = "SELECT p.kategori, SUM(d.jumlah) AS total " +
                    "FROM detail_transaksi_jual AS d " +
                    "JOIN transaksi_jual AS l " +
                    "ON l.id_jual = d.id_jual " +
                    "JOIN barang AS p " +
                    "ON p.id_barang = d.id_barang " +
                    "WHERE MONTH(d.tgl_jual) = "+tgl1+" AND YEAR(d.tgl_jual) = " + tgl2 + 
                    " GROUP BY p.kategori";
        System.out.println(sql);
        
        // membuat data set untuk menampung data        
        DefaultPieDataset barDataset = new DefaultPieDataset();
        
        try{
            Connection c = (Connection) Koneksi.configDB();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery(sql);
            
            // mengembalikan data total pesanan berdasarkan jenis bahan
            while(r.next()){
                int total = r.getInt("total");
                if(total > 0){
                    barDataset.setValue(r.getString("p.kategori"), total);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        System.out.println("JUMLAH ITEM -> : " + barDataset.getItemCount());
        // membuat chart
        JFreeChart piechart = ChartFactory.createPieChart(title,barDataset, false,true,false);//explain
        piechart.setTitle(new TextTitle(title, new java.awt.Font("Dialog", 1, 12)));

        // merubah warna dari setiap data pada chart
        PiePlot piePlot =(PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(new java.awt.Color(255,255,255));
        
        // menampilkan chart ke panel
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panel.removeAll();
        panel.add(barChartPanel, BorderLayout.CENTER);
        panel.validate();
   }
    
    private int getBulan (){
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        return date.getMonth();
    }
    
    private int getTahun (){
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        return date.getYear();
    }
      
    

    
//    private int getPieDataPenjualan(int tgl1, int tgl2){
//        // membuat query
//        String sql = "SELECT p.kategori, SUM(d.jumlah) AS total " +
//                    "FROM detail_transaksi_jual AS d " +
//                    "JOIN transaksi_jual AS l " +
//                    "ON l.id_jual = d.id_jual " +
//                    "JOIN barang AS p " +
//                    "ON p.id_barang = d.id_barang " +
//                    "WHERE MONTH(d.tgl_jual) = 12 AND YEAR(d.tgl_jual) = 2022" +
//                    "GROUP BY p.kategori";
//        System.out.println(sql);
//        
//        try{
//            Connection c = (Connection) Koneksi.configDB();
//            Statement s = c.createStatement();
//            ResultSet r = s.executeQuery(sql);
//            
//            // mengembalikan data total pesanan berdasarkan jenis bahan
//            if(r.next()){
//                int total = r.getInt("total_harga");
//                c.close(); s.close(); r.close();
//                return total;
//            }
//        }catch(SQLException ex){
//            ex.printStackTrace();
//        }
//       return 0;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnTransaksi = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        tgl = new javax.swing.JLabel();
        lbJam = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        lbNama = new javax.swing.JLabel();
        lbTerjual = new javax.swing.JLabel();
        lbPengeluaran = new javax.swing.JLabel();
        lbPendapatan = new javax.swing.JLabel();
        btnGrafik = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 170, 50));

        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 170, 50));

        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });
        jPanel1.add(btnBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, 50));

        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 170, 50));

        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 170, 50));

        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        jPanel1.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 170, 40));

        tgl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 120, 20));
        tgl.setText(new dateTime().getTgl());
        tgl.setPreferredSize(new java.awt.Dimension(120,30));

        lbJam.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(lbJam, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 80, 20));

        panel2.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 300, 230));

        lbNama.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(lbNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 180, 20));

        lbTerjual.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(lbTerjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 130, 40));

        lbPengeluaran.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(lbPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 130, 30));

        lbPendapatan.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(lbPendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 120, 30));

        btnGrafik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikActionPerformed(evt);
            }
        });
        jPanel1.add(btnGrafik, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 130, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/menu utama final.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 500));

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

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
         this.setVisible(false);
        new Barang.menubarang().setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        this.setVisible(false);
        new menuSupplier.menusupplier().setVisible(true);
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
        this.setVisible(false);
        new menuTransaksi.TransaksiUtama().setVisible(true);
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int status;
        status = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin keluar?", "confirm", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
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

    private void btnGrafikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikActionPerformed
        this.setVisible(false);
        new menuUtama.grafik().setVisible(true);
    }//GEN-LAST:event_btnGrafikActionPerformed
 
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
            java.util.logging.Logger.getLogger(dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dasboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dasboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnGrafik;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbJam;
    private javax.swing.JLabel lbNama;
    private javax.swing.JLabel lbPendapatan;
    private javax.swing.JLabel lbPengeluaran;
    private javax.swing.JLabel lbTerjual;
    private javax.swing.JPanel panel2;
    private javax.swing.JLabel tgl;
    // End of variables declaration//GEN-END:variables
}
