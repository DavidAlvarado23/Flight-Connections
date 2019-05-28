package proyectoFinal.tests;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import proyectoFinal.vuelos.Aerolinea;
import proyectoFinal.vuelos.Aeropuerto;
import proyectoFinal.vuelos.Extractor;
import proyectoFinal.vuelos.FicheroUrl;
import proyectoFinal.vuelos.Grafo;
import proyectoFinal.vuelos.Ruta;

public class GrafoTest {

	public Extractor extractor = new Extractor();
	public ArrayList<Aeropuerto> aeropuertos;
	public ArrayList<Aerolinea> aerolineas;
	public ArrayList<Ruta> rutas;
	public static Grafo grafo = null;
	
	@Test
	public void test() throws IOException {
		
		FicheroUrl paginaAviones = new FicheroUrl(
				"https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
		aeropuertos = extractor.separateAirport(paginaAviones);

		FicheroUrl paginaAerolinea = new FicheroUrl(
				"https://raw.githubusercontent.com/jpatokal/openflights/master/data/airlines.dat");
		aerolineas = extractor.separateAerolinea(paginaAerolinea);

		FicheroUrl paginaRutas = new FicheroUrl(
				"https://raw.githubusercontent.com/jpatokal/openflights/master/data/routes.dat");
		rutas = extractor.separateRutas(paginaRutas);
		
		grafo = new Grafo("Vuelos");
		grafo.airportsGraph(aeropuertos, aerolineas, rutas);
		System.out.println(Grafo.getShortestPath("1892","1892"));
		Assert.assertEquals("[1892]",Grafo.getShortestPath("1892","1892"));
	}
}
