package ie.josie.aa.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import ie.josie.aa.model.CityInfo;
import ie.josie.aa.model.Location;
import ie.josie.aa.model.rapidapi.RapidAPIAstro;
import ie.josie.aa.model.rapidapi.RapidAPIAstronomy;
import ie.josie.aa.model.rapidapi.RapidAPICityInfo;
import ie.josie.aa.model.rapidapi.RapidAPILocation;
import ie.josie.aa.model.rapidapi.RapidAPIWeather;
import ie.josie.aa.model.rapidapi.RapidAPWeatherCondition;
import ie.josie.aa.util.AAConverter;

@ExtendWith(MockitoExtension.class)
public class AAServerTest {

	@InjectMocks
	AAServer aaServer;
	
	@Mock
	RestTemplate   	mockRestTemplate;
	
	@Mock
	AAConverter		mockAAConverter;
	
    @Value("${weather.api.baseURL}")
    private String baseUrl;
	
	@Test
	public void getCityInfo_ImplCallsRestTemplateAndModelMapper() {
		//Data set-up
		String arbitaryCity = "Nairobi";
		RapidAPICityInfo rapidApiCityInfo1 = getArbitaryRapidAPICityInfo1();
		RapidAPICityInfo rapidApiCityInfo2 = getArbitaryRapidAPICityInfo2();
		RapidAPICityInfo radidInfoCityInfo1and2 = getArbitaryRapidAPICityInfo1();
		radidInfoCityInfo1and2.setAstronomy(getArbitaryRapidAPICityInfo2().getAstronomy());
		String currentURL = baseUrl+ "current.json?q=" + arbitaryCity;
		String astronomyURL = baseUrl+ "astronomy.json?q=" + arbitaryCity;
		CityInfo arbitaryCityInfo = getArbitaryCityInfo();
		//Mocking
		when(mockRestTemplate.getForEntity(currentURL,RapidAPICityInfo.class)).thenReturn(new ResponseEntity<RapidAPICityInfo>(rapidApiCityInfo1, HttpStatus.OK));
		when(mockRestTemplate.getForEntity(astronomyURL,RapidAPICityInfo.class)).thenReturn(new ResponseEntity<RapidAPICityInfo>(rapidApiCityInfo2, HttpStatus.OK));
		when(mockAAConverter.convert(radidInfoCityInfo1and2)).thenReturn( arbitaryCityInfo);
		//ACTUAL METHOD BEING TESTED
		CityInfo serverReturnObj = aaServer.getCityInfo(arbitaryCity);
		//Verifications & Assertions
		verify(mockRestTemplate).getForEntity(currentURL,RapidAPICityInfo.class);
		verify(mockRestTemplate).getForEntity(astronomyURL,RapidAPICityInfo.class);
		verify(mockAAConverter).convert(radidInfoCityInfo1and2);
		assertThat(serverReturnObj).isEqualTo(	arbitaryCityInfo);
	}

	private RapidAPICityInfo getArbitaryRapidAPICityInfo1() {
		return RapidAPICityInfo.builder().location(RapidAPILocation.builder().country("C1").region("R1").lat("La1").lon("Lo1").localtime(null).build())
										 .astronomy(RapidAPIAstronomy.builder().astro(RapidAPIAstro.builder().moonPhase("MP1").sunset(null).sunrise(LocalTime.NOON).build()).build())
										 .current(RapidAPIWeather.builder().tempC("T1").condition(RapidAPWeatherCondition.builder().text("Te1").icon("I1").build()).build())
										 .build();
										 
	}

	private RapidAPICityInfo getArbitaryRapidAPICityInfo2() {
		return RapidAPICityInfo.builder().location(RapidAPILocation.builder().country("C2").region("R2").lat("La2").lon("Lo2").localtime(null).build())
										 .astronomy(RapidAPIAstronomy.builder().astro(RapidAPIAstro.builder().moonPhase("MP1").sunset(LocalTime.MIDNIGHT).sunrise(null).build()).build())
										 .current(RapidAPIWeather.builder().tempC("T2").condition(RapidAPWeatherCondition.builder().text("Te2").icon("I2").build()).build())
										 .build();
										 
	}

	private CityInfo getArbitaryCityInfo() {
		return CityInfo.builder().location(Location.builder().country("C").region("R").build()).build();
	}
	

}
