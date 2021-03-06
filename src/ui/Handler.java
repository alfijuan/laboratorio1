package ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import bo.EmpleadoBO;
import bo.HorasBO;
import bo.ProyectoBO;
import bo.TareaBO;
import bo.UserBO;
import dao.EmpleadoDaoImpl;
import dao.HorasDaoImpl;
import dao.ProyectoDaoImpl;
import dao.TareaDaoImpl;
import dao.UserDaoImpl;
import empresa.Empleado;
import empresa.Hora;
import empresa.Proyecto;
import empresa.Tarea;
import empresa.User;
import exceptions.SystemException;
import exceptions.user.UserOrPassDontExistException;
import ui.table.HorasTable;
import ui.table.EmpleadoTable;
import ui.table.ProyectoCostoDetalladoTable;
import ui.table.TareaTable;

public class Handler {
	
	private MiFrame frame;
	private LoginFrame loginFrame;
	private EmpleadoBO empleadoBO;
	private TareaBO tareaBO;
	private HorasBO horasBO;
	private ProyectoBO proyectoBO;
	private UserBO userBO;

	public Handler() {
		empleadoBO = new EmpleadoBO();
		empleadoBO.setEmpDao(new EmpleadoDaoImpl());
		
		tareaBO = new TareaBO();
		tareaBO.setTareaDAO(new TareaDaoImpl());
		
		horasBO = new HorasBO();
		horasBO.setHorasDao(new HorasDaoImpl());
		
		proyectoBO = new ProyectoBO();
		proyectoBO.setProyectoDao(new ProyectoDaoImpl());
		
		userBO = new UserBO();
		userBO.setUserDao(new UserDaoImpl());
		
		loginFrame = new LoginFrame(this);
		frame = new MiFrame(this);
	}
	
	public void init() {
		loginFrame.setVisible(true);
        frame.setVisible(false);
        mostrarLogin();
	}
	
	public void mostrarLogin() {
		loginFrame.cambiarPanel(new Login(this));
	}
	
	public void mostrarHoras() {
		loginFrame.setVisible(false);
        frame.setVisible(true);
	}
		
	public void loginUsuario(User user) {
		try {
			userBO.loginUser(user);
			mostrarHoras();
		} catch (UserOrPassDontExistException e1) {
			mostrarModal("Los datos ingresados son incorrectos");
		} catch (SystemException e1) {
			mostrarModal(e1.getMessage());
		}
	}
	
	public void mostrarModal(String title) {
		JOptionPane.showOptionDialog(null, title, "", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, null, null);
	}
	
	public void mostrarTablaEmpleado() {
		try {
			frame.cambiarPanel(new EmpleadoTable(this, this.getEmpleadoBO().obtenerEmpleados()));
		} catch (SystemException e1) {
			this.mostrarModal(e1.getMessage());
		}
	}
	
	public void mostrarAgregarEmpleado() {
		frame.cambiarPanel(new EmpleadoAlta(this));
	}
	
	public void mostrarEditarEmpleado(Empleado emp) {
		frame.cambiarPanel(new EmpleadoModificacion(this, emp));
	}
	
	public void mostrarBorrarEmpleado(int legajo) {
		try {
			this.empleadoBO.eliminarEmpleado(legajo);
			mostrarModal("Empleado borrado correctamente!");
			this.mostrarTablaEmpleado();
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
	}
	
	public Boolean verificarBorradoDeEmpleado (int legajo) {
		Boolean resultado = false;
		try {
			resultado = empleadoBO.validarEliminacionDeEmpleado(legajo);
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
		return resultado;
	}
	
	public void agregarEmpleado(Empleado empleado) {
		try {
			empleadoBO.agregarEmpleado(empleado);
			mostrarModal("Empleado agregado correctamente!");
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
	}
	
	public void editarEmpleado(Empleado empleado) {
		try {
			getEmpleadoBO().editarEmpleado(empleado);
			mostrarModal("Empleado editado correctamente!");
			mostrarTablaEmpleado();
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
	}
	
	
	public void mostrarAgregarTarea() {
		frame.cambiarPanel(new TareaAlta(this));
	}
	
	public void mostrarTablaTarea() {
		try {
			frame.cambiarPanel(new TareaTable(this, this.getTareaBO().obtenerTareas()));			
		} catch (SystemException e1) {
			mostrarModal(e1.getMessage());
		}
	}
	public void agregarTarea(Tarea tarea) {
		try {
			tareaBO.agregarTarea(tarea);
			mostrarModal("Tarea agregada correctamente!");
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
	}
	public void mostrarEditarTarea(Tarea tarea) {
		frame.cambiarPanel(new TareaModificacion(this, tarea));
	}
	
	public void editarTarea(Tarea tarea) {
		try {
			getTareaBO().editarTarea(tarea);
			mostrarModal("Tarea editada correctamente!");
			mostrarTablaTarea();
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
	}
	
	public void borrarTarea(int id) {
		try {
			getTareaBO().borrarTarea(id);
			mostrarModal("Tarea borrada correctamente!");
			mostrarTablaTarea();
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
	}
	
	public Boolean verificarBorradoDeTarea (int id) {
		Boolean resultado = false;
		try {
			resultado = getTareaBO().validarEliminacionDeTarea(id);
		} catch (SystemException e) {
			e.printStackTrace();
			mostrarModal(e.getMessage());
		}
		return resultado;
	}
	
	public void mostrarTablaHoras() {
		try {
			frame.cambiarPanel(new HorasTable(this, this.getHorasBO().obtenerHoras()));
		} catch (SystemException e1) {
			this.mostrarModal(e1.getMessage());
		}
	}
	
	public void mostrarCargarHoras() {
		frame.cambiarPanel(new HoraAlta(this));
	}
	
	public void agregarHora(Hora hora) {
		try {
			horasBO.cargarHoras(hora);
			mostrarModal("Hora cargada correctamente!");
		} catch (SystemException e) {
			e.printStackTrace();
			mostrarModal(e.getMessage());
		}
	}
	
	public void mostrarEditarHora(Hora hora) {
		frame.cambiarPanel(new HoraModificacion(this, hora));
	}
	
	public void editarHora(Hora hora) {
		try {
			getHorasBO().editarHoras(hora);
			mostrarModal("Registro editado correctamente!");
			mostrarTablaHoras();
		} catch (Exception e) {
			this.mostrarModal(e.getMessage());
		}
	}
	
	public void borrarHora(Integer idHora) {
		try {
			getHorasBO().eliminarHoras(idHora);
			mostrarModal("Registro borrado correctamente!");
			mostrarTablaHoras();
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
	}
	
	public void mostrarCostoProyecto() {
		frame.cambiarPanel(new ProyectoCosto(this));
	}
	
	public void mostrarCostoProyectoDetallado(int idProyecto) {
		try {
			frame.cambiarPanel(new ProyectoCostoDetalladoTable(proyectoBO.obtenerCostosProyectosById(idProyecto), this));
		} catch (Exception e) {
			mostrarModal(e.getMessage());
		}
	}
	
	public void mostrarCostosDetallados() {
		frame.cambiarPanel(new ProyectoCostoDetallado(this));
	}

	public Double calcularCostoProyecto(int id) {
		Double costo = null;
		try {
			costo = proyectoBO.obtenerCostosById(id).getCosto();
		} catch (Exception e) {
			e.printStackTrace();
			this.mostrarModal(e.getMessage());
		}
		return costo;
	}
	
	public void calcularCostoEmpleado(int legajo) {
		try {
			List<Integer> resultados = getHorasBO().obtenerHoras(legajo);
			if(resultados.size() > 0 ) {
				mostrarModal("Al empleado se le deben $ " + Integer.toString(resultados.get(1)) + " correspondientes a " + Integer.toString(resultados.get(0)) + " horas.");
			} else {
				mostrarModal("El empleado no registra horas cargadas");
			}
		} catch (SystemException e) {
			this.mostrarModal(e.getMessage());
		}
	}
	
	public List<Tarea> obtenerTareas(){
		List<Tarea> tareas = new ArrayList<Tarea>();
	
		try {
			tareas = getTareaBO().obtenerTareas();
		} catch (SystemException e) {
			e.printStackTrace();
			mostrarModal(e.getMessage());
		}
	
		return tareas;
	}
	
	public List<Empleado> obtenerEmpleados(){
		List<Empleado> empleados = new ArrayList<Empleado>();
		try {
			 empleados = getEmpleadoBO().obtenerEmpleados();
		} catch (Exception e) {
			e.printStackTrace();
			mostrarModal(e.getMessage());
		}
		
		return empleados;
	}
	
	public List<Proyecto> obtenerProyectos(){
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		try {
			 proyectos = getProyectoBO().obtenerProyectos();
		} catch (Exception e) {
			e.printStackTrace();
			mostrarModal(e.getMessage());
		}
		return proyectos;
	}
	
	
	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}

	public void setEmpleadoBO(EmpleadoBO empleadoBO) {
		this.empleadoBO = empleadoBO;
	}
	
	public TareaBO getTareaBO() {
		return tareaBO;
	}

	public void setTareaBO(TareaBO tareaBO) {
		this.tareaBO = tareaBO;
	}

	public HorasBO getHorasBO() {
		return horasBO;
	}

	public void setHorasBO(HorasBO horasBO) {
		this.horasBO = horasBO;
	}

	public ProyectoBO getProyectoBO() {
		return proyectoBO;
	}

	public void setProyectoBO(ProyectoBO proyectoBO) {
		this.proyectoBO = proyectoBO;
	}
	
	
}
