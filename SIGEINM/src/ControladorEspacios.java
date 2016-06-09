/*************************************************

Nombre de la clase: ControladorEspacios.java

Última modificación: 06/06/2016

Descripción: Controla el despliegue y funcionamiento
	de la interfaz para manipular los inventarios 
	de la colección.

*************************************************/
import java.awt.event.*;
import java.security.IdentityScope;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ControladorEspacios extends JDialog implements ActionListener,MouseListener {
	private ModeloInventarios modelo;
	private int idInmueble;
	private DefaultTableModel modeloTabla=new DefaultTableModel();
	private JTable tabla=new JTable(modeloTabla);
	private int instancia;
	private int espacio;
	private int idInventario;
	private JButton botonEditarInventario=new JButton("editar");
	private JButton aprovarBoton=new JButton("aprovar");
	private String tipo;
	public ControladorEspacios(JDialog parent,int idInmueble,ModeloInventarios modelo,String tipo) {
		super(parent,"",true);
		this.modelo=modelo;
		this.idInmueble=idInmueble;
		this.idInventario=modelo.getIdInventario(idInmueble);
		this.tipo=tipo;
		setSize(400,400);
		setLocation(130, 100);
		setLayout(null);
		JLabel label=new JLabel("id: "+String.valueOf(idInmueble)+"  direccion: "+modelo.getDireccionInmueble(idInmueble));
		label.setSize(label.getPreferredSize());
		label.setLocation(10, 10);
		add(label);
		createTable();
		tabla.setModel(modelo.obtenerInstanciasInventarios(idInmueble));
		botonEditarInventario.setSize(botonEditarInventario.getPreferredSize());
		botonEditarInventario.setLocation(230,100);
		botonEditarInventario.addActionListener(this);
		botonEditarInventario.setEnabled(false);
		if(tipo.equals("R") || tipo.equals("S")){
			aprovarBoton.setSize(aprovarBoton.getPreferredSize());
			aprovarBoton.setLocation(230,130);
			aprovarBoton.addActionListener(this);
			if(modelo.getAprovacion(idInventario)){
				aprovarBoton.setEnabled(false);
			}
			add(aprovarBoton);
		}
		add(botonEditarInventario);
		setVisible(true);
	}
	void createTable(){
		String [][] datos={{}};
		String [] cabezera={"ESPACIO","NUMERO"};
		modeloTabla.setDataVector(new String[0][2], cabezera);
		tabla.addMouseListener(this);
		JScrollPane scrollPane=new JScrollPane(tabla);
		scrollPane.setPreferredSize(tabla.getPreferredSize());
		add(scrollPane);
		scrollPane.setSize(200, 300);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setLocation(10, 40);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==1){
			int fila=tabla.rowAtPoint(e.getPoint());
			System.out.println(tabla.getValueAt(fila, 0));
			if(fila>-1){
				instancia=Integer.parseInt(tabla.getValueAt(fila, 1).toString());
				espacio=modelo.getIdEspacio(tabla.getValueAt(fila, 0).toString());
				botonEditarInventario.setEnabled(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botonEditarInventario){
			if(modelo.InformacionCreadaInventario(idInventario, espacio, instancia)){
				new ControladorInventarioEspacio(this, modelo, idInmueble, idInventario, espacio, instancia, "E",tipo);
			}
			else{
				new ControladorInventarioEspacio(this, modelo, idInmueble, idInventario, espacio, instancia, "G",tipo);
			}
		}
		else if(e.getSource()==aprovarBoton){
			if(modelo.aprovarInventario(idInventario)){
				JOptionPane.showMessageDialog(this, "el inventario ha sido aprovado");
			}
			else{
				JOptionPane.showMessageDialog(this, "no se a podido aprovar el inventario");
			}
		}
	}

}
