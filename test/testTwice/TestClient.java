package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Client;
import model.Car.CarType;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle.MotorcycleType;

class TestClient {

	//Tested Class
	private Client client;
	
	//Scenes
	private void setUpSceneEmptyClient() {
		client=new Client("Esteban", "acosta57esteban@gmail.com", "Minecraft3");
	}
	
	@Test
	void testAddCar() {
		setUpSceneEmptyClient();
		assertTrue(client.addCar("Rojo", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1));
		assertFalse(client.addCar("Gris", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1));
		assertFalse(client.addCar("Rojo", "LMT123", VehicleFuel.GASOLINE, CarType.STANDARD, 1));
		assertFalse(client.addCar("Amado", "holaNinos1223", VehicleFuel.GASOLINE, CarType.STANDARD, 1));
	}

	@Test
	void testAddMotorcycle() {
		setUpSceneEmptyClient();
		assertTrue(client.addMotorcycle("Rojo", "ICT567", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1));
		assertFalse(client.addMotorcycle("Gris", "ICT567", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1));
		assertFalse(client.addMotorcycle("Rojo", "LMT123", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1));
		assertFalse(client.addMotorcycle("Amado", "holaNinos1223", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1));
	}
	
	@Test
	void testAddBicycle() {
		setUpSceneEmptyClient();
		assertTrue(client.addBicycle("Amado", "Rojo"));
		assertFalse(client.addBicycle("Amado", "Verde"));
	}
	
	@Test
	void testSearchVehicles() {
		
	}
	
	@Test
	void testSearchVehicle() {
		
	}
	
	@Test
	void testSearchMotorVehicles() {
		
	}
	
	@Test
	void testSortVehiclesByName() {
		
	}
	
	@Test
	void testSortVehiclesByUses() {
		
	}
	
	@Test
	void testSortVehiclesByPlate() {
		
	}
	
	@Test
	void testLoad() {
		
	}
	
}
