package ie.josie.aa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ie.josie.aa.model.CityInfo;
import ie.josie.aa.service.AAServer;

@RestController
public class AAController {

	private static final Logger log = LoggerFactory.getLogger(AAController.class);
	
	@Autowired
	AAServer weatherServer;
	
	@GetMapping(value = "/weather/{city}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public CityInfo getCityInfo(@PathVariable(required=true) String city){
		log.info("city = {}", city);
		return weatherServer.getCityInfo(city);
	}
	
}
