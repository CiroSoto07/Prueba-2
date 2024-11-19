/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.clientes.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba.clientes.repo.PruebaClientesRepository;
import prueba.models.Clientes;

/**
 * The Class PruebaClientesService.
 */
@Service
public class PruebaClientesService {

	/** The prueba clientes repository. */
	@Autowired
	private PruebaClientesRepository pruebaClientesRepository;

	/** The log. */
	Logger log = LoggerFactory.getLogger(PruebaClientesService.class);

	/**
	 * Read.
	 *
	 * @return the list
	 */
	public List<Clientes> read() {
		log.trace("Ingresa al metodo read");
		return pruebaClientesRepository.findAll();
	}

	/**
	 * Read only one.
	 *
	 * @param id the id
	 * @return the clientes
	 * @throws Exception the exception
	 */
	public Clientes readOnlyOne(Long id) throws Exception {
		log.trace("Ingresa al metodo readOnlyOne");
		return pruebaClientesRepository.findById(id).orElse(null);
	}

	/**
	 * Creates the.
	 *
	 * @param register the register
	 * @return the clientes
	 * @throws Exception the exception
	 */
	public Clientes create(Clientes register) throws Exception {
		log.trace("Ingresa al metodo create");
		Long id = pruebaClientesRepository.consecutivo();
		log.trace("Secuencia generada: {}", id);

		Long existeDocumento = pruebaClientesRepository.buscarDocumento(register.getNumeroDocumento());
		
		Long validaRut = pruebaClientesRepository.validaRut(register.getRut());
		
		if ( existeDocumento == 0 || validaRut == 0)  {

				if (register.getTipoCliente().equals("J")) {
					if (register.getRazonSocial()== null) {
						throw new Exception("el campo razon social es obligatorio");
					}
					if (register.getAnoFundacion()== null) {
						throw new Exception("el campo año fundacion es obligatorio");
					}
				}
				
				register.setId(id);
				return pruebaClientesRepository.save(register);
		
		} else {
			log.error("Error, No se pudo asignar un id consecutivo para guardar el registro");
			throw new Exception("Error, No se pudo asignar un id consecutivo para guardar el registro, porque ya existe ese numero documento o rut");
		}
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param register the register
	 * @return the clientes
	 * @throws Exception the exception
	 */
	public Clientes update(Long id, Clientes register) throws Exception {
		log.trace("Ingresa al metodo update");

		try {
			Clientes clientes = pruebaClientesRepository.findById(id)
					.orElse(null);
			if (clientes != null) {
				
				if (register.getTipoCliente().equals("J")) {
					if (register.getRazonSocial()== null) {
						throw new Exception("el campo razon social es obligatorio");
					}
					if (register.getAnoFundacion()== null) {
						throw new Exception("el campo año fundacion es obligatorio");
					}
				}

				register.setId(clientes.getId());

				return pruebaClientesRepository.save(register);
			} else {
				log.trace("No se encontro informacion para el cliente");
				throw new Exception("No se encontro informacion parael cliente");
			}

		} catch (Exception e) {
			log.error("Error al actualizar el cliente: {}", e);
			throw new Exception("Error al actualizar el cliente: " + e);
		}
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the clientes
	 * @throws Exception the exception
	 */
	public Clientes delete(Long id) throws Exception {
		log.trace("Ingresó al metodo delete");

		Clientes clientes = pruebaClientesRepository.findById(id)
				.orElse(null);
		if (clientes != null) {
			 pruebaClientesRepository.deleteById(clientes.getId());
		} else {
			log.error("No se encontro informacion para el cliente");
			throw new Exception("No se encontro informacion para el clientel");
		}
		return clientes; 
	}

}
