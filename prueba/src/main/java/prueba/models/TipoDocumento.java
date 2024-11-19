/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the TIPO_DOCUMENTO database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="TIPO_DOCUMENTO" , schema = "ADMINISTRACION")
public class TipoDocumento implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1900230547274774349L;

	/** The id. */
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@NotNull(message = "el campo id no puede ser nulo")
	private Long id;

	/** The estado. */
	@Column(name="ESTADO")
	private String estado;

	/** The nombre. */
	@Column(name="NOMBRE")
	@NotNull(message = "el campo id no puede ser nulo")
	private String nombre;

	/** The tipo. */
	@Column(name="TIPO")
	@NotNull(message = "el campo id no puede ser nulo")
	private String tipo;

	/**
	 * Gets the desc estado.
	 *
	 * @return the desc estado
	 */
	public String getdescEstado() {
		if ("A".equals(this.estado)) {
			return "ACTIVO";
		} else {
			return "INACTIVO";
		}
	}


}