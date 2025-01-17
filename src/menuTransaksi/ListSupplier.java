/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuTransaksi;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import login.Koneksi;

/**
 *
 * @author Administrator
 */
public class ListSupplier extends javax.swing.JFrame {
DefaultTableModel tabModel;
        ResultSet RsList=null;
    /**
     * Creates new form ListSupplier
     */
    public ListSupplier() {
        initComponents();
        loadData();
        loadData1();
        
        
        this.btnBatal.setBackground(new Color (0,0,0,0));
         this.btnPilih.setBackground(new Color (0,0,0,0));
          this.txtPencarian.setBackground(new Color (0,0,0,0));
    }
    
    private void loadData(){
         DefaultTableModel model = new DefaultTableModel ();
        model.addColumn("Id Supplier");
        model.addColumn("Nama Supplier");
     
        
        
             
         try  {
            String sql = "select * from supplier " ;
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.Statement pst=conn.createStatement();
            java.sql.ResultSet res=pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[] {res.getString(1), res.getString(2)
                });
            }
            tblDatasupp.setModel(model);
        }catch (Exception e){
            
        } 
    }
    
     private void loadData1(){
         DefaultTableModel model = new DefaultTableModel ();
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Beli");
         try  {
             
            String sql = "select * from barang " ;
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.Statement pst=conn.createStatement();
            java.sql.ResultSet res=pst.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[] {res.getString(1), res.getString(2),res.getString(5) });
            }
            tblBarang.setModel(model);
        }catch (Exception e){
            
        } 
    }
        
        
        
    
      
    
      
        private void cariData(String key){
         try{
            Object[] judul_kolom = {"Id Barang", "Nama Barang","Stok","Harga Beli"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            tblBarang.setModel(tabModel);
            
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            Statement pst=conn.createStatement();
            tabModel.getDataVector().removeAllElements();
            
            RsList=pst.executeQuery("SELECT * from barang WHERE id_barang LIKE '%"+key+"%' OR nama_barang LIKE '%"+key+"%' OR stok LIKE '%"+key+"%' OR harga_beli LIKE '%"+key+"%'");  
            while(RsList.next()){
                Object[] data={
                    RsList.getString("id_barang"),
                    RsList.getString("nama_barang"),
                    RsList.getString("harga_beli"),
                };
               tabModel.addRow(data);
            }                
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
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
        btnPilih = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        txtPencarian = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatasupp = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPilih.setBorder(null);
        btnPilih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPilihMouseClicked(evt);
            }
        });
        jPanel1.add(btnPilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 30));

        btnBatal.setBorder(null);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 70, 30));

        txtPencarian.setBorder(null);
        txtPencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPencarianActionPerformed(evt);
            }
        });
        txtPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPencarianKeyReleased(evt);
            }
        });
        jPanel1.add(txtPencarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 16, 100, -1));

        tblDatasupp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Supplier", "Nama Supplier"
            }
        ));
        tblDatasupp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatasuppMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatasupp);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 400, 80));

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id Barang", "Nama Barang", "Harga"
            }
        ));
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBarang);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 400, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/figma/List supplier (1).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void txtPencarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPencarianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPencarianActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void tblDatasuppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatasuppMouseClicked
       
    }//GEN-LAST:event_tblDatasuppMouseClicked

    private void btnPilihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPilihMouseClicked
        int baris = tblDatasupp.getSelectedRow();
        int baris1 = tblBarang.getSelectedRow();
        String id_supplier =tblDatasupp.getValueAt(baris,0).toString();
        menuTransaksiBeli.txtIdsupp.setText(id_supplier);
        
        
        String id_Barang =tblBarang.getValueAt(baris1,0).toString();
        menuTransaksiBeli.txtIdbarang.setText(id_Barang);
        String harga =tblBarang.getValueAt(baris1,2).toString();
        menuTransaksiBeli.txtHarga.setText(harga);
       
       
        this.dispose();
    }//GEN-LAST:event_btnPilihMouseClicked

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
       
    }//GEN-LAST:event_tblBarangMouseClicked

    private void txtPencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyReleased
        String key = txtPencarian.getText();
        System.out.println(key);
        if(key!=""){
            cariData(key);
        }else{
            loadData1();
        }
                   
    }//GEN-LAST:event_txtPencarianKeyReleased

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
            java.util.logging.Logger.getLogger(ListSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListSupplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnPilih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTable tblDatasupp;
    private javax.swing.JTextField txtPencarian;
    // End of variables declaration//GEN-END:variables
}
