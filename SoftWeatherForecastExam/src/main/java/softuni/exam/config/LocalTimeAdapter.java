package softuni.exam.config;

import org.apache.tomcat.jni.Local;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {
    @Override
    public LocalTime unmarshal(String s) throws Exception {
        return LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public String marshal(LocalTime localTime) throws Exception {
        return localTime.toString();
    }
}
