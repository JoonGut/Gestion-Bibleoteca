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
import model.Categoria;
import model.Editorial;
import model.Libro;
import model.Pais;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Libros_DAO;
import modelo_DAO.Pais_DAO;
import modelo_DAO.Poblacion_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import model.Poblacion;

public class InsertarPoblacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertarPoblacion dialog = new InsertarPoblacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertarPoblacion() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nueva Poblacion");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(170, 10, 133, 35);
		contentPanel.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre Poblacion");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBackground(new Color(128, 128, 128));
		lblNombre.setOpaque(true);
		lblNombre.setBounds(16, 69, 153, 29);
		contentPanel.add(lblNombre);
		
		JLabel lblPais = new JLabel("ID Pais");
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPais.setBackground(new Color(128, 128, 128));
		lblPais.setOpaque(true);
		lblPais.setBounds(291, 69, 96, 29);
		contentPanel.add(lblPais);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(26, 108, 123, 24);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPais = new JTextField();
		txtPais.setBounds(291, 108, 112, 24);
		contentPanel.add(txtPais);
		txtPais.setColumns(10);
		
		JButton btnInsertar = new JButton("Nueva Poblacion");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnInsertar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!validarCampos()) {
							JOptionPane.showMessageDialog(null, "Rellena correctamente los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}else {
							try {
								//Autor_DAO autorDAO = new Autor_DAO(Byte.parseByte(tfTienda.getText()), tfNombre.getText(), tfApellido.getText(), tfEmail.getText(),Short.parseShort(tfDireccion.getText()), Boolean.parseBoolean(tfActivo.getText()),fecha,modificacion);
								
								Poblacion poblacion = new Poblacion();
								poblacion.setNomPoblacion(txtNombre.getText());
								
								Pais pais = new Pais();
								pais.setId_pais(Integer.parseInt(txtPais.getText()));
							
								
								poblacion.setId_pais(pais);

								Poblacion_DAO poblacionDAO = new Poblacion_DAO();
								
								if (poblacionDAO.insertar(poblacion)) {
									 JOptionPane.showMessageDialog(null, "Alta Correcta ", "ALTAS", JOptionPane.INFORMATION_MESSAGE);
									 txtNombre.setText(" ");
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
			}
		});
		btnInsertar.setBounds(180, 184, 123, 35);
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
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="insertar";
				ListadoPais dialog = new ListadoPais(accion, InsertarPoblacion.this);
				dialog.setVisible(true);
			}
		});
		btnBuscar.setIcon(new ImageIcon(InsertarPoblacion.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		btnBuscar.setBounds(397, 69, 27, 24);
		contentPanel.add(btnBuscar);
		
	}
	public void buscarPaisPorId(int idPais) {

	    Pais_DAO paisDAO = new Pais_DAO();
	    model.Pais pais = paisDAO.buscar(idPais);

	    txtPais.setText(String.valueOf(idPais));
	    //txtCodCategoria.setText(categoria.getNomCategoria());
	    //esBuscado = true;
	}
	public boolean validarCampos() {
		if (txtNombre.getText().trim().isEmpty() || txtPais.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	} 
}
