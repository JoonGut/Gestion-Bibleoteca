package fronted;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(150, 150, 656, 404);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel(" Gestion Bibleoteca");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 214, 165));
		lblTitulo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 19));
		lblTitulo.setBounds(244, 10, 145, 35);
		contentPane.add(lblTitulo);
		
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
		btSalir.setBounds(244, 291, 145, 46);
		contentPane.add(btSalir);
		
		JButton btGestionLibros = new JButton("Gestion de Libros");
		btGestionLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionLibros dialog = new GestionLibros();
				dialog.setVisible(true);
			}
		});
		btGestionLibros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btGestionLibros.setBounds(84, 75, 178, 35);
		contentPane.add(btGestionLibros);
		
		JButton btnAutor = new JButton("Gestion de Autores");
		btnAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAutores dialog = new GestionAutores();
				dialog.setVisible(true);
			}
		});
		btnAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAutor.setBounds(84, 134, 178, 35);
		contentPane.add(btnAutor);
		
		JButton btGestionEditorial = new JButton("Gestion de Editoriales");
		btGestionEditorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionEditoriales dialog = new GestionEditoriales();
				dialog.setVisible(true);
			}
		});
		btGestionEditorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btGestionEditorial.setBounds(350, 75, 178, 35);
		contentPane.add(btGestionEditorial);
		
		JButton btnGestionCategoria = new JButton("Gestion de Categorias");
		btnGestionCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionCategorias dialog = new GestionCategorias();
				dialog.setVisible(true);
			}
		});
		btnGestionCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGestionCategoria.setBounds(350, 134, 178, 35);
		contentPane.add(btnGestionCategoria);
		
		JButton btnPoblacion = new JButton("Gestion Poblacion");
		btnPoblacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionPoblacion dialog = new GestionPoblacion();
				dialog.setVisible(true);
			}
		});
		btnPoblacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPoblacion.setBounds(350, 192, 178, 35);
		contentPane.add(btnPoblacion);
		
		JButton btnPais = new JButton("Gestion Pais");
		btnPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionPais dialog = new GestionPais();
				dialog.setVisible(true);
			}
		});
		btnPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPais.setBounds(84, 192, 178, 35);
		contentPane.add(btnPais);

	}
}
