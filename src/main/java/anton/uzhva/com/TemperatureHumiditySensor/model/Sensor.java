package anton.uzhva.com.TemperatureHumiditySensor.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
@Entity
@Table(name = "sensor", schema = "sensor_proj")
public class Sensor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "name")
    @NotEmpty (message = "The sensor`s name should not be empty")
    @Size(min = 3, max=30, message = "The quantity of  characters in sensor`s name should be between 3 and 30 characters ")
    private String sensorName;

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
