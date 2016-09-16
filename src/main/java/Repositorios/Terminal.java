package Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import GestorDeMail.GestorDeMailTrucho;
import GestorDeMail.GestorMailInterface;
import TypePois.POI;

@Entity
@Table(name = "Terminal")
public class Terminal {
	@Id
	@Column(name="name_terminal")
	private String nombre;
	
	@ElementCollection
	@CollectionTable(name="reporteParcialPorTerminal", joinColumns=@JoinColumn(name="nombre_terminal"))
	private List<Integer> reporteParcialPorTerminal = new ArrayList<Integer>();
	
	@Transient
	private GestorMailInterface gestorDeMail = new GestorDeMailTrucho();

	
	private String mailAdmin = "mailprueba@gmail.com";

	public Terminal(String nombre) {
		this.nombre = nombre;
	}

	public void setGestorDeMail(GestorMailInterface gestorDeMail) {
		this.gestorDeMail = gestorDeMail;
	}

	public int cantidadDeResultadosPorBusqueda(List<POI> puntosSegunPalabra) {
		return puntosSegunPalabra.size();
	}

	public void addResultadosParcialesAlReporte(List<POI> puntosSegunPalabra) {
		reporteParcialPorTerminal.add(cantidadDeResultadosPorBusqueda(puntosSegunPalabra));
	}

	public List<Integer> getReporteParcialPorTerminal() {
		return reporteParcialPorTerminal;
	}

	public int sumarResultados(List<Integer> enteros) {
		int resultado = 0;
		for (Integer entero : enteros) {
			resultado += entero;
		}
		return resultado;
	}

	public int resultadosTotales() {
		return this.sumarResultados(reporteParcialPorTerminal);
	}

	public boolean avisaAlAdminTiempoExcedido(double tiempo, String frase, LocalDate fecha, String terminal) {
		return gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, "Tiempo Excedido", "");
	}

	public String getNombre() {
		return nombre;
	}

	public GestorMailInterface getGestor() {
		return gestorDeMail;
	}
}