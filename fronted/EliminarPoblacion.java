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

import modelo_DAO.Pais_DAO;
import modelo_DAO.Poblacion_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarPoblacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCod;
	private boolean esBuscado = false;
	private JTextField txtIdPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarPoblacion dialog = new EliminarPoblacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarPoblacion() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Eliminado Poblacion");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(171, 10, 163, 35);
		contentPanel.add(lblTitulo);
		
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
		lblNombre.setBounds(323, 72, 66, 25);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(299, 107, 111, 25);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCod = new JTextField();
		txtCod.setBounds(56, 107, 111, 25);
		contentPanel.add(txtCod);
		txtCod.setColumns(10);
		
		JButton btnBuscar = new JButton("Listado Poblacion");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="eliminar";
				ListadoPoblacion dialog = new ListadoPoblacion(accion);
				dialog.setVisible(true);
			}
		});
		btnBuscar.setBounds(176, 208, 130, 25);
		contentPanel.add(btnBuscar);
		
		JButton btnEliminar = new JButton("Eliminar Poblacion");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int idPoblacion = Short.parseShort(txtCod.getText());
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas eliminar la poblacion con ID: " + idPoblacion, "Eliminacion Poblacion", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						Poblacion_DAO poblacionDAO = new Poblacion_DAO();
						if(poblacionDAO.borrar(idPoblacion)) {
							JOptionPane.showMessageDialog(null, "Poblacion Eliminado exitosamente", "Eliminacion Poblacion", JOptionPane.INFORMATION_MESSAGE);
							 txtCod.setText(" ");
							 txtNombre.setText(" ");
							 txtIdPais.setText(" ");
							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "La poblacion no ha podido ser eliminado exitosamente", "Eliminacion Poblacion", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Eliminacion Poblacion Cancelada", "Eliminacion Poblacion", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnEliminar.setBounds(171, 259, 135, 25);
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
		
		JLabel lblIdPais = new JLabel("Id Pais");
		lblIdPais.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdPais.setBackground(new Color(128, 128, 128));
		lblIdPais.setOpaque(true);
		lblIdPais.setBounds(209, 127, 80, 20);
		contentPanel.add(lblIdPais);

		
		txtIdPais = new JTextField();
		txtIdPais.setBounds(187, 157, 111, 25);
		contentPanel.add(txtIdPais);
		txtIdPais.setColumns(10);

	}
	public void buscarPoblacionPorId(int idPoblacion) {

	    	Poblacion_DAO poblacionDAO = new Poblacion_DAO();
	    model.Poblacion poblacion = poblacionDAO.buscar(idPoblacion);

	    txtCod.setText(String.valueOf(idPoblacion));
	    txtNombre.setText(poblacion.getNomPoblacion());
	    txtIdPais.setText(String.valueOf(poblacion.getId_pais()));
	    esBuscado = true;
	}
}
