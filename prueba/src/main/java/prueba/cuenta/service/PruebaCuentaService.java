/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.cuenta.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba.cuenta.repo.PruebaCuentaRepository;
import prueba.models.Cuenta;

/**
 * The Class PruebaCuentaService.
 */
@Service
public class PruebaCuentaService {

	/** The prueba cuenta repository. */
	@Autowired
	private PruebaCuentaRepository pruebaCuentaRepository;

	/** The log. */
	Logger log = LoggerFactory.getLogger(PruebaCuentaService.class);

	/**
	 * Read.
	 *
	 * @return the list
	 */
	public List<Cuenta> read() {
		log.trace("Ingresa al metodo read");
		return pruebaCuentaRepository.findAll();
	}

	/**
	 * Read only one.
	 *
	 * @param id the id
	 * @return the cuenta
	 * @throws Exception the exception
	 */
	public Cuenta readOnlyOne(Long id) throws Exception {
		log.trace("Ingresa al metodo readOnlyOne");
		return pruebaCuentaRepository.findById(id).orElse(null);
	}

	/**
	 * Creates the.
	 *
	 * @param register the register
	 * @return the cuenta
	 * @throws Exception the exception
	 */
	public Cuenta create(Cuenta register) throws Exception {
		log.trace("Ingresa al metodo create");
		Long id = pruebaCuentaRepository.consecutivo();
		log.trace("Secuencia generada: {}", id);

		Long existeCuenta =  pruebaCuentaRepository.buscarCuenta(register.getNumeroCuenta())	;
		
			if (existeCuenta == 0) {
				register.setId(id);
				return pruebaCuentaRepository.save(register);
				
			}else {
				throw new Exception("Error, el numero de cuenta ya esta registrado");
			}
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param register the register
	 * @return the cuenta
	 * @throws Exception the exception
	 */
	public Cuenta update(Long id, Cuenta register) throws Exception {
		log.trace("Ingresa al metodo update");

		try {
			Cuenta cuenta = pruebaCuentaRepository.findById(id)
					.orElse(null);
			if (cuenta != null) {

				register.setId(cuenta.getId());

				return pruebaCuentaRepository.save(register);
			} else {
				log.trace("No se encontro informacion de esa cuenta");
				throw new Exception("No se encontro informacion de esa cuenta");
			}

		} catch (Exception e) {
			log.error("Error al actualizar la cuenta indicada: {}", e);
			throw new Exception("Error al actualizar la cuenta indicada: " + e);
		}
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the cuenta
	 * @throws Exception the exception
	 */
	public Cuenta delete(Long id) throws Exception {
		log.trace("Ingresó al metodo delete");

		Cuenta cuenta = pruebaCuentaRepository.findById(id)
				.orElse(null);
		
		Long cantidadMovimientos = pruebaCuentaRepository.cantidadMovimiento(cuenta.getId());
		
		if (cantidadMovimientos == 0) {

			 pruebaCuentaRepository.deleteById(cuenta.getId());
		} else {
			log.error("No se puede borrar la cuenta tiene movimientos asociados ");
			throw new Exception("No se puede borrar la cuenta tiene movimientos asociados");
		}
		return cuenta;
	}

}
