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
import modelo_DAO.Autor_DAO;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Libros_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActualizarAutor extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtAutor;
	private boolean esBuscado=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActualizarAutor dialog = new ActualizarAutor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActualizarAutor() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Actualizar Autor");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(177, 10, 117, 35);
		contentPanel.add(lblTitulo);
		
			
		JLabel lblAutor = new JLabel("Nombre");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAutor.setBackground(new Color(128, 128, 128));
		lblAutor.setOpaque(true);
		lblAutor.setBounds(304, 72, 66, 25);
		contentPanel.add(lblAutor);
		
		JLabel lblCodigo = new JLabel("Codigo autor");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodigo.setBackground(new Color(128, 128, 128));
		lblCodigo.setOpaque(true);
		lblCodigo.setBounds(56, 72, 117, 25);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(66, 118, 106, 25);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(304, 118, 96, 25);
		contentPanel.add(txtAutor);
		txtAutor.setColumns(10);
		
		JButton btnBuscar = new JButton("Listado Autores");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="modificar";
				ListadoAutores dialog = new ListadoAutores(accion);
				dialog.setVisible(true);
			}
		});
		btnBuscar.setBounds(191, 177, 134, 25);
		contentPanel.add(btnBuscar);
		
		JButton btnActualizar = new JButton("Actualizar Autores");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {

		            Autor autor = new Autor();
		            
		            // 3. Obtener las entidades desde la base de datos
		            autor.setCodAutor(Integer.parseInt(txtCodigo.getText()));
		            autor.setNomAutor(txtAutor.getText());
					
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas modificar al libro con ISBN: " + autor.getCodAutor(), "Modificacion Libro", JOptionPane.YES_NO_OPTION);
					Autor_DAO autorDao = new Autor_DAO();
					if (respuesta == JOptionPane.YES_OPTION) {
						if(autorDao.actualizar(autor)) {
							JOptionPane.showMessageDialog(null, "Libro Modificado exitosamente", "Modificaion Libro", JOptionPane.INFORMATION_MESSAGE);
							 txtCodigo.setText(" ");
							 txtAutor.setText(" ");

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
		
		
		btnActualizar.setBounds(191, 231, 134, 25);
		contentPanel.add(btnActualizar);
		
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
	public void buscarAutorPorId(int idAutor) {

	    Autor_DAO autorDAO = new Autor_DAO();
	    model.Autor autor = autorDAO.buscar(idAutor);

	    txtCodigo.setText(String.valueOf(idAutor));
	    txtAutor.setText(autor.getNomAutor());
	    esBuscado = true;
	}

}
