/*************************************************

Nombre de la clase: ControladorGestionUsuarios.java

Última modificación: 06/06/2016

Descripción: Tiene la tarea de agrgar un 
nuevo objetos y asociarlo a los espacion en la base de datos

*************************************************/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class ControladorAgregarObjeto extends JDialog implements ActionListener {
	JTextField objetoInput=new JTextField();
	JButton agrgarObjetoBoton=new JButton("agregarObjeto");
	public ControladorAgregarObjeto(JDialog parent) {
		super(parent,"agrgar Objeto");
		setSize(300,200);
		setLayout(null);
		
		JLabel label=new JLabel("ingrese el nuevo objeto");
		label.setSize(label.getPreferredSize());
		label.setLocation(10, 10);
		add(label);
		
		objetoInput.setColumns(20);
		objetoInput.setSize(objetoInput.getPreferredSize());
		objetoInput.setLocation(10, 50);
		add(objetoInput);
		
		agrgarObjetoBoton.setSize(agrgarObjetoBoton.getPreferredSize());
		agrgarObjetoBoton.setLocation(10, 90);
		agrgarObjetoBoton.addActionListener(this);
		add(agrgarObjetoBoton);
		
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==agrgarObjetoBoton){
			if(!objetoInput.getText().equals("")){
				if(new ModeloInventarios("").agragarObjeto(objetoInput.getText())){
					JOptionPane.showMessageDialog(this,"Se pudo agrgar el nuevo objeto");
				}
				else{
					JOptionPane.showMessageDialog(this,"No se pudo agrgar el nuevo objeto");
				}
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(this,"el campo no puede quedar vacio");
			}
		}
		
	}

}
