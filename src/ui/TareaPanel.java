package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import empresa.Tarea;
import handler.Handler;
import ui.containers.InputContainer;

public class TareaPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2756342206651835302L;
	private Handler handler;
	private Tarea current;

	public TareaPanel(Handler handler, String titulo) {
    	this.setHandler(handler);
		initUI(titulo);
    }
	
	public TareaPanel(Handler handler, String titulo, Tarea tarea) {
    	this.setHandler(handler);
    	this.setTarea(tarea);
		initUI(titulo);
    }
	
	private void initUI(String titulo) {
    	Box vertical = Box.createVerticalBox();
   
    	InputContainer idField = new InputContainer("ID", 30);
    	InputContainer nameField = new InputContainer("Nombre", 30);
    	InputContainer descriptionField = new InputContainer("Descripcion", 30);
    	
    	
    	if(this.getTarea() != null) {
    		vertical.add(idField.createHelperBox(Integer.toString(this.getTarea().getId()), true));
            vertical.add(Box.createVerticalStrut(20));
    		vertical.add(nameField.createHelperBox(this.getTarea().getNombre(), false));
            vertical.add(Box.createVerticalStrut(20));
            vertical.add(descriptionField.createHelperBox(this.getTarea().getDescripcion(), false));
            vertical.add(Box.createVerticalStrut(20));
    	} else {
    		vertical.add(Box.createVerticalStrut(20));
            vertical.add(idField.createHelperBox());
    		vertical.add(Box.createVerticalStrut(20));
            vertical.add(nameField.createHelperBox());
            vertical.add(Box.createVerticalStrut(20));
            vertical.add(descriptionField.createHelperBox());
            vertical.add(Box.createVerticalStrut(20));
    	}
    	
    	Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        JButton OKBtn = new JButton("OK");
        OKBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	        	if(current != null) {
	        		current.setNombre(nameField.getField().getText());
	        		current.setDescripcion(descriptionField.getField().getText());
	        		
	        		handler.editarTarea(current);
	        	} else {
	        		Tarea tarea= new Tarea(
	        			Integer.parseInt(idField.getField().getText()),	
    	        		nameField.getField().getText(),
    	        		descriptionField.getField().getText()
    				);
    				handler.agregarTarea(tarea);
	        	}
			}
		});
        
        JButton SalirBtn = new JButton("Volver");
        SalirBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handler.mostrarTablaTarea();
			}
		});
        
        botonera.add(OKBtn);
        botonera.add(Box.createHorizontalGlue());
        
        botonera.add(SalirBtn);
        botonera.add(Box.createHorizontalGlue());
        vertical.add(botonera);
        vertical.add(Box.createVerticalStrut(30));
        
        add(vertical);
	
	}
    	
    	public Handler getHandler() {
    		return handler;
    	}

    	public void setHandler(Handler handler) {
    		this.handler = handler;
    	}

    	public Tarea getTarea() {
    		return current;
    	}

    	public void setTarea(Tarea tarea) {
    		this.current = tarea;
    	}
}
