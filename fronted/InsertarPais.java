package fronted;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Editorial;
import model.Pais;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Pais_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InsertarPais extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertarPais dialog = new InsertarPais();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertarPais() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nuevo Libro");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(191, 10, 96, 35);
		contentPanel.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre Pais");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBackground(new Color(128, 128, 128));
		lblNombre.setOpaque(true);
		lblNombre.setBounds(191, 74, 109, 29);
		contentPanel.add(lblNombre);

		
		txtPais = new JTextField();
		txtPais.setBounds(178, 124, 128, 29);
		contentPanel.add(txtPais);
		txtPais.setColumns(10);
		
		JButton btnInsertar = new JButton("Nuevo Pais");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validarCampos()) {
					JOptionPane.showMessageDialog(null, "Rellena correctamente los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}else {
					try {
						//Autor_DAO autorDAO = new Autor_DAO(Byte.parseByte(tfTienda.getText()), tfNombre.getText(), tfApellido.getText(), tfEmail.getText(),Short.parseShort(tfDireccion.getText()), Boolean.parseBoolean(tfActivo.getText()),fecha,modificacion);
						
						Pais pais = new Pais();
						pais.setNomPais(txtPais.getText());
						Pais_DAO paisDAO = new Pais_DAO();
						
						if (paisDAO.insertar(pais)) {
							 JOptionPane.showMessageDialog(null, "Alta Correcta ", "ALTAS", JOptionPane.INFORMATION_MESSAGE);
							 txtPais.setText(" ");
							 
						}else {
							 JOptionPane.showMessageDialog(null, "Alta Incorrecta ", "ALTAS", JOptionPane.ERROR_MESSAGE);
						}
					
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				
			}		
				
		});
		btnInsertar.setBounds(191, 199, 104, 29);
		contentPanel.add(btnInsertar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 128, 128));
		btnVolver.setOpaque(true);
		btnVolver.setBounds(10, 283, 84, 20);
		contentPanel.add(btnVolver);

	}
	public boolean validarCampos() {
		if (txtPais.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}
}
