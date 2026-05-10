package org.getfin.vistas;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class DashboardVista extends JPanel {

    public DashboardVista() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel principal con padding
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de tarjetas (cuadros pequeños)
        JPanel panelTarjetas = new JPanel(new GridLayout(1, 2, 15, 0));
        panelTarjetas.setBackground(Color.WHITE);
        panelTarjetas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tarjeta 1: Total Ingresos (Título arriba, valor debajo)
        JPanel tarjetaIngresos = crearTarjetaIngresos();
        panelTarjetas.add(tarjetaIngresos);

        // Tarjeta 2: Total Egresos (Título arriba, valor debajo)
        JPanel tarjetaEgresos = crearTarjetaEgresos();
        panelTarjetas.add(tarjetaEgresos);

        // Agregar al panel principal
        panelPrincipal.add(panelTarjetas, BorderLayout.NORTH);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    // Método para crear tarjeta de INGRESOS (Título arriba, valor debajo)
    private JPanel crearTarjetaIngresos() {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Panel para la información (vertical)
        JPanel panelInfo = new JPanel(new GridLayout(2, 1, 0, 8));
        panelInfo.setBackground(Color.WHITE);

        // Título (ARRIBA)
        JLabel lblTitulo = new JLabel("Total Ingresos");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitulo.setForeground(Color.GRAY);

        // Valor (DEBAJO)
        JLabel lblValor = new JLabel("$ 1687,000");
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblValor.setForeground(new Color(34, 197, 94)); // Verde

        panelInfo.add(lblTitulo);
        panelInfo.add(lblValor);

        // Icono a la izquierda
        JLabel lblIcono = new JLabel("💰");
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        tarjeta.add(lblIcono, BorderLayout.WEST);
        tarjeta.add(panelInfo, BorderLayout.CENTER);

        return tarjeta;
    }

    // Método para crear tarjeta de EGRESOS (Título arriba, valor debajo)
    private JPanel crearTarjetaEgresos() {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Panel para la información (vertical)
        JPanel panelInfo = new JPanel(new GridLayout(2, 1, 0, 8));
        panelInfo.setBackground(Color.WHITE);

        // Título (ARRIBA)
        JLabel lblTitulo = new JLabel("Total Egresos");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitulo.setForeground(Color.GRAY);

        // Valor (DEBAJO)
        JLabel lblValor = new JLabel("$ 2.80");
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblValor.setForeground(new Color(239, 68, 68)); // Rojo

        panelInfo.add(lblTitulo);
        panelInfo.add(lblValor);

        // Icono a la izquierda
        JLabel lblIcono = new JLabel("📉");
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 28));

        tarjeta.add(lblIcono, BorderLayout.WEST);
        tarjeta.add(panelInfo, BorderLayout.CENTER);

        return tarjeta;
    }
}