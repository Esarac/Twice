package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Bicycle;
import model.Car;
import model.Car.CarType;
import model.MotorVehicle;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle;
import model.Motorcycle.MotorcycleType;

class TestMotorVehicle {

	//Tested Class
	private MotorVehicle motorVehicle;
	
	//Scenes
	private void setUpSceneEmptyCar() throws Exception {//[BAD]
		motorVehicle=new Car("Jihyo", "JHO907", VehicleFuel.GASOLINE, CarType.STANDARD, 2);
	}
	
	//Tests
	@Test
	void testValidatePlate() throws Exception {//[BAD]
		setUpSceneEmptyCar();
		assertTrue(motorVehicle.validatePlate("A1B2C3"));
		assertTrue(motorVehicle.validatePlate("A1B2C3D"));
		assertFalse(motorVehicle.validatePlate("!@#$%^"));
		assertFalse(motorVehicle.validatePlate("ASDF1234"));
	}
	
	@Test
	void testComparePlate() throws Exception {
		setUpSceneEmptyCar();
		assertTrue(motorVehicle.comparePlate(new Motorcycle("Dahyun", "DAU345", VehicleFuel.HYBRID, MotorcycleType.STANDARD, 200))>0);
		assertTrue(motorVehicle.comparePlate(new Motorcycle("Mina", "MIA820", VehicleFuel.ELECTRIC, MotorcycleType.QUAD, 250))<0);
		assertEquals(motorVehicle.comparePlate(new Car("Jihyo", "JHO907", VehicleFuel.GASOLINE, CarType.STANDARD, 2)), 0);
		assertEquals(motorVehicle.comparePlate(new Bicycle("Sana", "Purple")), -1);
	}
	
}
