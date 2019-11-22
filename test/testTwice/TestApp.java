package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.App;

class TestApp {

	//Tested Class
	private App app;
	
	//Scenes
	private void setUpSceneEmptyApp(){
		app=new App("Twice");
	}
	
	private void setUpSceneNormalApp(){
		app=new App("Twice");
		app.addClient("Johan","j.sebas036@gmail.com","Minecraft3");
		app.addClient("Esteban","acosta57esteban@gmail.com","Minecraft3");
		app.addClient("Mateo","mxyz1111@gmail.com","Minecraft3");
	}
	
	//Tests
	@Test
	void testAddClient(){
		setUpSceneEmptyApp();
		app.addClient("Esteban","acosta57esteban@gmail.com","Minecraft3");
		app.addClient("Johan","j.sebas036@gmail.com","Minecraft3");
		assertEquals(app.getRootUser().getEmail(), "acosta57esteban@gmail.com");
		assertEquals(app.getRootUser().getRight().getEmail(), "j.sebas036@gmail.com");
	}
	
	@Test
	void testAddOwner(){
		setUpSceneEmptyApp();
		app.addOwner("Esteban","acosta57esteban@gmail.com","Minecraft3");
		app.addOwner("Johan","j.sebas036@gmail.com","Minecraft3");
		assertEquals(app.getRootUser().getEmail(), "acosta57esteban@gmail.com");
		assertEquals(app.getRootUser().getRight().getEmail(), "j.sebas036@gmail.com");
	}
	
	@Test
	void testLogIn(){
		setUpSceneNormalApp();
		assertNotNull(app.logIn("acosta57esteban@gmail.com","Minecraft3",false));
		assertNotNull(app.logIn("j.sebas036@gmail.com","Minecraft3",false));
		assertNull(app.logIn("acosta57esteban@gmail.com","Esteban2",false));
	}

	@Test
	void testSearchUser() {
		setUpSceneNormalApp();
		assertNotNull(app.searchUser("acosta57esteban@gmail.com"));
		assertNotNull(app.searchUser("mxyz1111@gmail.com"));
		assertNull(app.searchUser("juan.ossa1@yahoo.com"));
	}
	
}
