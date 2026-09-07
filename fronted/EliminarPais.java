package fronted;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Pais_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarPais extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCod;
	private boolean esBuscado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarPais dialog = new EliminarPais();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarPais() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Eliminado Pais");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(177, 10, 123, 35);
		contentPanel.add(lblTitulo);
		
		JButton btBuscar = new JButton("Listado Paises");
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="eliminar";
				ListadoPais dialog = new ListadoPais(accion);
				dialog.setVisible(true);
			}
		});
		btBuscar.setBounds(177, 173, 123, 25);
		contentPanel.add(btBuscar);
		
		JLabel lblCod = new JLabel("Codigo pais");
		lblCod.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCod.setBackground(new Color(128, 128, 128));
		lblCod.setOpaque(true);
		lblCod.setBounds(56, 72, 111, 25);
		contentPanel.add(lblCod);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBackground(new Color(128, 128, 128));
		lblNombre.setOpaque(true);
		lblNombre.setBounds(304, 72, 66, 25);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(280, 107, 111, 25);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCod = new JTextField();
		txtCod.setBounds(56, 107, 111, 25);
		contentPanel.add(txtCod);
		txtCod.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar Pais");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int idPais = Short.parseShort(txtCod.getText());
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar el pais con ID: " + idPais, "Eliminacion Pais", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						Pais_DAO paisDAO = new Pais_DAO();
						if(paisDAO.borrar(idPais)) {
							JOptionPane.showMessageDialog(null, "Pais Eliminado exitosamente", "Eliminacion Pais", JOptionPane.INFORMATION_MESSAGE);
							 txtCod.setText(" ");
							 txtNombre.setText(" ");
							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "El Pais no ha podido ser eliminado exitosamente", "Eliminacion Pais", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Eliminacion Pais Cancelada", "Eliminacion Pais", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnEliminar.setBounds(177, 223, 123, 24);
		contentPanel.add(btnEliminar);
		
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
	public void buscarPaisPorId(int idPais) {

	    Pais_DAO paisDAO = new Pais_DAO();
	    model.Pais pais = paisDAO.buscar(idPais);

	    txtCod.setText(String.valueOf(idPais));
	    txtNombre.setText(pais.getNomPais());
	    esBuscado = true;
	}

}
