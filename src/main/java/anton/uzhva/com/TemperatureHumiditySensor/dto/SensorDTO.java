package anton.uzhva.com.TemperatureHumiditySensor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "The sensor`s name should not be empty")
    @Size(min = 3, max=30, message = "The quantity of  characters in sensor`s name should be between 3 and 30 characters ")
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
