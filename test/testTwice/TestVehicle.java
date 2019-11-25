package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Bicycle;
import model.Bill;
import model.Vehicle;

class TestVehicle {
	
	//Tested Class
	private Vehicle vehicle;
	
	//Scenes
	private void setUpSceneEmptyBicycle() {
		vehicle=new Bicycle("ChaeYoung", "Morada");
	}
	
	private void setUpSceneNormalBicycle() {
		vehicle=new Bicycle("ChaeYoung", "Morada");
		vehicle.addBill(new GregorianCalendar(), "Parqueadero Nayeon", "Cl. 15 #121-25, Gangdong-gu, Seoul, Corea del Sur");
		vehicle.addBill(new GregorianCalendar(), "Parqueadero Sana", "Cra. 83 #26-26, Kyotanabe, Kyoto, Japon");
		vehicle.getFirstBill().pay();
		vehicle.addBill(new GregorianCalendar(), "Parqueadero Tzuyu", "Cl. 1 #23-24, Tainan, East District, Taiwan");
	}
	
	//Tests
	@Test
	void testAddBill(){
		setUpSceneEmptyBicycle();
		vehicle.addBill(new GregorianCalendar(), "Parqueadero Nayeon", "Cl. 15 #121-25, Gangdong-gu, Seoul, Corea del Sur");
		vehicle.addBill(new GregorianCalendar(), "Parqueadero Sana", "Cra. 83 #26-26, Kyotanabe, Kyoto, Japon");
		assertEquals(vehicle.getFirstBill().getParkingName(),"Parqueadero Sana");
		assertEquals(vehicle.getFirstBill().getNext().getParkingName(),"Parqueadero Nayeon");
	}
	
	@Test
	void testUnpaidBills() {
		setUpSceneNormalBicycle();
		ArrayList<Bill> bills=vehicle.unpaidBills();
		assertEquals(bills.get(0).getParkingName(), "Parqueadero Tzuyu");
		assertEquals(bills.get(1).getParkingName(), "Parqueadero Nayeon");
	}
	
	@Test
	void testBillsSize() {
		setUpSceneEmptyBicycle();
		assertEquals(vehicle.billsSize(), 0);
		setUpSceneNormalBicycle();
		assertEquals(vehicle.billsSize(), 3);
	}
	
	@Test
	void testCompareTo() {
		setUpSceneEmptyBicycle();
		assertTrue(vehicle.compareTo(new Bicycle("Ariza", "Naranja"))>0);
		assertTrue(vehicle.compareTo(new Bicycle("Dahyun", "Rosada"))<0);
		assertEquals(vehicle.compareTo(new Bicycle("ChaeYoung", "Morada")), 0);
	}
	
	@Test
	void testCompare() {
		setUpSceneNormalBicycle();
		
		Vehicle v1=new Bicycle("Mina", "Naranja");
		
		Vehicle v2=new Bicycle("Dahyun", "Rosada");
		v2.addBill(new GregorianCalendar(), "Parqueadero Nayeon", "Cl. 15 #121-25, Gangdong-gu, Seoul, Corea del Sur");
		
		Vehicle v3=new Bicycle("Jihyo", "Azul");
		v3.addBill(new GregorianCalendar(), "Parqueadero Nayeon", "Cl. 15 #121-25, Gangdong-gu, Seoul, Corea del Sur");
		
		Vehicle v4=new Bicycle("ChaeYoung", "Morada");
		v4.addBill(new GregorianCalendar(), "Parqueadero Nayeon", "Cl. 15 #121-25, Gangdong-gu, Seoul, Corea del Sur");
		v4.addBill(new GregorianCalendar(), "Parqueadero Sana", "Cra. 83 #26-26, Kyotanabe, Kyoto, Japon");
		
		assertTrue(vehicle.compare(v1, v2)<0);
		assertTrue(vehicle.compare(v4, v3)>0);
		assertEquals(vehicle.compare(v2, v3),0);
	}
	
}
