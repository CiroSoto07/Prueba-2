/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.maestro.tipomoneda.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import prueba.models.TipoMoneda;

/**
 * The Interface PruebaTipoMonedaRepository.
 */
@Repository
public interface PruebaTipoMonedaRepository extends JpaRepository<TipoMoneda, Long> {

	/**
	 * Consecutivo.
	 *
	 * @return the long
	 */
	@Query(value = "SELECT (NVL(MAX(ID), 0) + 1) AS CONSECUTIVO FROM ADMINISTRACION.TIPO_MONEDA", nativeQuery = true)
	public Long consecutivo();
}
