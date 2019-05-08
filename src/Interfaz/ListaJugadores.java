package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoManager;
import mySQL.MysqlManager;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JLabel;

public class ListaJugadores extends JFrame {

	private JPanel contentPane;
	private DaoManager manager;
	private JugadoresTableModel modelo;
	private JTable tabla;
	public DetalleAlumnoPanel dj= new DetalleAlumnoPanel();
	public ListaJugadores(DaoManager manager) throws ClassNotFoundException, SQLException {
		
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
		toolBar.add(btnEditar);
		
		JButton btnBorrar = new JButton("borrar");
		toolBar.add(btnBorrar);
		
		JButton btnGuardar = new JButton("guardar");
		toolBar.add(btnGuardar);
		
		JButton btnCancelar = new JButton("cancelar");
		toolBar.add(btnCancelar);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		tabla = new JTable();
		panel.add(tabla);
		
		
		
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

