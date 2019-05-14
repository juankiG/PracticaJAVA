package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.GridLayout;
import javax.swing.JScrollPane;

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
	TableModel ltm;
	private JPanel panel;
	private JScrollPane scrollPane;
	
		public ListaLiga() throws ClassNotFoundException, SQLException {
			 this.manager= new MysqlManager();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		this.ltm= new TableModel(manager.getliga().BuscarTodosRSUL());
		
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		tabla.setModel(ltm);
		
		this.tabla.getSelectionModel().addListSelectionListener(e ->{
			boolean seleccionvalida= (tabla.getSelectedRow()!=-1);
			btnEditar.setEnabled(seleccionvalida);;
			btnBorrar.setEnabled(seleccionvalida);
		});
		panel.add(dlp);
		dlp.setLayout(new BorderLayout(0, 0));
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
			int res=JOptionPane.showConfirmDialog(null, "¿Estas seguro?","alerta", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if(res==0) {
				try {
					Liga liga= getLigaSeleccionado();
					manager.getliga().eliminar(liga.getId());
					
					ltm= new TableModel(manager.getliga().BuscarTodosRSUL());
					tabla.setModel(ltm);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				int res=JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres modificar?"+" "+liga.getNombre(),"alerta", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(res==0) {
					manager.getliga().modificar(liga);
				
				}
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
			//ltm.ActualizarModelo();
			try {
				ltm= new TableModel(manager.getliga().BuscarTodosRSUL());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tabla.setModel(ltm);
			
			
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
						
						new ListaLiga().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}


}
