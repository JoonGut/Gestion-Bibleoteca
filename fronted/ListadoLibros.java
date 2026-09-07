package fronted;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Editorial;
import model.Libro;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Libros_DAO;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListadoLibros extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String accion;

	/**
	 * Launch the application.
	 */
	public ListadoLibros(String accion) {
		this.accion=accion;
		inicializar();
		
	}
	/*public static void main(String[] args) {
		try {
			ListadoLibros dialog = new ListadoLibros();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	public void inicializar() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 600, 404);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel(" Listado Libros");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(218, 28, 125, 35);
		contentPanel.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 91, 530, 149);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escogerFila = table.getSelectedRow();
				if (escogerFila != -1) {
					if(accion.equals("modificar")) {
					    String isbn = (table.getValueAt(escogerFila, 0).toString());
					    
					    ActualizarLibro actualizarLibro = new ActualizarLibro();
					    actualizarLibro.buscarLibroISBN(isbn);
					    actualizarLibro.setVisible(true);	
						dispose();
			
					}else {
					    String isbn = (table.getValueAt(escogerFila, 0).toString());
						
						EliminarLibro eliminarLibro = new EliminarLibro();
						eliminarLibro.buscarLibroISBN(isbn);
						eliminarLibro.setVisible(true);					
						dispose();
					}
				}
			}
		});
		btnConfirmar.setBounds(218, 268, 125, 35);
		contentPanel.add(btnConfirmar);
		inicializarTabla();
		cargarListaLibros();
	}
	private void inicializarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ISBN");
		model.addColumn("TITULO");
		model.addColumn("COD AUTOR");
		model.addColumn("COD CATEGORIA");
		model.addColumn("COD EDITORIAL");
		model.addColumn("PRECIO");
		model.addColumn("FECHA EDICION");
		model.addColumn(" POBLACION");
		model.addColumn("STOCK");
		table.setModel(model);
	}
	private void cargarListaLibros() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		Libros_DAO dao = new Libros_DAO();
		ArrayList<Libro> libros = dao.listarTodos();

		for (Libro libro : libros) {
			model.addRow(new Object[] { libro.getIsbn(),libro.getTitulo(),libro.getAutor().getNomAutor(),libro.getCategoria().getNomCategoria(),libro.getEditorial().getNomEditorial(),libro.getPrecio(),libro.getFecha_edicion(),libro.getPoblacion().getNomPoblacion(),libro.getStock() });
		}
	}
	
}
