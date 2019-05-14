package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoEquipo;
import Dao.DaoJugador;
import Dao.DaoManager;
import Modelos.Equipo;
import Modelos.Jugadores;
import mySQL.MysqlEquipo;
import mySQL.MysqlJugadores;
import mySQL.MysqlManager;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

public class ListaJugadores extends JFrame {

	private JPanel contentPane;
	private DaoManager manager;
	private DaoJugador daoj= null;
	private DaoEquipo daoq= null;
	private TableModel modelo;
	private JTable tabla;
	private JButton btnEditar;
	private JToolBar toolBar;
	private JButton btnAñadir;
	private JButton btnBorrar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	public DetalleJugadorPanel dj= new DetalleJugadorPanel();
	private JPanel panel;
	private JScrollPane scrollPane;
	
	public ListaJugadores() throws ClassNotFoundException, SQLException {
		DaoManager manager = new MysqlManager();
		daoj= new MysqlJugadores();
		daoq= new MysqlEquipo();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		btnAñadir = new JButton("anadir");
		btnAñadir.addActionListener(new BtnAñadirActionListener());
		toolBar.add(btnAñadir);
		
		 btnEditar = new JButton("editar");
		 btnEditar.addActionListener(new BtnEditarActionListener());
		btnEditar.setEnabled(false);
		toolBar.add(btnEditar);
		
		 btnBorrar = new JButton("borrar");
		 btnBorrar.addActionListener(new BtnBorrarActionListener());
		btnBorrar.setEnabled(false);
		toolBar.add(btnBorrar);
		
		 btnGuardar = new JButton("guardar");
		 btnGuardar.addActionListener(new BtnGuardarActionListener());
		btnGuardar.setEnabled(false);
		toolBar.add(btnGuardar);
		
		btnCancelar = new JButton("cancelar");
		btnCancelar.addActionListener(new BtnCancelarActionListener());
		btnCancelar.setEnabled(false);
		toolBar.add(btnCancelar);
		this.manager= manager;
		//con esto se crea la tabla
		this.modelo=new TableModel(manager.getJugador().BuscarTodosRSUL());
		
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		tabla.setModel(modelo);
		
		
		this.tabla.getSelectionModel().addListSelectionListener(e ->{
			boolean seleccionvalida= (tabla.getSelectedRow()!=-1);
			btnEditar.setEnabled(seleccionvalida);;
			btnBorrar.setEnabled(seleccionvalida);
		});
		panel.add(dj);
		
		dj.setCombo(new ComboboxEquipo(manager.getEquipo()));
		dj.setLayout(new BorderLayout(0, 0));
					
				
	}
	
	private class BtnAñadirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dj.setJugador(null);
			dj.cargarDatos();
			dj.setEditable(true);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
		}
	}
	
	private class BtnEditarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Jugadores jugador = getJugadorSeleccionado();
				dj.setJugador(jugador);
				dj.setEditable(true);
				dj.cargarDatos();
				btnGuardar.setEnabled(true);
				btnCancelar.setEnabled(true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private class BtnCancelarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dj.setJugador(null);
			dj.setEditable(false);
			dj.cargarDatos();
			tabla.clearSelection();
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
		}
	}
	private class BtnBorrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		int res=JOptionPane.showConfirmDialog(null, "¿Estas seguro?","alerta", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		System.out.println(res);
		if(res==0) {
			try {
				Jugadores jugador= getJugadorSeleccionado();
				manager.getJugador().eliminar(jugador.getId());
				modelo= new TableModel(manager.getJugador().BuscarTodosRSUL());
				tabla.setModel(modelo);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			
		}
	}
	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dj.guardarDatos();
			Jugadores jugador =dj.getJugador();
			try {
				if(jugador.getId()==null) {
						manager.getJugador().insertar(jugador);
						
					
				
			}else {
				int res=JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres modificar?"+" "+jugador.getNombre(),"alerta", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(res==0) {
					manager.getJugador().modificar(jugador);
				
				}
				}
			}
				catch (Exception e) {
					// TODO: handle exception
				}
			
			dj.setJugador(null);
			dj.setEditable(false);
			dj.cargarDatos();
			tabla.clearSelection();
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
			
			try {
				modelo= new TableModel(manager.getJugador().BuscarTodosRSUL());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tabla.setModel(modelo);
			
		}
	}
	private Jugadores getJugadorSeleccionado() throws ClassNotFoundException, SQLException {
		Integer id= (Integer) tabla.getValueAt(tabla.getSelectedRow(), 0);
		return manager.getJugador().buscar(id);
	}


	public static void main(String[] args) {
		DaoManager manager = new MysqlManager();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					new ListaJugadores().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

