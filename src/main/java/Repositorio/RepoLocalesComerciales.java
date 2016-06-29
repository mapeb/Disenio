package Repositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import TypePois.Local;

public class RepoLocalesComerciales {
	ArrayList<Local> listaLC;
	static RepoLocalesComerciales instancia;

	public static RepoLocalesComerciales getInstance() {
		if (instancia == null) {
			instancia = new RepoLocalesComerciales();
			instancia.inicializarLocalesComerciales();
		}
		return instancia;
	}

	public void inicializarLocalesComerciales() {
		listaLC = new ArrayList<Local>();
	}

	public List<Local> tieneUnLocalConNombre(String nombre) {

		return listaLC.stream().filter(unLocal -> unLocal.getNombre().equals(nombre)).collect(Collectors.toList());
	}

	public void actualizarLocal(String nombre, ArrayList<String> palabrasClave) {

		Local localAModificar = this.tieneUnLocalConNombre(nombre).get(0);
		listaLC.remove(localAModificar);

		localAModificar.setPalabrasClave(palabrasClave);
		listaLC.add(localAModificar);

	}

	public boolean isEmpty() {
		return listaLC.isEmpty();
	}

	public int size() {
		return listaLC.size();
	}
	
	public void add(String nombre, ArrayList<String> palabrasClave){
		Local localito = new Local(null, nombre, null, null, null);
		localito.setPalabrasClave(palabrasClave);
		
		listaLC.add(localito);
	}
}
