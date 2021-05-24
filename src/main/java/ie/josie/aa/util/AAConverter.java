package ie.josie.aa.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ie.josie.aa.model.Astronomy;
import ie.josie.aa.model.CityInfo;
import ie.josie.aa.model.Weather;
import ie.josie.aa.model.rapidapi.RapidAPICityInfo;

@Component
public class AAConverter {
	
    @Autowired
    private ModelMapper modelMapper;
	
	public CityInfo convert(RapidAPICityInfo rapidAPICity) {
		CityInfo cityInfo = modelMapper.map(rapidAPICity, CityInfo.class);
		cityInfo.setName(rapidAPICity.getLocation().getName());
		cityInfo.getLocation().setLatitude(rapidAPICity.getLocation().getLat());
		cityInfo.getLocation().setLongitude(rapidAPICity.getLocation().getLon());
		cityInfo.getLocation().setLocalDateTime(rapidAPICity.getLocation().getLocaltime());
		cityInfo.setWeather(Weather.builder().tempInCelius(rapidAPICity.getCurrent().getTempC())
				   							 .description(rapidAPICity.getCurrent().getCondition().getText())
				   							 .iconUrl(rapidAPICity.getCurrent().getCondition().getIcon()).build());
		cityInfo.setAstronomy(modelMapper.map(rapidAPICity.getAstronomy().getAstro(), Astronomy.class));
		return cityInfo;
	}
}
