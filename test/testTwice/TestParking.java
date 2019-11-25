package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.InvalidPlateException;
import model.Bicycle;
import model.Car;
import model.MotorVehicle;
import model.Motorcycle;
import model.Parking;
import model.Vehicle;

class TestParking {
	
	private Parking parking;
	
	private void setUpScenario1() {
		
		parking = new Parking("Jihyo","","","","", "", "", null);
	}
	
	@Test
	void testAddSlot() throws InvalidPlateException {
		
		setUpScenario1();
		
		parking.addSlot(2, Bicycle.class);
		parking.addSlot(3, Motorcycle.class);
		assertEquals(5, parking.slotsSize());
		
		
		assertTrue(parking.getSlot(0).insertVehicle(new Bicycle(null, null), "", ""));
		assertFalse(parking.getSlot(0).insertVehicle(new Motorcycle(null, "SWD342", null, null, 0), "", ""));
		assertFalse(parking.getSlot(0).insertVehicle(new Car(null, "SWD342", null, null, 0), "", ""));
		assertTrue(parking.getSlot(0).getActualVehicle() instanceof Bicycle);
		
		assertTrue(parking.getSlot(3).getActualVehicle() == null);
		assertFalse(parking.getSlot(3).insertVehicle(new Car(null, "SFD922", null, null, 0), "", ""));
		assertTrue(parking.getSlot(3).getActualVehicle() == null);
		assertTrue(parking.getSlot(3).getType() == Motorcycle.class);
		assertFalse(parking.getSlot(3).insertVehicle(new Bicycle(null, null), "", ""));
		assertTrue(parking.getSlot(3).getActualVehicle() == null);
		assertTrue(parking.getSlot(3).insertVehicle(new Motorcycle(null, "FFE323", null, null, 0), "", ""));
		assertTrue(parking.getSlot(3).getActualVehicle().getClass() == Motorcycle.class);
		assertTrue(parking.getSlot(3).getActualVehicle() instanceof Motorcycle);
		assertFalse(parking.getSlot(3).insertVehicle(new Bicycle(null, null), "", ""));
		assertTrue(parking.getSlot(3).getActualVehicle() instanceof Motorcycle);
		
		parking.addSlot(3, MotorVehicle.class);
		assertEquals(8, parking.slotsSize());
		
		assertTrue(parking.getSlot(7).insertVehicle(new Car(null, "SFD922", null, null, 0), "", ""));
		assertTrue(parking.getSlot(7).getActualVehicle().getClass() == Car.class);	
		assertTrue(parking.getSlot(7).insertVehicle(new Motorcycle(null, "FFE323", null, null, 0), "", ""));
		assertTrue(parking.getSlot(7).getActualVehicle().getClass() == Motorcycle.class);
		assertFalse(parking.getSlot(7).insertVehicle(new Bicycle(null, null), "", ""));
		assertTrue(parking.getSlot(3).getActualVehicle() != null);
		assertTrue(parking.getSlot(7).getActualVehicle().getClass() == Motorcycle.class);
	}

}