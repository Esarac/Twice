package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.InvalidPlateException;
import model.Bicycle;
import model.Motorcycle;
import model.Parking;

class TestParking {
	
	private Parking parking;
	
	private void setUpScenario1() throws InvalidPlateException {
		
		parking = new Parking("Jihyo","","","","", "", "", null);
		
		parking.<Bicycle>addSlot(5);
		parking.<Motorcycle>addSlot(5);
	}
	
	@Test
	void test() throws InvalidPlateException {
		
		setUpScenario1();
	}

}
