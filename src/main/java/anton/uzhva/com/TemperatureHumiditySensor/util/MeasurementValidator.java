package anton.uzhva.com.TemperatureHumiditySensor.util;

import anton.uzhva.com.TemperatureHumiditySensor.dto.MeasurementDTO;
import anton.uzhva.com.TemperatureHumiditySensor.model.Measurement;
import anton.uzhva.com.TemperatureHumiditySensor.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator  implements Validator {
    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       Measurement measurement = (Measurement) target;
        if(!sensorService.isSensorExist(measurement.getSensor().getName())) {
            errors.rejectValue("sensor", "", "There are no sensors with such name");
        }
    }
}
