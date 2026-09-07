package fronted;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Pais;
import modelo_DAO.Pais_DAO;
import modelo_DAO.Poblacion_DAO;

import javax.swing.JLabel;
import javax.swing.JTable;
import model.Poblacion;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoPoblacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String accion;
	private JTable table;
	private InsertarLibro insertarLibro;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListadoPoblacion dialog = new ListadoPoblacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public ListadoPoblacion(String accion) {
		this.accion=accion;
		inicializar();
		
	}
	public ListadoPoblacion(InsertarLibro insertarLibro,String accion) {
		inicializar();
		this.insertarLibro = insertarLibro;
		this.accion=accion;
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
		
		JLabel lblTitulo = new JLabel(" Listado Poblacion");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(224, 27, 147, 35);
		contentPanel.add(lblTitulo);
		
		JButton btnConfirma = new JButton("Confirma");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escogerFila = table.getSelectedRow();
				if (escogerFila != -1) {
					if(accion.equals("modificar")) {
						int idPoblacion = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
					    
					    ActualizarPoblacion actualizaPoblacion = new ActualizarPoblacion();
					    actualizaPoblacion.buscarPoblacionPorId(idPoblacion);
					    actualizaPoblacion.setVisible(true);	
					    dispose();
					}else if (accion.equals(("insertar"))){
						int idPoblacion = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						insertarLibro.buscarPoblacionId(idPoblacion);
						dispose();			
					}else {
					    int idPoblacion = Short.parseShort(table.getValueAt(escogerFila, 0).toString());
						
						EliminarPoblacion eliminarPoblacion = new EliminarPoblacion();
						eliminarPoblacion.buscarPoblacionPorId(idPoblacion);
						eliminarPoblacion.setVisible(true);					
						dispose();
					}
				}
			}
		});
		btnConfirma.setBounds(224, 286, 117, 28);
		contentPanel.add(btnConfirma);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 98, 517, 144);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		inicializarTabla();
		cargarListaPoblacion();


	}
	private void inicializarTabla() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID_POBLACION");
		model.addColumn("NOMBRE POBLACION");
		model.addColumn("ID PAIS");
	    table.setModel(model);
	}
	private void cargarListaPoblacion() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		Poblacion_DAO dao = new Poblacion_DAO();
		ArrayList<Poblacion> poblaciones = dao.listarTodos();

		for (Poblacion poblacion : poblaciones) {
			model.addRow(new Object[] { poblacion.getId_poblacion(),poblacion.getNomPoblacion(),poblacion.getId_pais()});
		}
	}
}
