package ie.josie.aa.model.rapidapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RapidAPIWeather {
	private String tempC;
	private RapidAPWeatherCondition condition;
}
