package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistsException;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import model.App;

class TestApp {

	//Tested Class
	private App app;
	
	//Scenes
	private void setUpSceneEmptyApp() {
		app=new App("Twice");
	}
	
	private void setUpSceneNormalApp()throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		app=new App("Twice");
		app.addClient("Sana","nosananolife@gmail.com","Password6");
		app.addClient("Nayeon","prettynayeon@gmail.com","Password6");
		app.addClient("Jihyo","godjihyo@gmail.com","Password6");
	}
	
	//Tests
	@Test
	void testAddClient()throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneEmptyApp();
		app.addClient("Jeongyeon","girlpower@gmail.com","Password6");
		app.addClient("Chaeyoung","sweetstrawberry@gmail.com","Password6");
		assertEquals(app.getRootUser().getEmail(), "girlpower@gmail.com");
		assertEquals(app.getRootUser().getRight().getEmail(), "sweetstrawberry@gmail.com");
	}
	
	@Test
	void testAddOwner()throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneEmptyApp();
		app.addOwner("Dahyun","eagledance@gmail.com","Password6");
		app.addOwner("Momo","momo.dancemachine@gmail.com","Password6");
		assertEquals(app.getRootUser().getEmail(), "eagledance@gmail.com");
		assertEquals(app.getRootUser().getRight().getEmail(), "momo.dancemachine@gmail.com");
	}
	
	@Test
	void testLogIn()throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneNormalApp();
		assertNotNull(app.logIn("prettynayeon@gmail.com","Password6",false));
		assertNull(app.logIn("prettynayeon@gmail.com","Password3",false));
		assertNull(app.logIn("minaballerina@gmail.com","Password6",false));
	}

	@Test
	void testSearchUser() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneNormalApp();
		assertEquals(app.searchUser("nosananolife@gmail.com").getEmail(),"nosananolife@gmail.com");
		assertEquals(app.searchUser("prettynayeon@gmail.com").getEmail(),"prettynayeon@gmail.com");
		assertNull(app.searchUser("tzuyoda@gmail.com"));
	}
	
	@Test
	void testLoad() throws InvalidEmailException, InvalidPasswordException, AlreadyExistsException{
		setUpSceneNormalApp();
		assertEquals(app.load("test/testFiles/ActualUser.txt").getEmail(), "prettynayeon@gmail.com");
	}
	
}
