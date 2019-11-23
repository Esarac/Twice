package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
* <b>Description:</b> The class App in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class App implements FileLoader<User>{
	
	//Constant
	public static final String APP_PATH="dat/Users.twc";
	public static final String ACTUAL_USER_PATH="dat/ActualUser.twc";
	
	//Attributes
	private String name;
	private User rootUser;
	
	//Constructor
	public App(String name){
		this.name=name;
		loadUsers();
	}
	
	//Add
	public boolean addClient(String name, String email, String password){
		boolean possible=true;
		Client user=new Client(name, email, password);
		if(rootUser!=null){
			possible=rootUser.addUser(user);
		}
		else {
			rootUser=user;
		}
		return possible;
	}
	
	public boolean addOwner(String name, String email, String password){
		boolean possible=true;
		Owner user=new Owner(name, email, password);
		if(rootUser!=null){
			possible=rootUser.addUser(user);
		}
		else {
			rootUser=user;
		}
		return possible;
	}
	
	//LogIn
	public User logIn(String email, String password, boolean keepLoged){
		User actualUser=searchUser(email);
		if(!password.equals(actualUser.decrypt())){
			actualUser=null;
		}
		if(keepLoged){
			saveActualUser(actualUser);
		}
		return actualUser;
	}
	
	public User automaticLogIn(){
		User actualUser=load(ACTUAL_USER_PATH);
		return actualUser;
	}
	
	//Search
	public User searchUser(String email){
		User user=null;
		if(rootUser!=null){
			user=rootUser.searchUser(email);
		}
		return user;
	}
	
	//Read
	public String read(String path) throws IOException{//[FILE]
		String text="";
		
		File file=new File(path);
		if(file.exists()){
			file.createNewFile();
			FileReader fileReader=new FileReader(file);
			BufferedReader reader=new BufferedReader(fileReader);
			String actualLine;
			while((actualLine=reader.readLine())!=null){
				text+=actualLine+"\n";
			}
			reader.close();
		}
		else{
			text=null;
		}
		
		return text;
	}

	//Load
	public boolean loadUsers() {//[FILE]
		boolean possible=true;
		try{
			FileInputStream file=new FileInputStream(APP_PATH);
			ObjectInputStream creator=new ObjectInputStream(file);
			this.rootUser=(User)creator.readObject();
			creator.close();
		}
		catch (IOException e) {saveUsers();} 
		catch (ClassNotFoundException e) {possible=false;}
		return possible;
	}
	
	public User load(String path) {//[FILE]
		User actualUser=null;
		try{
			String[] data=read(path).split("\n");
			logIn(data[0], data[1], false);
		}
		catch (IOException | IndexOutOfBoundsException e){}
		return actualUser;
	}
	
	//Save
	public boolean saveUsers() {//[FILE]
		boolean possible=true;
		try {
			File dir=new File("dat//");
			dir.mkdir();
			FileOutputStream file=new FileOutputStream(APP_PATH);
			ObjectOutputStream creator=new ObjectOutputStream(file);
			creator.writeObject(rootUser);
			creator.close();
		}
		catch (IOException e) {possible=false;}
		return possible;
	}
	
	public boolean saveActualUser(User actualUser){//[FILE]
		boolean possible=true;
		try {
			File dir=new File("dat//");
			dir.mkdir();
			File actual=new File(ACTUAL_USER_PATH);
			String text=actualUser.getEmail()+"\n"+actualUser.getPassword();
			PrintWriter writer=new PrintWriter(actual);
			writer.append(text);
			writer.close();
		}
		catch (IOException | NullPointerException e) {possible=false;}
		return possible;
	}
	
	//Get
	public User getRootUser() {
		return rootUser;
	}
	
}
