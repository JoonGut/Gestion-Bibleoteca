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

import model.Categoria;
import model.Editorial;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActualizarCategoria extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCod;
	private JTextField txtNombre;
	private boolean esBuscado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActualizarCategoria dialog = new ActualizarCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActualizarCategoria() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificacion Categoria");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(177, 10, 182, 35);
		contentPanel.add(lblTitulo);
		
		JLabel lblCod = new JLabel("Codigo Categoria");
		lblCod.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCod.setBackground(new Color(128, 128, 128));
		lblCod.setOpaque(true);
		lblCod.setBounds(58, 102, 146, 25);
		contentPanel.add(lblCod);

		JLabel lblNombre = new JLabel("Categoria");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBackground(new Color(128, 128, 128));
		lblNombre.setOpaque(true);
		lblNombre.setBounds(327, 102, 85, 25);
		contentPanel.add(lblNombre);
		
		txtCod = new JTextField();
		txtCod.setBounds(49, 144, 155, 25);
		contentPanel.add(txtCod);
		txtCod.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(303, 137, 140, 25);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Listado Categorias");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="modificar";
				ListadoCategoria dialog = new ListadoCategoria(accion);
				dialog.setVisible(true);
			}
		});
		btnBuscar.setBounds(193, 200, 155, 25);
		contentPanel.add(btnBuscar);
		
		JButton btnActualizar = new JButton("Actualizar Categoria");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {

		            Categoria categoria = new Categoria();
		            
		            // 3. Obtener las entidades desde la base de datos
		            categoria.setCodCategoria(Integer.parseInt(txtCod.getText()));
		            categoria.setNomCategoria(txtNombre.getText());
					
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas modificar al la categoria con ID: " + categoria.getCodCategoria(), "Modificacion Libro", JOptionPane.YES_NO_OPTION);
					Categoria_DAO categoriaDAO = new Categoria_DAO();
					if (respuesta == JOptionPane.YES_OPTION) {
						if(categoriaDAO.actualizar(categoria)) {
							JOptionPane.showMessageDialog(null, "Categoria Modificado exitosamente", "Modificaion Categoria", JOptionPane.INFORMATION_MESSAGE);
							txtCod.setText(" ");
							 txtNombre.setText(" ");

							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "La categoria no ha podido ser modificado exitosamente", "Modificacion Categoria", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Modificacion Categoria Cancelada", "Modificacion Categoria", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			
		});
		btnActualizar.setBounds(193, 260, 155, 25);
		contentPanel.add(btnActualizar);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 128, 128));
		btnVolver.setOpaque(true);
		btnVolver.setBounds(10, 310, 84, 20);
		contentPanel.add(btnVolver);

	}
	public void buscarCategorialPorId(int idCategoria) {

	    Categoria_DAO categoriaDAO = new Categoria_DAO();
	    model.Categoria categoria = categoriaDAO.buscar(idCategoria);

	    txtCod.setText(String.valueOf(idCategoria));
	    txtNombre.setText(categoria.getNomCategoria());
	    esBuscado = true;
	}

}
