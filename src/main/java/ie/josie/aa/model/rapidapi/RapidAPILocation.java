package ie.josie.aa.model.rapidapi;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RapidAPILocation {
	private String name;
	private String region;
	private String country;
	private String lat;
	private String lon;
	@JsonFormat(pattern = "yyyy-MM-dd H:mm")
	private LocalDateTime localtime;
	
}
