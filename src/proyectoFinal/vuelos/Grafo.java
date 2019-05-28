package proyectoFinal.vuelos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

/**
 *         				   Clase Grafo		          				*
 * Recibe un nombre que se le asignará al grafo que se creará. 		*
 * Utiliza un renderizador para poder ver las linea como curvas y	* 
 * se añaden atributos al grafo como mejor calidad y anti-aliasing. * 
 * El método createGraph() recibe las tres listas a trabajar, 		*
 * coloca los aeropuertos como nodos en su posición y les da un 	*
 * estilo a los nodos y a las aristas.								*
 * El método routesGraph() crea las aristas uniendo los aeropuertos *
 * recorriendo las tres listas, para cada ruta una aerolinea		*
 * encargada y dos aeropuertos. A los aeropuertos que tienen rutas	* 
 * asignadas cambia su nodo de color a rojo. 						*
 * El método getShortestPath() recibe un aeropuerto de origen y uno	*
 * de destino para buscarlo con el algoritmo de Dijkstra, la ruta 	* 
 * se devuelve en forma de String. 									*
 * El método showGraph() muestra el grafo en una posición dada. 	*
**/
 
public class Grafo{

	private static ArrayList<Aeropuerto> airports;
	private ArrayList<Aerolinea> airlines;
	private ArrayList<Ruta> routes;
	private static Graph graph = null;
	Viewer vista;

	public Grafo(String name) {
		graph = new MultiGraph(name);
	}

	public void airportsGraph(ArrayList<Aeropuerto> airports, ArrayList<Aerolinea> airlines, ArrayList<Ruta> routes) {
		Grafo.airports = airports;
		this.airlines = airlines;
		this.routes = routes;
		graph.clear();
		for (Aeropuerto airport : airports) {
			graph.addNode("" + airport.getId());
			Node node = graph.getNode("" + airport.getId());
			node.setAttribute("xyz", airport.getPosX(), airport.getPosY(), airport.getPosZ());
		}
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "node { size: 5px; fill-color: #777; text-mode: hidden; z-index: 0; }"
				+ "edge { size: 0.05px; shape: line; fill-color: #228B22; }");
		rutesGraph(Grafo.graph);
	}

	private Graph rutesGraph(Graph grafo) {
		String aerolinea = "";
		String nodoOrigen = "";
		String nodoDestino = "";
		for (Ruta route : this.routes) {
			for (Aerolinea airline : this.airlines) {
				if (airline.getId() == route.getAirlineId()) 
					aerolinea = "" + airline.getId();
			}
			int paradas = route.getStops();
			int aeropuertoOrigen = route.getSourceAirportId();
			int aeropuertoDestino = route.getDestAirId();
			for (Aeropuerto airport : Grafo.airports) {
				if (airport.getId() == aeropuertoOrigen) {
					nodoOrigen = "" + airport.getId();
					aerolinea += airport.getId();
					for (Aeropuerto airports : Grafo.airports) {
						if (airports.getId() == aeropuertoDestino) {
							nodoDestino = "" + airports.getId();
							aerolinea += airports.getId();
							grafo.getNode(nodoOrigen).setAttribute("ui.style", "fill-color: #D73329;");
							grafo.getNode(nodoDestino).setAttribute("ui.style", "fill-color: #D73329;");
							grafo.addEdge(aerolinea, nodoOrigen, nodoDestino).addAttribute("length", paradas);
						}
					}
				}
			}
		}
		return grafo;
	}

	public static String getShortestPath(String idOrigen, String idDestino) throws IOException {
		Vuelos.resetMap();
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(idOrigen));
		dijkstra.compute();
		
		while (graph.getEdgeCount() > 0)
			graph.removeEdge(0);

		for (Node node : graph)
			node.addAttribute("ui.style", "fill-color: #777; size:4px;");

		for (Node node : dijkstra.getPathNodes(graph.getNode(idDestino)))
			node.addAttribute("ui.style", "fill-color: red; size:10px;");

		List<Node> nodos = new ArrayList<Node>();
		for (Node node : dijkstra.getPathNodes(graph.getNode(idDestino)))
			nodos.add(0, node);

		for (int i = 0; i < nodos.size() - 1; i++) {
			graph.addEdge("rutaOptima" + i, nodos.get(i), nodos.get(i + 1)).addAttribute("ui.style", "size:5px;");
		}

		String rutaOptima = (dijkstra.getPath(graph.getNode(idDestino))).toString();

		dijkstra.clear();

		return rutaOptima;
	}

	public void showGraph() {	
		vista = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		
		Viewer viewer = graph.display(false);
		View view = viewer.getDefaultView();
		viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
		((ViewPanel) view).resizeFrame(1000,800);
		view.getCamera().setViewCenter(0, 12, 0);

		graph.addAttribute("ui.stylesheet",
				"node {" + "	size: 5px;" + "	fill-color: #777;" + "	text-mode: hidden;" + "	z-index: 0;" + "}"
						+ "edge {" + "   size: 0.05px; " + "	shape: line;" + "	fill-color: #228B22;" + "}");
	}

	public ArrayList<Aeropuerto> getAirports() {
		return Grafo.airports;
	}
}