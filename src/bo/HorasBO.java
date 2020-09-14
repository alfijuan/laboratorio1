package bo;

import java.util.List;
import dao.HorasDAO;
import empresa.Hora;
import exceptions.SystemException;
import exceptions.horas.HoraAlreadyExists;
import exceptions.horas.HoraNotFoundException;

public class HorasBO {
	private HorasDAO horasDao;
	
	public HorasBO() {}
	
	public void cargarHoras(Hora hora) throws SystemException, HoraAlreadyExists{
		if(horasDao.obtenerHora(hora.getIdHora()) == null) {
			horasDao.cargarHoras(hora);
		} else {
			throw new HoraAlreadyExists("La hora ya existe!");
		}
	}
	
	public void editarHoras(Hora hora) throws SystemException, HoraNotFoundException {
		if(horasDao.obtenerHora(hora.getIdHora()) != null) {
			horasDao.editarHoras(hora);
		} else {
			throw new HoraNotFoundException("La hora no existe!");
		}
	}
	
	public void eliminarHoras(int idHora) throws SystemException, HoraNotFoundException {
		if(horasDao.obtenerHora(idHora) != null) {
			horasDao.eliminarHoras(idHora);
		} else {
			throw new HoraNotFoundException("La hora no existe!");
		}
	}
	
	public List<Hora> obtenerHoras() throws SystemException {
		return horasDao.obtenerHoras();
	}
	
	public List<Integer> obtenerHoras(int legajo) throws SystemException{
		return horasDao.obtenerHoras(legajo);
	}
	
	public void setHorasDao(HorasDAO horasDao) {
		this.horasDao = horasDao;
	}

}
