package ie.josie.aa.util;

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
import org.modelmapper.ModelMapper;

import ie.josie.aa.model.Astronomy;
import ie.josie.aa.model.CityInfo;
import ie.josie.aa.model.Location;
import ie.josie.aa.model.rapidapi.RapidAPIAstro;
import ie.josie.aa.model.rapidapi.RapidAPIAstronomy;
import ie.josie.aa.model.rapidapi.RapidAPICityInfo;
import ie.josie.aa.model.rapidapi.RapidAPILocation;
import ie.josie.aa.model.rapidapi.RapidAPIWeather;
import ie.josie.aa.model.rapidapi.RapidAPWeatherCondition;

@ExtendWith(MockitoExtension.class)
public class AAConverterTest {

	@InjectMocks
	AAConverter 	aaConverter;
	
	@Mock
	ModelMapper   mockModelMapper;
	
	@Test
	public void getCityInfo_ImplCallsModelMapper() {
		//Data set-up
		RapidAPICityInfo rapidApiCityInfo = getArbitaryRapidAPICityInfo();
		//Mocking
		when(mockModelMapper.map(rapidApiCityInfo, CityInfo.class)).thenReturn( getArbitaryCityInfo());
		when(mockModelMapper.map(rapidApiCityInfo.getAstronomy().getAstro(),Astronomy.class)).thenReturn( getArbitaryAstronomy());
		//ACTUAL METHOD BEING TESTED
		CityInfo converterReturnObj = aaConverter.convert(rapidApiCityInfo);
		//Verifications & Assertions
		verify(mockModelMapper).map(rapidApiCityInfo,CityInfo.class);
		verify(mockModelMapper).map(rapidApiCityInfo.getAstronomy().getAstro(),Astronomy.class);
		assertThat(converterReturnObj.getName()).isEqualTo(	rapidApiCityInfo.getLocation().getName());
		assertThat(converterReturnObj.getLocation().getLatitude()).isEqualTo(	rapidApiCityInfo.getLocation().getLat());
		assertThat(converterReturnObj.getLocation().getLongitude()).isEqualTo(	rapidApiCityInfo.getLocation().getLon());
		assertThat(converterReturnObj.getLocation().getLocalDateTime()).isEqualTo(	rapidApiCityInfo.getLocation().getLocaltime());
		assertThat(converterReturnObj.getWeather().getTempInCelius()).isEqualTo(rapidApiCityInfo.getCurrent().getTempC());
		assertThat(converterReturnObj.getWeather().getDescription()).isEqualTo(rapidApiCityInfo.getCurrent().getCondition().getText());
		assertThat(converterReturnObj.getWeather().getIconUrl()).isEqualTo(rapidApiCityInfo.getCurrent().getCondition().getIcon());
	}
	private Astronomy getArbitaryAstronomy() {
		return Astronomy.builder().moonPhase("XX").build();
	}
	private RapidAPICityInfo getArbitaryRapidAPICityInfo() {
		return RapidAPICityInfo.builder().location(RapidAPILocation.builder().name("N").country("C").region("R").lat("La").lon("Lo").localtime(LocalDateTime.now()).build())
										 .astronomy(RapidAPIAstronomy.builder().astro(RapidAPIAstro.builder().moonPhase("MP").sunset(LocalTime.MIDNIGHT).sunrise(LocalTime.NOON).build()).build())
										 .current(RapidAPIWeather.builder().tempC("T").condition(RapidAPWeatherCondition.builder().text("Te").icon("I").build()).build())
										 .build();							 
	}
	private CityInfo getArbitaryCityInfo() {
		return CityInfo.builder().location(Location.builder().country("C").region("R").build()).build();
	}
}
