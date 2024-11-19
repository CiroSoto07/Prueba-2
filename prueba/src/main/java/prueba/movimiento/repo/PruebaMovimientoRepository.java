/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.movimiento.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import prueba.models.Movimiento;

/**
 * The Interface PruebaMovimientoRepository.
 */
@Repository
public interface PruebaMovimientoRepository extends JpaRepository<Movimiento, Long> {

	/**
	 * Consecutivo.
	 *
	 * @return the long
	 */
	@Query(value = "SELECT (NVL(MAX(ID), 0) + 1) AS CONSECUTIVO FROM ADMINISTRACION.MOVIMIENTOS", nativeQuery = true)
	public Long consecutivo();
	
	/**
	 * Saldo.
	 *
	 * @param cuentaId the cuenta id
	 * @return the double
	 */
	@Query(value = "select sum(M.SALDO ) from ADMINISTRACION.MOVIMIENTOS M where M.CUENTA_ID = :cuentaId", nativeQuery = true)
	public Double saldo(@Param("cuentaId") Long cuentaId);
	
	/**
	 * Cliente.
	 *
	 * @param cuentaId the cuenta id
	 * @return the long
	 */
	@Query(value = "select distinct C.CLIENTE_ID \r\n"
			+ "from ADMINISTRACION.MOVIMIENTOS M,\r\n"
			+ "     ADMINISTRACION.CUENTAS C\r\n"
			+ "where M.CUENTA_ID = :cuentaId\r\n"
			+ "and M.CUENTA_ID = C.ID", nativeQuery = true)
	public Long cliente(@Param("cuentaId") Long cuentaId);
	
	
	/**
	 * Procedure.
	 *
	 * @param id the id
	 * @param cuentaId the cuenta id
	 * @param valor the valor
	 * @param signo the signo
	 * @return the string
	 */
	@Query(value = "declare prueba number; \r\n"
			+ "BEGIN     \r\n"
			+ "ADMINISTRACION.PCK_ACT_MOVIMIENTO.sp_act_saldo_movimiento(:id,:cuentaId,:valor, :signo); \r\n"
			+ "exception when others then DBMS_OUTPUT.put_line('error consultando/insertando parámetro'); \r\n"
			+ "end;", nativeQuery = true)
	public String procedure(@Param("id") Long id,
			@Param("cuentaId") Long cuentaId,
			@Param("valor") Long valor,
			@Param("signo") String signo
			);
	
}
