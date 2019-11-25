package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistsException;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import model.Client;
import model.Owner;
import model.User;

class TestUser {

	//Tested Class
	private User user;
	
	//Scenes
	private void setUpSceneEmptyClient() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		user=new Client("Momo", "momo.dancemachine@gmail.com", "Mohirai6");
	}
	
	private void setUpSceneNormalClient() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		user=new Client("Momo", "momo.dancemachine@gmail.com", "Mohirai6");
		user.addUser(new Owner("Mina","minaballerina@gmail.com","Password1234"));
		user.addUser(new Owner("Tzuyu","tzuyoda@gmail.com","Princess21"));
	}
	
	//Tests
	@Test
	void testAddUser() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneEmptyClient();
		user.addUser(new Owner("Mina","minaballerina@gmail.com","Password1234"));
		user.addUser(new Owner("Tzuyu","tzuyoda@gmail.com","Princess21"));
		user.addUser(new Owner("Momo", "momo.dancemachine@gmail.com", "Mohirai6"));
		user.addUser(new Owner("Dahyun","dahyun.tofu@gmail.com","LolXd1903"));
		user.addUser(new Owner("Sana","nosananolife@gmail.com","S4n4B4n4n4"));
	}
	
	@Test
	void testSearchUser() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneNormalClient();
		assertEquals(user.searchUser("momo.dancemachine@gmail.com").getEmail(), "momo.dancemachine@gmail.com");
		assertEquals(user.searchUser("minaballerina@gmail.com").getEmail(), "minaballerina@gmail.com");
		assertEquals(user.searchUser("tzuyoda@gmail.com").getEmail(), "tzuyoda@gmail.com");
		assertNull(user.searchUser("godjihyo@gmail.com"));
	}
	
	@Test
	void testVerifyPassword() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneEmptyClient();
		assertTrue(user.verifyPassword("Password6"));
		assertFalse(user.verifyPassword("Pass6"));
		assertFalse(user.verifyPassword("PasswordPasswordPassword6"));
		assertFalse(user.verifyPassword("password6"));
		assertFalse(user.verifyPassword("PasswordSix"));
	}
	
	@Test
	void testVerifyEmail() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneEmptyClient();
		assertTrue(user.verifyEmail("nosananolife@gmail.com"));
		assertFalse(user.verifyEmail("nosananolifegmail.com"));
	}
	
	@Test
	void testEncrypt() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneEmptyClient();
		assertEquals(user.encrypt("Chae	"),"009101097104067");
	}
	
	@Test
	void testDecrypt() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneEmptyClient();
		assertEquals(user.decrypt("009101097104067"),"Chae	");
	}
	
}
