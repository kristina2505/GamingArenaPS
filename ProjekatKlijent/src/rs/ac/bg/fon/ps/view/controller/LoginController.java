/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Zaposleni;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;

/**
 *
 * @author Lenovo
 */
public class LoginController {

    private final FrmLogin frmLogin;

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        addActionListener();
    }

    public void openForm() {
        frmLogin.setVisible(true);
        frmLogin.setLocationRelativeTo(null);
    }

    private void addActionListener() {
        frmLogin.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginZaposleni(e);
            }

            private void loginZaposleni(ActionEvent e) {

                resetForm();
                try {
                    String username = frmLogin.getTxtUSername().getText().trim();
                    String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());
                    validateForm(username, password);
                    

                    Zaposleni zaposleni = Communication.getInstance().zaposleniLogin(username, password);
                    JOptionPane.showMessageDialog(frmLogin, "Uspesno ste se prijavili!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    frmLogin.dispose();
                    MainCordinator.getInstance().addParam(Constants.TRENUTNO_ULOGOVANI, zaposleni);
                    MainCordinator.getInstance().openMainForm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
                if (username.isEmpty()) {
                    frmLogin.getLblUsernameError().setText("Korisnicko ime ne sme biti prazno!");

                    errorMessage += "Korisnicko ime ne sme biti prazno!\n";
                }
                if (password.isEmpty()) {
                    frmLogin.getLblPasswordError().setText("Lozinka ne sme biti prazna!");
                    errorMessage += "Lozinka ne sme biti prazna!\n";
                }
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }

            private void resetForm() {
                frmLogin.getLblPasswordError().setText("");
                frmLogin.getLblUsernameError().setText("");
            }
        });
    }

}
