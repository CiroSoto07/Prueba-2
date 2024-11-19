/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.maestro.tipodocumento.controller;

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

import prueba.maestro.tipodocumento.service.PruebaTipoDocumentoService;
import prueba.message.ResponseMessage;
import prueba.models.TipoDocumento;

/**
 * The Class PruebaTipoDocumentoController.
 */
@RestController
@RequestMapping("/tipoDocumento")
public class PruebaTipoDocumentoController  {

	/** The prueba tipo documento service. */
	@Autowired
	private PruebaTipoDocumentoService pruebaTipoDocumentoService;

	/**
	 * Read operation crud.
	 *
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/")
	public ResponseEntity<List<TipoDocumento>> readOperationCrud() throws Exception {

		List<TipoDocumento> tipoDocumento = pruebaTipoDocumentoService.read();
		if (!tipoDocumento.isEmpty()) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoDocumento);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tipoDocumento);
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
	public ResponseEntity<TipoDocumento> readOnlyOneOperationCrud(@PathVariable Long id) throws Exception {

		TipoDocumento tipoDocumento = pruebaTipoDocumentoService.readOnlyOne(id);
		if (tipoDocumento != null) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoDocumento);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tipoDocumento);
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
	public ResponseEntity<TipoDocumento> createOperationCrud(
			@Valid @RequestBody TipoDocumento register) throws Exception {

		TipoDocumento tipoDocumento = pruebaTipoDocumentoService.create(register);
		if (tipoDocumento != null) {
			new ResponseMessage("Informacion guardada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoDocumento);
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
	public ResponseEntity<TipoDocumento> updateOperationCrud(
			@Valid @RequestBody TipoDocumento register, @PathVariable Long id) throws Exception {

		TipoDocumento tipoDocumento = pruebaTipoDocumentoService.update(id, register);
		if (tipoDocumento != null) {
			new ResponseMessage("Informacion actualizada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoDocumento);
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
	public ResponseEntity<TipoDocumento> deleteOperationCrud(@PathVariable Long id) throws Exception {

		TipoDocumento tipoDocumento = pruebaTipoDocumentoService.delete(id);
		if (tipoDocumento != null) {
			new ResponseMessage("Informacion eliminada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoDocumento);
	}

}
