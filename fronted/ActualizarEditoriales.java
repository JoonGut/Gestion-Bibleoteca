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

public class ActualizarEditoriales extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private boolean esBuscado=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActualizarEditoriales dialog = new ActualizarEditoriales();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActualizarEditoriales() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Eliminado Autor");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(177, 10, 117, 35);
		contentPanel.add(lblTitulo);
		
		JLabel lblCod = new JLabel("Codigo Editorial");
		lblCod.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCod.setBackground(new Color(128, 128, 128));
		lblCod.setOpaque(true);
		lblCod.setBounds(58, 102, 146, 25);
		contentPanel.add(lblCod);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEditorial.setBackground(new Color(128, 128, 128));
		lblEditorial.setOpaque(true);
		lblEditorial.setBounds(327, 102, 85, 25);
		contentPanel.add(lblEditorial);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(72, 155, 126, 25);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(316, 155, 107, 25);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Listado Editoriales");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="modificar";
				ListadoEditorial dialog = new ListadoEditorial(accion);
				dialog.setVisible(true);
			}
		});
		btnBuscar.setBounds(177, 212, 146, 35);
		contentPanel.add(btnBuscar);
		
		JButton btnActualizar = new JButton("Actualizar Editorial");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {

		            Editorial editorial = new Editorial();
		            
		            // 3. Obtener las entidades desde la base de datos
		            editorial.setCodEditorial(Integer.parseInt(txtCodigo.getText()));
		            editorial.setNomEditorial(txtNombre.getText());
					
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas modificar al libro con ISBN: " + editorial.getCodEditorial(), "Modificacion Libro", JOptionPane.YES_NO_OPTION);
					Editorial_DAO editorialrDao = new Editorial_DAO();
					if (respuesta == JOptionPane.YES_OPTION) {
						if(editorialrDao.actualizar(editorial)) {
							JOptionPane.showMessageDialog(null, "Libro Modificado exitosamente", "Modificaion Libro", JOptionPane.INFORMATION_MESSAGE);
							 txtCodigo.setText(" ");
							 txtNombre.setText(" ");

							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "El Libro no ha podido ser modificado exitosamente", "Modificacion Autor", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Modificacion Autor Cancelada", "Modificacion Autor", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			
		});
		btnActualizar.setBounds(177, 276, 146, 35);
		contentPanel.add(btnActualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 128, 128));
		btnVolver.setOpaque(true);
		btnVolver.setBounds(10, 315, 84, 20);
		contentPanel.add(btnVolver);

	}
	public void buscarEditorialPorId(int idEditorial) {

	    Editorial_DAO editorialDAO = new Editorial_DAO();
	    model.Editorial editorial = editorialDAO.buscar(idEditorial);

	    txtCodigo.setText(String.valueOf(idEditorial));
	    txtNombre.setText(editorial.getNomEditorial());
	    esBuscado = true;
	}

}
