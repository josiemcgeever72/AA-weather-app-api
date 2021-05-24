package ie.josie.aa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ie.josie.aa.model.CityInfo;
import ie.josie.aa.model.rapidapi.RapidAPICityInfo;
import ie.josie.aa.util.AAConverter;

@Service
public class AAServer {
	
	private static final Logger log = LoggerFactory.getLogger(AAServer.class);

    @Value("${weather.api.baseURL}")
    private String baseUrl;
	
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    AAConverter converter;
    
	public CityInfo getCityInfo(String name)
	{
		CityInfo cityInfo = null;
		log.info("getCityInfo: parm = {}", name);
        ResponseEntity<RapidAPICityInfo> response1 = restTemplate.getForEntity(baseUrl+ "current.json?q=" + name, RapidAPICityInfo.class );
        if (response1.getStatusCode().equals(HttpStatus.OK)) {
        	RapidAPICityInfo resp1= response1.getBody();
            ResponseEntity<RapidAPICityInfo> response2 = restTemplate.getForEntity(baseUrl+ "astronomy.json?q=" + name, RapidAPICityInfo.class );
            if (response2.getStatusCode().equals(HttpStatus.OK)) {
            	resp1.setAstronomy(response2.getBody().getAstronomy());
            }
            cityInfo = converter.convert(resp1);
        }
        log.info("getCityInfo: cityInfo = {}", cityInfo);
		return cityInfo;
	}

}
