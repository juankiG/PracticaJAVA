package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoManager;
import Modelos.Equipo;
import Modelos.Jugadores;
import mySQL.MysqlManager;

import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ListaEquipos extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private DaoManager  manager;
	private EquiposTableModel equipostablemodel;
	private JButton btnEditar;
	private JToolBar toolBar;
	private JButton btnAñadir;
	private JButton btnBorrar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	public DetalleEquipoPanel dep = new DetalleEquipoPanel();
	
	public ListaEquipos(DaoManager manager) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		 toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		 btnAñadir = new JButton("Añadir");
		btnAñadir.addActionListener(new BtnAñadirActionListener());
			toolBar.add(btnAñadir);
		
		 btnEditar = new JButton("Editar");
		 btnEditar.setEnabled(false);
		 btnEditar.addActionListener(new BtnEditarActionListener());
		toolBar.add(btnEditar);
		
		 btnBorrar = new JButton("Borrar");
		 btnBorrar.setEnabled(false);
		 btnBorrar.addActionListener(new BtnBorrarActionListener());
		toolBar.add(btnBorrar);
		
		 btnGuardar = new JButton("Guardar");
		 btnGuardar.setEnabled(false);
		 btnGuardar.addActionListener(new BtnGuardarActionListener());
		toolBar.add(btnGuardar);
		
		 btnCancelar = new JButton("Cancelar");
		 btnCancelar.setEnabled(false);
		 btnCancelar.addActionListener(new BtnCancelarActionListener());
		toolBar.add(btnCancelar);
		
		tabla = new JTable();
		contentPane.add(tabla, BorderLayout.WEST);
		
		dep.setCombo(new ComboboxLiga(manager.getliga()));
		this.manager=manager;
		this.equipostablemodel= new EquiposTableModel(manager.getEquipo());
		this.equipostablemodel.ActualizarModelo();
		tabla.setModel(equipostablemodel);
		
		this.tabla.getSelectionModel().addListSelectionListener(e ->{
			boolean seleccionvalida= (tabla.getSelectedRow()!=-1);
			btnEditar.setEnabled(seleccionvalida);;
			btnBorrar.setEnabled(seleccionvalida);
		});
		
		getContentPane().add(dep, BorderLayout.CENTER);
		dep.setLayout(new BorderLayout(0, 0));
	}
	
	
	private class BtnAñadirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dep.setEquipo(null);
			dep.cargarDatos();
			dep.setEditable(true);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
		}
	}
	
	private class BtnEditarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println();
			try {
				Equipo equipo = getEquipoSeleccionado();
				dep.setEquipo(equipo);
				dep.setEditable(true);
				dep.cargarDatos();
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
			dep.setEquipo(null);
			dep.setEditable(false);
			dep.cargarDatos();
			tabla.clearSelection();
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
		}
	}
	private class BtnBorrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Equipo equipo= getEquipoSeleccionado();
				manager.getEquipo().eliminar(equipo.getId());
				equipostablemodel.ActualizarModelo();
				equipostablemodel.fireTableDataChanged();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}
	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dep.guardarDatos();
			Equipo equipo =dep.getEquipo();
			try {
				if(equipo.getId()==null) {
						manager.getEquipo().insertar(equipo);
					
				
			}else {
				
					manager.getEquipo().modificar(equipo);
				
				}
				}catch (Exception e) {
					// TODO: handle exception
				}
			
			dep.setEquipo(null);
			dep.setEditable(false);
			dep.cargarDatos();
			tabla.clearSelection();
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
			equipostablemodel.ActualizarModelo();
			equipostablemodel.fireTableDataChanged();
			
		}
	}
	private Equipo getEquipoSeleccionado() throws ClassNotFoundException, SQLException {
		Integer id= (Integer) tabla.getValueAt(tabla.getSelectedRow(), 0);
		return manager.getEquipo().buscar(id);
	}
	public static void main(String[] args) {
		DaoManager manager = new MysqlManager();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					new ListaEquipos(manager).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
