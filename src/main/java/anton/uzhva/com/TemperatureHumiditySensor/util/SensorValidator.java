package anton.uzhva.com.TemperatureHumiditySensor.util;


import anton.uzhva.com.TemperatureHumiditySensor.model.Sensor;
import anton.uzhva.com.TemperatureHumiditySensor.service.SensorService;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.getSensorByName(sensor.getName()) != null|| errors.getFieldErrorCount()==2) {
            errors.rejectValue("name", "", "This sensor`s name is already taken");
        }
    }
}
