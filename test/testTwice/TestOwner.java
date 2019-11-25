package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import model.Owner;
import model.Parking;

class TestOwner {
	
	private Owner owner;
	
	private void setUpScenario1() throws InvalidEmailException, InvalidPasswordException {
		
		owner = new Owner("","","P2349kef230");
		
		ArrayList<Parking> parkings = new ArrayList<Parking>();
		
		parkings.add(new Parking("Im Na-yeon","","","","", "", "", null));
		parkings.add(new Parking("Hirai Momo","","","","", "", "", null));
		parkings.add(new Parking("Kim Da-hyun","","","","", "", "", null));
		parkings.add(new Parking("Minatozaki Sana","","","","", "", "", null));
		parkings.add(new Parking("Park Ji-hyo","","","","", "", "", null));
		parkings.add(new Parking("Son Chae-young","","","","", "", "", null));
		parkings.add(new Parking("Yoo Jeong-yeon","","","","", "", "", null));
		parkings.add(new Parking("Chou Tzu-yu","","","","", "", "", null));
		parkings.add(new Parking("Myoui Mina","","","","", "", "", null));
		parkings.add(new Parking("Minatozaki Sana","","","","", "", "", null));
		
		owner.setParkings(parkings);
	}
	
	public void setUpScenario2()throws InvalidEmailException, InvalidPasswordException {
		
		owner = new Owner("","","P2349kef230");
		
		ArrayList<Parking> parkings = new ArrayList<Parking>();
		
		parkings.add(new Parking("","","","","Chou Tzu-yu", "", "", null));
		parkings.add(new Parking("","","","","Son Chae-young", "", "", null));
		parkings.add(new Parking("","","","","Yoo Jeong-yeon", "", "", null));
		parkings.add(new Parking("","","","","Kim Da-hyun", "", "", null));
		parkings.add(new Parking("","","","","Im Na-yeon", "", "", null));
		parkings.add(new Parking("","","","","Hirai Momo", "", "", null));
		parkings.add(new Parking("","","","","Minatozaki Sana", "", "", null));
		parkings.add(new Parking("","","","","Park Ji-hyo", "", "", null));
		parkings.add(new Parking("","","","","Myoui Mina", "", "", null));
		parkings.add(new Parking("","","","","Yoo Jeong-yeon", "", "", null));
		
		owner.setParkings(parkings);
	}
	
	public void setUpScenario3() throws InvalidEmailException, InvalidPasswordException{
		
		owner = new Owner("","","P2349kef230");
		
		ArrayList<Parking> parkings = new ArrayList<Parking>();
		
		parkings.add(new Parking("","","","","", "", "", new double[] {1500, 800, 400}));
		parkings.add(new Parking("","","","","", "", "", new double[] {3400, 2000, 1800}));
		parkings.add(new Parking("","","","","", "", "", new double[] {400, 6000, 200}));
		parkings.add(new Parking("","","","","", "", "", new double[] {1430, 200, 900}));
		parkings.add(new Parking("","","","","", "", "", new double[] {1500, 800, 400}));
		parkings.add(new Parking("","","","","", "", "", new double[] {4000, 5000, 6000}));
		parkings.add(new Parking("","","","","", "", "", new double[] {143, 83, 2}));
		
		owner.setParkings(parkings);
	}
	
	private void setUpScenario4() throws InvalidEmailException, InvalidPasswordException{
		
		owner = new Owner("","","P2349kef230");
		
		ArrayList<Parking> parkings = new ArrayList<Parking>();
		
		parkings.add(new Parking("Nayeon","","","","", "", "", null));
		parkings.add(new Parking("Momo","","","","", "", "", null));
		parkings.add(new Parking("Da-hyun","","","","", "", "", null));
		parkings.add(new Parking("Minatozaki","","","","", "", "", null));
		parkings.add(new Parking("Jihyo","","","","", "", "", null));
		parkings.add(new Parking("Chae-young","","","","", "", "", null));
		parkings.add(new Parking("Jeongyeon","","","","", "", "", null));
		parkings.add(new Parking("Tzuyu","","","","", "", "", null));
		parkings.add(new Parking("Mina","","","","", "", "", null));
		parkings.add(new Parking("Minatozaki Sana","","","","", "", "", null));
		
		owner.setParkings(parkings);
	}

	@Test
	void testSortParkingsByName() throws InvalidEmailException, InvalidPasswordException{
		
		setUpScenario1();
		
		owner.sortParkingsByName();
		
		ArrayList<Parking> parkings = owner.getParkings();
		
		assertEquals("Chou Tzu-yu", parkings.get(0).getName());
		assertEquals("Hirai Momo", parkings.get(1).getName());
		assertEquals("Im Na-yeon", parkings.get(2).getName());
		assertEquals("Kim Da-hyun", parkings.get(3).getName());
		assertEquals("Minatozaki Sana", parkings.get(4).getName());
		assertEquals("Minatozaki Sana", parkings.get(5).getName());
		assertEquals("Myoui Mina", parkings.get(6).getName());
		assertEquals("Park Ji-hyo", parkings.get(7).getName());
		assertEquals("Son Chae-young", parkings.get(8).getName());
		assertEquals("Yoo Jeong-yeon", parkings.get(9).getName());
	}
	
	@Test
	void testSortParkingsByCountry() throws InvalidEmailException, InvalidPasswordException{
		
		setUpScenario2();
		
		owner.sortParkingsByCountry();
		
		ArrayList<Parking> parkings = owner.getParkings();
		
		assertEquals("Chou Tzu-yu", parkings.get(0).getCountry());
		assertEquals("Hirai Momo", parkings.get(1).getCountry());
		assertEquals("Im Na-yeon", parkings.get(2).getCountry());
		assertEquals("Kim Da-hyun", parkings.get(3).getCountry());
		assertEquals("Minatozaki Sana", parkings.get(4).getCountry());
		assertEquals("Myoui Mina", parkings.get(5).getCountry());
		assertEquals("Park Ji-hyo", parkings.get(6).getCountry());
		assertEquals("Son Chae-young", parkings.get(7).getCountry());
		assertEquals("Yoo Jeong-yeon", parkings.get(8).getCountry());
		assertEquals("Yoo Jeong-yeon", parkings.get(9).getCountry());
	}
	
	@Test
	void testSortParkingsByPrice() throws InvalidEmailException, InvalidPasswordException{
		
		setUpScenario3();
		
		owner.sortParkingsByPrice();
		
		ArrayList<Parking> parkings = owner.getParkings();
		
		assertEquals(76, parkings.get(0).calculateAverage());
		assertEquals(843.3333333333334, parkings.get(1).calculateAverage());
		assertEquals(900, parkings.get(2).calculateAverage());
		assertEquals(900, parkings.get(3).calculateAverage());
		assertEquals(2200, parkings.get(4).calculateAverage());
		assertEquals(2400, parkings.get(5).calculateAverage());
		assertEquals(5000, parkings.get(6).calculateAverage());
	}
	
	@Test
	void testSearchParking() throws InvalidEmailException, InvalidPasswordException{
		
		setUpScenario4();
		
		ArrayList<Parking> parkings = owner.searchParkings("Mina");
		
		assertEquals("Mina", parkings.get(0).getName());
		assertEquals("Minatozaki", parkings.get(1).getName());
		assertEquals("Minatozaki Sana", parkings.get(2).getName());
		assertEquals(3, parkings.size());
		
		parkings = owner.searchParkings("M");
		
		assertEquals("Mina", parkings.get(0).getName());
		assertEquals("Minatozaki", parkings.get(1).getName());
		assertEquals("Minatozaki Sana", parkings.get(2).getName());
		assertEquals("Momo", parkings.get(3).getName());
		assertEquals(4, parkings.size());
		
		parkings = owner.searchParkings("J");
		
		assertEquals("Jeongyeon", parkings.get(0).getName());
		assertEquals("Jihyo", parkings.get(1).getName());
		assertEquals(2, parkings.size());
		
		parkings = owner.searchParkings("csdwdef");
		
		assertEquals(0, parkings.size());
		
		parkings = owner.searchParkings("mINa");
		
		assertEquals("Mina", parkings.get(0).getName());
		assertEquals("Minatozaki", parkings.get(1).getName());
		assertEquals("Minatozaki Sana", parkings.get(2).getName());
		assertEquals(3, parkings.size());
		
		parkings = owner.searchParkings("j");
		
		assertEquals("Jeongyeon", parkings.get(0).getName());
		assertEquals("Jihyo", parkings.get(1).getName());
		assertEquals(2, parkings.size());
		
	}

}
