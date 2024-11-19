/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.maestro.tipodocumento.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba.maestro.tipodocumento.repo.PruebaTipoDocumentoRepository;
import prueba.models.TipoDocumento;

/**
 * The Class PruebaTipoDocumentoService.
 */
@Service
public class PruebaTipoDocumentoService {

	/** The prueba tipo documento repository. */
	@Autowired
	private PruebaTipoDocumentoRepository pruebaTipoDocumentoRepository;

	/** The log. */
	Logger log = LoggerFactory.getLogger(PruebaTipoDocumentoService.class);

	/**
	 * Read.
	 *
	 * @return the list
	 */
	public List<TipoDocumento> read() {
		log.trace("Ingresa al metodo read");
		return pruebaTipoDocumentoRepository.findAll();
	}

	/**
	 * Read only one.
	 *
	 * @param id the id
	 * @return the tipo documento
	 * @throws Exception the exception
	 */
	public TipoDocumento readOnlyOne(Long id) throws Exception {
		log.trace("Ingresa al metodo readOnlyOne");
		return pruebaTipoDocumentoRepository.findById(id).orElse(null);
	}

	/**
	 * Creates the.
	 *
	 * @param register the register
	 * @return the tipo documento
	 * @throws Exception the exception
	 */
	public TipoDocumento create(TipoDocumento register) throws Exception {
		log.trace("Ingresa al metodo create");
		Long id = pruebaTipoDocumentoRepository.consecutivo();
		log.trace("Secuencia generada: {}", id);

		if (id != null) {
			try {
				register.setId(id);
				return pruebaTipoDocumentoRepository.save(register);
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
	 * @return the tipo documento
	 * @throws Exception the exception
	 */
	public TipoDocumento update(Long id, TipoDocumento register) throws Exception {
		log.trace("Ingresa al metodo update");

		try {
			TipoDocumento TipoDocumento = pruebaTipoDocumentoRepository.findById(id)
					.orElse(null);
			if (TipoDocumento != null) {

				register.setId(TipoDocumento.getId());

				return pruebaTipoDocumentoRepository.save(register);
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
	 * @return the tipo documento
	 * @throws Exception the exception
	 */
	public TipoDocumento delete(Long id) throws Exception {
		log.trace("Ingresó al metodo delete");

		TipoDocumento TipoDocumento = pruebaTipoDocumentoRepository.findById(id)
				.orElse(null);
		if (TipoDocumento != null) {

			return pruebaTipoDocumentoRepository.save(TipoDocumento);
		} else {
			log.error("No se encontro informacion para el para tipo documento");
			throw new Exception("No se encontro informacion para el  para tipo documento");
		}
	}

}
