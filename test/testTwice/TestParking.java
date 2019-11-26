package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Bicycle;
import model.Car;
import model.Client;
import model.MotorVehicle;
import model.MotorVehicle.VehicleFuel;
import model.Motorcycle;
import model.Motorcycle.MotorcycleType;
import model.Parking;
import model.Vehicle;

class TestParking {
	
	private Parking parking;
	
	private void setUpSceneEmptyParking() {
		double[] pricePerHour= {1000, 500, 200};
		parking = new Parking("Twice","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "twicetagram@gmail.com", "Fancy you!", pricePerHour);
	}
	
	private void setUpSceneSlotNormalParking() {
		double[] pricePerHour= {2000, 500, 200};
		parking = new Parking("Twice","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "twicetagram@gmail.com", "Fancy you!", pricePerHour);
		parking.addSlot(1, Bicycle.class);
		parking.addSlot(1, Motorcycle.class);
		parking.addSlot(1, Car.class);
		parking.addSlot(1, MotorVehicle.class);
	}
	
	private void setUpSceneReportNormalParking() {
		double[] pricePerHour= {1000, 500, 200};
		parking = new Parking("Twice","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "twicetagram@gmail.com", "Fancy you!", pricePerHour);
		parking.addReport("nosananolife@gmail.com","SNA212");
		parking.addReport("nosananolife@gmail.com","MIN212");
	}
	
	@Test
	void testAddSlot(){
		setUpSceneEmptyParking();
		parking.addSlot(1, Bicycle.class);
		parking.addSlot(2, Motorcycle.class);
		parking.addSlot(1, Car.class);
		
		assertEquals(parking.getFirstSlot().getId(), 4);
		assertEquals(parking.getFirstSlot().getType(), Car.class);
		assertEquals(parking.getFirstSlot().getNext().getType(), Motorcycle.class);
		assertEquals(parking.getFirstSlot().getNext().getNext().getType(), Motorcycle.class);
		assertEquals(parking.getFirstSlot().getNext().getNext().getNext().getType(), Bicycle.class);
	}
	
	@Test
	void testAddReport() {
		setUpSceneEmptyParking();
		parking.addReport("nosananolife@gmail.com","SNA212");
		parking.addReport("eagledance@gmail.com","DHY187");
		parking.addReport("tzuyoda@gmail.com","TZY305");
		parking.addReport("nosananolife@gmail.com","SNA212");
		assertEquals(parking.getRootReport().getClientEmail(), "nosananolife@gmail.com");
		assertEquals(parking.getRootReport().getLeft().getClientEmail(), "eagledance@gmail.com","DHY187");
		assertEquals(parking.getRootReport().getRight().getClientEmail(), "tzuyoda@gmail.com");
		assertEquals(parking.getRootReport().getNext().getClientEmail(), "nosananolife@gmail.com");
	}

	@Test
	void testInsertVehicle() throws Exception{
		setUpSceneSlotNormalParking();
		Client client=new Client("Mateo","myxz@hotmail.com","Password3");
		Vehicle vehicle1=new Bicycle("Johan","Rojo");
		client.getVehicles().add(vehicle1);
		Vehicle vehicle2=new Motorcycle("Ariza","ARZ567", VehicleFuel.GASOLINE, MotorcycleType.STANDARD, 200);
		client.getVehicles().add(vehicle2);
		
		parking.insertVehicle(vehicle1, client, 1);
		parking.insertVehicle(vehicle2, client, 2);
		assertEquals(parking.getFirstSlot().getNext().getNext().getNext().getActualVehicle().getName(), "Johan");
		assertNull(parking.getFirstSlot().getNext().getActualVehicle());
		assertEquals(parking.getRootReport().getClientEmail(), "myxz@hotmail.com");
	}
	
	@Test
	void testRemoveVehicle() throws Exception{
		setUpSceneSlotNormalParking();
		Client client=new Client("Mateo","myxz@hotmail.com","Password3");
		Vehicle vehicle=new Bicycle("Johan","Rojo");
		client.getVehicles().add(vehicle);
		parking.insertVehicle(vehicle, client, 1);
		
		assertTrue(parking.removeVehicle(client));
		assertFalse(parking.removeVehicle(client));
		assertNotEquals(parking.getRootReport().getPrice(), 0);
		assertNotNull(parking.getRootReport().getDepartureDate());
	}
	
	@Test
	void testSearchReport() {
		setUpSceneEmptyParking();
		assertNull(parking.searchReport("eagledance@gmail.com"));
		
		setUpSceneReportNormalParking();
		assertEquals(parking.searchReport("nosananolife@gmail.com").getClientEmail(), "nosananolife@gmail.com");
	}
	
	@Test
	void testSearchLastReport(){
		setUpSceneEmptyParking();
		assertNull(parking.searchLastReport("eagledance@gmail.com"));
		
		setUpSceneReportNormalParking();
		assertEquals(parking.searchLastReport("nosananolife@gmail.com").getVehiclePlate(), "MIN212");
	}
	
	@Test
	void testSearchSlotOwnerVehicle() throws Exception{
		setUpSceneEmptyParking();
		Client client=new Client("Mateo","myxz@hotmail.com","Password3");
		
		assertNull(parking.searchSlotOwnerVehicle(client));
		
		setUpSceneSlotNormalParking();
		Vehicle vehicle=new Bicycle("Johan","Rojo");
		client.getVehicles().add(vehicle);
		
		assertNull(parking.searchSlotOwnerVehicle(client));
		
		parking.insertVehicle(vehicle, client, 1);
		
		assertEquals(parking.searchSlotOwnerVehicle(client).getId(), 1);
	}
	
	@Test
	void testIsEmpty() throws Exception{
		setUpSceneEmptyParking();
		assertTrue(parking.isEmpty());
		
		setUpSceneSlotNormalParking();
		assertTrue(parking.isEmpty());
		
		Client client=new Client("Mateo","myxz@hotmail.com","Password3");
		Vehicle vehicle=new Bicycle("Johan","Rojo");
		client.getVehicles().add(vehicle);
		parking.insertVehicle(vehicle, client, 1);
		
		assertFalse(parking.isEmpty());
	}
	
	@Test
	void testCalculateAverage(){
		setUpSceneEmptyParking();
		assertEquals(parking.calculateAverage(), 566.66, 0.01);
		
		setUpSceneSlotNormalParking();
		assertEquals(parking.calculateAverage(), 900);
	}
	
	@Test
	void testEmptySlotsQuantity() {
		setUpSceneSlotNormalParking();
		assertEquals(parking.emptySlotsQuantity(Car.class), 2);
		assertEquals(parking.emptySlotsQuantity(Motorcycle.class), 2);
		assertEquals(parking.emptySlotsQuantity(Bicycle.class), 1);
		assertEquals(parking.emptySlotsQuantity(MotorVehicle.class), 1);
	}
	
	@Test
	void testGenerateMapUrl() {
		setUpSceneEmptyParking();
		assertEquals(parking.generateMapUrl(), "https://www.google.com/maps/place/Calle+15+%23121-25,+Cali,+Valle+del+Cauca,+Colombia");
	}
	
	@Test
	void testCompareTo() {
		setUpSceneEmptyParking();
		double[] pricePerHour= {1000, 500, 200};
		assertTrue(parking.compareTo(new Parking("Dahyun","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour))>0);
		assertTrue(parking.compareTo(new Parking("Yao","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour))<0);
		assertEquals(parking.compareTo(new Parking("Twice","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour)),0);
	}
	
	@Test
	void testCompare() {
		setUpSceneEmptyParking();
		double[] pricePerHour= {1000, 500, 200};
		assertTrue(parking.compare(parking,new Parking("Dahyun","Calle 15 #121-25","Medellin","Antioquia","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour))>0);
		assertTrue(parking.compare(parking,new Parking("Momo","Calle 15 #121-25","Ginebra","Valle del Cauca","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour))<0);
		assertEquals(parking.compare(parking,new Parking("Twice","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour)),0);
	}
	
	@Test
	void testCompareByAverage() {
		setUpSceneEmptyParking();
		double[] pricePerHour1= {700, 400, 100};
		double[] pricePerHour2= {1500, 1000, 400};
		double[] pricePerHour3= {1000, 500, 200};
		assertTrue(parking.compareByAverage(new Parking("Dahyun","Calle 15 #121-25","Medellin","Antioquia","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour1))>0);
		assertTrue(parking.compareByAverage(new Parking("Momo","Calle 15 #121-25","Ginebra","Valle del Cauca","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour2))<0);
		assertEquals(parking.compareByAverage(new Parking("Twice","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "ywp@gmail.com", "Feel Special", pricePerHour3)),0);
	}
	
}