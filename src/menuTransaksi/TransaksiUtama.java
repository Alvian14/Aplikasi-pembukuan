/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuTransaksi;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import login.Login;

/**
 *
 * @author Administrator
 */
public class TransaksiUtama extends javax.swing.JFrame {

    /**
     * Creates new form TransaksiUtama
     */
    public TransaksiUtama() {
        initComponents();
        logo();
        
         this.lbNama.setText(Login.getNamaUser().toUpperCase());
        this.lbNama.setHorizontalAlignment(JLabel.CENTER);
        this.lbNama.setVerticalAlignment(JLabel.CENTER);
        
        this.btnTransaksiBeli.setBackground(new Color (0,0,0,0));
         this.btnTransaksiJual.setBackground(new Color (0,0,0,0));
          this.btnHome.setBackground(new Color (0,0,0,0));
         this.bntSupp.setBackground(new Color (0,0,0,0));
          this.btnBarang.setBackground(new Color (0,0,0,0));
         this.btnTransaksi.setBackground(new Color (0,0,0,0));
          this.btnSetting.setBackground(new Color (0,0,0,0));
         this.btnLogout.setBackground(new Color (0,0,0,0));
         this.btnLaporan.setBackground(new Color (0,0,0,0));
         
    }
     private void logo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnTransaksiBeli = new javax.swing.JButton();
        btnTransaksiJual = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        bntSupp = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        lbNama = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTransaksiBeli.setBorder(null);
        btnTransaksiBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiBeliActionPerformed(evt);
            }
        });
        jPanel2.add(btnTransaksiBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 230, 60));

        btnTransaksiJual.setBorder(null);
        btnTransaksiJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiJualActionPerformed(evt);
            }
        });
        jPanel2.add(btnTransaksiJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 230, 60));

        btnBarang.setBorder(null);
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });
        jPanel2.add(btnBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, 40));

        bntSupp.setBorder(null);
        bntSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSuppActionPerformed(evt);
            }
        });
        jPanel2.add(bntSupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 170, 40));

        btnHome.setBorder(null);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel2.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 170, 40));

        btnSetting.setBorder(null);
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        jPanel2.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 170, 40));

        btnLogout.setBorder(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 170, 40));

        btnTransaksi.setBorder(null);
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });
        jPanel2.add(btnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 170, 50));

        btnLaporan.setBorder(null);
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });
        jPanel2.add(btnLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 230, 50));

        lbNama.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel2.add(lbNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 160, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/6. Transaksi Utama final.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransaksiBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiBeliActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new menuTransaksiBeli().setVisible(true);
    }//GEN-LAST:event_btnTransaksiBeliActionPerformed

    private void btnTransaksiJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiJualActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new menutransaksijual().setVisible(true);
    }//GEN-LAST:event_btnTransaksiJualActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
       this.setVisible(false);
        new Barang.menubarang().setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void bntSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSuppActionPerformed
        this.setVisible(false);
        new menuSupplier.menusupplier().setVisible(true);
    }//GEN-LAST:event_bntSuppActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        new menuUtama.dasboard().setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

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

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
       
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        this.setVisible(false);
        new laporan().setVisible(true);
    }//GEN-LAST:event_btnLaporanActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSupp;
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton btnTransaksiBeli;
    private javax.swing.JButton btnTransaksiJual;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbNama;
    // End of variables declaration//GEN-END:variables
}
