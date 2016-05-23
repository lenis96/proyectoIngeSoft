import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ControladorGestionInmuebles extends JDialog implements ActionListener {
	private JButton mostrarInmueblesBoton=new JButton("mostrar inmuebles");
	private DefaultTableModel modeloTabla=new DefaultTableModel();
	private JTable tabla=new JTable(modeloTabla);
	public ControladorGestionInmuebles(JFrame parent,String user) {
		super(parent,"GestionInmuebles",true);
		setSize(400,600);
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
		
		setVisible(true);
		
	}
	
	void createTable(){
		String [][] datos={{}};
		String [] cabezera={"id","direccion","precio","otro"};
		modeloTabla.setDataVector(new String[0][4], cabezera);
		JScrollPane scrollPane=new JScrollPane(tabla);
		scrollPane.setPreferredSize(tabla.getPreferredSize());
		add(scrollPane);
		scrollPane.setSize(300,300);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setLocation(10, 40);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource()==mostrarInmueblesBoton){
			System.out.println("mostrar Inmuebles");
		}
	}
}
