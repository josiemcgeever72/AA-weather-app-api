package ie.josie.aa.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import ie.josie.aa.model.CityInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AAControllerIT {

	@LocalServerPort
	private int portNo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${integration.test.host}")
	private String host;
	
	@Value("${integration.test.input.city}")
	private String city;
	
	@Value("${integration.test.expected.output.country}")
	private String country;
	
	@Test
	public void getCityInfo() {
		String url = "http://"+ host+":"+portNo+ "/weather/"+city;
		ResponseEntity<CityInfo> response = restTemplate.getForEntity(url, CityInfo.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getLocation().getCountry()).isEqualTo(country);
		assertThat(response.getBody().getAstronomy().getSunrise().isBefore(response.getBody().getAstronomy().getSunset())).isTrue();
		
	}

	
}
