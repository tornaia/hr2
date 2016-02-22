package hu.interconnect.hr.module.personaldata.timeandattendance;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvResponseDTO;
import hu.interconnect.hr.backend.api.service.TimeandattendanceQueryService;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class TimeandattendanceQueryServiceImpl implements TimeandattendanceQueryService {
	
	@Autowired
	private TimeandattendanceMonthlyDataGenerator haviJelenletiIvEloallito;
	
	@Override
	@PreAuthorize(AuthorityConstants.PERSONAL_DATA_ID_EQUALS_TO + " #tsz " + AuthorityConstants.OR + AuthorityConstants.HAS_ROLE_ADMINISTRATOR + AuthorityConstants.OR + AuthorityConstants.HAS_ROLE_BETEKINTO)
	public HaviJelenletiIvResponseDTO getHaviJelenletiIv(int tsz, Date honap) {
		return haviJelenletiIvEloallito.getHaviJelenletiIv(tsz, honap);
	}
}
