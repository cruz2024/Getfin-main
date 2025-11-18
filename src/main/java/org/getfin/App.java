package org.getfin;

import org.getfin.vistas.VentanaPrincipal;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(VentanaPrincipal::new);

    }
}


