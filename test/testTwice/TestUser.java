package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistException;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import model.Client;
import model.Owner;
import model.Parking;
import model.User;

class TestUser {

	//Tested Class
	private User user;
	
	//Scenes
	private void setUpSceneEmptyClient() throws Exception{
		user=new Client("Momo", "momo.dancemachine@gmail.com", "Mohirai6");
	}
	
	private void setUpSceneNormalClient() throws Exception{
		user=new Client("Momo", "momo.dancemachine@gmail.com", "Mohirai6");
		user.addUser(new Owner("Mina","minaballerina@gmail.com","Password1234"));
		((Owner)user.getLeft()).addParking("Twice","Calle 15 #121-25","Cali","Valle del Cauca","Colombia", "twicetagram@gmail.com", "Fancy you!", null);
		user.addUser(new Owner("Tzuyu","tzuyoda@gmail.com","Princess21"));
	}
	
	//Tests
	@Test
	void testAddUser() throws Exception{
		setUpSceneEmptyClient();
		user.addUser(new Owner("Mina","minaballerina@gmail.com","Password1234"));
		user.addUser(new Owner("Tzuyu","tzuyoda@gmail.com","Princess21"));
		assertThrows(AlreadyExistException.class, ()->{
			user.addUser(new Owner("Momo", "momo.dancemachine@gmail.com", "Mohirai6"));
		});
		assertThrows(InvalidEmailException.class, ()->{
			user.addUser(new Owner("Dahyun","dahyun.tofugmail.com","LolXd1903"));
		});
		assertThrows(InvalidPasswordException.class, ()->{
			user.addUser(new Owner("Sana","nosananolife@gmail.com","hello"));
		});
	}
	
	@Test
	void testSearchUser() throws Exception{
		setUpSceneNormalClient();
		assertEquals(user.searchUser("momo.dancemachine@gmail.com").getEmail(), "momo.dancemachine@gmail.com");
		assertEquals(user.searchUser("minaballerina@gmail.com").getEmail(), "minaballerina@gmail.com");
		assertEquals(user.searchUser("tzuyoda@gmail.com").getEmail(), "tzuyoda@gmail.com");
		assertNull(user.searchUser("godjihyo@gmail.com"));
	}
	
	@Test
	void testVerifyPassword() throws Exception{
		setUpSceneEmptyClient();
		assertTrue(user.verifyPassword("Password6"));
		assertFalse(user.verifyPassword("Pass6"));
		assertFalse(user.verifyPassword("PasswordPasswordPassword6"));
		assertFalse(user.verifyPassword("password6"));
		assertFalse(user.verifyPassword("PasswordSix"));
	}
	
	@Test
	void testVerifyEmail() throws Exception{
		setUpSceneEmptyClient();
		assertTrue(user.verifyEmail("nosananolife@gmail.com"));
		assertFalse(user.verifyEmail("nosananolifegmail.com"));
	}
	
	@Test
	void testEncrypt() throws Exception{
		setUpSceneEmptyClient();
		assertEquals(user.encrypt("Chae	"),"009101097104067");
	}
	
	@Test
	void testDecrypt() throws Exception{
		setUpSceneEmptyClient();
		assertEquals(user.decrypt("009101097104067"),"Chae	");
	}
	
	@Test
	void testCompareTo() throws Exception{
		setUpSceneEmptyClient();
		assertTrue(user.compareTo(new Client("Nayeon", "imgonnabeastar@gmail.com", "Password1"))>0);
		assertTrue(user.compareTo(new Client("Chaeyoung", "sweetstrawberry@gmail.com", "Password1"))<0);
		assertEquals(user.compareTo(new Client("Momo", "momo.dancemachine@gmail.com", "Mohirai6")),0);
	}
	
	@Test
	void testGetAllParkings() throws Exception{
		setUpSceneNormalClient();
		ArrayList<Parking> parkings=new ArrayList<Parking>();
		user.getAllParkings(parkings);
		
		assertEquals(parkings.size(), 1);
		assertEquals(parkings.get(0).getName(), "Twice");
	}
}
