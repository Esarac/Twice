package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Car;
import model.Slot;

class TestSlot {

	//Tested Class
	private Slot slot;
	
	//Scenes
	private void setUpSceneEmptySlot() {
		slot=new Slot(1, Car.class);
	}
	
	//Tests
	void testInsertVehicle(){
		
	}
	
}
