package interfazGrafica;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;



import baseDatos.BaseDatos;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frmGestionAutomaticaCiudad;
	private JTextField txtDispositivos;
	private JTable tableDispositivos;
	private JTextField textNucleos;
	private JTable tableNucleos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmGestionAutomaticaCiudad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}
/**
 * Crea la estructura básica del JTree y lo rellena utilizando los datos de la BD
 * @return Devuelve un DefaultTreeModel con la estructura y todos los datos cargados
 */
	private DefaultTreeModel cargarDatosArbol(Connection conexion) {
		
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Ciudad");
		DefaultMutableTreeNode nucleos = new DefaultMutableTreeNode("Núcleos");
		DefaultMutableTreeNode dispositivos = new DefaultMutableTreeNode("Dispositivos");
		DefaultMutableTreeNode personal = new DefaultMutableTreeNode("Personal");
		
		DefaultMutableTreeNode camaras = new DefaultMutableTreeNode("Cámaras");
		DefaultMutableTreeNode semaforos = new DefaultMutableTreeNode("Semáforos");
		DefaultMutableTreeNode farolas = new DefaultMutableTreeNode("Farolas");
		
		dispositivos.add(camaras);
		dispositivos.add(semaforos);
		dispositivos.add(farolas);
		
		DefaultMutableTreeNode vias = new DefaultMutableTreeNode("Vías");
		DefaultMutableTreeNode barrios = new DefaultMutableTreeNode("Barrios");
		DefaultMutableTreeNode distritos = new DefaultMutableTreeNode("Distritos");
		DefaultMutableTreeNode municipios = new DefaultMutableTreeNode("Municipios");
		
		nucleos.add(municipios);
		nucleos.add(distritos);
		nucleos.add(barrios);
		nucleos.add(vias);
		
		DefaultMutableTreeNode jefes = new DefaultMutableTreeNode("Jefes");
		DefaultMutableTreeNode supervisores = new DefaultMutableTreeNode("Supervisores");
		DefaultMutableTreeNode tecnicos = new DefaultMutableTreeNode("Técnicos");
		
		personal.add(jefes);
		personal.add(supervisores);
		personal.add(tecnicos);
		
		String[][] nucleosRecuperados = BaseDatos.recuperarNucleos(conexion);
		for(int i = 0;i<nucleosRecuperados.length;i++) {
			DefaultMutableTreeNode nucleo = new DefaultMutableTreeNode(nucleosRecuperados[i][2]);
			switch(nucleosRecuperados[i][1]) {
			case "vía":
				vias.add(nucleo);
				break;
			case "barrio":
				barrios.add(nucleo);
				break;
			case "distrito":
				distritos.add(nucleo);
				break;
			case "municipio":
				municipios.add(nucleo);
				break;
			default:
				vias.add(nucleo);
			}
		}
		
		String[][] dispositivosRecuperados = BaseDatos.recuperarDispositivos(conexion);
		for(int i = 0;i<dispositivosRecuperados.length;i++) {
			DefaultMutableTreeNode dispositivo = new DefaultMutableTreeNode(dispositivosRecuperados[i][1]+" "+dispositivosRecuperados[i][0]);
			switch(dispositivosRecuperados[i][1]) {
			case "Farola":
				farolas.add(dispositivo);
				break;
			case "Semáforo":
				semaforos.add(dispositivo);
				break;
			case "Cámara":
				camaras.add(dispositivo);
				break;
			}
		}
		String[][] personasRecuperadas = BaseDatos.recuperarPersonas(conexion);
		for(int i = 0;i<personasRecuperadas.length;i++) {
			DefaultMutableTreeNode persona = new DefaultMutableTreeNode(personasRecuperadas[i][0]+" "+personasRecuperadas[i][1]+" "+ personasRecuperadas[i][2]);
			if(personasRecuperadas[i][3]== null) {
			
				if(personasRecuperadas[i][4] == null) {
					jefes.add(persona);
				}else {
					supervisores.add(persona);
				}				
			}else {
				tecnicos.add(persona);
			}
		}
		raiz.add(nucleos);
		raiz.add(dispositivos);
		raiz.add(personal);
		DefaultTreeModel arbol = new DefaultTreeModel(raiz);
		
		return arbol;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmGestionAutomaticaCiudad = new JFrame();
		frmGestionAutomaticaCiudad.setTitle("Gesti\u00F3n Autom\u00E1tica Ciudad");
		frmGestionAutomaticaCiudad.setResizable(false);
		frmGestionAutomaticaCiudad.setBounds(100, 100, 800, 600);
		frmGestionAutomaticaCiudad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionAutomaticaCiudad.getContentPane().setLayout(null);
		
		JPanel panelSuperiorInicio = new JPanel();
		panelSuperiorInicio.setAutoscrolls(true);
		panelSuperiorInicio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSuperiorInicio.setBounds(159, 11, 613, 251);
		frmGestionAutomaticaCiudad.getContentPane().add(panelSuperiorInicio);
		panelSuperiorInicio.setLayout(null);
		
		txtDispositivos = new JTextField();
		txtDispositivos.setHorizontalAlignment(SwingConstants.CENTER);
		txtDispositivos.setEditable(false);
		txtDispositivos.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtDispositivos.setText("Dispositivos");
		txtDispositivos.setBounds(2, 2, 613, 20);
		panelSuperiorInicio.add(txtDispositivos);
		txtDispositivos.setColumns(10);
		
		String[][] datosTablaDispositivos = BaseDatos.recuperarDispositivos(BaseDatos.conexion(null, "root", "1234"));
		String[] camposTablaDispositivos = {"Codigo", "Tipo", "Descripción", "Fabricante", "Estado"};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(2, 21, 613, 230);
		panelSuperiorInicio.add(scrollPane);
		//creo el table model para que las celdas no sean editables, insertando los datos de la tabla 
		DefaultTableModel tableModel = new DefaultTableModel(datosTablaDispositivos, camposTablaDispositivos) {
			@Override 
			public boolean isCellEditable(int row, int column) { 
				//all cells false
				return false;
			}
		};  			
		
		tableDispositivos = new JTable(tableModel);
		scrollPane.setViewportView(tableDispositivos);
		tableDispositivos.setFillsViewportHeight(true);
		tableDispositivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		JPanel panelSecundarioInicio = new JPanel();
		panelSecundarioInicio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSecundarioInicio.setBounds(159, 273, 613, 219);
		frmGestionAutomaticaCiudad.getContentPane().add(panelSecundarioInicio);
		panelSecundarioInicio.setLayout(null);
		
		textNucleos = new JTextField();
		textNucleos.setFont(new Font("Tahoma", Font.BOLD, 11));
		textNucleos.setHorizontalAlignment(SwingConstants.CENTER);
		textNucleos.setEditable(false);
		textNucleos.setText("N\u00FAcleos");
		textNucleos.setBounds(2, 2, 613, 20);
		panelSecundarioInicio.add(textNucleos);
		textNucleos.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(2, 21, 613, 198);
		panelSecundarioInicio.add(scrollPane_1);
		
		String[][] datosTablaNucleos = BaseDatos.recuperarNucleos(BaseDatos.conexion(null, "root", "1234"));
		String[] camposTablaNucleos = {"Codigo", "Tipo", "Nombre", "H. Encendido", "H. Apagado"};
		
		DefaultTableModel tableModelNucleos = new DefaultTableModel(datosTablaNucleos, camposTablaNucleos) {
			@Override 
			public boolean isCellEditable(int row, int column) { 
				//all cells false
				return false;
			}
		};  
		
		tableNucleos = new JTable(tableModelNucleos);
		tableNucleos.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tableNucleos);
		
		JButton btnIncidencia = new JButton("Nueva Incidencia");
		btnIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearIncidencia();
			}
		});
		btnIncidencia.setBounds(159, 503, 139, 23);
		frmGestionAutomaticaCiudad.getContentPane().add(btnIncidencia);
		
		JButton btnEncender = new JButton("Encender");
		btnEncender.setBounds(308, 503, 89, 23);
		frmGestionAutomaticaCiudad.getContentPane().add(btnEncender);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(407, 503, 89, 23);
		frmGestionAutomaticaCiudad.getContentPane().add(btnApagar);
		
		JButton btnProgramar = new JButton("Programar");
		btnProgramar.setBounds(506, 503, 107, 23);
		frmGestionAutomaticaCiudad.getContentPane().add(btnProgramar);
		
		JButton btnEstado = new JButton("Comprobar Estado");
		btnEstado.setBounds(623, 503, 149, 23);
		frmGestionAutomaticaCiudad.getContentPane().add(btnEstado);
		
		JScrollPane scrollPaneArbol = new JScrollPane();
		scrollPaneArbol.setBounds(10, 11, 139, 515);
		frmGestionAutomaticaCiudad.getContentPane().add(scrollPaneArbol);
		
		JTree tree = new JTree(cargarDatosArbol(BaseDatos.conexion(null, "root", "1234")));
		scrollPaneArbol.setViewportView(tree);
		tree.setAutoscrolls(true);
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JMenuBar menuBar = new JMenuBar();
		frmGestionAutomaticaCiudad.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Men\u00FA");
		menuBar.add(mnMenu);
		
		JMenu mnNuevo = new JMenu("Nuevo");
		mnMenu.add(mnNuevo);
		
		JMenuItem mntmIncidencia = new JMenuItem("Incidencia");
		mnNuevo.add(mntmIncidencia);
		
		JMenuItem mntmNcleo = new JMenuItem("N\u00FAcleo");
		mnNuevo.add(mntmNcleo);
		
		JMenuItem mntmEmpleado = new JMenuItem("Empleado");
		mnNuevo.add(mntmEmpleado);
		
		JMenuItem mntmDispositivo = new JMenuItem("Dispositivo");
		mnNuevo.add(mntmDispositivo);
		
		JMenu mnGestionar = new JMenu("Gestionar");
		mnMenu.add(mnGestionar);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mnGestionar.add(mntmImportar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar");
		mnGestionar.add(mntmExportar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmCrearNuevo = new JMenuItem("Crear nuevo");
		mnUsuarios.add(mntmCrearNuevo);
		
		JMenuItem mntmBorrarUsuario = new JMenuItem("Borrar usuario");
		mnUsuarios.add(mntmBorrarUsuario);
		
		JMenuItem mntmEditarUsuario = new JMenuItem("Editar usuario");
		mnUsuarios.add(mntmEditarUsuario);
		
		JMenu mnDispositivos = new JMenu("Dispositivos");
		menuBar.add(mnDispositivos);
		
		JMenu mnNuevo_1 = new JMenu("A\u00F1adir");
		mnDispositivos.add(mnNuevo_1);
		
		JMenuItem mntmCmara = new JMenuItem("C\u00E1mara");
		mnNuevo_1.add(mntmCmara);
		
		JMenuItem mntmSemforo = new JMenuItem("Sem\u00E1foro");
		mnNuevo_1.add(mntmSemforo);
		
		JMenuItem mntmFarola = new JMenuItem("Farola");
		mnNuevo_1.add(mntmFarola);
		
		JMenu mnBuscar = new JMenu("Buscar");
		mnDispositivos.add(mnBuscar);
		
		JMenuItem mntmPorCdigo = new JMenuItem("Por c\u00F3digo");
		mnBuscar.add(mntmPorCdigo);
		
		JMenuItem mntmPorTipo = new JMenuItem("Por tipo");
		mnBuscar.add(mntmPorTipo);
		
		JMenuItem mntmPorNcleo = new JMenuItem("Por n\u00FAcleo");
		mnBuscar.add(mntmPorNcleo);
		
		JMenuItem mntmPorEstado = new JMenuItem("Por estado");
		mnBuscar.add(mntmPorEstado);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mnDispositivos.add(mntmEliminar);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mnDispositivos.add(mntmModificar);
		
		JMenuItem mntmEstadstica = new JMenuItem("Estad\u00EDstica");
		mnDispositivos.add(mntmEstadstica);
		
		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);
		
		JMenu mnNuevo_2 = new JMenu("Nuevo");
		mnEmpleados.add(mnNuevo_2);
		
		JMenuItem mntmTcnico = new JMenuItem("T\u00E9cnico");
		mnNuevo_2.add(mntmTcnico);
		
		JMenuItem mntmSupervisor = new JMenuItem("Supervisor");
		mnNuevo_2.add(mntmSupervisor);
		
		JMenuItem mntmJefe = new JMenuItem("Jefe");
		mnNuevo_2.add(mntmJefe);
		
		JMenu mnBuscar_1 = new JMenu("Buscar");
		mnEmpleados.add(mnBuscar_1);
		
		JMenuItem mntmPorNombre = new JMenuItem("Por nombre");
		mnBuscar_1.add(mntmPorNombre);
		
		JMenuItem mntmPorCdigo_1 = new JMenuItem("Por c\u00F3digo");
		mnBuscar_1.add(mntmPorCdigo_1);
		
		JMenuItem mntmPorFechaAlta = new JMenuItem("Por fecha alta");
		mnBuscar_1.add(mntmPorFechaAlta);
		
		JMenuItem mntmModificar_2 = new JMenuItem("Modificar");
		mnEmpleados.add(mntmModificar_2);
		
		JMenuItem mntmEstadsticas = new JMenuItem("Estad\u00EDsticas");
		mnEmpleados.add(mntmEstadsticas);
		
		JMenu mnNcleos = new JMenu("N\u00FAcleos");
		menuBar.add(mnNcleos);
		
		JMenu mnNuevo_3 = new JMenu("Nuevo");
		mnNcleos.add(mnNuevo_3);
		
		JMenuItem mntmVa = new JMenuItem("V\u00EDa");
		mnNuevo_3.add(mntmVa);
		
		JMenuItem mntmBarrio = new JMenuItem("Barrio");
		mnNuevo_3.add(mntmBarrio);
		
		JMenuItem mntmDistrito = new JMenuItem("Distrito");
		mnNuevo_3.add(mntmDistrito);
		
		JMenuItem mntmMunicipio = new JMenuItem("Municipio");
		mnNuevo_3.add(mntmMunicipio);
		
		JMenu mnBuscar_2 = new JMenu("Buscar");
		mnNcleos.add(mnBuscar_2);
		
		JMenuItem mntmPorNombre_1 = new JMenuItem("Por nombre");
		mnBuscar_2.add(mntmPorNombre_1);
		
		JMenuItem mntmPorTipo_1 = new JMenuItem("Por tipo");
		mnBuscar_2.add(mntmPorTipo_1);
		
		JMenuItem mntmPorCodigo = new JMenuItem("Por codigo");
		mnBuscar_2.add(mntmPorCodigo);
		
		JMenuItem mntmEliminar_1 = new JMenuItem("Eliminar");
		mnNcleos.add(mntmEliminar_1);
		
		JMenuItem mntmModificar_1 = new JMenuItem("Modificar");
		mnNcleos.add(mntmModificar_1);
		
		JMenuItem mntmEstadsticas_1 = new JMenuItem("Estad\u00EDsticas");
		mnNcleos.add(mntmEstadsticas_1);
		
		JMenu mnIncidencias = new JMenu("Incidencias");
		menuBar.add(mnIncidencias);
		
		JMenuItem mntmNueva = new JMenuItem("Nueva");
		mnIncidencias.add(mntmNueva);
		
		JMenu mnBuscar_3 = new JMenu("Buscar");
		mnIncidencias.add(mnBuscar_3);
		
		JMenuItem mntmPorCodigo_1 = new JMenuItem("Por codigo");
		mnBuscar_3.add(mntmPorCodigo_1);
		
		JMenuItem mntmPorFecha = new JMenuItem("Por fecha");
		mnBuscar_3.add(mntmPorFecha);
		
		JMenuItem mntmPorNcleo_1 = new JMenuItem("Por n\u00FAcleo");
		mnBuscar_3.add(mntmPorNcleo_1);
		
		JMenuItem mntmPorDescripcin = new JMenuItem("Por descripci\u00F3n");
		mnBuscar_3.add(mntmPorDescripcin);
		
		JMenuItem mntmModificar_3 = new JMenuItem("Modificar");
		mnIncidencias.add(mntmModificar_3);
		
		JMenuItem mntmEliminar_2 = new JMenuItem("Eliminar");
		mnIncidencias.add(mntmEliminar_2);
		
		JMenuItem mntmEstadsticas_2 = new JMenuItem("Estad\u00EDsticas");
		mnIncidencias.add(mntmEstadsticas_2);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnAyuda.add(mntmAcercaDe);
	}

	protected void CrearIncidencia() {
		CrearIncidencia incidencia = new CrearIncidencia();
		incidencia.setVisible(true);
		
	}
}
