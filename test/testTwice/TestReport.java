package testTwice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Report;

class TestReport {

	//Tested Class
	private Report report;
	
	//Scenes
	private void setUpSceneEmptyReport() {
		report=new Report(new GregorianCalendar(), "twicetagram@gmail.com", "TWC567");
	}
	
	//Scenes
	private void setUpSceneNormalReport() {
		report=new Report(new GregorianCalendar(), "twicetagram@gmail.com", "TWC567");
		report.addReport(new Report(new GregorianCalendar(),"yoojeong-yeon@gmail.com", "YJY123"));
		report.addReport(new Report(new GregorianCalendar(),"nosananolife@gmail.com", "SNA254"));
		report.addReport(new Report(new GregorianCalendar(),"twicetagram@gmail.com", "TWC567"));
	}
	
	//Tests
	@Test
	void testAddReport() {
		setUpSceneEmptyReport();
		report.addReport(new Report(new GregorianCalendar(),"yoojeong-yeon@gmail.com", "YJY123"));
		report.addReport(new Report(new GregorianCalendar(),"nosananolife@gmail.com", "SNA254"));
		report.addReport(new Report(new GregorianCalendar(),"twicetagram@gmail.com", "TWC567"));
		report.addReport(new Report(new GregorianCalendar(),"yoojeong-yeon@gmail.com", "SNA254"));
		report.addReport(new Report(new GregorianCalendar(),"nosananolife@gmail.com", "SNA254"));
		assertEquals(report.getLeft().getClientEmail(), "nosananolife@gmail.com");
		assertEquals(report.getLeft().getNext().getClientEmail(), "nosananolife@gmail.com");
		assertEquals(report.getRight().getClientEmail(), "yoojeong-yeon@gmail.com");
		assertEquals(report.getRight().getNext().getClientEmail(), "yoojeong-yeon@gmail.com");
		assertEquals(report.getNext().getClientEmail(), "twicetagram@gmail.com");
	}

	@Test
	void testAddSameEmailReport() {
		setUpSceneEmptyReport();
		report.addReport(new Report(new GregorianCalendar(),"twicetagram@gmail.com", "YJY123"));
		report.addReport(new Report(new GregorianCalendar(),"twicetagram@gmail.com", "SNA254"));
		assertEquals(report.getNext().getVehiclePlate(), "YJY123");
		assertEquals(report.getNext().getNext().getVehiclePlate(), "SNA254");
	}
	
	@Test
	void testSearchReports() {
		setUpSceneNormalReport();
		assertEquals(report.searchReports("twicetagram@gmail.com").getNext().getVehiclePlate(),"TWC567");
		assertEquals(report.searchReports("yoojeong-yeon@gmail.com").getVehiclePlate(),"YJY123");
		assertEquals(report.searchReports("nosananolife@gmail.com").getVehiclePlate(),"SNA254");
		assertNull(report.searchReports("momo.dancemachine@gmail.com"));
	}
	
	@Test
	void testCompareTo() {
		setUpSceneEmptyReport();
		assertTrue(report.compareTo(new Report(new GregorianCalendar(),"yoojeong-yeon@gmail.com", "YJY123"))<0);
		assertTrue(report.compareTo(new Report(new GregorianCalendar(),"nosananolife@gmail.com", "SNA254"))>0);
		assertEquals(report.compareTo(new Report(new GregorianCalendar(), "twicetagram@gmail.com", "TWC567")),0);
	}
	
}
