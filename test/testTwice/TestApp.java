package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.App;
import model.Owner;
import model.Parking;

class TestApp {

	//Tested Class
	private App app;
	
	//Scenes
	private void setUpSceneEmptyApp() {
		app=new App();
	}
	
	private void setUpSceneNormalApp() throws Exception{
		app=new App();
		app.addClient("Sana","nosananolife@gmail.com","Password6");
		app.addOwner("Nayeon","prettynayeon@gmail.com","Password6");
		app.addClient("Jihyo","godjihyo@gmail.com","Password6");
	}
	
	private void setUpSceneOwnerParkingsApp() throws Exception{
		app=new App();
		app.addOwner("Nayeon","prettynayeon@gmail.com","Password6");
		((Owner)app.getRootUser()).addParking("Jeongyeon","Calle 15 #121-25","Berlin","Santander","Alemania","girlpower@gmail.com","NaN",new double[] {1000, 800, 400});
		((Owner)app.getRootUser()).addParking("Momo","Cra. 12 #23-23","Roma","Quibdo","Italia","momo.dancemachine@gmail.com","NaN",new double[] {1500, 400, 200});
		app.addOwner("Sana","sananolife@gmail.com","Password6");
		((Owner)app.getRootUser().getRight()).addParking("Mina","Calle 15 #121-25","Paris","Antioquia","Francia","minaballerina@gmail.com","NaN",new double[] {1000, 1000, 1000});
		((Owner)app.getRootUser().getRight()).addParking("Chaeyoung","Cra. 12 #23-23","Brasilia","Narino","Brasil","sweetstrawberry@gmail.com","NaN",new double[] {1500, 1500, 1500});
		app.addOwner("Jihyo","godjihyo@gmail.com","Password6");
		((Owner)app.getRootUser().getLeft()).addParking("Tzuyu","Calle 15 #121-25","Cali","Valle del Cauca","Colombia","tzuyoda@gmail.com","",new double[] {500, 200, 100});
		((Owner)app.getRootUser().getLeft()).addParking("Dahyun","Cra. 12 #23-23","Lisboa","Cauca","Portugal","eagledance@gmail.com","NaN", new double[] {100, 200, 1000});
	}
	
	//Tests
	@Test
	void testAddClient() throws Exception{
		setUpSceneEmptyApp();
		app.addClient("Jeongyeon","girlpower@gmail.com","Password6");
		app.addClient("Chaeyoung","sweetstrawberry@gmail.com","Password6");
		assertEquals(app.getRootUser().getEmail(), "girlpower@gmail.com");
		assertEquals(app.getRootUser().getRight().getEmail(), "sweetstrawberry@gmail.com");
	}
	
	@Test
	void testAddOwner() throws Exception{
		setUpSceneEmptyApp();
		app.addOwner("Dahyun","eagledance@gmail.com","Password6");
		app.addOwner("Momo","momo.dancemachine@gmail.com","Password6");
		assertEquals(app.getRootUser().getEmail(), "eagledance@gmail.com");
		assertEquals(app.getRootUser().getRight().getEmail(), "momo.dancemachine@gmail.com");
	}
	
	@Test
	void testLogIn() throws Exception{
		setUpSceneNormalApp();
		assertNotNull(app.logIn("prettynayeon@gmail.com","Password6",false));
		assertNull(app.logIn("prettynayeon@gmail.com","Password3",false));
		assertNull(app.logIn("minaballerina@gmail.com","Password6",false));
	}

	@Test
	void testSearchUser() throws Exception{
		setUpSceneNormalApp();
		assertEquals(app.searchUser("nosananolife@gmail.com").getEmail(),"nosananolife@gmail.com");
		assertEquals(app.searchUser("prettynayeon@gmail.com").getEmail(),"prettynayeon@gmail.com");
		assertNull(app.searchUser("tzuyoda@gmail.com"));
	}
	
	@Test
	void testSortParkingsByName() throws Exception{
		setUpSceneOwnerParkingsApp();
		ArrayList<Parking> parkings=app.sortParkingsByName();
		assertEquals(parkings.get(0).getName(), "Chaeyoung");
		assertEquals(parkings.get(1).getName(), "Dahyun");
		assertEquals(parkings.get(2).getName(), "Jeongyeon");
		assertEquals(parkings.get(3).getName(), "Mina");
		assertEquals(parkings.get(4).getName(), "Momo");
		assertEquals(parkings.get(5).getName(), "Tzuyu");
	}
	
	@Test
	void testSortParkingsByAddress() throws Exception{
		setUpSceneOwnerParkingsApp();
		ArrayList<Parking> parkings=app.sortParkingsByAddress();
		assertEquals(parkings.get(0).getName(), "Jeongyeon");
		assertEquals(parkings.get(1).getName(), "Chaeyoung");
		assertEquals(parkings.get(2).getName(), "Tzuyu");
		assertEquals(parkings.get(3).getName(), "Mina");
		assertEquals(parkings.get(4).getName(), "Momo");
		assertEquals(parkings.get(5).getName(), "Dahyun");
	}
	
	@Test
	void testSortParkingsByPrice() throws Exception{
		setUpSceneOwnerParkingsApp();
		ArrayList<Parking> parkings=app.sortParkingsByPrice();
		assertEquals(parkings.get(0).getName(), "Tzuyu");
		assertEquals(parkings.get(1).getName(), "Dahyun");
		assertEquals(parkings.get(2).getName(), "Momo");
		assertEquals(parkings.get(3).getName(), "Jeongyeon");
		assertEquals(parkings.get(4).getName(), "Mina");
		assertEquals(parkings.get(5).getName(), "Chaeyoung");
	}
	
	@Test
	void testLoad() throws Exception{
		setUpSceneNormalApp();
		assertEquals(app.load("test/testFiles/ActualUser.txt").getEmail(), "prettynayeon@gmail.com");
	}
	
	@Test
	void testGetAllParkings() throws Exception{
		setUpSceneEmptyApp();
		assertEquals(app.getAllParkings().size(), 0);
		
		setUpSceneOwnerParkingsApp();
		assertEquals(app.getAllParkings().size(), 6);
	}
	
}
