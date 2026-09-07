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

import modelo_DAO.Editorial_DAO;
import modelo_DAO.Libros_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EliminarLibro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIsbn;
	private JTextField txtLibro;
	private boolean esBuscado=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarLibro dialog = new EliminarLibro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarLibro() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Eliminado Libro");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(181, 10, 124, 35);
		contentPanel.add(lblTitulo);
		
		JLabel lblTituloLibro = new JLabel("Titulo Libro:");
		lblTituloLibro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTituloLibro.setBackground(new Color(128, 128, 128));
		lblTituloLibro.setOpaque(true);
		lblTituloLibro.setBounds(325, 69, 102, 19);
		contentPanel.add(lblTituloLibro);
		
		txtIsbn = new JTextField();
		txtIsbn.setBounds(36, 98, 124, 23);
		contentPanel.add(txtIsbn);
		txtIsbn.setColumns(10);
		
		JLabel lblCodLibro = new JLabel("ISBN:");
		lblCodLibro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCodLibro.setBackground(new Color(128, 128, 128));
		lblCodLibro.setOpaque(true);
		lblCodLibro.setBounds(61, 69, 45, 19);
		contentPanel.add(lblCodLibro);
		
		txtLibro = new JTextField();
		txtLibro.setBounds(317, 98, 124, 23);
		contentPanel.add(txtLibro);
		txtLibro.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar Libro");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {
					String isbn =txtIsbn.getText();
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar al autor con ID: " + isbn, "Eliminacion Libro", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						Libros_DAO libroDAO = new Libros_DAO();
						if(libroDAO.borrar(isbn)) {
							JOptionPane.showMessageDialog(null, "Autor Eliminado exitosamente", "Eliminacion Libro", JOptionPane.INFORMATION_MESSAGE);
							 txtIsbn.setText(" ");
							 txtLibro.setText(" ");
							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "El Autor no ha podido ser eliminado exitosamente", "Eliminacion Autor", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Eliminacion Autor Cancelada", "Eliminacion Autor", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnEliminar.setBounds(192, 211, 113, 20);
		contentPanel.add(btnEliminar);
		
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
		
		JButton btBuscar = new JButton("Listado Libros");
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="eliminar";
				ListadoLibros dialog = new ListadoLibros(accion);
				dialog.setVisible(true);
			}
		});
		btBuscar.setBounds(190, 166, 115, 25);
		contentPanel.add(btBuscar);
		

	}
	public void buscarLibroISBN(String isbn) {

	    Libros_DAO libroDAO = new Libros_DAO();
	    model.Libro libro = libroDAO.buscar(isbn);

	    txtIsbn.setText(isbn);
	    txtLibro.setText(libro.getTitulo());
	    esBuscado = true;
	}
}
