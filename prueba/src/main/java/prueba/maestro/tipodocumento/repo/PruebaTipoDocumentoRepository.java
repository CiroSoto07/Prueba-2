/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.maestro.tipodocumento.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import prueba.models.TipoDocumento;

/**
 * The Interface PruebaTipoDocumentoRepository.
 */
@Repository
public interface PruebaTipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

	/**
	 * Consecutivo.
	 *
	 * @return the long
	 */
	@Query(value = "SELECT (NVL(MAX(ID), 0) + 1) AS CONSECUTIVO FROM ADMINISTRACION.TIPO_DOCUMENTO", nativeQuery = true)
	public Long consecutivo();
}
