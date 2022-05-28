package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastImportDto;
import softuni.exam.models.dto.ForecastImportRootDto;
import softuni.exam.models.entity.DayofWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final CityService cityService;

    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.cityService = cityService;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/files/xml/forecasts.xml")));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder builder = new StringBuilder();
        ForecastImportRootDto forecastImportRootDto = this.xmlParser.parseXml(ForecastImportRootDto.class, "src/main/resources/files/xml/forecasts.xml");

        for (ForecastImportDto forecast : forecastImportRootDto.getForecasts()) {
            DayofWeek dayofWeek;
            try {
                dayofWeek = DayofWeek.valueOf(forecast.getDayOfWeek());
            }catch (Exception e){
                builder.append("Invalid forecast")
                        .append(System.lineSeparator());
                continue;
            }

            if (this.validationUtil.isValid(forecast)){
                Forecast map = this.modelMapper.map(forecast, Forecast.class);
                map.setDayofWeek(dayofWeek);
                map.setCity(this.cityService.findById(forecast.getCity()));
                this.forecastRepository.saveAndFlush(map);

                builder.append(String.format("Successfully import forecast %s - %.2f", forecast.getDayOfWeek(),forecast.getMaxTemperature()))
                        .append(System.lineSeparator());
            }else {
                builder.append("Invalid forecast")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder builder = new StringBuilder();

        Set<Forecast> forecasts = this.forecastRepository.onlySundayForecasts(DayofWeek.SUNDAY, 150000);

        for (Forecast forecast : forecasts) {
            builder.append(String.format("City: %s:", forecast.getCity().getCityName())).append(System.lineSeparator());
            builder.append(String.format("\t-min temperature: %.2f", forecast.getMinTemperature())).append(System.lineSeparator());
            builder.append(String.format("\t--max temperature: %.2f", forecast.getMaxTemperature())).append(System.lineSeparator());
            builder.append(String.format("\t---sunrise: %s", forecast.getSunrise())).append(System.lineSeparator());
            builder.append(String.format("\t----sunset: %s", forecast.getSunset())).append(System.lineSeparator());
        }

        return builder.toString();
    }
}
