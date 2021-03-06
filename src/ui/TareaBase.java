package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import empresa.Proyecto;
import empresa.Tarea;
import ui.containers.InputContainer;

public abstract class TareaBase extends Base {
	
	private static final long serialVersionUID = 2756342206651835302L;
	private String project;
	private JLabel titulo;
	private InputContainer id;
	private InputContainer nombre;
	private InputContainer description;
	private InputContainer idProyecto;
	private JLabel comboProyectoLabel;
	private JComboBox<String> comboProyecto;
	private List<Proyecto> proyectos;
	
	public TareaBase(Handler handler){
		super(handler);
		initUI();
	}
	
	@Override
	public void initUI(){
		Box vertical = Box.createVerticalBox();
		Box inLineProyecto = Box.createHorizontalBox();
		
		titulo = new JLabel(setTitulo(), JLabel.LEFT);
		
		id = new InputContainer("ID", 30);
		
		nombre = new InputContainer("Nombre", 30);
		
		description = new InputContainer("Descripcion", 30);
		
		proyectos = new ArrayList<Proyecto>();
		setProyectos(getHandler().obtenerProyectos());
		
		comboProyecto = new JComboBox<String>();
		comboProyecto.addItem("Seleccionar proyecto");
    	for(Proyecto proyecto : proyectos) {
    		comboProyecto.addItem(String.valueOf(proyecto.getIdProyecto() + "-" + proyecto.getNombre()));
    	}
		
    	comboProyectoLabel = new JLabel("Proyectos", JLabel.LEFT);
    	
    	
    	vertical.add(titulo);
		vertical.add(Box.createVerticalStrut(getHeightSpace()));
    	
    	vertical.add(id.createHelperBox());
		vertical.add(Box.createVerticalStrut(getHeightSpace()));
    	
    	vertical.add(nombre.createHelperBox());
		vertical.add(Box.createVerticalStrut(getHeightSpace()));
    	
    	vertical.add(description.createHelperBox());
		vertical.add(Box.createVerticalStrut(getHeightSpace()));
    	
    	inLineProyecto.add(comboProyectoLabel);
    	inLineProyecto.add(comboProyecto);
    	vertical.add(inLineProyecto);
		
		vertical.add(Box.createVerticalStrut(40));
		vertical.add(agregarBotones());
        
        add(vertical);
		
	}
	
	@Override
	public Tarea panelToObject() {
		Tarea tarea = new Tarea(
			Integer.parseInt(getId().getField().getText()),	
			getNombre().getField().getText(),
			getDescription().getField().getText(),
			Integer.parseInt(getProject().split("-")[0])
		);
		return tarea;
	}
	
	@Override
	public void objectToPanel(Object data) {
		Tarea tarea = (Tarea) data;
		getNombre().getField().setText(tarea.getNombre());
		getId().getField().setText(Integer.toString(tarea.getId()));
		getId().getField().setEnabled(false);
		getDescription().getField().setText(tarea.getDescripcion());
		getComboProyecto().setSelectedItem(String.valueOf(tarea.getIdProyecto()));
	}
	
	public InputContainer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(InputContainer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public InputContainer getId() {
		return id;
	}

	public void setId(InputContainer id) {
		this.id = id;
	}

	public InputContainer getNombre() {
		return nombre;
	}

	public void setNombre(InputContainer nombre) {
		this.nombre = nombre;
	}

	public InputContainer getDescription() {
		return description;
	}

	public void setDescription(InputContainer description) {
		this.description = description;
	}

	public JLabel getComboProyectoLabel() {
		return comboProyectoLabel;
	}

	public void setComboProyectoLabel(JLabel comboProyectoLabel) {
		this.comboProyectoLabel = comboProyectoLabel;
	}

	public JComboBox<String> getComboProyecto() {
		return comboProyecto;
	}

	public void setComboProyecto(JComboBox<String> comboProyecto) {
		this.comboProyecto = comboProyecto;
	}
	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}
	
	public String getProject() {
		return project;
	}
	
	public void setProject(String project) {
		this.project = project;
	}

}
