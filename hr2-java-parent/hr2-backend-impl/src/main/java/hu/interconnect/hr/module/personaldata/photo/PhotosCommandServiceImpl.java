package hu.interconnect.hr.module.personaldata.photo;

import static hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus.KEPTARHELY;
import static hu.interconnect.util.FileUtils.writeTo;
import static hu.interconnect.util.ImageUtils.toJpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.FenykepCreateDTO;
import hu.interconnect.hr.backend.api.dto.FenykepekSaveDTO;
import hu.interconnect.hr.backend.api.service.PhotosCommandService;
import hu.interconnect.hr.dao.FenykepDAO;
import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Fenykep;
import hu.interconnect.hr.domain.RendszerParameter;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class PhotosCommandServiceImpl implements PhotosCommandService {

	private static final int IMAGE_MAX_WIDTH = 1600;
	private static final int IMAGE_MAX_HEIGHT = 1200;
	
	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private RendszerParameterDAO rendszerParameterDAO;
	
	@Autowired
	private FenykepDAO fenykepDAO;

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(FenykepCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		String type = dto.getEredetiNev().substring(dto.getEredetiNev().lastIndexOf('.') + 1);
		kepfeltoltes(szemelyitorzs, dto.getEredetiNev(), type, dto.getAdat());
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void save(FenykepekSaveDTO dto) {
		dto.getFenykepek()
			.stream()
			.forEach(p -> fenykepDAO.findById(p.getId()).merge(p));
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		fenykepDAO.delete(id);
	}

	private void kepfeltoltes(Szemelyitorzs szemelyitorzs, String filenev, String type, byte[] b) {
		Fenykep fenykep = new Fenykep(szemelyitorzs, filenev, false);
		fenykepDAO.persist(fenykep);

		RendszerParameter keptarhelyParameter = rendszerParameterDAO.findByTipus(KEPTARHELY);
		String konyvtarUtvonal = keptarhelyParameter.getErtek();

		String kepFileName = fenykep.getId() + ".jpg";

		byte[] atmeretezettJpg = toJpg(type, b, IMAGE_MAX_WIDTH, IMAGE_MAX_HEIGHT);
		writeTo(konyvtarUtvonal + kepFileName, atmeretezettJpg);
	}
}
