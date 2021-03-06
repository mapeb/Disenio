package tests;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import Repositorios.RepoPOIs;

public class TestActualizacionDeLC {
	
	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	
	@Before
	public void init(){
		actualizador = new ActualizadorDeLC();
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		actualizador.setArchivoALevantar("LocalesComerciales.txt");
	}
	
	@Test
	public void RepoVacioTest() {
		Assert.assertTrue(repo.isEmpty());
	}
	
	@Test
	public void RepoSinLocalesComercialesEntoncesNoAgregaTest(){
		actualizador.actualizarListaDeLC();
		Assert.assertEquals(repo.size(), 0);
	}
	
	@Test
	public void RepoActualizadoCorrectamenteTest(){
		repo.addLocal("Carrousel", new ArrayList<String>());
		repo.addLocal("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC();
		//ArrayList<String> palabrasClaves1 = actualizador.getPalabrasClavesDeLinea("Carrousel");
		//ArrayList<String> palabrasClaves2 = actualizador.getPalabrasClavesDeLinea("LoDeMari");
		ArrayList<ArrayList<String>> palabrasClave = ActualizadorDeLC.getLista();
		Assert.assertTrue(repo.tieneLasPalabrasClaves("LoDeMari", palabrasClave));
		Assert.assertTrue(repo.tieneLasPalabrasClaves("Carrousel", palabrasClave));
	}
	
	@Test
	public void RepoActualizadoUnSoloLocalTest(){
		repo.addLocal("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC();
		ArrayList<ArrayList<String>> palabrasClaves2 = ActualizadorDeLC.getLista();
		Assert.assertTrue(repo.tieneLasPalabrasClaves("LoDeMari", palabrasClaves2));
	}


}
