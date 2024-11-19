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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CUENTAS database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="CUENTAS" , schema = "ADMINISTRACION" )
public class Cuenta implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@NotNull(message = "el campo id no puede ser nulo")
	private Long id;

	/** The estado. */
	@Column(name="ESTADO")
	private String estado;

	/** The numero cuenta. */
	@Column(name="NUMERO_CUENTA")
	@NotNull(message = "el campo numero cuenta  no puede ser nulo")
	private Long numeroCuenta;

	/** The saldo. */
	@Column(name="SALDO")
	private Double saldo;
	
	/** The clientes id. */
	//bi-directional many-to-one association to Clientes
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
	@NotNull(message = "el campo cliente id no puede ser nulo")
	private Clientes clientesId;
	
	/** The tipo moneda. */
	//bi-directional many-to-one association to TipoMoneda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TIPO_MONEDA_ID", referencedColumnName = "ID")
	@NotNull(message = "el campo tipo moneda no puede ser nulo")
	private TipoMoneda tipoMoneda;

	/** The movimientos. */
	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="cuentaId")
	private List<Movimiento> movimientos;
	
	
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