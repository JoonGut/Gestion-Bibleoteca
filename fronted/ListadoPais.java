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

import model.Libro;
import model.Pais;
import modelo_DAO.Libros_DAO;
import modelo_DAO.Pais_DAO;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListadoPais extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String accion; 
	private InsertarPoblacion insertarPoblacion;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListadoPais dialog = new ListadoPais();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public ListadoPais(String accion) {
		this.accion=accion;
		inicializar();
		
	}
	public ListadoPais (String accion, InsertarPoblacion insertarPoblacion) {
		this.accion=accion;
		 this.insertarPoblacion = insertarPoblacion;
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
		
		JLabel lblTitulo = new JLabel(" Listado Pais");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(237, 28, 113, 35);
		contentPanel.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 111, 496, 139);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConfirma = new JButton("Confirmar");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escogerFila = table.getSelectedRow();
				if (escogerFila != -1) {
					if(accion.equals("modificar")) {
						int idPais = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
					    
					    ActualizarPais actualizaPais = new ActualizarPais();
					    actualizaPais.buscarPaisPorId(idPais);
					    actualizaPais.setVisible(true);	
					    dispose();
					}else if (accion.equals(("insertar"))){
						int idPais = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						insertarPoblacion.buscarPaisPorId(idPais);
						dispose();						
					}else {
					    int idPais = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						EliminarPais eliminarPais = new EliminarPais();
						eliminarPais.buscarPaisPorId(idPais);
						eliminarPais.setVisible(true);					
						dispose();
					}
				}
			}
		});
		btnConfirma.setBounds(248, 278, 118, 28);
		contentPanel.add(btnConfirma);
		
		inicializarTabla();
		cargarListaPais();
	}
	private void inicializarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID_PAIS");
		model.addColumn("NOMBRE PAIS");
		table.setModel(model);
	}
	private void cargarListaPais() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		Pais_DAO dao = new Pais_DAO();
		ArrayList<Pais> paises = dao.listarTodos();

		for (Pais pais : paises) {
			model.addRow(new Object[] { pais.getId_pais(),pais.getNomPais()});
		}
	}
}
