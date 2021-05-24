package ie.josie.aa.model.rapidapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RapidAPWeatherCondition {
	private String text;
	private String icon;
}
