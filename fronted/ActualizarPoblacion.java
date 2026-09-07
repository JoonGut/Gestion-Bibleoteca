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

import model.Autor;
import model.Categoria;
import model.Editorial;
import model.Libro;
import model.Pais;
import model.Poblacion;
import modelo_DAO.Autor_DAO;
import modelo_DAO.Categoria_DAO;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Libros_DAO;
import modelo_DAO.Pais_DAO;
import modelo_DAO.Poblacion_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class ActualizarPoblacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCod;
	private JTextField txtNombre;
	private JTextField txtPais;
	private boolean esBuscado=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActualizarPoblacion dialog = new ActualizarPoblacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActualizarPoblacion() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Actualizar Poblacion");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(171, 10, 163, 35);
		contentPanel.add(lblTitulo);
		
		JLabel lblCod = new JLabel("Codigo Poblacion");
		lblCod.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCod.setBackground(new Color(128, 128, 128));
		lblCod.setOpaque(true);
		lblCod.setBounds(56, 72, 148, 25);
		contentPanel.add(lblCod);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBackground(new Color(128, 128, 128));
		lblNombre.setOpaque(true);
		lblNombre.setBounds(369, 72, 66, 25);
		contentPanel.add(lblNombre);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 128, 128));
		btnVolver.setOpaque(true);
		btnVolver.setBounds(10, 320, 84, 20);
		contentPanel.add(btnVolver);
		

		JLabel lblPais = new JLabel("ID Pais");
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPais.setBackground(new Color(128, 128, 128));
		lblPais.setOpaque(true);
		lblPais.setBounds(211, 152, 84, 25);
		contentPanel.add(lblPais);
		
		txtCod = new JTextField();
		txtCod.setBounds(66, 107, 138, 25);
		contentPanel.add(txtCod);
		txtCod.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(327, 107, 138, 25);
		contentPanel.add(txtNombre);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(180, 187, 138, 25);
		contentPanel.add(txtPais);
		
		JButton btnListado = new JButton("Listado Poblacion");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="modificar";
				ListadoPoblacion dialog = new ListadoPoblacion(accion);
				dialog.setVisible(true);
				dispose();
			}
		});
		btnListado.setBounds(94, 260, 148, 25);
		contentPanel.add(btnListado);
		
		JButton btnActualizar = new JButton("Actualizar Poblacion");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {
					Poblacion_DAO poblacionDAO = new Poblacion_DAO();
					Poblacion poblacionExistente = poblacionDAO.buscar(Integer.parseInt(txtCod.getText()));
		            // Ejemplo (debes crear estos DAOs si no los tienes):
		            Pais_DAO paisDAO = new Pais_DAO();

		            
		            // 3. Obtener las entidades desde la base de datos
		            Pais pais = paisDAO.buscar(Integer.parseInt(txtPais.getText()));

					
		            poblacionExistente.setNomPoblacion(txtNombre.getText());
		            poblacionExistente.setId_pais(pais);
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas modificar la poblacion con ISBN: " + poblacionExistente.getId_poblacion(), "Modificacion Poblacion", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						if(poblacionDAO.actualizar(poblacionExistente)) {
							JOptionPane.showMessageDialog(null, "Poblacion Modificado exitosamente", "Modificaion Poblacion", JOptionPane.INFORMATION_MESSAGE);
							 txtCod.setText(" ");
							 txtNombre.setText(" ");
							 txtPais.setText(" ");
							 
							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "La poblacion no ha podido ser modificado exitosamente", "Modificacion Poblacion", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Modificacion Poblacion Cancelada", "Modificacion Poblacion", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			
		});
		
		btnActualizar.setBounds(287, 260, 163, 25);
		contentPanel.add(btnActualizar);

	}
	public void buscarPoblacionPorId(int idPoblacion) {

    	Poblacion_DAO poblacionDAO = new Poblacion_DAO();
    model.Poblacion poblacion = poblacionDAO.buscar(idPoblacion);

    txtCod.setText(String.valueOf(idPoblacion));
    txtNombre.setText(poblacion.getNomPoblacion());
    txtPais.setText(String.valueOf(poblacion.getId_pais().getId_pais()));
    esBuscado = true;
}

}
