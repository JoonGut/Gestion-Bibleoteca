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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EliminarAutor extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	boolean esBuscado=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarAutor dialog = new EliminarAutor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarAutor() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("Eliminado Autor");
			lblTitulo.setBackground(new Color(255, 214, 165));
			lblTitulo.setOpaque(true);
			lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
			lblTitulo.setBounds(177, 10, 117, 35);
			contentPanel.add(lblTitulo);
		}
		{
			JLabel lblCod = new JLabel("Codigo autor");
			lblCod.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCod.setBackground(new Color(128, 128, 128));
			lblCod.setOpaque(true);
			lblCod.setBounds(56, 72, 117, 25);
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
		
		txtId = new JTextField();
		txtId.setBounds(56, 121, 117, 25);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(285, 121, 117, 25);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		
		JButton btBuscar = new JButton("Listado Autores");
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="eliminar";
				ListadoAutores dialog = new ListadoAutores(accion);
				dialog.setVisible(true);
			}
		});
		btBuscar.setBounds(163, 167, 131, 25);
		contentPanel.add(btBuscar);
		
		JButton btnEliminar = new JButton("Eliminar Autor");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int idAutor = Short.parseShort(txtId.getText());
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar al autor con ID: " + idAutor, "Eliminacion Autor", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						Autor_DAO autorDAO = new Autor_DAO();
						if(autorDAO.borrar(idAutor)) {
							JOptionPane.showMessageDialog(null, "Autor Eliminado exitosamente", "Eliminacion Autor", JOptionPane.INFORMATION_MESSAGE);
							 txtId.setText(" ");
							 txtNombre.setText(" ");
							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "El Autor no ha podido ser eliminado exitosamente", "Eliminacion Autor", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Eliminacion Autor Cancelada", "Eliminacion Autor", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnEliminar.setForeground(new Color(255, 0, 0));
		btnEliminar.setBounds(163, 241, 131, 25);
		contentPanel.add(btnEliminar);
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
	public void buscarAutorPorId(int idAutor) {

	    Autor_DAO autorDAO = new Autor_DAO();
	    model.Autor autor = autorDAO.buscar(idAutor);

	    txtId.setText(String.valueOf(idAutor));
	    txtNombre.setText(autor.getNomAutor());
	    esBuscado = true;
	}


}
