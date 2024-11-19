/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.clientes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import prueba.models.Clientes;

/**
 * The Interface PruebaClientesRepository.
 */
@Repository
public interface PruebaClientesRepository extends JpaRepository<Clientes, Long> {

	/**
	 * Consecutivo.
	 *
	 * @return the long
	 */
	@Query(value = "SELECT (NVL(MAX(ID), 0) + 1) AS CONSECUTIVO FROM ADMINISTRACION.CLIENTES", nativeQuery = true)
	public Long consecutivo();
	
	/**
	 * Buscar documento.
	 *
	 * @param numeroDocumento the numero documento
	 * @return the long
	 */
	@Query(value = "select count(PN.NUMERO_DOCUMENTO ) from ADMINISTRACION.CLIENTES PN where PN.NUMERO_DOCUMENTO = :numeroDocumento", nativeQuery = true)
	public Long buscarDocumento(@Param("numeroDocumento") Long numeroDocumento);
	
	/**
	 * Valida rut.
	 *
	 * @param rut the rut
	 * @return the long
	 */
	@Query(value = "select count(PN.RUT ) from ADMINISTRACION.CLIENTES PN where PN.RUT = :rut", nativeQuery = true)
	public Long validaRut(@Param("rut") Long rut);
}
