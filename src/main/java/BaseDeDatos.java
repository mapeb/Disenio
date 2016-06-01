import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseDeDatos {
	List<POI> puntosDeIntereses = new ArrayList<POI>();
	List<Busqueda> busquedas = new ArrayList<Busqueda>();
	Map<LocalDate, Integer> reportePorFecha = new HashMap<LocalDate, Integer>();
	Map<String, Integer> reporteBusquedasTotales = new HashMap<String, Integer>();
	List<Terminal> terminales = new ArrayList<Terminal>();

	public void addBusqueda(Terminal terminal, String frase, double tiempo) {
		Busqueda busqueda = new Busqueda(terminal, frase, tiempo);
		busquedas.add(busqueda);
	}

	public int cantidadDeBusquedasPorFecha(LocalDate fecha) {
		return busquedas.stream().filter(unaBusqueda -> unaBusqueda.conFechaDe(fecha)).collect(Collectors.toList())
				.size();
	}

	public void addBusquedasPorFechaAlReporte(LocalDate fecha) {
		reportePorFecha.put(fecha, this.cantidadDeBusquedasPorFecha(fecha));
	}

	public Map<LocalDate, Integer> getReportePorFecha() {
		return reportePorFecha;
	}

	public void addReportesPorTerminal() {

		for (Terminal unaTerminal : terminales) {
			reporteBusquedasTotales.put(unaTerminal.getNombre(), unaTerminal.resultadosTotales());
		}

	}
	public void agregarNuevosPoi(POI nuevoPOI){
		puntosDeIntereses.add(nuevoPOI);
	}
	public void sacarPoi(POI POIaSacar){
	puntosDeIntereses.remove(puntosDeIntereses.stream().filter(unPoi -> sonIguales(unPoi,POIaSacar)).collect(Collectors.toList()).get(1));
		
		
	}
	public int cantidadDePOI(){
		return puntosDeIntereses.size();
	}
	private boolean sonIguales(POI point1, POI point2){
		return point1.point.getLatitud()==point2.point.getLatitud() &&point1.point.getLongitud()==point2.point.getLongitud();
		// dos point son iguales si estan exactamente en el mismo  punto.
	}

}
