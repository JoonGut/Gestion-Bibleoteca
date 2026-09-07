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

public class GestionLibros extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionLibros dialog = new GestionLibros();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionLibros() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 650, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel(" Gestion Libros");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(243, 10, 124, 35);
		contentPanel.add(lblTitulo);
		
		JButton btnInsertar = new JButton("Nuevo Libro");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarLibro dialog = new InsertarLibro();
				dialog.setVisible(true);
			}
		});
		btnInsertar.setBounds(43, 104, 124, 35);
		contentPanel.add(btnInsertar);
		
		JButton btnEliminar = new JButton("Eliminar ");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarLibro dialog = new EliminarLibro();
				dialog.setVisible(true);
			}
		});
		btnEliminar.setBounds(243, 104, 124, 35);
		contentPanel.add(btnEliminar);
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
		btSalir.setBounds(232, 209, 145, 46);
		contentPanel.add(btSalir);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarLibro dialog = new ActualizarLibro();
				dialog.setVisible(true);
			}
		});
		btnActualizar.setBounds(451, 104, 124, 35);
		contentPanel.add(btnActualizar);

	}
}
