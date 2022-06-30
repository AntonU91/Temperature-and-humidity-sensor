package anton.uzhva.com.TemperatureHumiditySensor.util;

import java.util.Date;

public class MeasurementErrorResponse {
    private String message;
    private Date timestamp;

    public MeasurementErrorResponse(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
