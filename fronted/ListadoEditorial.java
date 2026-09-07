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
import model.Editorial;
import modelo_DAO.Autor_DAO;
import modelo_DAO.Editorial_DAO;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListadoEditorial extends JDialog {

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
			ListadoEditorial dialog = new ListadoEditorial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public ListadoEditorial(String accion) {
		this.accion=accion;
		inicializar();
		
	}
	public ListadoEditorial(String accion, InsertarLibro insertarLibro) {
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
		{
			JLabel lblTitulo = new JLabel(" Listado Editorial");
			lblTitulo.setOpaque(true);
			lblTitulo.setBackground(new Color(255, 214, 165));
			lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
			lblTitulo.setBounds(226, 10, 125, 35);
			contentPanel.add(lblTitulo);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 76, 463, 164);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConfirma = new JButton("Confirma");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escogerFila = table.getSelectedRow();
				if (escogerFila != -1) {
					if(accion.equals("modificar")) {
						int idEditorial = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
					    
					    ActualizarEditoriales actualizaEditorial = new ActualizarEditoriales();
					    actualizaEditorial.buscarEditorialPorId(idEditorial);
					    actualizaEditorial.setVisible(true);	
					    dispose();
					}else if (accion.equals(("insertar"))){
						int idEditorial = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						insertarLibro.buscarEditoriaLibrolPorId(idEditorial);
						dispose();						
					}else {
					    int idEditorial = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						EliminarEditorial eliminarEditorial = new EliminarEditorial();
						eliminarEditorial.buscarEditorialPorId(idEditorial);
						eliminarEditorial.setVisible(true);					
						dispose();
					}
				}
			}
		});
		btnConfirma.setBounds(247, 261, 84, 20);
		contentPanel.add(btnConfirma);
		inicializarTabla();
		cargarListaEditoriales();

	}
	private void inicializarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID EDITORIAL");
		model.addColumn("EDITORIAL");
		table.setModel(model);
	}
	private void cargarListaEditoriales() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		Editorial_DAO dao = new Editorial_DAO();
		ArrayList<Editorial> editoriales = dao.listarTodos();

		for (Editorial editorial : editoriales) {
			model.addRow(new Object[] { editorial.getCodEditorial(), editorial.getNomEditorial() });
		}
	}
}
