package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import model.Client;
import model.Car.CarType;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle.MotorcycleType;
import model.Vehicle;

class TestClient {

	//Tested Class
	private Client client;
	
	//Scenes
	private void setUpSceneEmptyClient() throws InvalidEmailException, InvalidPasswordException{
		client=new Client("Nayeon", "acosta57esteban@gmail.com", "Minecraft3");
	}
	
	private void setUpSceneNormalClient() throws InvalidEmailException, InvalidPasswordException{
		client=new Client("Nayeon", "acosta57esteban@gmail.com", "Minecraft3");
		client.addMotorcycle("Tzuyu", "ABC12C", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 50);
		client.addBicycle("Jihyo", "Verde");
		client.addBicycle("jihYWP", "Roja");
		client.getVehicles().get(2).addBill(new GregorianCalendar(),"Sana", "Calle 15 #121-25, Japon");
		client.addCar("Mina", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1);
	}
	
	//Tests
	@Test
	void testAddVehicle() throws InvalidEmailException, InvalidPasswordException{
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
	void testAddCar() throws InvalidEmailException, InvalidPasswordException {
		setUpSceneEmptyClient();
		assertTrue(client.addCar("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1));
		assertFalse(client.addCar("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1));
		assertFalse(client.addCar("Chaeyoung", "holaNinos1223", VehicleFuel.GASOLINE, CarType.STANDARD, 1));
		assertEquals(client.getVehicles().get(0).getName(),"Jeongyeon");
	}

	@Test
	void testAddMotorcycle() throws InvalidEmailException, InvalidPasswordException {
		setUpSceneEmptyClient();
		assertTrue(client.addMotorcycle("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1));
		assertFalse(client.addMotorcycle("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1));
		assertFalse(client.addMotorcycle("Chaeyoung", "holaNinos1223", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1));
		assertEquals(client.getVehicles().get(0).getName(),"Jeongyeon");
	}
	
	@Test
	void testAddBicycle() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneEmptyClient();
		assertTrue(client.addBicycle("Jeongyeon", "Rojo"));
		assertFalse(client.addBicycle("Jeongyeon", "Rojo"));
		assertEquals(client.getVehicles().get(0).getName(),"Jeongyeon");
	}
	
	@Test
	void testDeleteVehicle() throws InvalidEmailException, InvalidPasswordException {
		setUpSceneNormalClient();
		assertTrue(client.deleteVehicle("mina"));
		assertFalse(client.deleteVehicle("dahyun"));
		assertFalse(client.deleteVehicle("JihYWP"));
		assertEquals(client.getVehicles().size(), 3);
	}
	
	@Test
	void testSearchVehicles() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneNormalClient();
		ArrayList<Vehicle> vehicles=client.searchVehicles("jih");
		assertEquals(vehicles.get(0).getName(), "jihYWP");
		assertEquals(vehicles.get(1).getName(), "Jihyo");
	}
	
	@Test
	void testSearchVehicle() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneNormalClient();
		assertNotNull(client.searchVehicle("Jihyo"));
		assertNull(client.searchVehicle("Sana"));
	}
	
	@Test
	void testSearchMotorVehicles() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneNormalClient();
		assertNotNull(client.searchMotorVehicle("ICT567"));
		assertNull(client.searchMotorVehicle("Jihyo"));
	}
	
	@Test
	void testSortVehiclesByName() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneNormalClient();
		client.sortVehiclesByName();
		assertEquals(client.getVehicles().get(0).getName(), "Jihyo");
		assertEquals(client.getVehicles().get(1).getName(), "jihYWP");
		assertEquals(client.getVehicles().get(2).getName(), "Mina");
		assertEquals(client.getVehicles().get(3).getName(), "Tzuyu");
	}
	
	@Test
	void testSortVehiclesByUses() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneNormalClient();
		client.sortVehiclesByUses();
		assertEquals(client.getVehicles().get(0).getName(), "jihYWP");
		assertEquals(client.getVehicles().get(1).getName(), "Jihyo");
		assertEquals(client.getVehicles().get(2).getName(), "Tzuyu");
		assertEquals(client.getVehicles().get(3).getName(), "Mina");
	}
	
	@Test
	void testSortVehiclesByPlate() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneNormalClient();
		client.sortVehiclesByPlate();
		assertEquals(client.getVehicles().get(0).getName(), "jihYWP");
		assertEquals(client.getVehicles().get(1).getName(), "Jihyo");
		assertEquals(client.getVehicles().get(2).getName(), "Mina");
		assertEquals(client.getVehicles().get(3).getName(), "Tzuyu");
	}
	
	@Test
	void testLoad() throws InvalidEmailException, InvalidPasswordException{
		setUpSceneEmptyClient();
		assertNotNull(client.load("test/testFiles/Car.txt"));
		assertNotNull(client.load("test/testFiles/Motorcycle.txt"));
		assertNotNull(client.load("test/testFiles/Bicycle.txt"));
		assertNull(client.load("test/testFiles/VehicleError.txt"));
	}
	
}
