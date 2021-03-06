package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class FrameBase extends JFrame {

	private static final long serialVersionUID = -5551627370502464343L;
	protected Handler handler;
	
	public FrameBase(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void initUI();
	
	public void cambiarPanel(JPanel panel){
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().validate();
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	protected abstract String setTitulo();
	
}
