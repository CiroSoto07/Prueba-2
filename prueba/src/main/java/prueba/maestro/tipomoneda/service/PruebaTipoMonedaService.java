/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.maestro.tipomoneda.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba.maestro.tipomoneda.repo.PruebaTipoMonedaRepository;
import prueba.models.TipoMoneda;

/**
 * The Class PruebaTipoMonedaService.
 */
@Service
public class PruebaTipoMonedaService {

	/** The prueba tipo moneda repository. */
	@Autowired
	private PruebaTipoMonedaRepository pruebaTipoMonedaRepository;

	/** The log. */
	Logger log = LoggerFactory.getLogger(PruebaTipoMonedaService.class);

	/**
	 * Read.
	 *
	 * @return the list
	 */
	public List<TipoMoneda> read() {
		log.trace("Ingresa al metodo read");
		return pruebaTipoMonedaRepository.findAll();
	}

	/**
	 * Read only one.
	 *
	 * @param id the id
	 * @return the tipo moneda
	 * @throws Exception the exception
	 */
	public TipoMoneda readOnlyOne(Long id) throws Exception {
		log.trace("Ingresa al metodo readOnlyOne");
		return pruebaTipoMonedaRepository.findById(id).orElse(null);
	}

	/**
	 * Creates the.
	 *
	 * @param register the register
	 * @return the tipo moneda
	 * @throws Exception the exception
	 */
	public TipoMoneda create(TipoMoneda register) throws Exception {
		log.trace("Ingresa al metodo create");
		Long id = pruebaTipoMonedaRepository.consecutivo();
		log.trace("Secuencia generada: {}", id);

		if (id != null) {
			try {
				register.setId(id);
				return pruebaTipoMonedaRepository.save(register);
			} catch (Exception e) {
				log.error("Error, No se pudo guardar tipo documento: {}", e);
				throw new Exception("Error, No se pudo guardartipo documento: " + e);
			}
		} else {
			log.error("Error, No se pudo asignar un id consecutivo para guardar el registro");
			throw new Exception("Error, No se pudo asignar un id consecutivo para guardar el registro");
		}
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param register the register
	 * @return the tipo moneda
	 * @throws Exception the exception
	 */
	public TipoMoneda update(Long id, TipoMoneda register) throws Exception {
		log.trace("Ingresa al metodo update");

		try {
			TipoMoneda TipoMoneda = pruebaTipoMonedaRepository.findById(id)
					.orElse(null);
			if (TipoMoneda != null) {

				register.setId(TipoMoneda.getId());

				return pruebaTipoMonedaRepository.save(register);
			} else {
				log.trace("No se encontro informacion para tipo documento");
				throw new Exception("No se encontro informacion para tipos documento");
			}

		} catch (Exception e) {
			log.error("Error al actualizar tipos terceros multiples: {}", e);
			throw new Exception("Error al actualizar tipos terceros multiples: " + e);
		}
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the tipo moneda
	 * @throws Exception the exception
	 */
	public TipoMoneda delete(Long id) throws Exception {
		log.trace("Ingresó al metodo delete");

		TipoMoneda TipoMoneda = pruebaTipoMonedaRepository.findById(id)
				.orElse(null);
		if (TipoMoneda != null) {

			return pruebaTipoMonedaRepository.save(TipoMoneda);
		} else {
			log.error("No se encontro informacion para el para tipo documento");
			throw new Exception("No se encontro informacion para el  para tipo documento");
		}
	}

}
