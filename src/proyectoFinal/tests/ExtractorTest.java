package proyectoFinal.tests;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import proyectoFinal.vuelos.Aeropuerto;
import proyectoFinal.vuelos.Extractor;
import proyectoFinal.vuelos.FicheroUrl;

public class ExtractorTest {

	public Extractor extractor = new Extractor();
	
	@Test
	public void test() throws IOException {
		FicheroUrl paginaAviones = new FicheroUrl(
				"https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
		ArrayList<Aeropuerto> aeropuertos = extractor.separateAirport(paginaAviones);
		String answer = aeropuertos.get(0).getName();
		Assert.assertEquals("Madang Airport", answer);
	}
	
	@Test
	public void sizeTest() throws IOException {
		FicheroUrl paginaAviones = new FicheroUrl(
				"https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
		ArrayList<Aeropuerto> aeropuertos = extractor.separateAirport(paginaAviones);
		int answer = aeropuertos.size();
		System.out.println(answer);
		Assert.assertTrue(12057>=answer);
	}
	
	@Test(expected = IOException.class)
	public void testIndexOutOfBoundsException() throws IOException {
		FicheroUrl paginaAviones = new FicheroUrl(
				"https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.da");
		extractor.separateAirport(paginaAviones);
	}

}
