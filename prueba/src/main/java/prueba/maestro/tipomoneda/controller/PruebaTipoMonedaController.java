/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.maestro.tipomoneda.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import prueba.maestro.tipomoneda.service.PruebaTipoMonedaService;
import prueba.message.ResponseMessage;
import prueba.models.TipoMoneda;

/**
 * The Class PruebaTipoMonedaController.
 */
@RestController
@RequestMapping("/tipoMoneda")
public class PruebaTipoMonedaController  {

	/** The prueba tipo moneda service. */
	@Autowired
	private PruebaTipoMonedaService pruebaTipoMonedaService;

	/**
	 * Read operation crud.
	 *
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/")
	public ResponseEntity<List<TipoMoneda>> readOperationCrud() throws Exception {

		List<TipoMoneda> tipoMoneda = pruebaTipoMonedaService.read();
		if (!tipoMoneda.isEmpty()) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoMoneda);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tipoMoneda);
		}
	}

	/**
	 * Read only one operation crud.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TipoMoneda> readOnlyOneOperationCrud(@PathVariable Long id) throws Exception {

		TipoMoneda tipoMoneda = pruebaTipoMonedaService.readOnlyOne(id);
		if (tipoMoneda != null) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoMoneda);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tipoMoneda);
		}
	}

	/**
	 * Creates the operation crud.
	 *
	 * @param register the register
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/")
	public ResponseEntity<TipoMoneda> createOperationCrud(
			@Valid @RequestBody TipoMoneda register) throws Exception {

		TipoMoneda tipoMoneda = pruebaTipoMonedaService.create(register);
		if (tipoMoneda != null) {
			new ResponseMessage("Informacion guardada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoMoneda);
	}

	/**
	 * Update operation crud.
	 *
	 * @param register the register
	 * @param id the id
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<TipoMoneda> updateOperationCrud(
			@Valid @RequestBody TipoMoneda register, @PathVariable Long id) throws Exception {

		TipoMoneda tipoMoneda = pruebaTipoMonedaService.update(id, register);
		if (tipoMoneda != null) {
			new ResponseMessage("Informacion actualizada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoMoneda);
	}

	/**
	 * Delete operation crud.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping("/{id}")
	public ResponseEntity<TipoMoneda> deleteOperationCrud(@PathVariable Long id) throws Exception {

		TipoMoneda tipoMoneda = pruebaTipoMonedaService.delete(id);
		if (tipoMoneda != null) {
			new ResponseMessage("Informacion eliminada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoMoneda);
	}

}
