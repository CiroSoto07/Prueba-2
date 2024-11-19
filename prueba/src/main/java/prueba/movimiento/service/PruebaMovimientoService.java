/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.movimiento.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba.models.DtoCuentasList;
import prueba.models.Movimiento;
import prueba.movimiento.repo.PruebaDtoCuentasListRepository;
import prueba.movimiento.repo.PruebaMovimientoRepository;

/**
 * The Class PruebaMovimientoService.
 */
@Service
public class PruebaMovimientoService {

	/** The prueba movimiento repository. */
	@Autowired
	private PruebaMovimientoRepository pruebaMovimientoRepository;

	/** The prueba dto cuentas list repository. */
	@Autowired
	private PruebaDtoCuentasListRepository pruebaDtoCuentasListRepository;
	
	/** The em. */
	@Autowired
	private EntityManager em;

	/** The log. */
	Logger log = LoggerFactory.getLogger(PruebaMovimientoService.class);

	/**
	 * Read.
	 *
	 * @return the list
	 */
	public List<Movimiento> read() {
		log.trace("Ingresa al metodo read");
		return pruebaMovimientoRepository.findAll();
	}

	/**
	 * Read only one.
	 *
	 * @param id the id
	 * @return the movimiento
	 * @throws Exception the exception
	 */
	public Movimiento readOnlyOne(Long id) throws Exception {
		log.trace("Ingresa al metodo readOnlyOne");
		return pruebaMovimientoRepository.findById(id).orElse(null);
	}

	/**
	 * Creates the.
	 *
	 * @param register the register
	 * @return the movimiento
	 * @throws Exception the exception
	 */
	public Movimiento create(Movimiento register) throws Exception {
		log.trace("Ingresa al metodo create");
		Long id = pruebaMovimientoRepository.consecutivo();
		log.trace("Secuencia generada: {}", id);

		Double saldoActual = pruebaMovimientoRepository.saldo(register.getCuentaId());
		
		Long clienteId = pruebaMovimientoRepository.cliente(register.getCuentaId());
				
		List<DtoCuentasList> tipoCuenta = pruebaDtoCuentasListRepository.listTipoCuenta(clienteId);
		
		if ( tipoCuenta != null  ) {
			for (DtoCuentasList listaCuentas : tipoCuenta) {
				//id 1 es Pesos
				//id 2 es Dolar
				//id 3 es Euro
				if (listaCuentas.getTipoMonedaId() == 1) {  
					if (register.getValor() + saldoActual > 1000000) {
						throw new Exception("Error, esta movimiento supera el tope establecido para la cuenta en peso que es:  1.000.000" );
					}else {
						register.setId(id);
						 pruebaMovimientoRepository.save(register);
						 callProcedure(register.getId(), register.getCuentaId(), register.getValor(), register.getSigno());
					}
					
					if (listaCuentas.getTipoMonedaId() == 2) {
						if (register.getValor() + saldoActual > 300) {
							throw new Exception(
									"Error, esta movimiento supera el tope establecido para la cuenta en dolares que es:  300");
						} else {
							register.setId(id);
							 pruebaMovimientoRepository.save(register);
							 callProcedure(register.getId(), register.getCuentaId(), register.getValor(), register.getSigno());
						}
						
						if (listaCuentas.getTipoMonedaId() == 3) {
							if (register.getValor() + saldoActual > 150) {
								throw new Exception(
										"Error, esta movimiento supera el tope establecido para la cuenta en euro que es:  150");
							} else {
								register.setId(id);
								 pruebaMovimientoRepository.save(register);
								 callProcedure(register.getId(), register.getCuentaId(), register.getValor(), register.getSigno());
							}
						}
					}
				}
			}
		}
		return register;
	}
	
	/**
	 * Call function.
	 *
	 * @param id the id
	 * @param cuentaId the cuenta id
	 * @param valor the valor
	 * @param signo the signo
	 * @return the object
	 */
	public Object callFunction(Long id, Long cuentaId, Long valor, String signo) {
		Query query = em.createNativeQuery("select ADMINISTRACION.F_ACT_SALDO(:id, :cuentaId, :valor, :signo) from dual");
		query.setParameter("id", id);
		query.setParameter("cuentaId", cuentaId);
		query.setParameter("valor", valor);
		query.setParameter("signo", signo);
		return query.getSingleResult();     
		
	}
	
	/**
	 * Call procedure.
	 *
	 * @param id the id
	 * @param cuentaId the cuenta id
	 * @param valor the valor
	 * @param signo the signo
	 * @throws SQLException the SQL exception
	 */
	public void callProcedure(Long id, Long cuentaId, Long valor, String signo) throws SQLException {
		CallableStatement db  = null;  
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//10.1.140.101:1521/db104", "NOMINA", "nomina");
		try {
			db.executeUpdate(pruebaMovimientoRepository.procedure(id, cuentaId, valor, signo));
			conn.commit();
		} catch (Exception e) {
			
		}
	}
	
}
