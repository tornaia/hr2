package hu.interconnect.hr.module.personaldata.photo;

import static hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus.KEPTARHELY;
import static hu.interconnect.util.FileUtils.getClasspathByteArray;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.FenykepekResponseDTO;
import hu.interconnect.hr.backend.api.dto.FenykepekResponseDTO.FenykepResponseDTO;
import hu.interconnect.hr.backend.api.dto.FileResponseDTO;
import hu.interconnect.hr.backend.api.service.PhotosQueryService;
import hu.interconnect.hr.dao.FenykepDAO;
import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.domain.Fenykep;
import hu.interconnect.hr.domain.RendszerParameter;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.util.FileUtils;
import hu.interconnect.util.ImageUtils;

@Component
public class PhotosQueryServiceImpl implements PhotosQueryService {
	
	private static final int PERSONAL_DETAILS_WIDTH = 200;
	private static final int PERSONAL_DETAILS_HEIGHT = 288;
	private static final byte[] NO_PIC = getClasspathByteArray("/images/icon_no_photo.png");
	
	private static final Function<Fenykep, FenykepResponseDTO> FENYKEP_TO_DTO_FUNCTION = (Fenykep f) -> new FenykepResponseDTO(f.getId(), f.getEredetiNev(), f.isMiniatur());
	
	@Autowired
	private RendszerParameterDAO rendszerParameterDAO;
	
	@Autowired
	private FenykepDAO fenykepDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public byte[] get(int id) {
		Fenykep fenykep = fenykepDAO.findById(id);
		byte[] bytes = getFenykepAsByteArray(fenykep);
		return ImageUtils.toJpg(ImageUtils.KnownImageType.JPG.name(), bytes, PERSONAL_DETAILS_WIDTH, PERSONAL_DETAILS_HEIGHT);
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public byte[] getMiniatur(int tsz) {
		Optional<Fenykep> optionalFenykep = fenykepDAO.findMiniatur(tsz);
		if (optionalFenykep.isPresent()) {
			Fenykep fenykep = optionalFenykep.get();
			byte[] bytes = getFenykepAsByteArray(fenykep);
			return ImageUtils.toJpg(ImageUtils.KnownImageType.JPG.name(), bytes, PERSONAL_DETAILS_WIDTH, PERSONAL_DETAILS_HEIGHT);
		}
		return NO_PIC;
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public FenykepekResponseDTO getFenykepDTOk(int tsz) {
		List<Fenykep> fenykepek = fenykepDAO.findBySzemelyitorzs(tsz);
		return new FenykepekResponseDTO(fenykepek.stream().map(FENYKEP_TO_DTO_FUNCTION).collect(Collectors.toList()));
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public FileResponseDTO download(int id) {
		Fenykep fenykep = fenykepDAO.findById(id);
		String eredetiNev = fenykep.getEredetiNev();
		String filenev = eredetiNev.substring(0, Math.max(0, eredetiNev.lastIndexOf('.'))) + ".jpg";
		return new FileResponseDTO(filenev, getFenykepAsByteArray(fenykep));
	}

	private byte[] getFenykepAsByteArray(Fenykep fenykep) {
		 return FileUtils.getByteArray(getKeptarhelyUtvonal() + fenykep.getId() + ".jpg");
	}

	private String getKeptarhelyUtvonal() {
		RendszerParameter keptarhelyParameter = rendszerParameterDAO.findByTipus(KEPTARHELY);
		return keptarhelyParameter.getErtek();
	}
}
