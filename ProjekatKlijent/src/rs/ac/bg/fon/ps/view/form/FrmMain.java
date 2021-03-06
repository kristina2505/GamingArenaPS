/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import rs.ac.bg.fon.ps.view.controller.ViewController;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

/**
 *
 * @author Lenovo
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        super("Gaming arena - Pocetna forma");
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTrenutnoUlogovani = new javax.swing.JLabel();
        btnOdjava = new javax.swing.JButton();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuPaket = new javax.swing.JMenu();
        jmiNoviPaket = new javax.swing.JMenuItem();
        jmiPregledPaketa = new javax.swing.JMenuItem();
        jMenuPrijava = new javax.swing.JMenu();
        jmiNovaPrijava = new javax.swing.JMenuItem();
        jmiOdjava = new javax.swing.JMenuItem();
        jmKlijent = new javax.swing.JMenu();
        jmiUnosKlijenta = new javax.swing.JMenuItem();
        jmiPregledKlijenata = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTrenutnoUlogovani.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btnOdjava.setText("Logout");

        jMenuPaket.setText(" Paket");

        jmiNoviPaket.setText("Novi paket");
        jmiNoviPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNoviPaketActionPerformed(evt);
            }
        });
        jMenuPaket.add(jmiNoviPaket);

        jmiPregledPaketa.setText("Pregled paketa");
        jmiPregledPaketa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPregledPaketaActionPerformed(evt);
            }
        });
        jMenuPaket.add(jmiPregledPaketa);

        jMenuBarMain.add(jMenuPaket);

        jMenuPrijava.setText("Prijava");

        jmiNovaPrijava.setText("Nova prijava");
        jMenuPrijava.add(jmiNovaPrijava);

        jmiOdjava.setText("Odjava");
        jMenuPrijava.add(jmiOdjava);

        jMenuBarMain.add(jMenuPrijava);

        jmKlijent.setText("Klijent");

        jmiUnosKlijenta.setText("Novi klijent");
        jmKlijent.add(jmiUnosKlijenta);

        jmiPregledKlijenata.setText("Pregled kllijenata");
        jmKlijent.add(jmiPregledKlijenata);

        jMenuBarMain.add(jmKlijent);

        setJMenuBar(jMenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTrenutnoUlogovani, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOdjava, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOdjava, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTrenutnoUlogovani, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiNoviPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNoviPaketActionPerformed
        // TODO add your handling code here:
//        JDialog frmPaket=new FrmPaket(this, true, FormMode.FORMA_DODAVANJE);
//        frmPaket.setVisible(true);
    }//GEN-LAST:event_jmiNoviPaketActionPerformed

    private void jmiPregledPaketaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPregledPaketaActionPerformed
        //ViewController.getInstance().pokreniFrmPregledPaketa();
    }//GEN-LAST:event_jmiPregledPaketaActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOdjava;
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenu jMenuPaket;
    private javax.swing.JMenu jMenuPrijava;
    private javax.swing.JMenu jmKlijent;
    private javax.swing.JMenuItem jmiNovaPrijava;
    private javax.swing.JMenuItem jmiNoviPaket;
    private javax.swing.JMenuItem jmiOdjava;
    private javax.swing.JMenuItem jmiPregledKlijenata;
    private javax.swing.JMenuItem jmiPregledPaketa;
    private javax.swing.JMenuItem jmiUnosKlijenta;
    private javax.swing.JLabel lblTrenutnoUlogovani;
    // End of variables declaration//GEN-END:variables

    public void jmiNoviPaketAddActionListener(ActionListener actionListener) {
        jmiNoviPaket.addActionListener(actionListener);
    }

    public void jmiPregledPaketaAddActionListener(ActionListener actionListener) {
        jmiPregledPaketa.addActionListener(actionListener);
    }

    public JLabel getLblTrenutnoUlogovani() {
        return lblTrenutnoUlogovani;
    }

    public JMenuItem getJmiNovaPrijava() {
        return jmiNovaPrijava;
    }

    public JMenu getJmKlijent() {
        return jmKlijent;
    }

    public JMenuItem getJmiUnosKlijenta() {
        return jmiUnosKlijenta;
    }

    public JMenuItem getJmiPregledKlijenata() {
        return jmiPregledKlijenata;
    }

    public JMenuItem getJmiOdjava() {
        return jmiOdjava;
    }

    public javax.swing.JButton getBtnOdjava() {
        return btnOdjava;
    }
    
    
    
    
}
