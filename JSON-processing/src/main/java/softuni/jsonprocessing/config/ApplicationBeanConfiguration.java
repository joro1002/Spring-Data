package softuni.jsonprocessing.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> LocalDateTime
                        .parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .create();
    }
}
