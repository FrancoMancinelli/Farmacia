package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.MedicamentoDAO;
import dao.VentasDAO;
import models.Medicamento;
import models.Ventas;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private VentasDAO ventasDAO;
	private JButton btnGuardarAct;
	private JButton btnCancelarAct;
	private JTextField tfPedidoCant;
	private JButton btnConfirPedido;
	private JButton btnIncrementarPedido;
	private JButton btnDisminuirPedido;
	private int modo;

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
		setPanelBase();
		frame.setVisible(true);
		this.ventasDAO = new VentasDAO();
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
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVenta.setBounds(164, 246, 89, 23);
		frame.getContentPane().add(btnVenta);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(305, 246, 89, 23);
		frame.getContentPane().add(btnActualizar);
		
		btnVentaDia = new JButton("Ventas del Dia");
		btnVentaDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		tfPedidoCant = new JTextField();
		tfPedidoCant.setEditable(false);
		tfPedidoCant.setText("0");
		tfPedidoCant.setBounds(167, 193, 86, 20);
		frame.getContentPane().add(tfPedidoCant);
		tfPedidoCant.setColumns(10);
		
		btnConfirPedido = new JButton("Confirmar");
		btnConfirPedido.setBounds(240, 267, 89, 23);
		frame.getContentPane().add(btnConfirPedido);
		
		btnIncrementarPedido = new JButton("+");
		btnIncrementarPedido.setBounds(109, 192, 54, 23);
		frame.getContentPane().add(btnIncrementarPedido);
		
		btnDisminuirPedido = new JButton("-");
		btnDisminuirPedido.setBounds(260, 192, 65, 23);
		frame.getContentPane().add(btnDisminuirPedido);
		
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
				setPanelBase();
				printPagina();
			}
		});
		
		btnGuardarAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(btnGuardarAct,
						"¿Estás seguro de que deseas guardar los cambios?");
				if (confirmar == 0) { // Quiere guardar
					guardarCambios();
				}
			}
		});
		
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modo = 1;
				setPedidoON();
			}
		});
		
		btnConfirPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modo == 1) {
					int confirmar = JOptionPane.showConfirmDialog(btnGuardarAct,
							"¿Estás seguro de que deseas confirmar el pedido?");
					if (confirmar == 0) { // Quiere confirmar
						confirmarPedido();
					}
				} else if (modo == 2) {
					int confirmar = JOptionPane.showConfirmDialog(btnGuardarAct,
							"¿Estás seguro de que deseas confirmar la venta?");
					if (confirmar == 0) { // Quiere confirmar
						confirmarVenta();
					}
				}
			}
		});
		
		btnIncrementarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = Integer.parseInt(tfPedidoCant.getText());
				if(modo == 1) {
					a++;
					tfPedidoCant.setText(String.valueOf(a));
					if(a != 0) {
						btnDisminuirPedido.setEnabled(true);
					}
				} else if (modo == 2) {
					a++;
					tfPedidoCant.setText(String.valueOf(a));
					if(a == Integer.parseInt(tfCantidad.getText())) {
						btnIncrementarPedido.setEnabled(false);
					}
					
					if(a != 0) {
						btnDisminuirPedido.setEnabled(true);
					}
				}
			}
		});
		
		btnDisminuirPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = Integer.parseInt(tfPedidoCant.getText());
				if(modo == 1) {
					if(a != 0) {
						a--;
						tfPedidoCant.setText(String.valueOf(a));
					}
					if (a == 0) {
						btnDisminuirPedido.setEnabled(false);
					}
				} else if (modo == 2) {
					if(a != 0) {
						a--;
						tfPedidoCant.setText(String.valueOf(a));
						btnIncrementarPedido.setEnabled(true);
					}
					if (a == 0) {
						btnDisminuirPedido.setEnabled(false);
					}
				}
			}
		});
		
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modo = 2;
				setPedidoON();
			}
		});
		
		btnVentaDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnVentaDia, "Cantidad ventas totales: "+ ventasDAO.consultaVentas());
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
			if(m.getCantidad() == 0) {
				btnVenta.setEnabled(false);
			} else {
				btnVenta.setEnabled(true);
			}
			
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
	
	private void setPanelBase() {
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
		btnSalir.setVisible(true);
		tfPedidoCant.setVisible(false);
		btnConfirPedido.setVisible(false);
		tfNombreMed.setVisible(true);
		tfFechaIncor.setVisible(true);
		tfTipo.setVisible(true);
		tfPrecio.setVisible(true);
		tfCantidad.setVisible(true);
		tfPpioActivo.setVisible(true);
		btnIncrementarPedido.setVisible(false);
		btnDisminuirPedido.setVisible(false);


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
		btnSalir.setVisible(false);
	}
	
	private void setPedidoON() {
		tfPedidoCant.setText("0");
		tfNombreMed.setVisible(false);
		tfFechaIncor.setVisible(false);
		tfTipo.setVisible(false);
		tfPrecio.setVisible(false);
		tfCantidad.setVisible(false);
		tfPpioActivo.setVisible(false);
		btnSiguiente.setVisible(false);
		btnAnterior.setVisible(false);
		btnActualizar.setVisible(false);
		btnPedido.setVisible(false);
		btnVenta.setVisible(false);
		btnVentaDia.setVisible(false);
		btnCancelarAct.setVisible(true);
		btnGuardarAct.setVisible(false);
		btnSalir.setVisible(false);
		tfPedidoCant.setVisible(true);
		tfPedidoCant.setEditable(false);
		btnConfirPedido.setVisible(true);
		btnIncrementarPedido.setVisible(true);
		btnDisminuirPedido.setVisible(true);
		btnDisminuirPedido.setEnabled(false);
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
		setPanelBase();
		printPagina();
	}
	
	private void confirmarPedido() {
		JOptionPane.showMessageDialog(btnConfirPedido, "El pedido ha sido realizado con éxito y ya ha sido entregado");
		Medicamento m = medicamentos.get(pagina);
		m.setCantidad(Integer.parseInt(tfPedidoCant.getText())+Integer.parseInt(tfCantidad.getText()));
		tfCantidad.setText(String.valueOf(Integer.parseInt(tfPedidoCant.getText())+Integer.parseInt(tfCantidad.getText())));
		medicamentoDAO.updateCantidad(m);
		setPanelBase();
		printPagina();
	}
	
	private void confirmarVenta() {
		JOptionPane.showMessageDialog(btnConfirPedido, "La venta ha sido realizada con éxito");
		Medicamento m = medicamentos.get(pagina);
		Ventas v = new Ventas(0, null, m, Integer.parseInt(tfPedidoCant.getText()));
		m.setCantidad(Integer.parseInt(tfCantidad.getText())-Integer.parseInt(tfPedidoCant.getText()));
		tfCantidad.setText(String.valueOf(Integer.parseInt(tfCantidad.getText())-Integer.parseInt(tfPedidoCant.getText())));
		medicamentoDAO.updateCantidad(m);
		ventasDAO.nuevaVenta(v);
		setPanelBase();
		printPagina();
		
	}
	
} //CIERRE CLASE
