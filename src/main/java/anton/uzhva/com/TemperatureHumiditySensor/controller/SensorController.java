package anton.uzhva.com.TemperatureHumiditySensor.controller;

import anton.uzhva.com.TemperatureHumiditySensor.dto.SensorDTO;
import anton.uzhva.com.TemperatureHumiditySensor.model.Sensor;
import anton.uzhva.com.TemperatureHumiditySensor.service.SensorService;
import anton.uzhva.com.TemperatureHumiditySensor.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }


    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorService.saveSensor(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSensor(@PathVariable int id) throws SensorNotFoundException {
        Optional<Sensor> sensor = sensorService.getSensorById(id);
        if (sensor.isPresent()) {
            sensorService.deleteSensor(sensor.get());
            return ResponseEntity.ok(HttpStatus.OK);
        }
        throw new SensorNotFoundException("There no such sensor with id " + id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateSensorName(@PathVariable(name = "id") int id,
                                                       @RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) throws SensorNotFoundException, SensorNameNotUpdetedException {
        Optional<Sensor> sensor = sensorService.getSensorById(id);
        if (sensor.isPresent()) {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMsg = new StringBuilder();
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors) {
                    errorMsg.append(error.getField()).append("- ").append(error.getDefaultMessage()).append("\n");
                }
                throw new SensorNameNotUpdetedException(errorMsg.toString());
            }
            sensorService.updateSensorName(sensor.get(), sensorDTO.getName());
            return ResponseEntity.ok(HttpStatus.OK);
        }
        throw new SensorNotFoundException("There no such sensor with id " + id);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException ex) {
        SensorErrorResponse response = new SensorErrorResponse(ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException ex) {
        SensorErrorResponse response = new SensorErrorResponse(ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException (SensorNameNotUpdetedException ex) {
        SensorErrorResponse response = new SensorErrorResponse(ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

}
