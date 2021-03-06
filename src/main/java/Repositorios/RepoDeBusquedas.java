package Repositorios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import AsignarAccionesUsuario.Accion;

public class RepoDeBusquedas implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	private static RepoDeBusquedas instancia = null;
	List<Busqueda> busquedas;
	Map<LocalDateTime, Integer> reportePorFecha;
	RepoTerminales repoTerminales;

	public static RepoDeBusquedas getInstance() {
		if (instancia == null) {
			instancia = new RepoDeBusquedas();
			instancia.inicializarBaseDeDatos();
		}
		return instancia;
	}

	public void persistirBusqueda(Busqueda unObjeto) {
		EntityTransaction transaccion = entityManager().getTransaction();
		transaccion.begin();
		 PerThreadEntityManagers.getEntityManager().persist(unObjeto);
		transaccion.commit();
		

	}

	public void persistirAccion(Accion unObjeto) {
		entityManager().persist(unObjeto);
	}

	public Accion obtenerObjetoAccion(Integer id) {
		return entityManager().find(Accion.class, id);
	}

	public Busqueda obtenerUnaBusqueda(Integer id) {
		return entityManager().find(Busqueda.class, id);
	}

	public List<Busqueda> listarTodo() {
		busquedas = entityManager()//
				.createQuery(" FROM Busqueda", Busqueda.class) //
				.getResultList();
		return busquedas;
	}

	public List<Busqueda> getBusquedas() {
		return busquedas;
	}

	public void inicializarBaseDeDatos() {
		busquedas = new ArrayList<Busqueda>();
		reportePorFecha = new HashMap<LocalDateTime, Integer>();
		repoTerminales = RepoTerminales.getInstance();
	}

	public Busqueda addBusqueda(String terminal, String frase, double tiempo, double tiempoMax,
			List<Integer> puntosObtenidos) {
		Busqueda busqueda = new Busqueda(terminal, frase, tiempo, tiempoMax, puntosObtenidos);
		busquedas.add(busqueda);
		addBusquedasPorFechaAlReporte(busqueda.getFecha());
		persistirBusqueda(busqueda);
		return busqueda;
	}

	public int cantidadDeBusquedasPorFecha(LocalDateTime fecha) {
		return busquedas.stream().filter(unaBusqueda -> unaBusqueda.esDeLaFecha(fecha)).collect(Collectors.toList())
				.size();
	}

	public void addBusquedasPorFechaAlReporte(LocalDateTime fecha) {
		reportePorFecha.put(fecha, this.cantidadDeBusquedasPorFecha(fecha));
	}

	public Map<LocalDateTime, Integer> getReportePorFecha() {
		return reportePorFecha;
	}

}
