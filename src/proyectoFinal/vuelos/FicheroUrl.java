package proyectoFinal.vuelos;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

/**
*         				   Clase FicheroUrl          				*
* Recibe como parametro una url.  									*
* El metodo leerPagina() recibe la codificación con la cual se      *
* leerá la url y devuelve cada linea en un ArrayList.           	*      
**/

public class FicheroUrl {

	private String url; 

	public FicheroUrl(String url) throws IOException {
		this.url = url;
	}

	public ArrayList<String> leerPagina(String encode) throws IOException {
		URL pagina = new URL(this.url);
		ArrayList<String> pageInLines = new ArrayList<String>();
		BufferedReader in;
			in = new BufferedReader(new InputStreamReader(pagina.openStream(), encode));
			while (in.readLine() != null)
				pageInLines.add(in.readLine());
			in.close();
		return pageInLines;
	}

}
