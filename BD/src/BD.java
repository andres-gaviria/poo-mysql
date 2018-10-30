import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;

public class BD {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblresp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BD window = new BD();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BD() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 63, 14);
		frame.getContentPane().add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(83, 8, 356, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(83, 43, 356, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 46, 63, 14);
		frame.getContentPane().add(lblMatricula);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/universidad", "root", "");
					
					if(conexion != null) {
						Statement st = conexion.createStatement();
						lblresp.setText("la conexión se realizo con exito ._.");
					}else
						System.out.println("conexión fallida");
				}
				
				catch (SQLException e) {e.printStackTrace();}
				catch (ClassNotFoundException e ) {e.printStackTrace();}
				catch (Exception e) {e.printStackTrace();}
				
			}
		});
		btnNewButton.setBounds(60, 95, 86, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion =
							DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
					
					Statement statement = conexion.createStatement();
					
					String nombre = textField.getText();
					((java.sql.Statement)statement).executeUpdate("insert into alumnos(nombre) values('"+nombre+"')");
					conexion.close();
				}catch (ClassNotFoundException o) {
					
					o.printStackTrace();
					
				}catch (SQLException l) {
					l.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(156, 95, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id = Integer.parseInt(textField_1.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "");
					Statement statement = conexion.createStatement();
					ResultSet resultSet = statement.executeQuery("select nombre from alumnos where matricula="+id);
					
					if(resultSet.next() == true) {
						lblMatricula.setText(resultSet.getString("nombre"));
					}
					
					conexion.close();
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(257, 95, 86, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id = Integer.parseInt(textField_1.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad","root","");
					Statement statement = conexion.createStatement();
					
					String query = "Delete from alumnos where matricula ="+id;
					
					conexion.close();
					
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBorrar.setBounds(353, 95, 86, 23);
		frame.getContentPane().add(btnBorrar);
		
		lblresp = new JLabel("label");
		lblresp.setHorizontalAlignment(SwingConstants.CENTER);
		lblresp.setBounds(30, 139, 409, 111);
		frame.getContentPane().add(lblresp);
	}
}
