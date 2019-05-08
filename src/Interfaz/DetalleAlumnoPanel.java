package Interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Modelos.Jugadores;

import javax.swing.JTextArea;

public class DetalleAlumnoPanel extends JPanel {
	private JTextField textFieldNombre;
	private JTextField textFieldDorsal;
	private JTextField textFieldiDEquipo;
	private Jugadores jugador;
	private boolean editable;
	public Jugadores getJugador() {
		return jugador;
	}
	public void setJugador(Jugadores jugador) {
		this.jugador = jugador;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
		textFieldDorsal.setEditable(editable);
		textFieldNombre.setEditable(editable);
		textFieldiDEquipo.setEditable(editable);
		
	}
	public void cargarDatos() {
		if(jugador!=null) {
			textFieldDorsal.setText(String.valueOf(jugador.getDorsal()));
			textFieldNombre.setText(jugador.getNombre());
			textFieldiDEquipo.setText(String.valueOf(jugador.getIdEquipo()));
		}else {
			textFieldDorsal.setText("");
			textFieldNombre.setText("");
			textFieldiDEquipo.setText("");
		}
	}
	public void guardarDatos() {
		if(jugador==null) {
			jugador= new Jugadores();
		}
		jugador.setNombre(textFieldNombre.getText());
		jugador.setDorsal(Integer.parseInt(textFieldDorsal.getText()));
		jugador.setIdEquipo(Integer.parseInt(textFieldiDEquipo.getText()));
	}
	/**
	 * Create the panel.
	 */
	public DetalleAlumnoPanel() {
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 13, 45, 16);
		add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Dorsal");
		lblNewLabel.setBounds(22, 56, 36, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Idequipo");
		lblNewLabel_1.setBounds(18, 95, 49, 16);
		add(lblNewLabel_1);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(132, 10, 116, 22);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldDorsal = new JTextField();
		textFieldDorsal.setBounds(132, 53, 116, 22);
		add(textFieldDorsal);
		textFieldDorsal.setColumns(10);
		
		textFieldiDEquipo = new JTextField();
		textFieldiDEquipo.setBounds(132, 92, 116, 22);
		add(textFieldiDEquipo);
		textFieldiDEquipo.setColumns(10);

	}
}
