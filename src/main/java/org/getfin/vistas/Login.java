package org.getfin.vistas;

import org.getfin.dao.UsuarioDAO;
import org.getfin.modelos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Login extends JFrame {

    private UsuarioDAO usuarioDAO;

    public Login() {
        // Inicializar DAO
        usuarioDAO = new UsuarioDAO();

        /** Configuración de la ventana */
        setTitle("Login");
        setSize(670, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        /** Panel exterior con márgenes reducidos */
        JPanel panelExterior = new JPanel(new BorderLayout());
        panelExterior.setBackground(Color.WHITE);
        panelExterior.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        /** Panel principal */
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(new RoundedBorder(new Color(0, 153, 76), 15, 2));

        /** Panel de la imagen */
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fondo = new ImageIcon(getClass().getResource("/Finnn.jpeg"));
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelImagen.setBackground(Color.LIGHT_GRAY);
        panelImagen.setBorder(null);

        /** Panel del login */
        JPanel contenedorLogin = new JPanel(new BorderLayout());
        contenedorLogin.setBackground(Color.WHITE);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);

        contenedorLogin.add(loginPanel, BorderLayout.CENTER);
        contenedorLogin.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /** Titulo de URACCAN */
        JLabel lblTitulo = new JLabel("URACCAN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(0, 102, 51));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        loginPanel.add(lblTitulo, gbc);

        gbc.insets = new Insets(10, 15, 10, 15);

        /** Campo de usuario */
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(lblUsuario, gbc);

        JTextField txtUsuario = new JTextField(15);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
        txtUsuario.setPreferredSize(new Dimension(180, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(txtUsuario, gbc);

        /** Campo de contraseña */
        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(lblContraseña, gbc);

        JPasswordField txtContraseña = new JPasswordField(15);
        txtContraseña.setFont(new Font("Arial", Font.PLAIN, 13));
        txtContraseña.setPreferredSize(new Dimension(180, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(txtContraseña, gbc);

        /** Botón iniciar sesión */
        JButton btnLogin = new JButton("INICIAR SESIÓN");
        btnLogin.setBackground(new Color(0, 153, 76));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 13));
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(200, 35));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 15, 20);
        loginPanel.add(btnLogin, gbc);

        /** Panel de enlaces */
        JPanel panelEnlaces = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelEnlaces.setBackground(Color.WHITE);

        JLabel lblOlvido = new JLabel("¿Olvidó su contraseña?");
        lblOlvido.setForeground(new Color(0, 102, 204));
        lblOlvido.setFont(new Font("Arial", Font.PLAIN, 11));
        lblOlvido.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblRegistrar = new JLabel("Registrarse");
        lblRegistrar.setForeground(new Color(0, 153, 76));
        lblRegistrar.setFont(new Font("Arial", Font.BOLD, 11));
        lblRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelEnlaces.add(lblOlvido);
        panelEnlaces.add(lblRegistrar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 20, 25, 20);
        loginPanel.add(panelEnlaces, gbc);

        /** Layout principal */
        GridBagConstraints gbcMain = new GridBagConstraints();

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 2.10;
        gbcMain.weighty = 1.0;
        gbcMain.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(panelImagen, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.gridy = 0;
        gbcMain.weightx = 0.2;
        gbcMain.weighty = 1.0;
        gbcMain.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(contenedorLogin, gbcMain);

        panelExterior.add(panelPrincipal, BorderLayout.CENTER);
        add(panelExterior);

        /** Evento del botón INICIAR SESIÓN */
        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String contraseña = new String(txtContraseña.getPassword()).trim();

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor complete todos los campos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar credenciales
            Usuario user = usuarioDAO.validarCredenciales(usuario, contraseña);

            if (user != null) {
                JOptionPane.showMessageDialog(this,
                        "✅ Bienvenido " + user.getNombre(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Cerrar login y abrir dashboard
                dispose();
                SwingUtilities.invokeLater(() -> {
                    new VentanaPrincipal().setVisible(true);
                });

            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Usuario o contraseña incorrectos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        /** Evento para registrarse - Abre ventana de registro */
        lblRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirVentanaRegistro();
            }
        });

        lblOlvido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(Login.this,
                        "Contacte al administrador para recuperar su contraseña",
                        "Recuperar contraseña",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /** Ventana para registrar nuevos usuarios */
    private void abrirVentanaRegistro() {
        JDialog dialog = new JDialog(this, "Registro de Usuario", true);
        dialog.setSize(450, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());

        JPanel panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBackground(Color.WHITE);
        panelRegistro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTituloRegistro = new JLabel("REGISTRAR NUEVO USUARIO");
        lblTituloRegistro.setFont(new Font("Arial", Font.BOLD, 18));
        lblTituloRegistro.setForeground(new Color(0, 102, 51));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelRegistro.add(lblTituloRegistro, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 10, 5, 10);

        // Nombre completo
        JLabel lblNombre = new JLabel("Nombre completo:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRegistro.add(lblNombre, gbc);

        JTextField txtNombre = new JTextField(20);
        txtNombre.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelRegistro.add(txtNombre, gbc);

        // Usuario
        JLabel lblUsuarioReg = new JLabel("Usuario:");
        lblUsuarioReg.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRegistro.add(lblUsuarioReg, gbc);

        JTextField txtUsuarioReg = new JTextField(20);
        txtUsuarioReg.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelRegistro.add(txtUsuarioReg, gbc);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRegistro.add(lblEmail, gbc);

        JTextField txtEmail = new JTextField(20);
        txtEmail.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelRegistro.add(txtEmail, gbc);

        // Contraseña
        JLabel lblPassReg = new JLabel("Contraseña:");
        lblPassReg.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelRegistro.add(lblPassReg, gbc);

        JPasswordField txtPassReg = new JPasswordField(20);
        txtPassReg.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelRegistro.add(txtPassReg, gbc);

        // Confirmar contraseña
        JLabel lblConfirmPass = new JLabel("Confirmar:");
        lblConfirmPass.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelRegistro.add(lblConfirmPass, gbc);

        JPasswordField txtConfirmPass = new JPasswordField(20);
        txtConfirmPass.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelRegistro.add(txtConfirmPass, gbc);

        // Botón registrar
        JButton btnRegistrar = new JButton("REGISTRARSE");
        btnRegistrar.setBackground(new Color(0, 153, 76));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panelRegistro.add(btnRegistrar, gbc);

        // Botón cancelar
        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 7;
        panelRegistro.add(btnCancelar, gbc);

        // Evento REGISTRAR
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String usuario = txtUsuarioReg.getText().trim();
            String email = txtEmail.getText().trim();
            String contraseña = new String(txtPassReg.getPassword()).trim();
            String confirmar = new String(txtConfirmPass.getPassword()).trim();

            // Validaciones
            if (nombre.isEmpty() || usuario.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!contraseña.equals(confirmar)) {
                JOptionPane.showMessageDialog(dialog, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setUsuario(usuario);
            nuevoUsuario.setContraseña(contraseña);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setRol("usuario");

            // Guardar en base de datos
            if (usuarioDAO.registrarUsuario(nuevoUsuario)) {
                JOptionPane.showMessageDialog(dialog,
                        "✅ Usuario registrado exitosamente!\nAhora puede iniciar sesión",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog,
                        "❌ Error al registrar usuario. El usuario o email ya existe",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(panelRegistro);
        dialog.setVisible(true);
    }

    /** Borde redondeado */
    static class RoundedBorder implements Border {
        private Color color;
        private int radius;
        private int thickness;

        public RoundedBorder(Color color, int radius, int thickness) {
            this.color = color;
            this.radius = radius;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x + thickness/2, y + thickness/2,
                    width - thickness, height - thickness,
                    radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(2, 2, 2, 2);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }

}