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

import modelo_DAO.Autor_DAO;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EliminarCategoria extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String accion;
	private JTextField txtCod;
	private JTextField txtNombre;
	private boolean esBuscado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarCategoria dialog = new EliminarCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarCategoria() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("Eliminado Categoria");
			lblTitulo.setBackground(new Color(255, 214, 165));
			lblTitulo.setOpaque(true);
			lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
			lblTitulo.setBounds(177, 10, 144, 35);
			contentPanel.add(lblTitulo);
		}
		{
			JLabel lblCod = new JLabel("Codigo Categoria");
			lblCod.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCod.setBackground(new Color(128, 128, 128));
			lblCod.setOpaque(true);
			lblCod.setBounds(56, 72, 134, 25);
			contentPanel.add(lblCod);
		}
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombre.setBackground(new Color(128, 128, 128));
			lblNombre.setOpaque(true);
			lblNombre.setBounds(304, 72, 66, 25);
			contentPanel.add(lblNombre);
		}
		{
			txtCod = new JTextField();
			txtCod.setBounds(280, 107, 124, 25);
			contentPanel.add(txtCod);
			txtCod.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(66, 107, 115, 25);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JButton btBuscar = new JButton("Listado Categorias");
			btBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String accion="eliminar";
					ListadoCategoria dialog = new ListadoCategoria(accion);
					dialog.setVisible(true);
				}
			});
			btBuscar.setBounds(177, 167, 131, 25);
			contentPanel.add(btBuscar);
		}
		{
			JButton btnEliminar = new JButton("Eliminar Categoria");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (esBuscado==false) {
						JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
					}else {
						int idCategoria = Short.parseShort(txtCod.getText());
						int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar la categoria con ID: " + idCategoria, "Eliminacion Categoria", JOptionPane.YES_NO_OPTION);
						if (respuesta == JOptionPane.YES_OPTION) {
							Categoria_DAO categoriaDAO = new Categoria_DAO();
							if(categoriaDAO.borrar(idCategoria)) {
								JOptionPane.showMessageDialog(null, "Categoria Eliminado exitosamente", "Eliminacion Autor", JOptionPane.INFORMATION_MESSAGE);
								 txtCod.setText(" ");
								 txtNombre.setText(" ");
								 esBuscado=false;
							}else {
								JOptionPane.showMessageDialog(null, "La Categoria no ha podido ser eliminado exitosamente", "Categoria Autor", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Eliminacion Categoria Cancelada", "Categoria Autor", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnEliminar.setBounds(196, 213, 84, 20);
			contentPanel.add(btnEliminar);
		}
		{
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

	}
	public void buscarCategorialPorId(int idCategoria) {

	    Categoria_DAO categoriaDAO = new Categoria_DAO();
	    model.Categoria categoria = categoriaDAO.buscar(idCategoria);

	    txtCod.setText(String.valueOf(idCategoria));
	    txtNombre.setText(categoria.getNomCategoria());
	    esBuscado = true;
	}

}
