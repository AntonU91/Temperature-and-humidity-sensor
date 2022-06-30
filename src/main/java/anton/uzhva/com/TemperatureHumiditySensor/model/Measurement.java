package anton.uzhva.com.TemperatureHumiditySensor.model;

import jdk.jfr.BooleanFlag;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Entity
@Table (name = "measurement" ,schema = "sensor_proj")
 public class Measurement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "value")
    @Range (min = -100, max =100, message = "The temperature range can only be between -100 and +100 degrees ")
    private double value;

    @Column(name ="raining")
    @NotNull
    private boolean isRaining;

    @ManyToOne ()
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    @NotNull
    private Sensor sensor;

    @Column(name = "made_at")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date made_at;

    public Measurement() {
    }

    public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

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

    public Date getMade_at() {
        return made_at;
    }

    public void setMade_at(Date made_at) {
        this.made_at = made_at;
    }
}
