package Interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Dao.DaoLiga;
import Modelos.Liga;

public class DetalleLigaPanel extends JPanel {
	
	private JTextField textField;
	private Liga liga;
	private boolean editable;
	
	public Liga getLiga() {
		return liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		textField.setEditable(editable);
		
	}
	public void cargarDatos() {
		if(liga!=null) {
			textField.setText(liga.getNombre());
		}else {
			textField.setText("");
		}
	}
	public void guardarDatos() {
		if(liga==null) {
			liga= new Liga();
		}
		liga.setNombre(textField.getText());
		
	}

	public DetalleLigaPanel() {
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(45, 30, 45, 16);
		add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(132, 27, 116, 22);
		add(textField);
		textField.setColumns(10);

	}
	

}
