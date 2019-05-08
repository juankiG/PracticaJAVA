package Interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Dao.DaoEquipo;
import Modelos.Equipo;
import Modelos.Jugadores;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DetalleAlumnoPanel extends JPanel {
	private JTextField textFieldNombre;
	private JTextField textFieldDorsal;
	private Jugadores jugador;
	private boolean editable;
	private ComboboxEquipo combo;
	private JComboBox<Equipo> comboBoxEquipo;
	
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
		comboBoxEquipo.setEnabled(editable);
		
	}
	public ComboboxEquipo getCombo() {
		return combo;
	}
	public void setCombo(ComboboxEquipo combo) {
		this.combo = combo;
		comboBoxEquipo.setModel(combo);
		combo.update();
	}
	public void cargarDatos() {
		if(jugador!=null) {
			textFieldDorsal.setText(String.valueOf(jugador.getDorsal()));
			textFieldNombre.setText(jugador.getNombre());
			//textFieldiDEquipo.setText(String.valueOf(jugador.getIdEquipo()));
		}else {
			textFieldDorsal.setText("");
			textFieldNombre.setText("");
			//textFieldiDEquipo.setText("");
		}
	}
	public void guardarDatos() {
		if(jugador==null) {
			jugador= new Jugadores();
		}
		jugador.setNombre(textFieldNombre.getText());
		jugador.setDorsal(Integer.parseInt(textFieldDorsal.getText()));
		//jugador.setIdEquipo(Integer.parseInt(textFieldiDEquipo.getText()));
	}
	/**
	 * Create the panel.
	 */
	public DetalleAlumnoPanel() {
		combo = new ComboboxEquipo(null);
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
		
		comboBoxEquipo = new JComboBox();
		comboBoxEquipo.setBounds(132, 95, 190, 19);
		add(comboBoxEquipo);

	}
	public DetalleAlumnoPanel(DaoEquipo equipo) {
		combo = new ComboboxEquipo(equipo);
		comboBoxEquipo.setModel(combo);
		combo.update();
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
		
		comboBoxEquipo = new JComboBox();
		comboBoxEquipo.setModel(new DefaultComboBoxModel(new String[] {"Equipo"}));
		comboBoxEquipo.setBounds(132, 95, 116, 19);
		add(comboBoxEquipo);

	}
}
