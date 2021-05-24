package ie.josie.aa.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ie.josie.aa.model.Astronomy;
import ie.josie.aa.model.CityInfo;
import ie.josie.aa.model.Location;
import ie.josie.aa.model.Weather;
import ie.josie.aa.service.AAServer;

@ExtendWith(MockitoExtension.class)
public class AAControllerTest {
	
	@InjectMocks
	AAController aaCntrlr;
	
	@Mock
	AAServer   mockAAServer;
	
	@Test
	public void getCityInfo_ImplCallsServiceWithParmAndRetrunsSeviceObject() {
		//Data set-up
		String arbitaryCity = "Nairobi";
		CityInfo arbitaryCityInfo = getArbitaryCityInfo();
		//Mocking
		when(mockAAServer.getCityInfo(arbitaryCity)).thenReturn(arbitaryCityInfo);
		//ACTUAL METHOD BEING TESTED
		CityInfo cntrlrReturnObj = aaCntrlr.getCityInfo(arbitaryCity);
		//Verifications & Assertions
		verify(mockAAServer).getCityInfo(arbitaryCity);
		assertThat(cntrlrReturnObj).isEqualTo(arbitaryCityInfo);
	}

	private CityInfo getArbitaryCityInfo() {
		return CityInfo.builder().location(Location.builder().country("C").region("R").latitude("lat").longitude("Lon").localDateTime(LocalDateTime.now()).build())
								 .astronomy(Astronomy.builder().sunrise(LocalTime.NOON).sunset(LocalTime.MIDNIGHT).moonPhase("MP").build())
								 .weather(Weather.builder().description("DESC").iconUrl("IU").tempInCelius("TIC").build())
								 .name("N")
								 .build();
	}
	

}
