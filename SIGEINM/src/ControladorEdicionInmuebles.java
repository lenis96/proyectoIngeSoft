import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class ControladorEdicionInmuebles extends JDialog implements ActionListener {
	
	JLabel label=new JLabel();
	JTextField direccionInput=new JTextField();
	JTextField lugarReferenciaInput=new JTextField();
	JTextField tamanoInput=new JTextField();
	JTextField estratoInput=new JTextField();
	JTextField tipoInput=new JTextField();
	JTextField habitacionesInput=new JTextField();
	JTextField usuarioInput=new JTextField();
	JTextField precioInput=new JTextField();
	ModeloGestionInmuebles modelo;
	int idInmueble=-1;
	public ControladorEdicionInmuebles(JDialog parent,ModeloGestionInmuebles modelo) {
		super(parent,"Edicion Inmuebles",true);
		mostrarInformacion();
	}
	public ControladorEdicionInmuebles(JDialog parent, ModeloGestionInmuebles modelo,int idInmueble){
		super(parent,"Edicion Inmuebles",true);
		label.setText("ID: "+String.valueOf(idInmueble));
		label.setSize(label.getPreferredSize());
		label.setLocation(10,10);
		add(label);
		this.idInmueble=idInmueble;
		this.modelo=modelo;
		mostrarInformacion();
	}
	public void mostrarInformacion(){
		int x=140;
		setSize(400,400);
		setLocation(100,100);
		setLayout(null);
		if(idInmueble!=-1){
			String [] datos=modelo.getDataInmueble(idInmueble);
			direccionInput.setText(datos[1]);
			lugarReferenciaInput.setText(datos[2]);
			tamanoInput.setText(datos[3]);
			estratoInput.setText(datos[4]);
			tipoInput.setText(datos[5]);
			habitacionesInput.setText(datos[6]);
			usuarioInput.setText(datos[7]);
			precioInput.setText(datos[8]);
		}
		label=new JLabel("Direccion: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,40);
		add(label);
		label=new JLabel("Lugar de Referencia: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,60);
		add(label);
		label=new JLabel("Tamaño: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,80);
		add(label);
		label=new JLabel("Estrato: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,100);
		add(label);
		label=new JLabel("Tipo: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,120);
		add(label);
		label=new JLabel("Habitaciones: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,140);
		add(label);
		label=new JLabel("Usuario: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,160);
		add(label);
		label=new JLabel("Precio: ");
		label.setSize(label.getPreferredSize());
		label.setLocation(10,180);
		add(label);
		
		
		direccionInput.setColumns(20);
		direccionInput.setSize(direccionInput.getPreferredSize());
		direccionInput.setLocation(x,40);
		add(direccionInput);
		lugarReferenciaInput.setColumns(20);
		lugarReferenciaInput.setSize(lugarReferenciaInput.getPreferredSize());
		lugarReferenciaInput.setLocation(x,60);
		add(lugarReferenciaInput);
		tamanoInput.setColumns(20);
		tamanoInput.setSize(tamanoInput.getPreferredSize());
		tamanoInput.setLocation(x, 80);
		add(tamanoInput);
		estratoInput.setColumns(20);
		estratoInput.setSize(estratoInput.getPreferredSize());
		estratoInput.setLocation(x, 100);
		add(estratoInput);
		tipoInput.setColumns(20);
		tipoInput.setSize(tipoInput.getPreferredSize());
		tipoInput.setLocation(x, 120);
		add(tipoInput);
		habitacionesInput.setColumns(20);
		habitacionesInput.setSize(habitacionesInput.getPreferredSize());
		habitacionesInput.setLocation(x, 140);
		add(habitacionesInput);
		usuarioInput.setColumns(20);
		usuarioInput.setSize(usuarioInput.getPreferredSize());
		usuarioInput.setLocation(x, 160);
		add(usuarioInput);
		precioInput.setColumns(20);
		precioInput.setSize(precioInput.getPreferredSize());
		precioInput.setLocation(x, 180);
		add(precioInput);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
