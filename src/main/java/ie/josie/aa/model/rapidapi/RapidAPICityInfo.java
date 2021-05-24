package ie.josie.aa.model.rapidapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RapidAPICityInfo {
	private RapidAPILocation	location;
	private RapidAPIWeather 	current;
	private RapidAPIAstronomy  	astronomy;
}
