package anton.uzhva.com.TemperatureHumiditySensor.util;

public class SensorNameNotUpdetedException extends Throwable {
    public SensorNameNotUpdetedException(String errorMsg) {
        super(errorMsg);
    }
}
