package model;

import java.util.Calendar;

/**
* <b>Description:</b> The class Report in the package model.<br>
* @author VoodLyc & Esarac.
*/

public class Report extends Record implements Comparable<Report>{//[TEST]

	//Attributes
	private String clientEmail;
	private String vehiclePlate;
	//Suppliers
	private Report left;
	private Report next;
	private Report right;
	
	//Constructor
	
	/**
	 * <b>Description:</b> Creates a new instance of Report.<br>
	 * @param entryDate The entry date on the parking.
	 * @param clientName The name of the client that uses the parking.
	 * @param carPlate The car license plate.
	 */
	
	public Report(Calendar entryDate, String clientEmail, String vehiclePlate) {
		
		super(entryDate);
		this.clientEmail = clientEmail;
		this.vehiclePlate = vehiclePlate;
		
	}
	
	//Add
	
	/**
	 * <b>Description:</b> This method allows adding a report.<br>
	 * <b>Post:</b> The report was added.<br>
	 * @param report The report that will be added.
	 */
	
	public void addReport(Report report){
		if(report.compareTo(this)<0){
			if(left!=null){
				left.addReport(report);
			}
			else {
				left=report;
			}
		}
		else if(report.compareTo(this)>0) {
			if(right!=null) {
				right.addReport(report);
			}
			else {
				right=report;
			}
		}
		else {
			addSameEmailReport(report);
		}
	}
	
	/**
	 * <b>Description:</b> This method allows adding a report with the same email that other reports.<br>
	 * <b>Post:</b> The report was added.<br>
	 * @param report The report that will be added.
	 */
	
	public void addSameEmailReport(Report report){
		Report prev=null;
		Report actual=next;
		
		while(actual!=null){
			prev=actual;
			actual=prev.next;
		}
		
		if(prev!=null){
			prev.next=report;
		}
		else{
			this.next=report;
		}
	}
	
	//Search
	
	/**
	 * <b>Description:</b> This method allows searching the reports that match the email.<br>
	 * @param email The report email.
	 * @return The reports if could be found, null in otherwise.
	 */
	
	public Report searchReports(String email){
		Report report=null;
		
		if(email.compareTo(clientEmail)<0){
			if(left!=null){
				report=left.searchReports(email);
			}
		}
		else if(email.compareTo(clientEmail)>0){
			if(right!=null){
				report=right.searchReports(email);
			}
		}
		else {
			report=this;
		}
		
		return report;
	}
	
	//Compare
	
	/**
	 * <b>Description:</b> This method allows comparing a report with other.<br>
	 * @return The difference.
	 */
	
	public int compareTo(Report report){
		int delta=clientEmail.compareTo(report.clientEmail);
		return delta;
	}
	
	//Get
	
	/**
	 * <b>Description:</b> Gets the value of the attribute clientEmail.<br>
	 * @return The attribute clientEmail.
	 */
	
	public String getClientEmail() {
		return clientEmail;
	}
	
	/**
	 * <b>Description:</b> Gets the value of the attribute vehiclePlate.<br>
	 * @return The attribute vehiclePlate.
	 */
	
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	
	/**
	 * <b>Description:</b> Gets the value of left.<br>
	 * @return The left.
	 */
	
	public Report getLeft(){
		return left;
	}
	
	/**
	 * <b>Description:</b> Gets the value of next.<br>
	 * @return The next.
	 */
	
	public Report getNext() {
		return next;
	}
	
	/**
	 * <b>Description:</b> Gets the value of right.<br>
	 * @return The right.
	 */
	
	public Report getRight(){
		return right;
	}
	
}