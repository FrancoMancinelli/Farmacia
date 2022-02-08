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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginView {

	private JFrame frame;
	private JLabel lblTitulo;
	private JButton btnLogin;
	private JPasswordField pfPIN;
	private UsuarioDAO usuarioDAO;


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
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Farmacia");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		lblTitulo.setBounds(135, 36, 165, 62);
		frame.getContentPane().add(lblTitulo);
		
		btnLogin = new JButton("Entrar");
		btnLogin.setBounds(182, 192, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		pfPIN = new JPasswordField();
		pfPIN.setBounds(161, 125, 139, 20);
		frame.getContentPane().add(pfPIN);
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
