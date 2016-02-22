package hu.interconnect.hr.module.menu;

import org.springframework.stereotype.Component;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.dto.MenuResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.hr.backend.api.service.MenuQueryService;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.util.FelhasznaloUtils;

@Component
public class MenuQueryServiceImpl implements MenuQueryService {

	@Override
	public MenuResponseDTO getMenu() {
		Felhasznalo aktualisFelhasznalo = FelhasznaloUtils.aktualisFelhasznalo();
		Szerep szerep = aktualisFelhasznalo.getSzerep();
		switch (szerep) {
		case NEM_VEDETT:
			throw new ProgramozasiHiba("NEM_VEDETT must not ask for menu");
		case DOLGOZO:
			return new MenuResponseDTO(
					new MenuResponseDTO.MenuItemDTO("TIMEANDATTENDANCE"));
		case BETEKINTO:
		case ADMINISTRATOR:
			return new MenuResponseDTO(
					new MenuResponseDTO.MenuItemDTO("PERSONALDATA"),
					new MenuResponseDTO.MenuItemDTO("reports"),
					new MenuResponseDTO.MenuItemDTO("administration", 
						new MenuResponseDTO.MenuItemDTO("users"),
						new MenuResponseDTO.MenuItemDTO("valuesets"),
						new MenuResponseDTO.MenuItemDTO("systemparameters"),
						new MenuResponseDTO.MenuItemDTO("scheduledtasks"),
						new MenuResponseDTO.MenuItemDTO("exceptiondays")));
			default: throw new ProgramozasiHiba("Unknown role: " + aktualisFelhasznalo.getSzerep());
		}
	}
	
	private MenuQueryServiceImpl() {
	}
}
