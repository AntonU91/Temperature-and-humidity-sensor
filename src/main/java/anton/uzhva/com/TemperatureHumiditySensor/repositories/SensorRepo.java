package anton.uzhva.com.TemperatureHumiditySensor.repositories;

import anton.uzhva.com.TemperatureHumiditySensor.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepo extends JpaRepository<Sensor, Integer> {
    Sensor findSensorByName(String  name);
}
