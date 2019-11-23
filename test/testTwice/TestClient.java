package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Client;
import model.Car.CarType;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle.MotorcycleType;
import model.Vehicle;

class TestClient {

	//Tested Class
	private Client client;
	
	//Scenes
	private void setUpSceneEmptyClient() {
		client=new Client("Esteban", "acosta57esteban@gmail.com", "Minecraft3");
	}
	
	private void setUpSceneNormalClient() {
		client=new Client("Esteban", "acosta57esteban@gmail.com", "Minecraft3");
		client.addBicycle("rOlo", "Verde");
		client.addCar("Rojo", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1);
		client.addMotorcycle("Grizelda", "ABC12C", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 50);
	}
	
	//Tests
	@Test
	void testAddVehicle(){
		setUpSceneEmptyClient();
		assertTrue(client.addVehicle("test/testFiles/Car.txt"));
		assertTrue(client.addVehicle("test/testFiles/Motorcycle.txt"));
		assertTrue(client.addVehicle("test/testFiles/Bicycle.txt"));
		assertFalse(client.addVehicle("test/testFiles/VehicleError.txt"));
		assertEquals(client.getVehicles().get(0).getName(),"Bmw");
		assertEquals(client.getVehicles().get(1).getName(),"Yamaha");
		assertEquals(client.getVehicles().get(2).getName(),"Bmx");
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
		setUpSceneNormalClient();
		ArrayList<Vehicle> vehicles=client.searchVehicles("Grizelda");
		assertEquals(vehicles.get(0).getName(), "Grizelda");
		vehicles=client.searchVehicles("Ro");
		assertEquals(vehicles.get(0).getName(), "Rojo");
		assertEquals(vehicles.get(1).getName(), "rOlo");
		vehicles=client.searchVehicles("Paro");
		assertEquals(vehicles.size(), 0);
	}
	
	@Test
	void testSearchVehicle() {
		setUpSceneNormalClient();
		assertNotNull(client.searchVehicle("rOlo"));
		assertNull(client.searchVehicle("Paro"));
	}
	
	@Test
	void testSearchMotorVehicles() {
		setUpSceneNormalClient();
		assertNotNull(client.searchMotorVehicle("ICT567"));
		assertNull(client.searchMotorVehicle("rOlo"));
	}
	
	@Test
	void testSortVehiclesByName() {
		setUpSceneNormalClient();
		client.sortVehiclesByName();
		assertEquals(client.getVehicles().get(0).getName(), "Grizelda");
		assertEquals(client.getVehicles().get(1).getName(), "Rojo");
		assertEquals(client.getVehicles().get(2).getName(), "rOlo");
		
	}
	
	@Test
	void testSortVehiclesByUses() {
		
	}
	
	@Test
	void testSortVehiclesByPlate() {
		setUpSceneNormalClient();
		client.sortVehiclesByName();
		assertEquals(client.getVehicles().get(0).getName(), "Grizelda");
		assertEquals(client.getVehicles().get(1).getName(), "Rojo");
		assertEquals(client.getVehicles().get(2).getName(), "rOlo");
	}
	
	@Test
	void testLoad() {
		setUpSceneEmptyClient();
		assertNotNull(client.load("test/testFiles/Car.txt"));
		assertNotNull(client.load("test/testFiles/Motorcycle.txt"));
		assertNotNull(client.load("test/testFiles/Bicycle.txt"));
		assertNull(client.load("test/testFiles/VehicleError.txt"));
	}
	
}
