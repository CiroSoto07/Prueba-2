/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the PERSONA_NATURAL database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="CLIENTES", schema = "ADMINISTRACION")
public class Clientes implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@NotNull(message = "el campo id no puede ser nulo")
	private Long id;

	/** The ano fundacion. */
	@Column(name="ANO_FUNDACION")
	private String anoFundacion;
	
	/** The apellido. */
	@Column(name="APELLIDO")
	@NotNull(message = "el campo apellido no puede ser nulo")
	private String apellido;

	/** The nombre. */
	@Column(name="NOMBRE")
	@NotNull(message = "el campo nombre no puede ser nulo")
	private String nombre;

	/** The numero documento. */
	@NotNull(message = "el campo numero documento no puede ser nulo")
	@Column(name="NUMERO_DOCUMENTO")
	private Long numeroDocumento;

	/** The razon social. */
	@Column(name="RAZON_SOCIAL")
	private String razonSocial;
	
	/** The tipo cliente. */
	@Column(name="TIPO_CLIENTE")
	@NotNull(message = "el campotipo cliente no puede ser nulo")
	private String tipoCliente;

	/** The rut. */
	@Column(name="RUT")
	@NotNull(message = "el campo rut no puede ser nulo")
	private Long rut;

	/** The tipo documento id. */
	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TIPO_DOCUMENTO_ID", referencedColumnName = "ID")
	@NotNull(message = "el campo tipo documento id  no puede ser nulo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoDocumento tipoDocumentoId;
	
	/** The cuentas. */
	//bi-directional many-to-one association to cuentas
	@OneToMany(mappedBy="clientesId")
	@JsonIgnoreProperties({"clientesId", "tipoMoneda", "movimientos"})
	private List<Cuenta> cuentas;
	
	/** The movimientos. */
	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="cuentaId")
	private List<Movimiento> movimientos;
		
	
	/**
	 * Gets the desc tipo cliente.
	 *
	 * @return the desc tipo cliente
	 */
	public String getdescTipoCliente() {
		if ("N".equals(this.tipoCliente)) {
			return "CLIENTE NATURAL";
		} else {
			return "CLIENTE JURIDICO";
		}
	}
}