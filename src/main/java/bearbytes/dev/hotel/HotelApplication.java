package bearbytes.dev.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The HotelApplication class initializes and starts the Spring boot
 * application.
 */
@SpringBootApplication
public class HotelApplication {
	/**
	 * Starts the Spring Boot application for the hotel.
	 * 
	 * @param args Possible arguments that could be passed via the command line,
	 *             unused.
	 */
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

}
