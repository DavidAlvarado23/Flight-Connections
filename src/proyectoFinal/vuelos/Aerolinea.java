package proyectoFinal.vuelos;

/**
*         				   Clase Aerolinea          				*
* Almacena las caracteristicas más importantes de una aerolinea.    *
* Contiene sus getters para luego poder solicitar la información.  	*
**/

public class Aerolinea {

	private int id;
	private String name;
	private String alias; 
	private String iata;
	private String icao;
	private String country;
	private Active active;

	public Aerolinea(int id, String name, String alias, String iata, String icao, String country, Active active) {
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.iata = iata;
		this.icao = icao;
		this.country = country;
		this.active = active;
	} 

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getIata() {
		return iata;
	}

	public String getIcao() {
		return icao;
	}

	public String getCountry() {
		return country;
	}

	public Active getActive() {
		return active;
	}
}