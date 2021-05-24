package ie.josie.aa.model.rapidapi;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RapidAPIAstro {

	@JsonFormat(pattern = "hh:mm a")
	private LocalTime sunset;
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime sunrise;
	private String moonPhase;
}
