package proyectoFinal.tests;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import proyectoFinal.vuelos.FicheroUrl;

public class FicheroUrlTest {

	@Test
	public void test() throws IOException {
		FicheroUrl paginaAviones = new FicheroUrl(
				"https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
		ArrayList<String> airport = paginaAviones.leerPagina("UTF-8");
		String answer = airport.get(0);
		String real = "2,\"Madang Airport\",\"Madang\",\"Papua New Guinea\",\"MAG\",\"AYMD\",-5.20707988739"
				+ ",145.789001465,20,10,\"U\",\"Pacific/Port_Moresby\",\"airport\",\"OurAirports\"";
		Assert.assertEquals(real, answer);
	}

}