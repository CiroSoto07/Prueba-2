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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class DtoCuentasList.
 */
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DtoCuentasList implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo moneda id. */
	@Id
	@Column(name="TIPO_MONEDA_ID")
	private Long tipoMonedaId;

	/** The nombre. */
	@Column(name="NOMBRE")
	private String nombre;
}