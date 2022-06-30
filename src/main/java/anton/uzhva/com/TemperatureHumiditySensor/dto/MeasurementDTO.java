package anton.uzhva.com.TemperatureHumiditySensor.dto;

import anton.uzhva.com.TemperatureHumiditySensor.model.Sensor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class MeasurementDTO {


    @Range(min = -100, max =100, message = "The temperature range can only be between -100 and +100 degrees ")
    private double value;


    @NotNull
    private boolean isRaining;



    @NotNull
    private Sensor sensor;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
