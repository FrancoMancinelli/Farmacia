package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.MedicamentoDAO;
import models.Medicamento;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FarmaciaView {

	private JFrame frame;
	private JTextField tfNombreMed;
	private JTextField tfFechaIncor;
	private JTextField tfTipo;
	private JTextField tfPrecio;
	private JTextField tfCantidad;
	private JTextField tfPpioActivo;
	private JButton btnPedido;
	private JButton btnVenta;
	private JButton btnActualizar;
	private JButton btnVentaDia;
	private JButton btnSalir;
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private int pagina;
	private JFrame frameLogin;
	private ArrayList<Medicamento> medicamentos;
	private MedicamentoDAO medicamentoDAO;
	private JButton btnGuardarAct;
	private JButton btnCancelarAct;

	/**
	 * Create the application.
	 */
	public FarmaciaView(JFrame loginView, int pagina) {
		this.frameLogin = loginView;
		this.pagina = pagina;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setUIComponents();
		setListeners();
		setActualizarOFF();
		frame.setVisible(true);
		this.medicamentoDAO = new MedicamentoDAO();
		this.medicamentos = medicamentoDAO.getAll();
		printPagina();
	}
	
	private void setUIComponents() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfNombreMed = new JTextField();
		tfNombreMed.setBounds(42, 97, 86, 20);
		frame.getContentPane().add(tfNombreMed);
		tfNombreMed.setColumns(10);
		
		tfFechaIncor = new JTextField();
		tfFechaIncor.setBounds(260, 97, 86, 20);
		frame.getContentPane().add(tfFechaIncor);
		tfFechaIncor.setColumns(10);
		
		tfTipo = new JTextField();
		tfTipo.setBounds(42, 150, 86, 20);
		frame.getContentPane().add(tfTipo);
		tfTipo.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setBounds(260, 150, 86, 20);
		frame.getContentPane().add(tfPrecio);
		tfPrecio.setColumns(10);
		
		tfCantidad = new JTextField();
		tfCantidad.setBounds(154, 150, 86, 20);
		frame.getContentPane().add(tfCantidad);
		tfCantidad.setColumns(10);
		
		tfPpioActivo = new JTextField();
		tfPpioActivo.setBounds(154, 97, 86, 20);
		frame.getContentPane().add(tfPpioActivo);
		tfPpioActivo.setColumns(10);
		
		btnPedido = new JButton("Pedido");
		btnPedido.setBounds(42, 246, 89, 23);
		frame.getContentPane().add(btnPedido);
		
		btnVenta = new JButton("Venta");
		btnVenta.setBounds(164, 246, 89, 23);
		frame.getContentPane().add(btnVenta);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(305, 246, 89, 23);
		frame.getContentPane().add(btnActualizar);
		
		btnVentaDia = new JButton("Ventas del Dia");
		btnVentaDia.setBounds(135, 285, 133, 23);
		frame.getContentPane().add(btnVentaDia);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnSalir);
		
		btnAnterior = new JButton("<");
		btnAnterior.setBounds(10, 192, 89, 23);
		frame.getContentPane().add(btnAnterior);
		
		btnSiguiente = new JButton(">");
		btnSiguiente.setBounds(335, 192, 89, 23);
		frame.getContentPane().add(btnSiguiente);
		
		btnGuardarAct = new JButton("Guardar");
		btnGuardarAct.setBounds(305, 285, 89, 23);
		frame.getContentPane().add(btnGuardarAct);
		
		btnCancelarAct = new JButton("Cancelar");
		btnCancelarAct.setBounds(36, 280, 89, 23);
		frame.getContentPane().add(btnCancelarAct);
		
	}
	
	private void setListeners() {
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(btnSalir,
						"¿Estás seguro de que deseas salir?");
				if (confirmar == 0) { // Quiere salir
					frameLogin.setVisible(true);
					frame.dispose();
				}
			}
		});
		
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printAnterior();
			}
		});
		
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printSiguiente();
			}
		});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setActualizarON();		
			}
		});
		
		btnCancelarAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setActualizarOFF();
				printPagina();
			}
		});
		
		btnGuardarAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(btnGuardarAct,
						"¿Estás seguro de que deseas guardar los cambios?");
				if (confirmar == 0) { // Quiere guardar
					guardarCambios();
					setActualizarOFF();
				}
			}
		});
	}
	
	private void printPagina() {
		if(medicamentos.size() > 0) {
			Medicamento m = medicamentos.get(pagina);
			tfNombreMed.setText(m.getNombre());
			tfFechaIncor.setText(m.getFechaIncorp());
			tfTipo.setText(m.getTipo());
			tfPrecio.setText(String.valueOf(m.getPrecio()));
			tfCantidad.setText(String.valueOf(m.getCantidad()));
			tfPpioActivo.setText(m.getPpioActivo());
		}
	}
	
	private void printSiguiente() {
		pagina++;
		if(pagina == medicamentos.size()) {
			pagina = 0;
		}
		printPagina();
	}
	
	private void printAnterior() {
		pagina--;
		if(pagina < 0) {
			pagina = medicamentos.size() - 1;
		}
		printPagina();
	}
	
	private void setActualizarOFF() {
		tfNombreMed.setEditable(false);
		tfFechaIncor.setEditable(false);
		tfTipo.setEditable(false);
		tfPrecio.setEditable(false);
		tfCantidad.setEditable(false);
		tfPpioActivo.setEditable(false);
		btnSiguiente.setVisible(true);
		btnAnterior.setVisible(true);
		btnActualizar.setVisible(true);
		btnPedido.setVisible(true);
		btnVenta.setVisible(true);
		btnVentaDia.setVisible(true);
		btnCancelarAct.setVisible(false);
		btnGuardarAct.setVisible(false);
	}
	
	private void setActualizarON() {
		tfNombreMed.setEditable(true);
		tfFechaIncor.setEditable(true);
		tfTipo.setEditable(true);
		tfPrecio.setEditable(true);
		tfPpioActivo.setEditable(true);
		btnSiguiente.setVisible(false);
		btnAnterior.setVisible(false);
		btnActualizar.setVisible(false);
		btnPedido.setVisible(false);
		btnVenta.setVisible(false);
		btnVentaDia.setVisible(false);
		btnCancelarAct.setVisible(true);
		btnGuardarAct.setVisible(true);
	}
	
	private void guardarCambios() {
		Medicamento m = medicamentos.get(pagina);
		m.setNombre(tfNombreMed.getText());
		m.setFechaIncorp(tfFechaIncor.getText());
		m.setTipo(tfTipo.getText());
		m.setPrecio(Double.parseDouble(tfPrecio.getText()));
		m.setCantidad(Integer.parseInt(tfCantidad.getText()));
		m.setPpioActivo(tfPpioActivo.getText());
		medicamentoDAO.updateMedicamento(m);
	}
} //CIERRE CLASE
