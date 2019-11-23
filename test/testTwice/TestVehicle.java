package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Bicycle;
import model.Vehicle;

class TestVehicle {
	
	//Tested Class
	private Vehicle vehicle;
	
	//Scenes
	private void setUpSceneEmptyBicycle() {
		vehicle=new Bicycle("Esteban", "Rojo");
	}
	
	private void setUpSceneNormalBicycle() {
		vehicle=new Bicycle("Esteban", "Rojo");
		vehicle.addBill(new GregorianCalendar(3,3,3), "Parqueadero Ariza", "Colombia, Valle del Cauca, Cali, Calle 15 #121-25");
		vehicle.addBill(new GregorianCalendar(1,1,1), "Parqueadero Johan", "Colombia, Valle del Cauca, Cali, Calle 3 #100-10");
	}
	
	//Tests
	@Test
	void testAddBill(){
		setUpSceneEmptyBicycle();
		vehicle.addBill(new GregorianCalendar(3,3,3), "Parqueadero Ariza", "Colombia, Valle del Cauca, Cali, Calle 15 #121-25");
		vehicle.addBill(new GregorianCalendar(1,1,1), "Parqueadero Johan", "Colombia, Valle del Cauca, Cali, Calle 3 #100-10");
		assertEquals(vehicle.getFirstBill().getParkingName(),"Parqueadero Johan");
		assertEquals(vehicle.getFirstBill().getNext().getParkingName(),"Parqueadero Ariza");
	}
	
	@Test
	void testBillsSize() {
		setUpSceneEmptyBicycle();
		assertEquals(vehicle.billsSize(), 0);
		setUpSceneNormalBicycle();
		assertEquals(vehicle.billsSize(), 2);
	}
	
}
