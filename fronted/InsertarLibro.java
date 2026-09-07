package fronted;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import model.Poblacion;
import modelo_DAO.Autor_DAO;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Libros_DAO;
import modelo_DAO.Poblacion_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class InsertarLibro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel btnEditorial = new JPanel();
	private JTextField txtCodCategoria;
	private JTextField txtTitulo;
	private JTextField txtCodAutor;
	private JTextField txtCodEditorial;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextField txtISBN;
	private JTextField txtPoblacion;
	private JTextField txtFecha;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertarLibro dialog = new InsertarLibro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertarLibro() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 650, 550);
		getContentPane().setLayout(new BorderLayout());
		btnEditorial.setBorder(new EmptyBorder(5, 5, 5, 5));
		btnEditorial.setBackground(new Color(0, 0, 128));
		getContentPane().add(btnEditorial, BorderLayout.CENTER);
		btnEditorial.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nuevo Libro");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(246, 10, 129, 35);
		btnEditorial.add(lblTitulo);
		
		JLabel lbltitulo_libro = new JLabel("Titulo");
		lbltitulo_libro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltitulo_libro.setBackground(new Color(128, 128, 128));
		lbltitulo_libro.setOpaque(true);
		lbltitulo_libro.setBounds(369, 72, 60, 29);
		btnEditorial.add(lbltitulo_libro);

		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 128, 128));
		btnVolver.setOpaque(true);
		btnVolver.setBounds(27, 451, 95, 35);
		btnEditorial.add(btnVolver);
		
		JLabel lblcodAutor = new JLabel("Cod.Autor");
		lblcodAutor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcodAutor.setBackground(new Color(128, 128, 128));
		lblcodAutor.setOpaque(true);
		lblcodAutor.setBounds(27, 181, 95, 29);
		btnEditorial.add(lblcodAutor);
		
		txtCodCategoria = new JTextField();
		txtCodCategoria.setBounds(246, 220, 119, 20);
		btnEditorial.add(txtCodCategoria);
		txtCodCategoria.setColumns(10);
		
		JLabel lblcodCategoria = new JLabel("Cod.Categoria");
		lblcodCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcodCategoria.setBackground(new Color(128, 128, 128));
		lblcodCategoria.setOpaque(true);
		lblcodCategoria.setBounds(246, 181, 129, 29);
		btnEditorial.add(lblcodCategoria);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(337, 110, 138, 20);
		btnEditorial.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtCodAutor = new JTextField();
		txtCodAutor.setBounds(27, 222, 139, 18);
		btnEditorial.add(txtCodAutor);
		txtCodAutor.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Cod.Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEditorial.setBackground(new Color(128, 128, 128));
		lblEditorial.setOpaque(true);
		lblEditorial.setBounds(10, 295, 110, 29);
		btnEditorial.add(lblEditorial);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecio.setBackground(new Color(128, 128, 128));
		lblPrecio.setOpaque(true);
		lblPrecio.setBounds(207, 296, 60, 27);
		btnEditorial.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStock.setBackground(new Color(128, 128, 128));
		lblStock.setOpaque(true);
		lblStock.setBounds(360, 296, 50, 27);
		btnEditorial.add(lblStock);
		
		txtCodEditorial = new JTextField();
		txtCodEditorial.setBounds(10, 334, 138, 20);
		btnEditorial.add(txtCodEditorial);
		txtCodEditorial.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(194, 334, 96, 20);
		btnEditorial.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(337, 334, 109, 20);
		btnEditorial.add(txtStock);
		txtStock.setColumns(10);
		
		JButton btnInsertar = new JButton("Confirmar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validarCampos()) {
					JOptionPane.showMessageDialog(null, "Rellena correctamente los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}else {
					try {
						//Autor_DAO autorDAO = new Autor_DAO(Byte.parseByte(tfTienda.getText()), tfNombre.getText(), tfApellido.getText(), tfEmail.getText(),Short.parseShort(tfDireccion.getText()), Boolean.parseBoolean(tfActivo.getText()),fecha,modificacion);
						
						Libro libro = new Libro();
						libro.setTitulo(txtTitulo.getText());
						libro.setStock(Short.parseShort(txtStock.getText()));
						libro.setPrecio(Double.parseDouble(txtPrecio.getText()));
						libro.setIsbn(txtISBN.getText());
						/*GESTION DE FECHA*/
						try {
						    java.sql.Date fechaSql = java.sql.Date.valueOf(txtFecha.getText());
						    libro.setFecha_edicion(fechaSql);
						} catch (IllegalArgumentException err) {
						    JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Usa yyyy-MM-dd", "Error Fecha", JOptionPane.ERROR_MESSAGE);
						}
						
						Poblacion poblacion = new Poblacion();
						poblacion.setId_poblacion(Integer.parseInt(txtPoblacion.getText()));
						
						
						Editorial editorial = new Editorial();
						editorial.setCodEditorial(Integer.parseInt(txtCodEditorial.getText()));
						
						Autor autor = new Autor();
						autor.setCodAutor(Integer.parseInt(txtCodAutor.getText()));
						
						Categoria categoria = new Categoria();
						categoria.setCodCategoria(Integer.parseInt(txtCodCategoria.getText()));
						
						//Poblacion poblacion = new Poblacion();
						//poblacion.setId_poblacion(Integer.parseInt(txtCodCategoria.getText()));
						
						libro.setAutor(autor);
						libro.setEditorial(editorial);
						libro.setCategoria(categoria);
						libro.setPoblacion(poblacion);
						
						
						
						Libros_DAO libroDAO = new Libros_DAO();
						
						if (libroDAO.insertar(libro)) {
							 JOptionPane.showMessageDialog(null, "Alta Correcta ", "ALTAS", JOptionPane.INFORMATION_MESSAGE);
							 txtCodAutor.setText(" ");
							 txtCodAutor.setText(" ");
							 txtCodCategoria.setText(" ");
							 txtPrecio.setText(" ");
							 txtStock.setText(" ");
							 txtTitulo.setText(" ");
							 txtCodEditorial.setText("");
							 txtFecha.setText(" ");
							 txtPoblacion.setText(" ");
							 
							 
						}else {
							 JOptionPane.showMessageDialog(null, "Alta Incorrecta ", "ALTAS", JOptionPane.ERROR_MESSAGE);
						}
					
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				
			}		
				
		});
		btnInsertar.setBounds(256, 401, 154, 46);
		btnEditorial.add(btnInsertar);
		
		JButton btnBuscarEditorial = new JButton("");
		btnBuscarEditorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="insertar";
				ListadoEditorial dialog = new ListadoEditorial(accion, InsertarLibro.this);
				dialog.setVisible(true);
			}
		});
		btnBuscarEditorial.setIcon(new ImageIcon(InsertarLibro.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnBuscarEditorial.setBounds(124, 304, 24, 20);
		btnEditorial.add(btnBuscarEditorial);
		
		JButton btnBuscarLibro = new JButton("");
		btnBuscarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="insertar";
				ListadoAutores dialog = new ListadoAutores(accion, InsertarLibro.this);
				dialog.setVisible(true);
			}
		});
		btnBuscarLibro.setIcon(new ImageIcon(InsertarLibro.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnBuscarLibro.setBounds(148, 190, 24, 20);
		btnEditorial.add(btnBuscarLibro);
		
		JButton btnBuscarCategoria = new JButton("");
		btnBuscarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="insertar";
				ListadoCategoria dialog = new ListadoCategoria(accion, InsertarLibro.this);
				dialog.setVisible(true);
			}
		});
		btnBuscarCategoria.setIcon(new ImageIcon(InsertarLibro.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnBuscarCategoria.setBounds(387, 190, 24, 20);
		btnEditorial.add(btnBuscarCategoria);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIsbn.setBackground(new Color(128, 128, 128));
		lblIsbn.setOpaque(true);
		lblIsbn.setBounds(148, 77, 50, 18);
		btnEditorial.add(lblIsbn);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(124, 110, 105, 20);
		btnEditorial.add(txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblPoblacion = new JLabel("Cod.Poblacion");
		lblPoblacion.setOpaque(true);
		lblPoblacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPoblacion.setBackground(Color.GRAY);
		lblPoblacion.setBounds(449, 181, 129, 29);
		btnEditorial.add(lblPoblacion);
		
		txtPoblacion = new JTextField();
		txtPoblacion.setColumns(10);
		txtPoblacion.setBounds(459, 222, 119, 20);
		btnEditorial.add(txtPoblacion);
		
		JButton btnBuscarPoblacion = new JButton("");
		btnBuscarPoblacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String accion="insertar";
				ListadoPoblacion dialog = new ListadoPoblacion(InsertarLibro.this, accion);
				dialog.setVisible(true);
			}
		});
		btnBuscarPoblacion.setIcon(new ImageIcon(InsertarLibro.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnBuscarPoblacion.setBounds(588, 190, 24, 20);
		btnEditorial.add(btnBuscarPoblacion);
		
		JLabel lblFechaEdicion = new JLabel("Fech Edicion");
		lblFechaEdicion.setOpaque(true);
		lblFechaEdicion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaEdicion.setBackground(Color.GRAY);
		lblFechaEdicion.setBounds(493, 295, 119, 27);
		btnEditorial.add(lblFechaEdicion);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(503, 334, 109, 20);
		btnEditorial.add(txtFecha);

	}
	public void buscarEditoriaLibrolPorId(int idEditorial) {

	    Editorial_DAO editorialDAO = new Editorial_DAO();
	    model.Editorial editorial = editorialDAO.buscar(idEditorial);

	    txtCodEditorial.setText(String.valueOf(idEditorial));
	    //txtCodEditorial.setText(editorial.getNomEditorial());
	    //esBuscado = true;
	}
	public void buscarAutorLibroPorId(int idAutor) {

	    Autor_DAO autorDAO = new Autor_DAO();
	    model.Autor autor = autorDAO.buscar(idAutor);

	    txtCodAutor.setText(String.valueOf(idAutor));
	    //txtCodAutor.setText(autor.getNomAutor());
	    //esBuscado = true;
	}
	public void buscarCategoriaLibrolPorId(int idCategoria) {

	    Categoria_DAO categoriaDAO = new Categoria_DAO();
	    model.Categoria categoria = categoriaDAO.buscar(idCategoria);

	    txtCodCategoria.setText(String.valueOf(idCategoria));
	    //txtCodCategoria.setText(categoria.getNomCategoria());
	    //esBuscado = true;
	}
	public void buscarPoblacionId(int idPoblacion) {

	    Poblacion_DAO poblacionDAO = new Poblacion_DAO();
	    model.Poblacion poblacion = poblacionDAO.buscar(idPoblacion);

	    txtPoblacion.setText(String.valueOf(idPoblacion));
	    //txtCodCategoria.setText(categoria.getNomCategoria());
	    //esBuscado = true;
	}
	public boolean validarCampos() {
		if (txtCodAutor.getText().trim().isEmpty() || txtCodCategoria.getText().trim().isEmpty() || txtCodEditorial.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty() || txtStock.getText().trim().isEmpty() || txtTitulo.getText().trim().isEmpty() || txtISBN.getText().trim().isEmpty() || txtPoblacion.getText().trim().isEmpty() || txtFecha.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}
	

}
