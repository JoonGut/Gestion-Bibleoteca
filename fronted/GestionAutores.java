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

public class GestionAutores extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionAutores dialog = new GestionAutores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionAutores() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(50, 50, 750, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitulo = new JLabel("Gestion Autores");
			lblTitulo.setOpaque(true);
			lblTitulo.setBackground(new Color(255, 214, 165));
			lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
			lblTitulo.setBounds(281, 10, 125, 35);
			contentPanel.add(lblTitulo);
		}
		{
			JButton btnInsertar = new JButton("Nuevo Autor");
			btnInsertar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InsertarAutor dialog = new InsertarAutor();
					dialog.setVisible(true);
				}
			});
			btnInsertar.setBounds(93, 135, 133, 35);
			contentPanel.add(btnInsertar);
		}
		{
			JButton btnModificar = new JButton("Modificar Autor");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ActualizarAutor dialog = new ActualizarAutor();
					dialog.setVisible(true);
				}
			});
			btnModificar.setBounds(478, 135, 133, 35);
			contentPanel.add(btnModificar);
		}
		{
			JButton btnEliminar = new JButton("Eliminar Autor");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EliminarAutor dialog = new EliminarAutor();
					dialog.setVisible(true);
				}
			});
			btnEliminar.setBounds(288, 135, 118, 35);
			contentPanel.add(btnEliminar);
		}
		{
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
			btSalir.setBounds(288, 255, 145, 46);
			contentPanel.add(btSalir);
		}
	}

}
