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

import model.Categoria;
import model.Editorial;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListadoCategoria extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String accion;
	private InsertarLibro insertarLibro; 

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListadoCategoria dialog = new ListadoCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public ListadoCategoria(String accion) {
		this.accion=accion;
		inicializar();
		
	}
	public ListadoCategoria(String accion, InsertarLibro insertarLibro) {
		this.accion=accion;
		this.insertarLibro = insertarLibro;
		inicializar();
		
	}

	/**
	 * Create the dialog.
	 */
	public void inicializar() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 600, 404);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel(" Listado Categorias");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(214, 10, 155, 35);
		contentPanel.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 100, 432, 111);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConfirma = new JButton("Confirmar");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escogerFila = table.getSelectedRow();
				if (escogerFila != -1) {
					if(accion.equals("modificar")) {
						int idCategoria = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
					    
					    ActualizarCategoria actualizaCategoria = new ActualizarCategoria();
					    actualizaCategoria.buscarCategorialPorId(idCategoria);
					    actualizaCategoria.setVisible(true);	
					    dispose();
					}else if (accion.equals(("insertar"))){
						int idEditorial = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						insertarLibro.buscarCategoriaLibrolPorId(idEditorial);
						dispose();						
					}else {
					    int idCategoria = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						EliminarCategoria eliminarCategoria = new EliminarCategoria();
						eliminarCategoria.buscarCategorialPorId(idCategoria);
						eliminarCategoria.setVisible(true);					
						dispose();
					}
				}
			}
		});
		btnConfirma.setBounds(249, 249, 109, 27);
		contentPanel.add(btnConfirma);
		inicializarTabla();
		cargarListaCategorias();
	}
	private void inicializarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID CATEGORIA");
		model.addColumn("CATEGORIA");
		table.setModel(model);
	}
	private void cargarListaCategorias() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		Categoria_DAO dao = new Categoria_DAO();
		ArrayList<Categoria> categorias = dao.listarTodos();

		for (Categoria categoria : categorias) {
			model.addRow(new Object[] { categoria.getCodCategoria(), categoria.getNomCategoria() });
		}
	}
}
