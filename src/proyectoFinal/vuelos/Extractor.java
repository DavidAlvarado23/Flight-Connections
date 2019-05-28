package proyectoFinal.vuelos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *         				   Clase Extractor          				*
 * Tiene una expresión regular para buscar las características en	*
 * la url de los aeropuertos, ya que no funcionaba con separarlos	*
 * por coma solamente. 												*
 * Tiene un método por cada una de las urls que se leerán en las	*
 * que recibe un objeto de tipo FicheroUrl. En cada uno se llama al *
 * método leerPagina de FicheroUrl para separa cada linea, e 		*
 * instanciar un objeto (Aeropuerto, Aerolinea o Ruta) con sus 		*
 * atributos y agregar este objeto a una nueva ArrayList, que luego * 
 * devolverá con todos los objetos que pudo leer. 					*
**/

public class Extractor { 

	private final String expression = "(\\d*)," + "(\"(([^\\\"]*)|(.*[\\\\\"].*[\\\\\"].*))\")," + "(\"([^\"]*)\"),"
			+ "(\"([^\"]*)\")," + "((\"([^\"]*)\")|\\\\N)," + "((\"([^\"]*)\")|\\\\N)," + "(-?\\d*\\.?\\d*),"
			+ "(-?\\d*\\.?\\d*)," + "(-?\\d*\\.?\\d*)";

	public Extractor() {
	}

	public ArrayList<Aeropuerto> separateAirport(FicheroUrl Url) throws IOException {
		ArrayList<Aeropuerto> airports = new ArrayList<Aeropuerto>();
		for (String lineOfFile : Url.leerPagina("UTF-8")) {
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(lineOfFile);
			String id = "", name = "", city = "", country = "", iata = "", icao = "", x = "", y = "", z = "";
			while (matcher.find()) {
				id = matcher.group(1);
				name = matcher.group(4);
				city = matcher.group(7);
				country = matcher.group(9);
				iata = matcher.group(12);
				icao = matcher.group(15);
				x = matcher.group(16);
				y = matcher.group(17);
				z = matcher.group(18);
			}
			Aeropuerto airport = new Aeropuerto(Integer.parseInt(id), name, city, country, iata, icao,
					Double.parseDouble(x), Double.parseDouble(y), Integer.parseInt(z));
			airports.add(airport);
		}
		System.out.println("Airports completed.");
		return airports;
	}

	public ArrayList<Aerolinea> separateAerolinea(FicheroUrl paginaAerolinea) throws IOException {
		ArrayList<Aerolinea> airlines = new ArrayList<Aerolinea>();
		for (String lineOfFile : paginaAerolinea.leerPagina("ISO-8859-1")) {
			lineOfFile = lineOfFile.replace("\"", "");
			String[] values = lineOfFile.split(",");
			Aerolinea aerolinea;
			if (values[6].toUpperCase().equals("Y"))
				aerolinea = new Aerolinea(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4],
						values[5], Active.Y);
			else
				aerolinea = new Aerolinea(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4],
						values[5], Active.N);
			airlines.add(aerolinea);
		}
		System.out.println("Airlines completed.");
		return airlines;
	}

	public ArrayList<Ruta> separateRutas(FicheroUrl paginaRutas) throws IOException {
		ArrayList<Ruta> routes = new ArrayList<Ruta>();
		ArrayList<String> lines = paginaRutas.leerPagina("ISO-8859-1");
		for (String lineOfFile : lines) {
			if (lineOfFile != null) {
				lineOfFile = lineOfFile.replace("\\N", "0");
				String[] values = lineOfFile.split(",");
				Ruta ruta = new Ruta(values[0], Integer.parseInt(values[1]), values[2], Integer.parseInt(values[3]),
						values[4], Integer.parseInt(values[5]), Integer.parseInt(values[7]));
				routes.add(ruta);
			}
		}
		System.out.println("Routes completed.");
		return routes;
	}

}