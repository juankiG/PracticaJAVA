package Interfaz;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.DaoEquipo;
import Dao.DaoLiga;
import Modelos.Equipo;
import Modelos.Jugadores;
import Modelos.Liga;

public class DetalleEquipoPanel extends JPanel {
	private JTextField textFieldNombre;
	private Equipo equipo;
	private boolean editable;
	private ComboboxLiga combo;
	private JComboBox<Liga> comboBoxliga;
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		textFieldNombre.setEditable(editable);
		comboBoxliga.setEnabled(editable);
	}
	public ComboboxLiga getCombo() {
		return combo;
	}

	public void setCombo(ComboboxLiga combo) {
		this.combo = combo;
		comboBoxliga.setModel(combo);
		combo.update();
	}
	public void cargarDatos() {
		if(equipo!=null) {
			textFieldNombre.setText(equipo.getNombre());
			comboBoxliga.setSelectedIndex(equipo.getIdLiga()-1);
		}else {
			textFieldNombre.setText("");
		
		}
	}
	public void guardarDatos() {
		if(equipo==null) {
			equipo= new Equipo();
		}
		equipo.setNombre(textFieldNombre.getText());
		Liga ecv= (Liga) comboBoxliga.getSelectedItem();
		
		equipo.setIdLiga(ecv.getId());
	}

	public DetalleEquipoPanel() {
		combo = new ComboboxLiga(null);
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 13, 45, 16);
		add(lblNombre);
		
		JLabel lbliga = new JLabel("IdLiga");
		lbliga.setBounds(22, 56, 36, 16);
		add(lbliga);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(132, 10, 116, 22);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		comboBoxliga = new JComboBox();
		comboBoxliga.setBounds(132, 55, 116, 19);
		add(comboBoxliga);

	}
	public DetalleEquipoPanel(DaoLiga liga) {
		combo = new ComboboxLiga(liga);
		comboBoxliga.setModel(combo);
		combo.update();
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 13, 45, 16);
		add(lblNombre);
		
		JLabel lbliga = new JLabel("IdLiga");
		lbliga.setBounds(22, 56, 36, 16);
		add(lbliga);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(132, 10, 116, 22);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		
		comboBoxliga = new JComboBox();
		comboBoxliga.setModel(new DefaultComboBoxModel(new String[] {"Liga"}));
		comboBoxliga.setBounds(132, 95, 116, 19);
		add(comboBoxliga);

	}

}
