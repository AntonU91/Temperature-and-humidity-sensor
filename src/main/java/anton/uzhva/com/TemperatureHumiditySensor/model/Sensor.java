package anton.uzhva.com.TemperatureHumiditySensor.model;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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
    private String name ;

//    @OneToMany (mappedBy = "sensor",orphanRemoval = true)
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
   // private List<Measurement> measurementList;

//    public List<Measurement> getMeasuarmentList() {
//        return measurementList;
//    }
//    public void addMeasuarment (Measurement measurement) {
//        if (measurement ==null) {
//            measurementList =  new ArrayList<>();
//        }
//        measurementList.add(measurement);
//        measurement.setSensor(this);
//    }
//    public void deleteMeasuarment(Measurement measurement) {
//        measurementList.remove(measurement);
//        measurement.setSensor(null);
  //  }

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
