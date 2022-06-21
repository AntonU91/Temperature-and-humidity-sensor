package anton.uzhva.com.TemperatureHumiditySensor.util;



import anton.uzhva.com.TemperatureHumiditySensor.model.Sensor;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
    }
}
