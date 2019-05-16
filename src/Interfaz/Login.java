package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoManager;
import mySQL.MysqlManager;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsuario;
	private boolean aceptado;
	private JPasswordField passwordField;
	DaoManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("usuario");
			lblNewLabel.setBounds(84, 64, 56, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			textFieldUsuario = new JTextField();
			textFieldUsuario.setBounds(186, 61, 116, 22);
			contentPanel.add(textFieldUsuario);
			textFieldUsuario.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
			lblNewLabel_1.setBounds(59, 114, 101, 19);
			contentPanel.add(lblNewLabel_1);
		}
		
		passwordField = new JPasswordField();
		passwordField.setBounds(186, 114, 116, 22);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptado=true;
						String usuario= getUsuario();
						String contraseña= getContraseña();
						System.out.println(usuario);
						try {
							manager= new MysqlManager(usuario, contraseña);
							PanelControl pn = new PanelControl(manager);
							pn.setVisible(true);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error en el usuario o contraseña", "Error", JOptionPane.WARNING_MESSAGE);
						}
						
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptado=false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public String getUsuario() {
		return new String(textFieldUsuario.getText());
	}
	public String getContraseña() {
		return new String(passwordField.getPassword());
	}
	public boolean isAceptado() {
		return aceptado;
	}
}
