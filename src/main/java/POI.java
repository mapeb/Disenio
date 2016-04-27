
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class POI {

	protected Geolocalizacion point;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();
	protected HorarioYDia horario = new HorarioYDia();
	protected List<Feriado> feriados;

	public POI(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, HorarioYDia horario,
			List<Feriado> feriados) {
		super();
		this.point = point;
		this.nombre = nombre;
		this.palabrasClave = palabrasClave;
		this.addPalabraClave(nombre);
		this.horario = horario;
		this.feriados = feriados;
	}

	public double distanciaConUnPOI(POI unPoi) {
		return this.point.distanciaCon(unPoi.point);
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return distanciaMenor(this.point.distanciaCon(point), 500);
	}
	
	public boolean distanciaMenor(double distancia, double rango){
		return distancia < rango;
	}

	public boolean tenesUnaPalabraDe(String unaFrase) {
		String[] listaDePalabrasDeFrase = unaFrase.split(" ");
		return palabrasClave.stream().anyMatch(palabra -> Arrays.asList(listaDePalabrasDeFrase).contains(palabra));

	}

	public void addPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
	}

	public void addFeriado(Feriado unFeriado) {
		this.feriados.add(unFeriado);
	}

	public boolean compararmeConFeriadosDiaYMes(LocalDate fecha) {
		return (feriados.stream().anyMatch(unFeriado -> unFeriado.comparateConDiaYMes(fecha)));
	}

	public boolean compararmeConFeriadosHorario(LocalDateTime horario) {
		return (feriados.stream().anyMatch(unFeriado -> unFeriado.incluisHorario(horario.toLocalTime())));
	}

	public boolean estasEnFeriado(LocalDateTime fecha) {
		return (compararmeConFeriadosDiaYMes(fecha.toLocalDate()) && compararmeConFeriadosHorario(fecha));
	}

	public boolean tenesFeriados() {
		return (!feriados.isEmpty());
	}

	public boolean estaDisponible(LocalDateTime horarioPreguntado) {

		if (tenesFeriados() && estasEnFeriado(horarioPreguntado) ){
			return false;
		}
		else {
			try {
				return (horario.incluyeHorario(horarioPreguntado));
			} catch (NullPointerException e) {
				return false;
			}
		}
	}

}
