package proyectoFinal.vuelos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.io.IOException;

/**
*         			 Clase Principal Welcome          				*
* Programa principal donde carga la ventana del menú principal, con	*
* sus opciones principales; tiene los eventos de los botones, uno	*
* carga el mapa mundial con todos sus aeropuertos y sus rutas y el	*
* otro para buscar la ruta más corta entre los aeropuertos 			*
* seleccionados.													*
**/

@SuppressWarnings("serial")
public class Welcome extends JFrame {

	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JComboBox<Aeropuerto> jComboBox1;
	private JComboBox<Aeropuerto> jComboBox2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private boolean show; 

	public static void main(String args[]) throws IOException {
		Vuelos.cargarData();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Welcome().setVisible(true);
			}
		});
	}
	
	public Welcome() {
		componentes();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void componentes() {

		jPanel1 = new JPanel();
		jButton1 = new JButton();
		jButton2 = new JButton();
		jLabel1 = new JLabel();
		jComboBox1 = new JComboBox();
		jComboBox2 = new JComboBox();
		jButton3 = new JButton();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new Color(255, 255, 255));

		jButton1.setFont(new Font("Segoe UI Light", 0, 14));
		jButton1.setText("Ver mapa mundial");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				todoMapaActionPerformed(evt);
			}
		});

		jButton2.setFont(new Font("Segoe UI Light", 0, 14));
		jButton2.setText("Buscar ruta más corta");
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rutaMapaActionPerformed(evt);
			}
		});
		 
		for (Aeropuerto aeropuerto : Vuelos.getAeropuertos()) {
			jComboBox1.addItem(new Aeropuerto(aeropuerto.getId(), aeropuerto.getName(), aeropuerto.getCity(), aeropuerto.getCountry(),
					aeropuerto.getIata(), aeropuerto.getIcao(), aeropuerto.getPosY(), aeropuerto.getPosX(), aeropuerto.getPosZ()));
			jComboBox2.addItem(new Aeropuerto(aeropuerto.getId(), aeropuerto.getName(), aeropuerto.getCity(), aeropuerto.getCountry(),
					aeropuerto.getIata(), aeropuerto.getIcao(), aeropuerto.getPosY(), aeropuerto.getPosX(), aeropuerto.getPosZ()));
		}

		jLabel1.setFont(new Font("Segoe UI Light", 0, 24));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("¿Qué desea hacer?");

		jComboBox1.setFont(new Font("Segoe UI Light", 0, 12));
		jComboBox1.setVisible(false);

		jComboBox2.setFont(new Font("Segoe UI Light", 0, 12));
		jComboBox2.setVisible(false);

		jButton3.setFont(new Font("Segoe UI Light", 0, 12)); 
		jButton3.setText("Buscar");
		jButton3.setVisible(false);
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buscarRutaActionPerformed(evt);
			}
		});

		jLabel2.setFont(new Font("Segoe UI Light", 0, 12));
		jLabel2.setVisible(false);
		jLabel2.setText("Desde:");

		jLabel3.setFont(new Font("Segoe UI Light", 0, 12));
		jLabel3.setVisible(false);
		jLabel3.setText("A:");

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(162, 162, 162).addComponent(jButton3))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(35, 35, 35)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(jLabel2)
										.addGroup(jPanel1Layout
												.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jButton1)
														.addGap(35, 35, 35).addComponent(jButton2))
												.addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jComboBox2, 0, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addComponent(jLabel3))))
						.addContainerGap(35, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addGap(54, 54, 54)
						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 42,
								GroupLayout.PREFERRED_SIZE)
						.addGap(64, 64, 64)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1).addComponent(jButton2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
						.addComponent(jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(8, 8, 8).addComponent(jLabel3)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(24, 24, 24).addComponent(jButton3).addContainerGap()));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		pack();
		setLocationRelativeTo(null);
	}

	protected void buscarRutaActionPerformed(ActionEvent evt) {
		Aeropuerto air1 = (Aeropuerto) jComboBox1.getSelectedItem();
		Aeropuerto air2 = (Aeropuerto) jComboBox2.getSelectedItem();
		try {
			Grafo.getShortestPath(String.valueOf(air1.getId()), String.valueOf(air2.getId()));
			Vuelos.loadMap();
		} catch (IOException e) {
			System.err.println("Compruebe su conexión a Internet.");
		}
	}

	private void todoMapaActionPerformed(ActionEvent evt) {
		try {
			Vuelos.resetMap();
			Vuelos.loadMap();
		} catch (IOException e) {
			System.err.println("Compruebe su conexión a Internet.");
		}
	}

	private void rutaMapaActionPerformed(ActionEvent evt) {
		showOptions(!show);
		pack();
	}

	private void showOptions(boolean show) {
		this.show = show;
		jComboBox1.setVisible(show);
		jComboBox2.setVisible(show);
		jLabel2.setVisible(show);
		jLabel3.setVisible(show);
		jButton3.setVisible(show);
	}
}