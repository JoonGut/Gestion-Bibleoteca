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

import model.Editorial;
import model.Pais;
import modelo_DAO.Editorial_DAO;
import modelo_DAO.Pais_DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActualizarPais extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCod;
	private JTextField txtNombre;
	private boolean esBuscado=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActualizarPais dialog = new ActualizarPais();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActualizarPais() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Eliminado Pais");
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(209, 10, 123, 35);
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
		lblNombre.setBounds(348, 72, 66, 25);
		contentPanel.add(lblNombre);
		
		txtCod = new JTextField();
		txtCod.setBounds(56, 121, 111, 25);
		contentPanel.add(txtCod);
		txtCod.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(332, 121, 96, 25);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Listado Pais");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accion="modificar";
				ListadoPais dialog = new ListadoPais(accion);
				dialog.setVisible(true);
			}
		});
		btnBuscar.setBounds(209, 189, 111, 25);
		contentPanel.add(btnBuscar);
		
		JButton btnActualizar = new JButton("Actualizar Pais");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esBuscado==false) {
					JOptionPane.showMessageDialog(null, "Debes buscar previamente", "Tipo de Dato Incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}else {

		            Pais pais = new Pais();
		            

		            pais.setId_pais(Integer.parseInt(txtCod.getText()));
		            pais.setNomPais(txtNombre.getText());
					
					int respuesta = JOptionPane.showConfirmDialog(null, "Deseas modificar al libro con ISBN: " + pais.getId_pais(), "Modificacion pais", JOptionPane.YES_NO_OPTION);
					Pais_DAO paisDAO = new Pais_DAO();
					if (respuesta == JOptionPane.YES_OPTION) {
						if(paisDAO.actualizar(pais)) {
							JOptionPane.showMessageDialog(null, "Pais Modificado exitosamente", "Modificaion Pais", JOptionPane.INFORMATION_MESSAGE);
							 txtCod.setText(" ");
							 txtNombre.setText(" ");

							 esBuscado=false;
						}else {
							JOptionPane.showMessageDialog(null, "El pais no ha podido ser modificado exitosamente", "Modificacion Pais", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Modificacion pais Cancelada", "Modificacion pais", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			
		});
		btnActualizar.setBounds(209, 252, 111, 25);
		contentPanel.add(btnActualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 128, 128));
		btnVolver.setOpaque(true);
		btnVolver.setBounds(10, 315, 84, 20);
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
