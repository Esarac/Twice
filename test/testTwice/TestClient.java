package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistException;
import exception.InvalidPlateException;
import model.Client;
import model.Car.CarType;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle.MotorcycleType;
import model.Vehicle;

class TestClient {

	//Tested Class
	private Client client;
	
	//Scenes
	private void setUpSceneEmptyClient() throws Exception{
		client=new Client("Nayeon", "acosta57esteban@gmail.com", "Minecraft3");
	}
	
	private void setUpSceneNormalClient() throws Exception{
		client=new Client("Nayeon", "acosta57esteban@gmail.com", "Minecraft3");
		client.addMotorcycle("Tzuyu", "ABC12C", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 50);
		client.addBicycle("Jihyo", "Verde");
		client.addBicycle("jihYWP", "Roja");
		client.getVehicles().get(2).addBill(new GregorianCalendar(),"Sana", "Calle 15 #121-25, Japon");
		client.addCar("Mina", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1);
	}
	
	//Tests
	@Test
	void testAddVehicle() throws Exception{
		setUpSceneEmptyClient();
		assertTrue(client.addVehicle("test/testFiles/Car.txt"));
		assertTrue(client.addVehicle("test/testFiles/Motorcycle.txt"));
		assertTrue(client.addVehicle("test/testFiles/Bicycle.txt"));
		assertThrows(AlreadyExistException.class,()->{
			client.addVehicle("test/testFiles/Bicycle.txt");
		});
		assertFalse(client.addVehicle("test/testFiles/VehicleError.txt"));
		assertEquals(client.getVehicles().get(0).getName(),"Bmw");
		assertEquals(client.getVehicles().get(1).getName(),"Bmx");
		assertEquals(client.getVehicles().get(2).getName(),"Yamaha");
	}
	
	@Test
	void testAddCar() throws Exception{
		setUpSceneEmptyClient();
		client.addCar("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1);
		assertThrows(AlreadyExistException.class, ()->{
			client.addCar("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, CarType.STANDARD, 1);
		});
		assertThrows(InvalidPlateException.class, ()->{
			client.addCar("Chaeyoung", "holaNinos1223", VehicleFuel.GASOLINE, CarType.STANDARD, 1);
		});
		assertEquals(client.getVehicles().get(0).getName(),"Jeongyeon");
	}

	@Test
	void testAddMotorcycle() throws Exception {
		setUpSceneEmptyClient();
		client.addMotorcycle("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1);
		assertThrows(AlreadyExistException.class, ()->{
			client.addMotorcycle("Jeongyeon", "ICT567", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1);
		});
		assertThrows(InvalidPlateException.class, ()->{
			client.addMotorcycle("Chaeyoung", "holaNinos1223", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 1);
		});
		assertEquals(client.getVehicles().get(0).getName(),"Jeongyeon");
	}
	
	@Test
	void testAddBicycle() throws Exception{
		setUpSceneEmptyClient();
		client.addBicycle("Jeongyeon", "Rojo");
		assertThrows(AlreadyExistException.class, ()->{
			client.addBicycle("Jeongyeon", "Rojo");
		});
		assertEquals(client.getVehicles().get(0).getName(),"Jeongyeon");
	}
	
	@Test
	void testDeleteVehicle() throws Exception{
		setUpSceneNormalClient();
		assertTrue(client.deleteVehicle("mina"));
		assertFalse(client.deleteVehicle("dahyun"));
		assertFalse(client.deleteVehicle("JihYWP"));
		assertEquals(client.getVehicles().size(), 3);
	}
	
	@Test
	void testSearchVehicles() throws Exception{
		setUpSceneNormalClient();
		ArrayList<Vehicle> vehicles=client.searchVehicles("jih");
		assertEquals(vehicles.get(0).getName(), "jihYWP");
		assertEquals(vehicles.get(1).getName(), "Jihyo");
	}
	
	@Test
	void testSearchVehicle() throws Exception{
		setUpSceneNormalClient();
		assertNotNull(client.searchVehicle("Jihyo"));
		assertNull(client.searchVehicle("Sana"));
	}
	
	@Test
	void testSearchMotorVehicles() throws Exception{
		setUpSceneNormalClient();
		assertNotNull(client.searchMotorVehicle("ICT567"));
		assertNull(client.searchMotorVehicle("Jihyo"));
	}
	
	@Test
	void testSortVehiclesByName() throws Exception{
		setUpSceneNormalClient();
		client.sortVehiclesByName();
		assertEquals(client.getVehicles().get(0).getName(), "Jihyo");
		assertEquals(client.getVehicles().get(1).getName(), "jihYWP");
		assertEquals(client.getVehicles().get(2).getName(), "Mina");
		assertEquals(client.getVehicles().get(3).getName(), "Tzuyu");
	}
	
	@Test
	void testSortVehiclesByUses() throws Exception{
		setUpSceneNormalClient();
		client.sortVehiclesByUses();
		assertEquals(client.getVehicles().get(0).getName(), "jihYWP");
		assertEquals(client.getVehicles().get(1).getName(), "Jihyo");
		assertEquals(client.getVehicles().get(2).getName(), "Tzuyu");
		assertEquals(client.getVehicles().get(3).getName(), "Mina");
	}
	
	@Test
	void testSortVehiclesByPlate() throws Exception{
		setUpSceneNormalClient();
		client.sortVehiclesByPlate();
		assertEquals(client.getVehicles().get(0).getName(), "jihYWP");
		assertEquals(client.getVehicles().get(1).getName(), "Jihyo");
		assertEquals(client.getVehicles().get(2).getName(), "Mina");
		assertEquals(client.getVehicles().get(3).getName(), "Tzuyu");
	}
	
	@Test
	void testLoad() throws Exception{
		setUpSceneEmptyClient();
		assertNotNull(client.load("test/testFiles/Car.txt"));
		assertNotNull(client.load("test/testFiles/Motorcycle.txt"));
		assertNotNull(client.load("test/testFiles/Bicycle.txt"));
		assertNull(client.load("test/testFiles/VehicleError.txt"));
	}
	
}
