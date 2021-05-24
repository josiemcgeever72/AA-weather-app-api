package ie.josie.aa.model;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Location {

	
	private String country;
	private String region;
	private String latitude;
	private String longitude;
	private LocalDateTime localDateTime;

}
