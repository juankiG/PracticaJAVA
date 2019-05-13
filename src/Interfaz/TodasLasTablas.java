package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoEquipo;
import Dao.DaoJugador;
import Dao.DaoLiga;
import Dao.DaoManager;
import Modelos.Equipo;
import Modelos.Liga;
import mySQL.MysqlBBDD;
import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlLiga;
import mySQL.MysqlManager;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TodasLasTablas extends JFrame {

	private JPanel contentPane;
	private DaoManager manager;
	private TableModel modelo;
	private DaoJugador daoj= null;
	private DaoEquipo daoq= null;
	private DaoLiga daol= null;
	private JPanel panel;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JToolBar toolBar;
	private JButton btnBuscarEquipo;
	private JButton btnBuscarJugador;
	private JButton btnLimpiarBusqueda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TodasLasTablas frame = new TodasLasTablas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TodasLasTablas() throws ClassNotFoundException, SQLException {
		this.manager=new MysqlManager();
		daoj= new MysqlJugadores();
		daoq= new MysqlEquipo();
		daol= new MysqlLiga();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		
		
		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);
		
		modelo= new TableModel(daol.BuscarTodosRSUL());
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(modelo);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		modelo= new TableModel(daoq.buscarRset(0));
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(modelo);
		
		
		modelo= new TableModel(daoj.buscarRset(0));
		
		scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2);
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(modelo);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnBuscarEquipo = new JButton("Buscar Equipo");
		btnBuscarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ligaSelecionada();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		});
		toolBar.add(btnBuscarEquipo);
		
		btnBuscarJugador = new JButton("Buscar Jugador");
		btnBuscarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					equipoSelecionado();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnBuscarJugador);
		
		btnLimpiarBusqueda = new JButton("Limpiar busqueda");
		btnLimpiarBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelo= new TableModel(daoq.buscarRset(0));
					table_1.setModel(modelo);
					modelo= new TableModel(daoj.buscarRset(0));
					table_2.setModel(modelo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		toolBar.add(btnLimpiarBusqueda);
	}
	public void ligaSelecionada() throws ClassNotFoundException, SQLException {
		Liga l= getLigaSeleccionado();
		System.out.println(l);
		modelo= new TableModel(daoq.buscarRset(l.getId()));
		table_1.setModel(modelo);
	}
	private Liga getLigaSeleccionado() throws ClassNotFoundException, SQLException {
		Integer id= (Integer) table.getValueAt(table.getSelectedRow(), 0);
		System.out.println(id);
		return manager.getliga().buscar(id);
	}
	
	public void equipoSelecionado() throws ClassNotFoundException, SQLException {
		Equipo l= getEquipoSeleccionado();
		System.out.println(l);
		modelo= new TableModel(daoj.buscarRset(l.getId()));
		table_2.setModel(modelo);
	}
	private Equipo getEquipoSeleccionado() throws ClassNotFoundException, SQLException {
		Integer id= (Integer) table_1.getValueAt(table_1.getSelectedRow(), 0);
		System.out.println(id);
		return manager.getEquipo().buscar(id);
	}


}
