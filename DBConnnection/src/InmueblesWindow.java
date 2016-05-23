import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InmueblesWindow extends JFrame implements ActionListener{
	JScrollPane panel1;
	JTable tablaInmuebes;
	MueblesModelo modelo;
	String [][] datos={{"","","",""}};
	String [] cabezera={"id","Direccion","precio","otro"};
	JButton mostrarInmueblesB;
	DefaultTableModel modeloTabla;
	public InmueblesWindow() {
		super ("Gestor de inmuebles");
		setSize(600,600);
		setLayout(null);
		setLocation(300, 200);
		setTable();
		mostrarInmueblesB=new JButton("mostrar Inmuebles");
		mostrarInmueblesB.setSize(mostrarInmueblesB.getPreferredSize());
		mostrarInmueblesB.setLocation(10, 300);
		mostrarInmueblesB.addActionListener(this);
		add(mostrarInmueblesB);
		setVisible(true);
		modelo=new MueblesModelo();
		
		
	}
	public void setTable(){
		modeloTabla=new DefaultTableModel(cabezera, 4);
		modeloTabla.setDataVector(datos, cabezera);
		tablaInmuebes=new JTable(datos,cabezera);
		JScrollPane JS=new JScrollPane(tablaInmuebes);
		JS.setPreferredSize(new Dimension(100,150));
		this.add(JS);
		JS.setSize(500,100);
		JS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JS.setLocation(10, 10);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mostrarInmueblesB){
			datos=modelo.obtenerInmuebles();
			modeloTabla.setDataVector(datos, cabezera);
			tablaInmuebes.setModel(modeloTabla);
			//tablaInmuebes.set;
		}
		
	}
}
