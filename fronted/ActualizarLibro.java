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
import model.Poblacion;
import modelo_DAO.Autor_DAO;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Libros_DAO;
import modelo_DAO.Poblacion_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActualizarLibro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtISBN;
	private JTextField txtCodAutor;
	private JTextField txtCodCategoria;
	private JTextField txtCodEditorial;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextField txtTitulo;
	private boolean esBuscado=false;
	private JTextField txtPoblacion;
	private JTextField txtFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActualizarLibro dialog = new ActualizarLibro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActualizarLibro() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 650, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel lblTitulo = new JLabel("Nuevo Libro");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(223, 10, 96, 35);
		contentPanel.add(lblTitulo);
		
		JLabel lbltitulo_libro = new JLabel("Titulo");
		lbltitulo_libro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltitulo_libro.setBackground(new Color(128, 128, 128));
		lbltitulo_libro.setOpaque(true);
		lbltitulo_libro.setBounds(99, 64, 60, 29);
		contentPanel.add(lbltitulo_libro);
		
		JLabel lblcodAutor = new JLabel("Cod.Autor");
		lblcodAutor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcodAutor.setBackground(new Color(128, 128, 128));
		lblcodAutor.setOpaque(true);
		lblcodAutor.setBounds(53, 159, 84, 29);
		contentPanel.add(lblcodAutor);
		
		JLabel lblcodCategoria = new JLabel("Cod.Categoria");
		lblcodCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcodCategoria.setBackground(new Color(128, 128, 128));
		lblcodCategoria.setOpaque(true);
		lblcodCategoria.setBounds(239, 159, 118, 29);
		contentPanel.add(lblcodCategoria);
		
		JLabel lblEditorial = new JLabel("Cod.Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEditorial.setBackground(new Color(128, 128, 128));
		lblEditorial.setOpaque(true);
		lblEditorial.setBounds(27, 275, 110, 29);
		contentPanel.add(lblEditorial);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecio.setBackground(new Color(128, 128, 128));
		lblPrecio.setOpaque(true);
		lblPrecio.setBounds(235, 276, 60, 27);
		contentPanel.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStock.setBackground(new Color(128, 128, 128));
		lblStock.setOpaque(true);
		lblStock.setBounds(372, 276, 50, 27);
		contentPanel.add(lblStock);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIsbn.setBackground(new Color(128, 128, 128));
		lblIsbn.setOpaque(true);
		lblIsbn.setBounds(418, 75, 50, 18);
		contentPanel.add(lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(389, 103, 108, 18);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		
		
		txtCodAutor = new JTextField();
		txtCodAutor.setBounds(41, 198, 118, 18);
		contentPanel.add(txtCodAutor);
		txtCodAutor.setColumns(10);
		
		
		txtCodCategoria = new JTextField();
		txtCodCategoria.setBounds(249, 198, 118, 18);
		contentPanel.add(txtCodCategoria);
		txtCodCategoria.setColumns(10);
		

		txtCodEditorial = new JTextField();
		txtCodEditorial.setBounds(27, 317, 138, 20);
		contentPanel.add(txtCodEditorial);
		txtCodEditorial.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(223, 317, 96, 18);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		
		txtStock = new JTextField();
		txtStock.setBounds(358, 317, 96, 18);
		contentPanel.add(txtStock);
		txtStock.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(84, 103, 96, 18);
		contentPanel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 128, 128));
		btnVolver.setOpaque(true);
		btnVolver.setBounds(10, 460, 84, 20);
		contentPanel.add(btnVolver);
		
		JButton btnBuscar = new JButton("Buscar Libro");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="modificar";
				ListadoLibros dialog = new ListadoLibros(accion);
				dialog.setVisible(true);
			}
		});
		btnBuscar.setBounds(140, 406, 110, 29);
		contentPanel.add(btnBuscar);
		
		JButton btnModificar = new JButton("Modificar Libro");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {
		            Libros_DAO libroDAO = new Libros_DAO();
		            Libro libroExistente = libroDAO.buscarConRelaciones(txtISBN.getText());
		            // Ejemplo (debes crear estos DAOs si no los tienes):
		            Autor_DAO autorDAO = new Autor_DAO();
		            Categoria_DAO categoriaDAO = new Categoria_DAO();
		            Editorial_DAO editorialDAO = new Editorial_DAO();
		            
		            // 3. Obtener las entidades desde la base de datos
		            Autor autor = autorDAO.buscar(Integer.parseInt(txtCodAutor.getText()));
		            Categoria categoria = categoriaDAO.buscar(Integer.parseInt(txtCodCategoria.getText()));
		            Editorial editorial = editorialDAO.buscar(Integer.parseInt(txtCodEditorial.getText()));
		            

		            Poblacion_DAO poblacionDAO = new Poblacion_DAO();
		            Poblacion poblacion = poblacionDAO.buscar(Integer.parseInt(txtPoblacion.getText()));
					
		            libroExistente.setTitulo(txtTitulo.getText());
		            libroExistente.setPrecio(Double.parseDouble(txtPrecio.getText()));
		            libroExistente.setStock(Integer.parseInt(txtStock.getText()));
		            libroExistente.setAutor(autor);
		            libroExistente.setCategoria(categoria);
		            libroExistente.setEditorial(editorial);
		            libroExistente.setPoblacion(poblacion);
					
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas modificar al libro con ISBN: " + libroExistente.getIsbn(), "Modificacion Libro", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						if(libroDAO.actualizar(libroExistente)) {
							JOptionPane.showMessageDialog(null, "Libro Modificado exitosamente", "Modificaion Libro", JOptionPane.INFORMATION_MESSAGE);
							 txtISBN.setText(" ");
							 txtTitulo.setText(" ");
							 txtCodAutor.setText(" ");
							 txtCodCategoria.setText(" ");
							 txtCodEditorial.setText(" ");
							 txtPrecio.setText(" ");
							 txtStock.setText(" ");
							 txtPoblacion.setText(" ");
							 txtFecha.setText(" ");
							 
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
		
		btnModificar.setBounds(306, 407, 126, 27);
		contentPanel.add(btnModificar);
		
		JLabel lblPoblacion = new JLabel("Cod.Poblacion");
		lblPoblacion.setOpaque(true);
		lblPoblacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPoblacion.setBackground(Color.GRAY);
		lblPoblacion.setBounds(431, 159, 118, 29);
		contentPanel.add(lblPoblacion);
		
		txtPoblacion = new JTextField();
		txtPoblacion.setColumns(10);
		txtPoblacion.setBounds(441, 198, 118, 18);
		contentPanel.add(txtPoblacion);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(493, 317, 118, 18);
		contentPanel.add(txtFecha);
		
		JLabel lblFechaEdicion = new JLabel("Fecha Edicion");
		lblFechaEdicion.setOpaque(true);
		lblFechaEdicion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaEdicion.setBackground(Color.GRAY);
		lblFechaEdicion.setBounds(493, 277, 118, 27);
		contentPanel.add(lblFechaEdicion);
		
		
	}
	public void buscarLibroISBN(String isbn) {

	    Libros_DAO libroDAO = new Libros_DAO();
	    model.Libro libro = libroDAO.buscarConRelaciones(isbn); // Asegúrate de que este método haga JOIN FETCH

	    if(libro != null) {
	        Autor autor = libro.getAutor();
	        Categoria categoria = libro.getCategoria();
	        Editorial editorial = libro.getEditorial();
	        //Poblacion poblacion = libro.getPoblacion();

	        txtISBN.setText(libro.getIsbn());
	        txtTitulo.setText(libro.getTitulo());
	        txtCodAutor.setText(String.valueOf(autor.getCodAutor()));	
	        txtCodCategoria.setText(String.valueOf(categoria.getCodCategoria()));
	        txtCodEditorial.setText(String.valueOf(editorial.getCodEditorial()));
	        txtPrecio.setText(String.valueOf(libro.getPrecio()));
	        txtStock.setText(String.valueOf(libro.getStock()));
	        txtPoblacion.setText(String.valueOf(libro.getPoblacion().getId_poblacion()));
	        txtFecha.setText(String.valueOf(libro.getFecha_edicion()));

	        esBuscado = true;
	    } else {
	        JOptionPane.showMessageDialog(this, "No se encontró el libro con ISBN: " + isbn);
	    }
	}
}
