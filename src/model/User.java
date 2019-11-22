package model;

/**
* <b>Description:</b> The abstract class User in the package model.<br>
* @author VoodLyc & Esarac.
*/

public abstract class User implements FileLoader, Encryptor{

	//Attributes
	private String name;
	private String email;
	private String password;
	//Suppliers
	private User left;
	private User right;
	
}
