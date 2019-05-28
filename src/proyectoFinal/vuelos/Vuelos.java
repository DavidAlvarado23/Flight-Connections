package proyectoFinal.vuelos;

import java.io.IOException;
import java.util.ArrayList;

/**
 *          	          Clase Vuelos		          			*
 * Crea un extractor y tres objetos de tipo FicheroUrl con su 	*
 * respectiva Url de donde extraerá los datos. Se crea un 		*
 * ArrayList que almacenará los objetos instanciados de cada	*
 * tipo (Aeropuerto, Aerolinea y Ruta). Se crea un objeto de	* 
 * tipo Grafo y se llama al método airportsGraph pasandole los	* 
 * tres Arraylist de los objetos creados. 						*
 **/

public class Vuelos {

	public static ArrayList<Aeropuerto> aeropuertos;
	public static ArrayList<Aerolinea> aerolineas;
	public static ArrayList<Ruta> rutas;
	public static Grafo grafo;

	public static void cargarData() throws IOException {
		Extractor extractor = new Extractor();

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
	} 
	
	public static void resetMap() throws IOException {
		grafo.airportsGraph(aeropuertos, aerolineas, rutas);
	}

	public static void loadMap() throws IOException {
		grafo.showGraph();
	}
	
	public static ArrayList<Aeropuerto> getAeropuertos() {
		return aeropuertos;
	}

	public static ArrayList<Aerolinea> getAerolineas() {
		return aerolineas;
	}

	public static ArrayList<Ruta> getRutas() {
		return rutas;
	}
}