package ie.josie.aa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CityInfo {

	private String name;
	private Location location;
	private Astronomy astronomy;
	private Weather weather;
}
