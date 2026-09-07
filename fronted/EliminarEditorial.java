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
import modelo_DAO.Editorial_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EliminarEditorial extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private boolean esBuscado=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarEditorial dialog = new EliminarEditorial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarEditorial() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("Eliminado Editorial");
			lblTitulo.setBackground(new Color(255, 214, 165));
			lblTitulo.setOpaque(true);
			lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
			lblTitulo.setBounds(177, 10, 144, 35);
			contentPanel.add(lblTitulo);
		}
		{
			JLabel lblCod = new JLabel("Codigo editorial");
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
			txtId = new JTextField();
			txtId.setBounds(271, 107, 135, 25);
			contentPanel.add(txtId);
			txtId.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(56, 107, 134, 25);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JButton btBuscar = new JButton("Listado Editoriales");
			btBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String accion="eliminar";
					ListadoEditorial dialog = new ListadoEditorial(accion);
					dialog.setVisible(true);
				}
			});
			btBuscar.setBounds(190, 166, 115, 25);
			contentPanel.add(btBuscar);
		}
		{
			JButton btnEliminar = new JButton("Eliminar Editorial");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (esBuscado==false) {
						JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
					}else {
						int idEditorial = Short.parseShort(txtId.getText());
						int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar la editorial con ID: " + idEditorial, "Eliminacion Autor", JOptionPane.YES_NO_OPTION);
						if (respuesta == JOptionPane.YES_OPTION) {
							Editorial_DAO editorialDAO = new Editorial_DAO();
							if(editorialDAO.borrar(idEditorial)) {
								JOptionPane.showMessageDialog(null, "Editorial Eliminado exitosamente", "Eliminacion Editorial", JOptionPane.INFORMATION_MESSAGE);
								 txtId.setText(" ");
								 txtNombre.setText(" ");
								 esBuscado=false;
							}else {
								JOptionPane.showMessageDialog(null, "El Autor no ha podido ser eliminado exitosamente", "Eliminacion Editorial", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Eliminacion Editorial Cancelada", "Eliminacion Editorial", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnEliminar.setForeground(new Color(255, 0, 0));
			btnEliminar.setBounds(191, 243, 114, 20);
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
	public void buscarEditorialPorId(int idEditorial) {

	    Editorial_DAO editorialDAO = new Editorial_DAO();
	    model.Editorial editorial = editorialDAO.buscar(idEditorial);

	    txtId.setText(String.valueOf(idEditorial));
	    txtNombre.setText(editorial.getNomEditorial());
	    esBuscado = true;
	}

}
