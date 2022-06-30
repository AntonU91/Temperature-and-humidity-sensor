package anton.uzhva.com.TemperatureHumiditySensor.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {

    private final static String URL_FOR_REGISTERING_SENSOR = "http://localhost:7070/sensors/registration";
    private final static String URL_TO_ADD_MEASUREMENT = "http://localhost:7070/measurements/add";

    public static void main(String[] args) {
        double minTemperature = -100.0;
        double maxTemperature = 100.0;
        double diapason = maxTemperature + Math.abs(minTemperature) + 1;

        // registerSensor("test sensor 3");


        for (int i = 0; i < 5; i++) {
            sendMeasurement(Math.random() * diapason - maxTemperature, new Random().nextBoolean(), "test sensor 3");
        }


    }

    public static void registerSensor(String sensorName) {
        Map<String, Object> newSensor = new HashMap<>();
        newSensor.put("name", sensorName);
        sendJson(newSensor, URL_FOR_REGISTERING_SENSOR);
    }

    public static void sendMeasurement(double value, boolean raining, String sensorName) {

        Map<String, Object> measurements = new HashMap<>();
        measurements.put("value", value);
        measurements.put("raining", raining);
        measurements.put("sensor", Map.of("name", sensorName));

        sendJson(measurements, URL_TO_ADD_MEASUREMENT);
    }

    public static void sendJson(Map<String, Object> json, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(json, headers);
        restTemplate.postForObject(url, request, String.class);

    }


}
