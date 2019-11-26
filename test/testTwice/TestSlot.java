package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.FullSlotException;
import exception.SlotMismatchException;
import model.Bicycle;
import model.Car;
import model.Motorcycle;
import model.Motorcycle.MotorcycleType;
import model.Car.CarType;
import model.MotorVehicle;
import model.MotorVehicle.VehicleFuel;
import model.Slot;
import model.Vehicle;

class TestSlot {

	//Tested Class
	private Slot slot;
	
	//Scenes
	private void setUpSceneEmptySlot(){
		slot=new Slot(1, MotorVehicle.class);
	}
	
	private void setUpSceneFullSlotBicycle() throws Exception{
		slot=new Slot(2, Bicycle.class);
		slot.insertVehicle(new Bicycle("Momo", "Red"), "Twice", "Calle 15 #121-25, Cali, Valle del Cauca, Colombia");
		slot.setInitTime((long)(System.nanoTime()-3.6e+12));
	}
	
	private void setUpSceneFullSlotMotorcycle() throws Exception{
		slot=new Slot(3, Motorcycle.class);
		slot.insertVehicle(new Motorcycle("Chaeyoung", "CHY267", VehicleFuel.DIESEL, MotorcycleType.STANDARD, 200), "Twice", "Calle 15 #121-25, Cali, Valle del Cauca, Colombia");
		slot.setInitTime((long)(System.nanoTime()-7.2e+12));
	}
	
	private void setUpSceneFullSlotCar() throws Exception{
		slot=new Slot(4, Car.class);
		slot.insertVehicle(new Car("Jihyo", "JYO567", VehicleFuel.GAS, CarType.TRUCK, 2), "Twice", "Calle 15 #121-25, Cali, Valle del Cauca, Colombia");
		slot.setInitTime((long)(System.nanoTime()-1.8e+12));
	}
	
	//Tests
	@Test
	void testInsertVehicle() throws Exception{
		setUpSceneEmptySlot();
		slot.insertVehicle(new Car("Jihyo", "JYO567", VehicleFuel.GAS, CarType.TRUCK, 2), "Twice", "Calle 15 #121-25, Cali, Valle del Cauca, Colombia");
		assertThrows(FullSlotException.class, ()->{
			slot.insertVehicle(new Motorcycle("Mina", "MNA123", VehicleFuel.GAS, MotorcycleType.QUAD, 300), "Twice", "Calle 15 #121-25, Cali, Valle del Cauca, Colombia");
		});
		assertEquals(slot.getActualVehicle().getName(), "Jihyo");
		assertNotNull(slot.getActualVehicle().getFirstBill());
		assertNotEquals(slot.getInitTime(), 0);
		
		setUpSceneEmptySlot();
		assertThrows(SlotMismatchException.class, ()->{
			slot.insertVehicle(new Bicycle("Dahyun","Pink"), "Twice", "Calle 15 #121-25, Cali, Valle del Cauca, Colombia");
		});
	}
	
	@Test
	void testRemoveVehicle() throws Exception{
		setUpSceneFullSlotBicycle();
		double[] pricePerHour= {1000, 500, 200};
		Vehicle vehicle=slot.getActualVehicle();
		double price=slot.removeVehicle(pricePerHour);
		
		assertEquals(price, 200, 0.1);
		assertEquals(slot.removeVehicle(pricePerHour), 0);
		
		assertNull(slot.getActualVehicle());
		assertEquals(slot.getInitTime(), 0);
		assertEquals(vehicle.getFirstBill().getPrice(), price);
		assertNotNull(vehicle.getFirstBill().getDepartureDate());
	}
	
	@Test
	void testCalculatePrice() throws Exception{
		double[] pricePerHour= {1000, 500, 200};
		
		setUpSceneFullSlotCar();
		assertEquals(slot.calculatePrice(pricePerHour), 500, 0.1);
		
		setUpSceneFullSlotMotorcycle();
		assertEquals(slot.calculatePrice(pricePerHour), 1000, 0.1);
		
		setUpSceneFullSlotBicycle();
		assertEquals(slot.calculatePrice(pricePerHour), 200, 0.1);
	}
	
}
