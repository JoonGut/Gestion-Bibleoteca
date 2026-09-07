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

import model.Autor;
import modelo_DAO.Autor_DAO;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListadoAutores extends JDialog {

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
			ListadoAutores dialog = new ListadoAutores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public ListadoAutores(String accion) {
		this.accion=accion;
		inicializar();
		
	}
	public ListadoAutores(String accion, InsertarLibro insertarLibro) {
		this.accion=accion;
		this.insertarLibro = insertarLibro;
		inicializar();
		
	}


	/**
	 * Create the dialog.
	 */
	public void inicializar() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 756, 404);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel(" Listado Autores");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(307, 10, 125, 35);
		contentPanel.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 66, 487, 173);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escogerFila = table.getSelectedRow();
				if (escogerFila != -1) {
					if(accion.equals("modificar")) {
						int idAutor = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
					    
					    ActualizarAutor actualizarAutor = new ActualizarAutor();
					    actualizarAutor.buscarAutorPorId(idAutor);
					    actualizarAutor.setVisible(true);	
					    dispose();
					}else if (accion.equals(("insertar"))){
						int idEditorial = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						insertarLibro.buscarAutorLibroPorId(idEditorial);
						dispose();						
					}else {
					    int idAutor = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						EliminarAutor eliminarAutor = new EliminarAutor();
						eliminarAutor.buscarAutorPorId(idAutor);
					    eliminarAutor.setVisible(true);					
						dispose();
					}
				}
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfirmar.setBounds(307, 266, 125, 27);
		contentPanel.add(btnConfirmar);
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		inicializarTabla();
		cargarListaAutores();
		
	}
	private void inicializarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID AUTOR");
		model.addColumn("NOMBRE");
		table.setModel(model);
	}
	private void cargarListaAutores() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		Autor_DAO dao = new Autor_DAO();
		ArrayList<Autor> autores = dao.listarTodos();

		for (Autor autor : autores) {
			model.addRow(new Object[] { autor.getCodAutor(), autor.getNomAutor()});
		}
	}
}
