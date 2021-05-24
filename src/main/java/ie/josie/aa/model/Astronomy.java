package ie.josie.aa.model;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Astronomy {

	private LocalTime sunrise;
	private LocalTime sunset;
	private String moonPhase;
}
