package hu.interconnect.scheduler;

import static hu.interconnect.util.DateUtils.aktualisIdo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.dao.IdozitettFuttatasDAO;
import hu.interconnect.hr.domain.IdozitettFuttatas;

@Service
public class IdozitettFuttatasEsemenyLetrehozo {

	@Autowired
	private IdozitettFuttatasDAO idozitettFuttatasDAO;

	public IdozitettFuttatas futtatastKezd(AbstractIdozitettFeladat abstractUtemezettFeladat) {
		String azonosito = abstractUtemezettFeladat.getClass().getSimpleName();
		IdozitettFuttatas futtatas = new IdozitettFuttatas(azonosito, aktualisIdo());
		idozitettFuttatasDAO.persist(futtatas);
		return futtatas;
	}

	public void futtatastSikeresenBefejez(IdozitettFuttatas futtatas, String eredmeny) {
		IdozitettFuttatas attachedFuttatas = idozitettFuttatasDAO.findById(futtatas.getId());
		attachedFuttatas.futastBefejez(eredmeny);
	}

	public void futtatastSikertelenulBefejez(IdozitettFuttatas futtatas, Throwable t) {
		IdozitettFuttatas attachedFuttatas = idozitettFuttatasDAO.findById(futtatas.getId());
		attachedFuttatas.futastBefejez(t.getMessage());
	}
}
