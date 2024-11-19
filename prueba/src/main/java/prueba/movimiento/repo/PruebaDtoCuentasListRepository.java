/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.movimiento.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import prueba.models.DtoCuentasList;

/**
 * The Interface PruebaDtoCuentasListRepository.
 */
@Repository
public interface PruebaDtoCuentasListRepository extends JpaRepository<DtoCuentasList, Long> {

	
	/**
	 * List tipo cuenta.
	 *
	 * @param clienteId the cliente id
	 * @return the list
	 */
	@Query(value = "select C.TIPO_MONEDA_ID , MON.NOMBRE\r\n"
			+ "from ADMINISTRACION.CUENTAS C ,\r\n"
			+ "     ADMINISTRACION.TIPO_MONEDA MON\r\n"
			+ "where C.CLIENTE_ID = :clienteId\r\n"
			+ "and C.TIPO_MONEDA_ID = MON.ID", nativeQuery = true)
	public List<DtoCuentasList> listTipoCuenta(@Param("clienteId") Long clienteId);
	
}
