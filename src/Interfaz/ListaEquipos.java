package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelos.Jugadores;

import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ListaEquipos extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	
	private JButton btnEditar;
	private JToolBar toolBar;
	private JButton btnAñadir;
	private JButton btnBorrar;
	private JButton btnGuardar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaEquipos frame = new ListaEquipos();
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
	public ListaEquipos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		 btnAñadir.addActionListener(new BtnEditarActionListener());
		toolBar.add(btnEditar);
		
		 btnBorrar = new JButton("Borrar");
		 btnBorrar.setEnabled(false);
		 btnAñadir.addActionListener(new BtnBorrarActionListener());
		toolBar.add(btnBorrar);
		
		 btnGuardar = new JButton("Guardar");
		 btnGuardar.setEnabled(false);
		 btnAñadir.addActionListener(new BtnGuardarActionListener());
		toolBar.add(btnGuardar);
		
		 btnCancelar = new JButton("Cancelar");
		 btnCancelar.setEnabled(false);
		 btnAñadir.addActionListener(new BtnCancelarActionListener());
		toolBar.add(btnCancelar);
		
		
	
		
		tabla = new JTable();
		contentPane.add(tabla, BorderLayout.CENTER);
	}
	private class BtnAñadirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			btnGuardar.setEnabled(true);
			btnCancelar.setEnabled(true);
		}
	}
	
	private class BtnEditarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	private class BtnCancelarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			tabla.clearSelection();
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
		}
	}
	private class BtnBorrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
		
			
		}
	}
	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);
		}
	}

}
