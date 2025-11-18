package org.getfin.vistas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final Barraopciones barraopciones = new Barraopciones();
    private final JPanel contenido = new JPanel(new BorderLayout());

    public VentanaPrincipal() {
        initComponents();
        configurarMenu();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1200, 700);
        setLocationRelativeTo(null);

        /** Mi barra superior */
        JPanel barraSuperior = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(41, 82, 128));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        barraSuperior.setLayout(new BorderLayout());
        barraSuperior.setPreferredSize(new Dimension(1200, 60));
        barraSuperior.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        JLabel lblTitulo = new JLabel("GETFIN");
        lblTitulo.setFont(new Font("Open Sans", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        barraSuperior.add(lblTitulo, BorderLayout.WEST);

        add(barraSuperior, BorderLayout.NORTH);

        /** Mi menú lateral */
        barraopciones.setPreferredSize(new Dimension(60, 100));
        add(barraopciones, BorderLayout.WEST);

        /** Mi panel central dinámico */
        contenido.setBackground(Color.WHITE);
        add(contenido, BorderLayout.CENTER);

        setVisible(true);
    }

    private void configurarMenu() {
        barraopciones.setOpcionSeleccionadaListener(opcion -> {
            switch (opcion) {
                case "Visita" -> cargarPanel(new VisitaVista());
                case "Inventario" -> cargarPanel(new ProductoVista());
                case "Cultivo" -> cargarPanel(new CultivoVista());
                case "Salir" -> System.exit(0);
                default -> contenido.removeAll();
            }
        });
    }

    private void cargarPanel(JPanel panel) {
        contenido.removeAll();
        contenido.add(panel, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }

}