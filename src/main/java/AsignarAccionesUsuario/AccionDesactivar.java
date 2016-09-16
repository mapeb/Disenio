package AsignarAccionesUsuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import Repositorios.Usuario;

@Entity
@DiscriminatorValue (value = "d")
public class AccionDesactivar extends Accion {

	@OneToOne
	Accion accionAquitar;

	public AccionDesactivar(Accion accion) {
		super();
		this.accionAquitar = accion;
	}

	public void ejecutarAccion(Usuario usuario) {
		usuario.quitar(accionAquitar);
		usuario.quitar(this);
	}

	@Override
	public int getId() {
		return this.id;
		
	}

}
