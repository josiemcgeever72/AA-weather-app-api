package ie.josie.aa.config;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
	
	private static Logger log = LoggerFactory.getLogger(AppConfig.class);
	
	@Value("${x.rapidapi.timeout.in.secs}")
	private int  timeout;
    
    @Value("${x.rapidapi.key.value}")
    private String xRapidapiKeyValue;

    @Value("${x.rapidapi.host.value}")
    private String xRapidapiHostValue;
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
		log.info("RestTemplate timeout in secs=[{}]", timeout);
		RestTemplate rt = builder.setConnectTimeout(Duration.ofSeconds(timeout)).setReadTimeout(Duration.ofSeconds(timeout)).build();
		rt.getInterceptors().add((req, body, exec) -> 	{ 	req.getHeaders().add("x-rapidapi-key", 	xRapidapiKeyValue);
															req.getHeaders().add("x-rapidapi-host", xRapidapiHostValue);
															return exec.execute(req, body);
														});
		return rt;
	}
	
	@Bean
	public ModelMapper modelMaeer() {
		return new ModelMapper();
	} 
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOriginPatterns("*")
				.allowedMethods("GET", "OPTIONS");
			}
		};
	}
	
	
}
