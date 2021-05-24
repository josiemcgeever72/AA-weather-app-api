package ie.josie.aa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Weather {
	private String tempInCelius;
	private String description;
	private String iconUrl;
}
