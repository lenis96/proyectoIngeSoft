import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class LoginWindow extends JFrame implements ActionListener {
	JTextField field;
	JPasswordField passwordField;
	JLabel l;
	JButton b;
	DBConnection modelo;
	LoginWindow(){
		super("login");
		//frame=new JFrame("Login");
		this.setSize(500,200);
		this.setLocation(300,200);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l=new JLabel("User");
		l.setLocation(10, 10);
		l.setSize(l.getPreferredSize());
		this.add(l);
		
		field=new JTextField();
		field.setColumns(25);
		field.setSize(field.getPreferredSize());
		field.setLocation(120, 10);
		field.setToolTipText("User name");
		this.add(field);
		
		l=new JLabel("Password");
		l.setLocation(10, 40);
		l.setSize(l.getPreferredSize());
		this.add(l);
		
		passwordField=new JPasswordField();
		passwordField.setColumns(25);
		passwordField.setSize(passwordField.getPreferredSize());
		passwordField.setLocation(120,40);
		passwordField.setToolTipText("password");
		this.add(passwordField);
		
		b=new JButton("enter");
		b.setSize(b.getPreferredSize());
		b.setLocation(150, 80);
		b.addActionListener(this);
		this.add(b);
		
		this.setVisible(true);
		modelo=new DBConnection();
	}
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Click en el boton");
		if(e.getSource()==b){
			System.out.println(passwordField.getText());
			if(modelo.auteticar(field.getText(), passwordField.getText())){
				JOptionPane.showMessageDialog(this, "Entraste al sistema");
			}
			else{
				JOptionPane.showMessageDialog(this, "No entraste al sistema","error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
