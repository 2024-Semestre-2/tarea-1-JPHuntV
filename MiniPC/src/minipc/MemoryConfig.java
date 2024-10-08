/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package minipc;

import java.io.Console;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanp
 */
public class MemoryConfig extends javax.swing.JFrame {
    
    MiniPC miPc;
    /**
     * Creates new form MemoryConfig
     */
    public MemoryConfig(MiniPC miPc) {
        initComponents();
        setLocationRelativeTo(null);
        this.miPc = miPc;
        inputTamMemoria.setText(Integer.toString(miPc.getTamMemoria()));
        inputTamMemOS.setText(Integer.toString(miPc.getTamMemoriaOS()));
        
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputTamMemoria = new javax.swing.JTextField();
        inputTamMemOS = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setTitle("Configuraci[on de memoria");

        jLabel1.setText("Tamaño total de la memoria");

        jLabel2.setText("Reservado para el sistema operativo");

        inputTamMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTamMemoriaActionPerformed(evt);
            }
        });

        inputTamMemOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTamMemOSActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(inputTamMemOS)
                    .addComponent(inputTamMemoria))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputTamMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputTamMemOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputTamMemoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTamMemoriaActionPerformed
        // TODO add your handling code here:
        if (inputTamMemoria.getText().isEmpty()) {
            System.out.println("No se ha ingresado un valor");
            return;
        }
        if (!Verifier.esEntero(inputTamMemoria.getText())) {
            System.out.println("El valor ingresado no es un entero");
            return;
        }
        String tamanioMemoria = inputTamMemoria.getText();
        if(Integer.parseInt(tamanioMemoria) >= 50){
            System.out.println(tamanioMemoria);
            inputTamMemOS.setText(Integer.toString(miPc.getTamMemoriaOS()));
        }
        else{
            System.out.println("La memoria no puede ser menor a 50");
        }
        
    }//GEN-LAST:event_inputTamMemoriaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String mensajeError = "";
        if (inputTamMemoria.getText().isEmpty() || inputTamMemOS.getText().isEmpty()) {
            System.out.println("No se han ingresado valores");
            mensajeError = "No se han ingresado valores";
            JOptionPane.showMessageDialog(null, mensajeError);
            return;

        }
        if (!Verifier.esEntero(inputTamMemoria.getText()) || !Verifier.esEntero(inputTamMemOS.getText())) {
            System.out.println("Los valores ingresados no son enteros");
            mensajeError = "Los valores ingresados no son enteros";
            JOptionPane.showMessageDialog(null, mensajeError);
            return;

        }
        if (Integer.parseInt(inputTamMemOS.getText()) >= Integer.parseInt(inputTamMemoria.getText())) {
            System.out.println("El tamaño de la memoria del sistema operativo no puede ser mayor o igual al de la memoria");
            mensajeError = "El tamaño de la memoria del sistema operativo no puede ser mayor o igual al de la memoria";
            JOptionPane.showMessageDialog(null, mensajeError);
            return;

        }
        if (Integer.parseInt(inputTamMemOS.getText()) < 20) {
            System.out.println("El tamaño de la memoria del sistema operativo no puede ser menor a 20");
            mensajeError = "El tamaño de la memoria del sistema operativo no puede ser menor a 20";
            inputTamMemOS.setText("20");
            JOptionPane.showMessageDialog(null, mensajeError);
            return;            
        }
        if (Integer.parseInt(inputTamMemoria.getText()) < 50) {
            System.out.println("El tamaño de la memoria no puede ser menor a 50");
            mensajeError = "El tamaño de la memoria no puede ser menor a 50";
            inputTamMemoria.setText("50");
            JOptionPane.showMessageDialog(null, mensajeError);
            return;

        }
        
        
            miPc.setTamMemoria(Integer.parseInt(inputTamMemoria.getText()));
            miPc.setTamMemoriaOS(Integer.parseInt(inputTamMemOS.getText()));
            this.dispose();
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void inputTamMemOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTamMemOSActionPerformed
        // TODO add your handling code here:
        if (inputTamMemOS.getText().isEmpty()) {
            System.out.println("No se ha ingresado un valor");
            return;
        }
        if (!Verifier.esEntero(inputTamMemOS.getText())) {
            System.out.println("El valor ingresado no es un entero");
            return;
        }
        String tamanioMemoriaOS = inputTamMemOS.getText();
        if(Integer.parseInt(tamanioMemoriaOS) >= 20){
            System.out.println(tamanioMemoriaOS);
            inputTamMemoria.setText(Integer.toString(miPc.getTamMemoria()));
        }
        else{
            System.out.println("La memoria no puede ser menor a 20");
        }
    }//GEN-LAST:event_inputTamMemOSActionPerformed



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
            java.util.logging.Logger.getLogger(MemoryConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemoryConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemoryConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemoryConfig.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemoryConfig(new MiniPC()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField inputTamMemOS;
    private javax.swing.JTextField inputTamMemoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
