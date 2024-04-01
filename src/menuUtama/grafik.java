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
import java.sql.Statement;
import javax.swing.Timer;
import login.Koneksi;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Administrator
 */
public class grafik extends javax.swing.JFrame {

    /**
     * Creates new form grafik
     */
    public grafik() {
        initComponents();
        logo();
        tampilJam();
        tampilGrafik();
        tampilGrafik1();
        
        this.btnBack.setBackground(new Color (0,0,0,0));
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
            
            lblJam.setText(jam + ":" + menit + ":" + detik + "");
            
        }
        
    };
        new Timer (1000, taskPerformer).start();
    }
    
    private double getPemasukan(int bulan){
        try{
            Connection c = (Connection)Koneksi.configDB();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT SUM(total_harga) FROM detail_transaksi_jual WHERE MONTH(tgl_jual) = " + bulan);
            
            if(r.next()){
               return r.getDouble(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

           
        public void tampilGrafik() {
        
//        double b12 = this.getPendapatan(12);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(this.getPemasukan(1), "Contribution Amount", "JANUARI");
        dataset.setValue(this.getPemasukan(2), "Contribution Amount", "FEBRUARI");
        dataset.setValue(this.getPemasukan(3), "Contribution Amount", "MARET");
        dataset.setValue(this.getPemasukan(4), "Contribution Amount", "APRIL");
        dataset.setValue(this.getPemasukan(5), "Contribution Amount", "MEI"); 
        dataset.setValue(this.getPemasukan(6), "Contribution Amount", "JUNI");
        dataset.setValue(this.getPemasukan(7), "Contribution Amount", "JULI");
        dataset.setValue(this.getPemasukan(8), "Contribution Amount", "AGUSTUS");
        dataset.setValue(this.getPemasukan(9), "Contribution Amount", "SEPTEMBER");
        dataset.setValue(this.getPemasukan(10), "Contribution Amount", "OKTOBER");
        dataset.setValue(this.getPemasukan(11), "Contribution Amount", "NOVEMBER");
        dataset.setValue(this.getPemasukan(12), "Contribution Amount", "DESEMBER");
        
        JFreeChart barChart = ChartFactory.createBarChart3D("Grafik Penjualan", "Bulan", "Tingkat ", dataset, PlotOrientation.HORIZONTAL, false, true, false);
        CategoryPlot p = barChart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.RED);
        
        ChartPanel barPanel = new ChartPanel(barChart);
        plPendapatan.removeAll();
        plPendapatan.add(barPanel,BorderLayout.CENTER);
        plPendapatan.validate();
        
      
    }
   
    private double getPengeluaran(int bulan){
        try{
            Connection c = (Connection)Koneksi.configDB();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT SUM(total_harga) FROM detail_transaksi_beli WHERE MONTH(tgl_beli) = " + bulan);
            
            if(r.next()){
               return r.getDouble(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
        public void tampilGrafik1() {
        
         // desain grafik
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(this.getPengeluaran(1), "Contribution Amount", "JANUARI");
        dataset.setValue(this.getPengeluaran(2), "Contribution Amount", "FEBRUARI");
        dataset.setValue(this.getPengeluaran(3), "Contribution Amount", "MARET");
        dataset.setValue(this.getPengeluaran(4), "Contribution Amount", "APRIL");
        dataset.setValue(this.getPengeluaran(5), "Contribution Amount", "MEI"); 
        dataset.setValue(this.getPengeluaran(6), "Contribution Amount", "JUNI");
        dataset.setValue(this.getPengeluaran(7), "Contribution Amount", "JULI");
        dataset.setValue(this.getPengeluaran(8), "Contribution Amount", "AGUSTUS");
        dataset.setValue(this.getPengeluaran(9), "Contribution Amount", "SEPTEMBER");
        dataset.setValue(this.getPengeluaran(10), "Contribution Amount", "OKTOBER");
        dataset.setValue(this.getPengeluaran(11), "Contribution Amount", "NOVEMBER");
        dataset.setValue(this.getPengeluaran(12), "Contribution Amount", "DESEMBER");
        
        JFreeChart barChart = ChartFactory.createBarChart3D("Grafik Pembelian", "Bulan", "Tingkat ", dataset, PlotOrientation.HORIZONTAL, false, true, false);
        CategoryPlot p = barChart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.RED);
        
        ChartPanel barPanel = new ChartPanel(barChart);
        plPengeluaran.removeAll();
        plPengeluaran.add(barPanel,BorderLayout.CENTER);
        plPengeluaran.validate();
        
       
        
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
        btnBack = new javax.swing.JButton();
        tgl = new javax.swing.JLabel();
        lblJam = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        plPendapatan = new javax.swing.JPanel();
        plPengeluaran = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 40, 30));

        tgl.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 110, 20));
        tgl.setText(new dateTime().getTgl());
        tgl.setPreferredSize(new java.awt.Dimension(120,30));

        lblJam.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jPanel1.add(lblJam, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 80, 20));

        plPendapatan.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Pendapatan", plPendapatan);

        plPengeluaran.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Pengeluaran", plPengeluaran);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 700, 420));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/grafika.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

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

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
      this.setVisible(false);
      new dasboard().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(grafik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(grafik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(grafik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(grafik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new grafik().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblJam;
    private javax.swing.JPanel plPendapatan;
    private javax.swing.JPanel plPengeluaran;
    private javax.swing.JLabel tgl;
    // End of variables declaration//GEN-END:variables
}
