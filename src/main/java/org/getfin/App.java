package org.getfin;

import org.getfin.vistas.Login;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

       //SwingUtilities.invokeLater(VentanaPrincipal::new);

        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });

    }
}


