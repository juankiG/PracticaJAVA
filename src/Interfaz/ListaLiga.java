package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Dao.DaoManager;

import Modelos.Equipo;
import Modelos.Liga;
import mySQL.MysqlBBDD;
import mySQL.MysqlManager;

import javax.swing.JToolBar;
import javax.swing.JButton;

public class ListaLiga extends JFrame {

	private JPanel contentPane;
	JToolBar toolBar;
	JButton btnAñadir;
	JButton btnEditar;
	JButton btnBorrar;
	JButton btnGuardar;
	JButton btnCancelar ;
	private JTable tabla;
	DaoManager manager;
	DetalleLigaPanel dlp= new DetalleLigaPanel();
	LigaTableModel ltm;
	
		public ListaLiga(DaoManager manager) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 336);
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
		getContentPane().add(dlp, BorderLayout.CENTER);
		dlp.setLayout(new BorderLayout(0, 0));
		tabla = new JTable();
		contentPane.add(tabla, BorderLayout.WEST);
		this.manager= manager;
		this.ltm= new LigaTableModel(manager.getliga());
		this.ltm.ActualizarModelo();
		tabla.setModel(ltm);
		
		this.tabla.getSelectionModel().addListSelectionListener(e ->{
			boolean seleccionvalida= (tabla.getSelectedRow()!=-1);
			btnEditar.setEnabled(seleccionvalida);;
			btnBorrar.setEnabled(seleccionvalida);
		});
	}
		private class BtnAñadirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dlp.setLiga(null);
			dlp.cargarDatos();
			dlp.setEditable(true);
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
		}
	}
	
	private class BtnEditarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println();
			try {
				Liga liga = getLigaSeleccionado();
				dlp.setLiga(liga);
				dlp.setEditable(true);
				dlp.cargarDatos();
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
			dlp.setLiga(null);
			dlp.setEditable(false);
			dlp.cargarDatos();
			tabla.clearSelection();
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
		}
	}
	private class BtnBorrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Liga liga= getLigaSeleccionado();
				manager.getliga().eliminar(liga.getId());
				ltm.ActualizarModelo();
				ltm.fireTableDataChanged();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}
	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dlp.guardarDatos();
			Liga liga =dlp.getLiga();
			try {
				if(liga.getId()==null) {
						manager.getliga().insertar(liga);
			}else {
				
					manager.getliga().modificar(liga);
				
				}
				}catch (Exception e) {
					// TODO: handle exception
				}
			
			dlp.setLiga(null);
			dlp.setEditable(false);
			dlp.cargarDatos();
			tabla.clearSelection();
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
			ltm.ActualizarModelo();
			ltm.fireTableDataChanged();
			
		}
	}
	private Liga getLigaSeleccionado() throws ClassNotFoundException, SQLException {
		Integer id= (Integer) tabla.getValueAt(tabla.getSelectedRow(), 0);
		return manager.getliga().buscar(id);
	}
		public static void main(String[] args) {
			DaoManager manager = new MysqlManager();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
						new ListaLiga(manager).setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}


}
