package ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ui.containers.SalirListener;

public class LoginFrame extends FrameBase {
	
	private static final long serialVersionUID = -5551627370502464343L;

	public LoginFrame(Handler handler) {
		super(handler);
		initUI();
		JMenuBar menuBar = new JMenuBar();
		agregarFuncionesMenu(menuBar);
		setJMenuBar(menuBar);
	}

	public void initUI() {
		setSize(800, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void agregarFuncionesMenu(JMenuBar menuBar) {
		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);
		
        JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(new SalirListener() {
		});
        menuArchivo.add(salir);
		
	}
	
	@Override
	protected String setTitulo() {
		return "Login";
	}
}
