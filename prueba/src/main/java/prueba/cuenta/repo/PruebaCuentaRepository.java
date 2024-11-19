/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.cuenta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import prueba.models.Cuenta;

/**
 * The Interface PruebaCuentaRepository.
 */
@Repository
public interface PruebaCuentaRepository extends JpaRepository<Cuenta, Long> {

	/**
	 * Consecutivo.
	 *
	 * @return the long
	 */
	@Query(value = "SELECT (NVL(MAX(ID), 0) + 1) AS CONSECUTIVO FROM ADMINISTRACION.CUENTAS", nativeQuery = true)
	public Long consecutivo();
	
	/**
	 * Buscar cuenta.
	 *
	 * @param numeroCuenta the numero cuenta
	 * @return the long
	 */
	@Query(value = "select count(PN.NUMERO_CUENTA ) from ADMINISTRACION.CUENTAS PN where PN.NUMERO_CUENTA = :numeroCuenta", nativeQuery = true)
	public Long buscarCuenta(@Param("numeroCuenta") Long numeroCuenta);
	
	/**
	 * Cantidad movimiento.
	 *
	 * @param cuentaId the cuenta id
	 * @return the long
	 */
	@Query(value = "select count(M.CUENTA_ID ) from ADMINISTRACION.MOVIMIENTOS M where M.CUENTA_ID = :cuentaId", nativeQuery = true)
	public Long cantidadMovimiento(@Param("cuentaId") Long cuentaId);
}
