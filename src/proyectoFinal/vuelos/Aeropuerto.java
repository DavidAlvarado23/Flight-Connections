package proyectoFinal.vuelos;

/**
*         				   Clase Aeropuerto          				*
* Almacena las caracteristicas más importantes de un aeropuerto.    *
* Contiene sus getters para luego poder solicitar la información.  	*
**/

public class Aeropuerto {

	private int id;
	private String name;
	private String city;
	private String country; 
	private String iata;
	private String icao;
	private double posX;
	private double posY;
	private int posZ;

	public Aeropuerto(int id, String name, String city, String country, String iata, String icao, double posY, double posX, int posZ) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public int getPosZ() {
		return posZ;
	}

	public String getIata() {
		return iata;
	}

	public String getIcao() {
		return icao;
	}
	
	public String toString() {
		return name;
	}	
}