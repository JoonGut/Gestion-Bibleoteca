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
import javax.swing.JLabel;

public class GestionCategorias extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionCategorias dialog = new GestionCategorias();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionCategorias() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(50, 50, 600, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gestion Categorias");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(206, 10, 140, 35);
		contentPanel.add(lblTitulo);
		
		JButton btnEliminar = new JButton("Eliminar Categoria");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarCategoria dialog = new EliminarCategoria();
				dialog.setVisible(true);
			}
		});
		btnEliminar.setBounds(219, 89, 145, 26);
		contentPanel.add(btnEliminar);
		
		JButton btnInsertar = new JButton("Nueva Categoria");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarCategorias dialog = new InsertarCategorias();
				dialog.setVisible(true);
			}
		});
		btnInsertar.setBounds(29, 89, 140, 26);
		contentPanel.add(btnInsertar);
		
		JButton btSalir = new JButton(" S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setOpaque(true);
		btSalir.setBackground(new Color(255, 128, 128));
		btSalir.setForeground(new Color(255, 0, 0));
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btSalir.setBounds(219, 222, 145, 46);
		contentPanel.add(btSalir);
		
		JButton btnActualizar = new JButton("Actualizar Categoria");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarCategoria dialog = new ActualizarCategoria();
				dialog.setVisible(true);
			}
		});
		btnActualizar.setBounds(393, 89, 183, 26);
		contentPanel.add(btnActualizar);
		
	}
}
