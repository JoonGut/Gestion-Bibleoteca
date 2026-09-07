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

import model.Autor;
import model.Editorial;
import modelo_DAO.Autor_DAO;
import modelo_DAO.Editorial_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InsertarEditorial extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEditorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertarEditorial dialog = new InsertarEditorial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertarEditorial() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("Nueva Editorial");
			lblTitulo.setBackground(new Color(255, 214, 165));
			lblTitulo.setOpaque(true);
			lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
			lblTitulo.setBounds(177, 10, 117, 35);
			contentPanel.add(lblTitulo);
		}
		
		txtEditorial = new JTextField();
		txtEditorial.setBounds(173, 127, 138, 28);
		contentPanel.add(txtEditorial);
		txtEditorial.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Nombre Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEditorial.setBackground(new Color(128, 128, 128));
		lblEditorial.setOpaque(true);
		lblEditorial.setBounds(173, 79, 138, 25);
		contentPanel.add(lblEditorial);
		{
			JButton btnInsertar = new JButton("Nueva Editorial");
			btnInsertar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!validarCampos()) {
						JOptionPane.showMessageDialog(null, "Rellena correctamente los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					}else {
						try {
							//Autor_DAO autorDAO = new Autor_DAO(Byte.parseByte(tfTienda.getText()), tfNombre.getText(), tfApellido.getText(), tfEmail.getText(),Short.parseShort(tfDireccion.getText()), Boolean.parseBoolean(tfActivo.getText()),fecha,modificacion);
							
							Editorial editorial = new Editorial();
							editorial.setNomEditorial(txtEditorial.getText());
							Editorial_DAO editorialDAO = new Editorial_DAO();
							
							if (editorialDAO.insertar(editorial)) {
								 JOptionPane.showMessageDialog(null, "Alta Correcta ", "ALTAS", JOptionPane.INFORMATION_MESSAGE);
								 txtEditorial.setText(" ");
								 
							}else {
								 JOptionPane.showMessageDialog(null, "Alta Incorrecta ", "ALTAS", JOptionPane.ERROR_MESSAGE);
							}
						
						}catch(Exception ex) {
							ex.printStackTrace();
						}
					}
					
				}		
					
			});
			btnInsertar.setBounds(192, 190, 102, 35);
			contentPanel.add(btnInsertar);
		}
		{
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

	}
	public boolean validarCampos() {
		if (txtEditorial.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}
}
