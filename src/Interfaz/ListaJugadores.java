package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoManager;
import Modelos.Jugadores;
import mySQL.MysqlManager;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaJugadores extends JFrame {

	private JPanel contentPane;
	private DaoManager manager;
	private JugadoresTableModel modelo;
	private JTable tabla;
	public DetalleAlumnoPanel dj= new DetalleAlumnoPanel();
	public ListaJugadores(DaoManager manager) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.manager= manager;
		//con esto se crea la tabla
		this.modelo=new JugadoresTableModel(manager.getJugador());
		this.modelo.updateModel();
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnAñadir = new JButton("anadir");
		
		toolBar.add(btnAñadir);
		
		JButton btnEditar = new JButton("editar");
		
		
		btnEditar.setEnabled(false);
		toolBar.add(btnEditar);
		
		JButton btnBorrar = new JButton("borrar");
		btnBorrar.setEnabled(false);
		toolBar.add(btnBorrar);
		
		JButton btnGuardar = new JButton("guardar");
		btnGuardar.setEnabled(false);
		toolBar.add(btnGuardar);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setEnabled(false);
		toolBar.add(btnCancelar);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		tabla = new JTable();
		getContentPane().add(tabla, BorderLayout.WEST);
		tabla.setModel(modelo);
		this.tabla.getSelectionModel().addListSelectionListener(e ->{
			boolean seleccionvalida= (tabla.getSelectedRow()!=-1);
			btnEditar.setEnabled(seleccionvalida);;
			btnBorrar.setEnabled(seleccionvalida);
		});
		getContentPane().add(dj, BorderLayout.CENTER);
		dj.setLayout(new BorderLayout(0, 0));
		
		//ACTIION DEL EDITAR
				btnEditar.addActionListener(new ActionListener() {
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
				});
				//action de añadir
				btnAñadir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					dj.setJugador(null);
					dj.cargarDatos();
					dj.setEditable(true);
					btnGuardar.setEnabled(true);
					btnCancelar.setEnabled(true);
					}
				});
				//action de cancelar
				
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dj.setJugador(null);
						dj.setEditable(false);
						dj.cargarDatos();
						tabla.clearSelection();
						btnGuardar.setEnabled(false);
						btnCancelar.setEnabled(false);
					}
				});
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
					
					new ListaJugadores(manager).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

