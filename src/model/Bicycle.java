package model;

/**
* <b>Description:</b> The class Bicycle in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Bicycle extends Vehicle implements TwoWheels {
	
	//Attributes
	private String color;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new Instance of Bicycle.<br>
	 * @param name The bicycle name.
	 * @param color The bicycle color.
	 */
	
	public Bicycle(String name, String color) {
		super(name);
		this.color=color;
	}
	
}
