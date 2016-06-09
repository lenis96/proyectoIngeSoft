/*************************************************

Nombre de la clase: ControladorGestionInmuebles.java

Última modificación: 06/06/2016

Descripción: Se encarga de controlar y desplegar 
	la interfaz para visualizar y modificar los 
	inventarios de los inmuebles.

*************************************************/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorGestionInmuebles extends JDialog implements ActionListener,MouseListener {
	private JButton mostrarInmueblesBoton=new JButton("mostrar inmuebles");
	private JButton editarInmueblesBoton=new JButton("Editar Inmueble");
	private JButton insertarInmuebleBoton=new JButton("Insertar Inmueble");
	private DefaultTableModel modeloTabla=new DefaultTableModel();
	private JFormattedTextField minPrecioInput=new JFormattedTextField(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
	private JFormattedTextField maxPrecioInput=new JFormattedTextField(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
	private JTextField direccionInput=new JTextField();
	private JTextField lugarReferenciaInput=new JTextField();
	private JTable tabla=new JTable(modeloTabla);
	private ModeloGestionInmuebles modelo=new ModeloGestionInmuebles();
	private int fila;
	private String tipo;
	public ControladorGestionInmuebles(JDialog parent,String user,String tipo) {
		super(parent,"GestionInmuebles",true);
		setSize(600,600);
		this.tipo=tipo;
		setLocation(100, 100);
		setLayout(null);
		JLabel label=new JLabel("usuario: "+user);
		label.setSize(label.getPreferredSize());
		label.setLocation(10, 10);
		add(label);
		
		createTable();
		label=new JLabel("precio minimo:");
		label.setSize(label.getPreferredSize());
		label.setLocation(10, 350);
		add(label);
		minPrecioInput.setColumns(15);
		minPrecioInput.setSize(minPrecioInput.getPreferredSize());
		minPrecioInput.setLocation(100, 350);
		add(minPrecioInput);
		label=new JLabel("precio maximo:");
		label.setSize(label.getPreferredSize());
		label.setLocation(280, 350);
		add(label);
		maxPrecioInput.setColumns(15);
		maxPrecioInput.setSize(maxPrecioInput.getPreferredSize());
		maxPrecioInput.setLocation(380,350);
		add(maxPrecioInput);
		
		label=new JLabel("direccion:");
		label.setSize(label.getPreferredSize());
		label.setLocation(10, 370);
		add(label);
		direccionInput.setColumns(15);
		direccionInput.setSize(direccionInput.getPreferredSize());
		direccionInput.setLocation(100,370);
		add(direccionInput);
		label=new JLabel("lugar referencia:");
		label.setSize(label.getPreferredSize());
		label.setLocation(280, 370);
		add(label);
		lugarReferenciaInput.setColumns(15);
		lugarReferenciaInput.setSize(lugarReferenciaInput.getPreferredSize());
		lugarReferenciaInput.setLocation(380,370);
		add(lugarReferenciaInput);
		
		mostrarInmueblesBoton.setSize(mostrarInmueblesBoton.getPreferredSize());
		mostrarInmueblesBoton.setLocation(10, 500);
		mostrarInmueblesBoton.addActionListener(this);
		add(mostrarInmueblesBoton);
		editarInmueblesBoton.setSize(editarInmueblesBoton.getPreferredSize());
		editarInmueblesBoton.setLocation(180, 500);
		editarInmueblesBoton.addActionListener(this);
		editarInmueblesBoton.setEnabled(false);
		add(editarInmueblesBoton);
		if(tipo.equals("R")){
			insertarInmuebleBoton.setSize(insertarInmuebleBoton.getPreferredSize());
			insertarInmuebleBoton.setLocation(350,500);
			insertarInmuebleBoton.addActionListener(this);
			add(insertarInmuebleBoton);
		}
		setVisible(true);
		
	}
	
	void createTable(){
		String [][] datos={{}};
		String [] cabezera={"ID","DIRECCION","PRECIO","USUARIO"};
		modeloTabla.setDataVector(new String[0][4], cabezera);
		tabla.addMouseListener(this);
		JScrollPane scrollPane=new JScrollPane(tabla);
		scrollPane.setPreferredSize(tabla.getPreferredSize());
		add(scrollPane);
		scrollPane.setSize(550, 300);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setLocation(10, 40);
	}
	
	public void actionPerformed(ActionEvent event){
		String minPrecio="0";
		String maxPrecio="100000000";
		if(event.getSource()==mostrarInmueblesBoton){
			boolean ok=true;
			if(ok && !minPrecioInput.getText().equals("") && !maxPrecioInput.getText().equals("") && Integer.parseInt(minPrecioInput.getText())>Integer.parseInt(maxPrecioInput.getText())){
				ok=false;
				JOptionPane.showMessageDialog(this,"el precio minimo no puede ser mayor al precio maximo");
			}
			else{
				if(!minPrecioInput.getText().equals("")){
					minPrecio=minPrecioInput.getText();
				}
				if(!maxPrecioInput.getText().equals("")){
					maxPrecio=maxPrecioInput.getText();
				}
			}
			if(ok){
				tabla.setModel(modelo.obtenerInmuebles(minPrecio,maxPrecio,direccionInput.getText(),lugarReferenciaInput.getText()));
			}
		}
		else if(event.getSource()==editarInmueblesBoton){
			int id=Integer.parseInt(((String)tabla.getValueAt(fila, 0)));
			new ControladorEdicionInmuebles(this, modelo,id,tipo);
			
		}
		else if(event.getSource()==insertarInmuebleBoton){
			this.setVisible(false);
			new ControladorEdicionInmuebles(this, modelo,tipo);
			this.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==1){
			int fila=tabla.rowAtPoint(e.getPoint());
			if(fila>-1){
				editarInmueblesBoton.setEnabled(true);
				this.fila=fila;
			}
			else{
				editarInmueblesBoton.setEnabled(false);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
