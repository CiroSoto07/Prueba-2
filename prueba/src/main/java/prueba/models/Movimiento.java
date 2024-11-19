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
 * The persistent class for the MOVIMIENTOS database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="MOVIMIENTOS" , schema = "ADMINISTRACION")
public class Movimiento implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", updatable = false)
	@NotNull(message = "el campo id no puede ser nulo")
	private Long id;

	/** The saldo. */
	@Column(name="SALDO", updatable = false, insertable = false)
	@NotNull(message = "el campo saldo no puede ser nulo")
	private Double saldo;

	/** The signo. */
	@Column(name="SIGNO", updatable = false)
	@NotNull(message = "el campo signo no puede ser nulo")
	private String signo;

	/** The valor. */
	@Column(name="VALOR", updatable = false)
	@NotNull(message = "el campo valor no puede ser nulo")
	private Long valor;

	/** The cuenta id. */
	@Column(name="CUENTA_ID", updatable = false)
	private Long cuentaId;

}