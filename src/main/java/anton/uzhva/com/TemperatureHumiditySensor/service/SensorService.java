package anton.uzhva.com.TemperatureHumiditySensor.service;

import anton.uzhva.com.TemperatureHumiditySensor.dto.SensorDTO;
import anton.uzhva.com.TemperatureHumiditySensor.model.Sensor;
import anton.uzhva.com.TemperatureHumiditySensor.repositories.SensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepo sensorRepo;

    @Autowired
    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;

    }

    public Optional<Sensor> getSensorById(int id) {
        return sensorRepo.findById(id);
    }

    public Sensor getSensorByName(String name) {
        return sensorRepo.findSensorByName(name);
    }

    @Transactional
    public void saveSensor(Sensor sensor) {
        sensorRepo.save(sensor);
    }

    @Transactional
    public void deleteSensor(Sensor sensor) {
        sensorRepo.delete(sensor);
    }

    @Transactional
    public void updateSensorName(Sensor sensor, String updatedName) {
        Sensor updatedSensor = sensorRepo.findById(sensor.getId()).orElse(null);
        updatedSensor.setName(updatedName);
        sensorRepo.save(updatedSensor);
    }


    public boolean isSensorExist(String name) {
        return sensorRepo.findSensorByName(name) != null;
    }
}

