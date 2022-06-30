package anton.uzhva.com.TemperatureHumiditySensor.util;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String errorMsg) {
        super(errorMsg);
    }
}
