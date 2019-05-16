package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoManager;
import mySQL.MysqlManager;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class PanelControl extends JFrame {

	private JPanel contentPane;
	private DaoManager manager;
	ListaJugadores lg;
	ListaEquipos le;
	ListaLiga ll;
	TodasLasTablas tt;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DaoManager manager = new MysqlManager();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PanelControl(manager).setVisible(true);
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
	public PanelControl(DaoManager manager) throws ClassNotFoundException, SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		this.manager= manager;
		
		JButton btnCrearBbdd = new JButton("crear BBDD");
		btnCrearBbdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manager.getBBDD().CrearBBDD("bd_equipos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar BBDD");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manager.getBBDD().EliminarBBDD("bd_equipos");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		panel.add(btnEliminar);
		panel.add(btnCrearBbdd);
		
		JButton btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					le= new ListaEquipos();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				le.setVisible(true);
			}
		});
		panel.add(btnEquipos);
		
		JButton btnLigas = new JButton("Ligas");
		btnLigas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ll= new ListaLiga();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ll.setVisible(true);
			}
			
			
		});
		panel.add(btnLigas);
		
		JButton btnJugadores = new JButton("Jugadores");
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lg= new ListaJugadores();
					lg.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnJugadores);
		
		JButton btnTablas = new JButton("Tablas");
		btnTablas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tt=new TodasLasTablas();
					tt.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnTablas);
		
		
		
		
		
	}

}
