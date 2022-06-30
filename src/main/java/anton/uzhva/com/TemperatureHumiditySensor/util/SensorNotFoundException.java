package anton.uzhva.com.TemperatureHumiditySensor.util;

public class SensorNotFoundException extends Throwable {
    public SensorNotFoundException(String errorMSg) {
        super(errorMSg);
    }
}
