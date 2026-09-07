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

public class GestionEditoriales extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionEditoriales dialog = new GestionEditoriales();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionEditoriales() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(50, 50, 600, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gestion Editoriales");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(208, 10, 138, 35);
		contentPanel.add(lblTitulo);
		
		JButton btnInsertar = new JButton("Nueva Editorial");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarEditorial dialog = new InsertarEditorial();
				dialog.setVisible(true);
			}
		});
		btnInsertar.setBounds(22, 105, 144, 35);
		contentPanel.add(btnInsertar);
		{
			JButton btnEliminar = new JButton("Eliminar Editorial");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EliminarEditorial dialog = new EliminarEditorial();
					dialog.setVisible(true);
				}
			});
			btnEliminar.setBounds(208, 105, 144, 35);
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
			btSalir.setBounds(219, 222, 145, 46);
			contentPanel.add(btSalir);
		}
		
		JButton btnActualizar = new JButton("Actualizar Editorial");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarEditoriales dialog = new ActualizarEditoriales();
				dialog.setVisible(true);
			}
		});
		btnActualizar.setBounds(398, 105, 178, 35);
		contentPanel.add(btnActualizar);
		
	}
}
