package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import dao.UsuarioDAO;
import models.Usuario;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class LoginView {

	private JFrame frame;
	private JLabel lblTitulo;
	private JButton btnLogin;
	private JPasswordField pfPIN;
	private UsuarioDAO usuarioDAO;
	private JLabel lblFondo;
	private JLabel lblPin;


	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
		this.usuarioDAO = new UsuarioDAO();
		frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setUIComponents();
		setListeners();
	}
	
	private void setUIComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 468, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Farmacia");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
		lblTitulo.setBounds(0, 31, 452, 78);
		frame.getContentPane().add(lblTitulo);
		
		btnLogin = new JButton("Entrar");
		btnLogin.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(150, 223, 150, 40);
		frame.getContentPane().add(btnLogin);
		
		pfPIN = new JPasswordField();
		pfPIN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pfPIN.setHorizontalAlignment(SwingConstants.CENTER);
		pfPIN.setBounds(150, 151, 150, 40);
		frame.getContentPane().add(pfPIN);
				
		lblPin = new JLabel("PIN:");
		lblPin.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblPin.setBounds(150, 123, 152, 30);
		frame.getContentPane().add(lblPin);

		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 452, 417);
		frame.getContentPane().add(lblFondo);
		lblFondo.setIcon(new ImageIcon(LoginView.class.getResource("/img/fondologin.png")));

	}
	
	private void setListeners() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analizarDatosAcceso();
			}
		});
		
		pfPIN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				 analizarDatosAcceso();
				}
		});
	}
	
	/**
	 * Verifica los datos introducidos para acceder a la farmacia
	 */
	private void analizarDatosAcceso() {
		String password = new String(pfPIN.getPassword());
		Usuario user = new Usuario(0, password);
		if(password.length() < 4 || password.length() > 4) { //Si el PIN NO mide 4 digitos
			JOptionPane.showMessageDialog(btnLogin, "ERR0R 140 - El PIN debe ser de 4 digitos");
		} else { //Si el PIN SÍ mide 4 digitos compruebo que exista...
			boolean loginCorrecto = usuarioDAO.login(user);
			if(loginCorrecto) { //Si se ha encontrado al usuario en la base de datos...
				JOptionPane.showMessageDialog(btnLogin, "Login Correcto. Bienvenid@");
				new FarmaciaView(frame, 0);
				frame.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(btnLogin, "                       ERR0R 203 - PIN Invalido\nAsegurate de haber introducido el PIN correctamente");
			}
		}
	}
} //CIERRE CLASE
