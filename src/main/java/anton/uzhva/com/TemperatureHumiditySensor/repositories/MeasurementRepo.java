package anton.uzhva.com.TemperatureHumiditySensor.repositories;

import anton.uzhva.com.TemperatureHumiditySensor.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepo extends JpaRepository<Measurement, Integer> {
     Integer countDistinctByIsRainingIsTrue();
}