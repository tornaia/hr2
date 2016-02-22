package test.unit.hu.interconnect.hr.module.personaldata.timeandattendance;

import static hu.interconnect.util.DateUtils.parseHonap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.personaldata.timeandattendance.TimeandattendanceMonthlyDataGenerator;
import hu.interconnect.hr.module.personaldata.timeandattendance.TimeandattendanceQueryServiceImpl;
import test.builder.SzemelyitorzsBuilder;
import test.unit.AbstractBackendUnitTest;

public class TimeandattendanceQueryServiceTest extends AbstractBackendUnitTest {

	@Mock
	private TimeandattendanceMonthlyDataGenerator timeandattendanceMonthlyDataGenerator;
	
	@InjectMocks
	private TimeandattendanceQueryServiceImpl timeandattendanceQueryService;

	private Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();

	private Date month = parseHonap("2013.3");
	
	@Test
	public void getHaviJelenletiIvBetekintoTest() {
		betekintoBejelentkezik();

		timeandattendanceQueryService.getHaviJelenletiIv(szemelyitorzs.getTsz(), month);

		verify(timeandattendanceMonthlyDataGenerator).getHaviJelenletiIv(szemelyitorzs.getTsz(), month);
		verifyNoMoreInteractions(timeandattendanceMonthlyDataGenerator);
	}
}
