/*************************************************

Nombre de la clase: ControladorGestionUsuarios.java

Última modificación: 06/06/2016

Descripción: Tiene la tarea de desplegar la ventana
	de ingreso a la aplicación junto con los campos
	respectivos para el usuario y contraseña.

*************************************************/
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorGestionUsuarios extends JFrame implements ActionListener{
	JTextField userInput=new JTextField();
	JPasswordField passwordInput=new JPasswordField();
	JButton botonIngreso=new JButton("Ingresar");
	JLabel label;
	ModeloGestionUsuarios modelo=new ModeloGestionUsuarios();
	public ControladorGestionUsuarios() {
		super("Login");
		setSize(500,200);
		setLocation(300,200);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label=new JLabel("User");
		label.setLocation(10, 10);
		label.setSize(label.getPreferredSize());
		add(label);
		
		userInput.setColumns(25);
		userInput.setSize(userInput.getPreferredSize());
		userInput.setLocation(120, 10);
		add(userInput);
		
		label=new JLabel("Password");
		label.setLocation(10, 40);
		label.setSize(label.getPreferredSize());
		add(label);
		
		passwordInput.setColumns(25);
		passwordInput.setSize(passwordInput.getPreferredSize());
		passwordInput.setLocation(120, 40);
		add(passwordInput);
		
		botonIngreso.setSize(botonIngreso.getPreferredSize());
		botonIngreso.setLocation(150, 80);
		botonIngreso.addActionListener(this);
		add(botonIngreso);
		
		label=new JLabel("");
		label.setLocation(100, 120);
		label.setSize(label.getPreferredSize());
		add(label);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource()==botonIngreso){
			if(modelo.verificarUsuario(userInput.getText(), passwordInput.getText())){
				label.setText("");
				label.setSize(label.getPreferredSize());
				String user=userInput.getText();
				userInput.setText("");
				passwordInput.setText("");
				String tipo=modelo.getTipoUsuario(user);
				System.out.println(tipo);
				this.setVisible(false);
				new ControladorOpciones(this,modelo.getTipoUsuario(user),user);
				this.setVisible(true);
			}
			else{
				label.setText("Usuario o contraseña incorrecta");
				label.setSize(label.getPreferredSize());
			}
		}
	}
	
}
