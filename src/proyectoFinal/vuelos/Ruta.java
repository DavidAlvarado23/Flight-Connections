package proyectoFinal.vuelos;

/**
*         				  	 Clase Ruta          					*
* Almacena las caracteristicas más importantes de una ruta como su  *
* destino y su origen.			  									*
* Contiene sus getters para luego poder solicitar la información.  	*
**/

public class Ruta {

	private String airline;
	private int airlineId;
	private String sourceAirport;
	private int sourceAirportId;
	private String destAir;
	private int destAirId;
	private int stops;

	public Ruta(String airline, int airlineId, String sourceAirport, int sourceairportId, String destAir,
			int destAirId, int stops) {
		this.airline = airline;
		this.airlineId = airlineId;
		this.sourceAirport = sourceAirport;
		this.sourceAirportId = sourceairportId;
		this.destAir = destAir;
		this.destAirId = destAirId;
		this.stops = stops;
	}

	public String getAirline() {
		return airline;
	}

	public int getAirlineId() {
		return airlineId;
	}

	public String getSourceAirport() {
		return sourceAirport;
	}

	public int getSourceAirportId() {
		return sourceAirportId;
	}

	public String getDestAir() {
		return destAir;
	}

	public int getDestAirId() {
		return destAirId;
	}

	public int getStops() {
		return stops;
	}
}