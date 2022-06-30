package anton.uzhva.com.TemperatureHumiditySensor.controller;

import anton.uzhva.com.TemperatureHumiditySensor.dto.MeasurementDTO;
import anton.uzhva.com.TemperatureHumiditySensor.model.Measurement;
import anton.uzhva.com.TemperatureHumiditySensor.service.MeasurementService;
import anton.uzhva.com.TemperatureHumiditySensor.util.MeasurementErrorResponse;
import anton.uzhva.com.TemperatureHumiditySensor.util.MeasurementNotAddedException;
import anton.uzhva.com.TemperatureHumiditySensor.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(convertToMeasurement(measurementDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder msgError = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : errorList) {
                msgError.append(fieldError.getField()).append("- ").append(fieldError.getDefaultMessage()).append("\\n");
            }
            throw new MeasurementNotAddedException(msgError.toString());
        }
        measurementService.addMeasurement(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.getAllMeasurements().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }
    @GetMapping("/rainyDaysCount")
    public Integer getCountOfRainyDays () {
       return measurementService.countOfRainyDays();
    }

    @ExceptionHandler
    ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAddedException ex) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }


}
