package anton.uzhva.com.TemperatureHumiditySensor.service;

import anton.uzhva.com.TemperatureHumiditySensor.model.Measurement;
import anton.uzhva.com.TemperatureHumiditySensor.model.Sensor;
import anton.uzhva.com.TemperatureHumiditySensor.repositories.MeasurementRepo;
import anton.uzhva.com.TemperatureHumiditySensor.repositories.SensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepo measurementRepo;
    private final SensorRepo sensorRepo;

    @Autowired
    public MeasurementService(MeasurementRepo measurementRepo, SensorRepo sensorRepo) {
        this.measurementRepo = measurementRepo;
        this.sensorRepo = sensorRepo;
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepo.findAll();
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        Sensor sensor = sensorRepo.findSensorByName(measurement.getSensor().getName());
        measurement.setSensor(sensor);
        measurement.setMade_at(new Date());
        measurementRepo.save(measurement);
    }



    public Integer countOfRainyDays () {
        return measurementRepo.countDistinctByIsRainingIsTrue();
    }

}
