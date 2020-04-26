package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Choice;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;

import baseDatos.BaseDatos;
import equipos.Averia;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearIncidencia extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigoAveria;
	private JTextField textFieldFechaAlta;
	private JTextField textFieldDescripcion;
	private JTextField textFieldCoste;
	private JTextField textFieldFechaReparacion;
	private Choice choiceGravedad;
	private JCheckBox chckbxReparada;
	private Choice choiceTipoDispositivo; 
	private Choice choiceCodigoDispositivo;
	private static Connection conexion;
	private LinkedList<Averia> averias = new LinkedList<Averia>();
	private JTextField textFieldCodigoIncidencia;
	private JTextField textFieldFechaAltaIncidencia;
	private JTextField textFieldDescripcionIncidencia;
	private JTextField textFieldCosteIncidencia;
	private JTextField textFieldFechaReparacionIncidencia;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearIncidencia frame = new CrearIncidencia();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearIncidencia() {
		conexion = BaseDatos.conexion(null, "root", "1234");
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Crear nueva incidencia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 587, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 27, 553, 120);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldFechaReparacion = new JTextField();
		textFieldFechaReparacion.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFechaReparacion.setEditable(false);
		textFieldFechaReparacion.setBounds(405, 57, 138, 20);
		panel.add(textFieldFechaReparacion);
		textFieldFechaReparacion.setColumns(10);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(10, 11, 46, 14);
		panel.add(lblCdigo);
		
		textFieldCodigoAveria = new JTextField();
		textFieldCodigoAveria.setEditable(false);
		ResultSet codigosAverias = BaseDatos.consulta("select codigo from gestionciudad.averias order by codigo desc;", conexion);
		
		try {
			if(codigosAverias.next()) {
				textFieldCodigoAveria.setText(codigosAverias.getString(1));
			}else {
				textFieldCodigoAveria.setText("1");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textFieldCodigoAveria.setBounds(55, 8, 73, 20);
		panel.add(textFieldCodigoAveria);
		textFieldCodigoAveria.setColumns(10);
		
		JLabel lblGravedad = new JLabel("Gravedad:");
		lblGravedad.setBounds(149, 11, 58, 14);
		panel.add(lblGravedad);
		
		choiceGravedad = new Choice();
		choiceGravedad.setBounds(213, 8, 46, 20);
		for(int i=1;i<10;i++) {
			choiceGravedad.add(String.valueOf(i));
		}
		
		panel.add(choiceGravedad);
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de alta:");
		lblFechaDeAlta.setBounds(293, 11, 88, 14);
		panel.add(lblFechaDeAlta);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFechaAlta.setBounds(405, 8, 138, 20);
		textFieldFechaAlta.setText(LocalDate.now().getDayOfMonth()+" - "+LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"))+" - "+ LocalDate.now().getYear());
		
		panel.add(textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 35, 82, 14);
		panel.add(lblDescripcin);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(102, 32, 441, 20);
		panel.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblCosteReparacin = new JLabel("Coste reparaci\u00F3n:");
		lblCosteReparacin.setBounds(10, 60, 103, 14);
		panel.add(lblCosteReparacin);
		
		textFieldCoste = new JTextField();
		textFieldCoste.setBounds(123, 57, 58, 20);
		panel.add(textFieldCoste);
		textFieldCoste.setColumns(10);
		
		JLabel lblFechaDeReparacin = new JLabel("Fecha reparaci\u00F3n:");
		lblFechaDeReparacin.setBounds(293, 60, 103, 14);
		panel.add(lblFechaDeReparacin);
		
		chckbxReparada = new JCheckBox("Reparada: ");
		chckbxReparada.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					textFieldFechaReparacion.setEditable(true);
					textFieldFechaReparacion.setEnabled(true);
					textFieldFechaReparacion.setText(LocalDate.now().getDayOfMonth()+" - "+LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"))+" - "+ LocalDate.now().getYear());
				}else {
					textFieldFechaReparacion.setEditable(false);
					textFieldFechaReparacion.setEnabled(false);
					textFieldFechaReparacion.setText("");
				}
			}
		});
		
		chckbxReparada.setHorizontalTextPosition(SwingConstants.LEFT);
		chckbxReparada.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxReparada.setBounds(197, 56, 88, 23);
		panel.add(chckbxReparada);
		
		JLabel lblCodDispositivo = new JLabel("Cod. Dispositivo:");
		lblCodDispositivo.setBounds(239, 86, 103, 14);
		panel.add(lblCodDispositivo);
		
		Choice choiceCodigoDispositivo = new Choice();
		choiceCodigoDispositivo.setBounds(348, 83, 58, 20);
		panel.add(choiceCodigoDispositivo);
		
		JLabel lblTipoDispositivo = new JLabel("Tipo dispositivo:");
		lblTipoDispositivo.setBounds(10, 85, 103, 14);
		panel.add(lblTipoDispositivo);
		
		choiceTipoDispositivo = new Choice();
		choiceTipoDispositivo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				choiceCodigoDispositivo.removeAll();
				ResultSet resultados;
				switch (choiceTipoDispositivo.getSelectedIndex()) {
				case 0:
					resultados = BaseDatos.consulta("Select codigo from gestionciudad.dispositivos;", conexion);
					try {
						
						while((resultados.next())) {
							choiceCodigoDispositivo.add(resultados.getString(1));
							
						}
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					break;
				case 1:
					resultados = BaseDatos.consulta("Select codigo from gestionciudad.camaras;", conexion);
					try {
						
						while((resultados.next())) {
							choiceCodigoDispositivo.add(resultados.getString(1));
						}
					} catch (SQLException exc) {
						// TODO Auto-generated catch block
						exc.printStackTrace();
					}
					break;
				case 2:
					resultados = BaseDatos.consulta("Select codigo from gestionciudad.farolas;", conexion);
					try {
						
						while((resultados.next())) {
							choiceCodigoDispositivo.add(resultados.getString(1));
						}
					} catch (SQLException exce) {
						// TODO Auto-generated catch block
						exce.printStackTrace();
					}
					break;
				case 3:
					resultados = BaseDatos.consulta("Select codigo from gestionciudad.semaforos;", conexion);
					try {
						
						while((resultados.next())) {
							choiceCodigoDispositivo.add(resultados.getString(1));
						}
					} catch (SQLException excep) {
						// TODO Auto-generated catch block
						excep.printStackTrace();
					}
					break;
				default:
					choiceCodigoDispositivo.removeAll();
						
				}
			}
		});
		choiceTipoDispositivo.setBounds(123, 83, 88, 20);
		choiceTipoDispositivo.add("Seleccione");
		choiceTipoDispositivo.add("Cámara");
		choiceTipoDispositivo.add("Farola");
		choiceTipoDispositivo.add("Semáforo");
		panel.add(choiceTipoDispositivo);
		
		JButton btnCrearAveria = new JButton("Crear");
		btnCrearAveria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Averia averia = new Averia(Integer.valueOf(textFieldCodigoAveria.getText()),Integer.valueOf(choiceGravedad.getSelectedItem()), Double.valueOf(textFieldCoste.getText()),
						textFieldFechaAlta.getText(), textFieldFechaReparacion.getText(), textFieldDescripcion.getText(), Boolean.valueOf(chckbxReparada.isSelected()));
				averias.add(averia);
			}
		});
		btnCrearAveria.setBounds(454, 86, 89, 23);
		panel.add(btnCrearAveria);
				
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 185, 553, 120);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setBounds(10, 14, 46, 14);
		panel_1.add(label);
		
		textFieldCodigoIncidencia = new JTextField();
		textFieldCodigoIncidencia.setEditable(false);
		textFieldCodigoIncidencia.setColumns(10);
		textFieldCodigoIncidencia.setBounds(55, 11, 73, 20);
		panel_1.add(textFieldCodigoIncidencia);
		
		JLabel lblPrioridad = new JLabel("Prioridad:");
		lblPrioridad.setBounds(149, 14, 58, 14);
		panel_1.add(lblPrioridad);
		
		Choice choicePrioridad = new Choice();
		choicePrioridad.setBounds(213, 11, 46, 20);
		panel_1.add(choicePrioridad);
		
		JLabel label_2 = new JLabel("Fecha de alta:");
		label_2.setBounds(293, 14, 88, 14);
		panel_1.add(label_2);
		
		textFieldFechaAltaIncidencia = new JTextField();
		textFieldFechaAltaIncidencia.setText("26 - abril - 2020");
		textFieldFechaAltaIncidencia.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFechaAltaIncidencia.setColumns(10);
		textFieldFechaAltaIncidencia.setBounds(405, 11, 138, 20);
		panel_1.add(textFieldFechaAltaIncidencia);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n:");
		label_3.setBounds(10, 38, 82, 14);
		panel_1.add(label_3);
		
		textFieldDescripcionIncidencia = new JTextField();
		textFieldDescripcionIncidencia.setColumns(10);
		textFieldDescripcionIncidencia.setBounds(102, 35, 441, 20);
		panel_1.add(textFieldDescripcionIncidencia);
		
		JLabel label_4 = new JLabel("Coste reparaci\u00F3n:");
		label_4.setBounds(10, 63, 103, 14);
		panel_1.add(label_4);
		
		textFieldCosteIncidencia = new JTextField();
		textFieldCosteIncidencia.setColumns(10);
		textFieldCosteIncidencia.setBounds(123, 60, 58, 20);
		panel_1.add(textFieldCosteIncidencia);
		
		JCheckBox chckbxResuelta = new JCheckBox("Resuelta: ");
		chckbxResuelta.setHorizontalTextPosition(SwingConstants.LEFT);
		chckbxResuelta.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxResuelta.setBounds(197, 59, 88, 23);
		panel_1.add(chckbxResuelta);
		
		JLabel label_5 = new JLabel("Fecha reparaci\u00F3n:");
		label_5.setBounds(293, 63, 103, 14);
		panel_1.add(label_5);
		
		textFieldFechaReparacionIncidencia = new JTextField();
		textFieldFechaReparacionIncidencia.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFechaReparacionIncidencia.setEditable(false);
		textFieldFechaReparacionIncidencia.setColumns(10);
		textFieldFechaReparacionIncidencia.setBounds(405, 60, 138, 20);
		panel_1.add(textFieldFechaReparacionIncidencia);
		
		JButton button = new JButton("Crear");
		button.setBounds(454, 89, 89, 23);
		panel_1.add(button);
		
		JLabel lblAvera = new JLabel("Aver\u00EDa:");
		lblAvera.setBounds(20, 11, 46, 14);
		contentPane.add(lblAvera);
		
		JLabel lblIncidencia = new JLabel("Incidencia:");
		lblIncidencia.setBounds(20, 168, 68, 14);
		contentPane.add(lblIncidencia);
	}
}
