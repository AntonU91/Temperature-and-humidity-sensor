package anton.uzhva.com.TemperatureHumiditySensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan ("anton/uzhva/com/TemperatureHumiditySensor")
public class TemperatureHumiditySensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperatureHumiditySensorApplication.class, args);
	}

}
