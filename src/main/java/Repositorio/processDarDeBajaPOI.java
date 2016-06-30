package Repositorio;

import java.time.LocalDateTime;
import java.util.Map;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.POI;

public interface processDarDeBajaPOI {
	public Map<Geolocalizacion, LocalDateTime> procesarPedido(String noProcesado);
	 
	public POI getPOI(Geolocalizacion geo);

	public void eliminarPOIs();
}