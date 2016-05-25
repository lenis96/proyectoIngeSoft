import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class ControladorGestionInmuebles extends JDialog implements ActionListener,MouseListener {
	private JButton mostrarInmueblesBoton=new JButton("mostrar inmuebles");
	private JButton editarInmueblesBoton=new JButton("Editar Inmueble");
	private DefaultTableModel modeloTabla=new DefaultTableModel();
	private JTable tabla=new JTable(modeloTabla);
	private ModeloGestionInmuebles modelo=new ModeloGestionInmuebles();
	private int fila;
	public ControladorGestionInmuebles(JFrame parent,String user) {
		super(parent,"GestionInmuebles",true);
		setSize(600,600);
		setLocation(100, 100);
		setLayout(null);
		//setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		JLabel label=new JLabel("usuario: "+user);
		label.setSize(label.getPreferredSize());
		label.setLocation(10, 10);
		add(label);
		
		createTable();
		
		mostrarInmueblesBoton.setSize(mostrarInmueblesBoton.getPreferredSize());
		mostrarInmueblesBoton.setLocation(10, 500);
		mostrarInmueblesBoton.addActionListener(this);
		add(mostrarInmueblesBoton);
		editarInmueblesBoton.setSize(editarInmueblesBoton.getPreferredSize());
		editarInmueblesBoton.setLocation(180, 500);
		editarInmueblesBoton.addActionListener(this);
		editarInmueblesBoton.setEnabled(false);
		add(editarInmueblesBoton);
		
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
		if(event.getSource()==mostrarInmueblesBoton){
			tabla.setModel(modelo.obtenerInmuebles());
		}
		else if(event.getSource()==editarInmueblesBoton){
			int id=Integer.parseInt(((String)tabla.getValueAt(fila, 0)));
			new ControladorEdicionInmuebles(this, modelo,id);
			
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
