package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistException;
import model.Owner;
import model.Parking;

class TestOwner {
	
	//Tested Class
	private Owner owner;
	
	//Scenes
	private void setUpSceneEmptyOwner() throws Exception {
		owner = new Owner("Sana","nosananolife@gmail.com","Password3");
	}

	private void setUpSceneNormalOwner() throws Exception {
		owner = new Owner("Sana","nosananolife@gmail.com","Password3");
		owner.addParking("mona","Calle 15 #121-25","Cali","Valle del Cauca","Colombia","minaballerina@gmail.com","NaN",new double[] {500,400,100});
		owner.addParking("Momo","Calle 15 #121-25","Cali","Cauca","Colombia","","NaN",new double[] {2000,1000,400});
		owner.addParking("Nayeon","Calle 15 #121-25","Cali","Antioquia","Colombia","bunny@gmail.com","NaN",new double[] {1000,500,200});
	}
	
	//Tests
	@Test
	void testAddParking() throws Exception{
		setUpSceneEmptyOwner();
		owner.addParking("Mina","Calle 15 #121-25","Cali","Valle del Cauca","Colombia","minaballerina@gmail.com","NaN",new double[] {1000,500,200});
		owner.addParking("Momo","Calle 15 #121-25","Cali","Valle del Cauca","Colombia","","NaN",new double[] {1000,500,200});
		
		assertThrows(AlreadyExistException.class,()->{
			owner.addParking("Mina","Calle 15 #121-25","Cali","Valle del Cauca","Colombia","minaballerina@gmail.com","NaN",new double[] {1000,500,200});
		});
		
		assertEquals(owner.getParkings().get(0).getName(), "Mina");
		assertEquals(owner.getParkings().get(1).getEmail(), "nosananolife@gmail.com");
	}
	
	@Test
	void testAddParkingPath() throws Exception{
		setUpSceneEmptyOwner();
		assertTrue(owner.addParking("test/testFiles/Parking.txt"));
		assertThrows(AlreadyExistException.class,()->{
			owner.addParking("test/testFiles/Parking.txt");
		});
		assertFalse(owner.addParking("test/testFiles/ParkingError.txt"));
		assertEquals(owner.getParkings().get(0).getName(), "Parqueadero Icesi");
	}
	
	@Test
	void testDeleteParking() throws Exception{
		setUpSceneNormalOwner();
		assertTrue(owner.deleteParking("Momo"));
		assertFalse(owner.deleteParking("Sana"));
		assertEquals(owner.getParkings().size(), 2);
	}
	
	@Test
	void testSearchParkings() throws Exception{
		setUpSceneNormalOwner();
		ArrayList<Parking> parkings=owner.searchParkings("Mo");
		assertEquals(parkings.get(0).getName(), "mona");
		assertEquals(parkings.get(1).getName(), "Momo");
	}
	
	@Test
	void testSearchRight() throws Exception{
		setUpSceneNormalOwner();
		ArrayList<Parking> parkings=new ArrayList<Parking>();
		owner.searchRight(0,"Mo",parkings);
		assertEquals(parkings.get(0).getName(), "Momo");
	}
	
	@Test
	void testSearchLeft() throws Exception{
		setUpSceneNormalOwner();
		ArrayList<Parking> parkings=new ArrayList<Parking>();
		owner.searchLeft(1,"Mo",parkings);
		assertEquals(parkings.get(0).getName(), "mona");
	}
	
	@Test
	void testCheckAlreadyExist() throws Exception{
		setUpSceneNormalOwner();
		assertTrue(owner.checkAlreadyExist("Momo"));
		assertFalse(owner.checkAlreadyExist("Sana"));
	}
	
	@Test
	void testSortParkingsByName() throws Exception{
		setUpSceneNormalOwner();
		owner.sortParkingsByName();
		assertEquals(owner.getParkings().get(0).getName(), "Momo");
		assertEquals(owner.getParkings().get(1).getName(), "mona");
		assertEquals(owner.getParkings().get(2).getName(), "Nayeon");
	}
	
	@Test
	void testSortParkingsByAddress() throws Exception{
		setUpSceneNormalOwner();
		owner.sortParkingsByAddress();
		assertEquals(owner.getParkings().get(0).getName(), "Nayeon");
		assertEquals(owner.getParkings().get(1).getName(), "Momo");
		assertEquals(owner.getParkings().get(2).getName(), "mona");
	}
	
	@Test
	void testSortParkingsByPrice() throws Exception{
		setUpSceneNormalOwner();
		owner.sortParkingsByPrice();
		assertEquals(owner.getParkings().get(0).getName(), "mona");
		assertEquals(owner.getParkings().get(1).getName(), "Nayeon");
		assertEquals(owner.getParkings().get(2).getName(), "Momo");
	}
	
	@Test
	void testLoad() throws Exception{
		setUpSceneEmptyOwner();
		assertNotNull(owner.load("test/testFiles/Parking.txt"));
		assertNull(owner.load("test/testFiles/ParkingError.txt"));
	}
	
}
