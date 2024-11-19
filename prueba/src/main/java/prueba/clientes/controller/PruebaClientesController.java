/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.clientes.controller;

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

import prueba.clientes.service.PruebaClientesService;
import prueba.message.ResponseMessage;
import prueba.models.Clientes;

/**
 * The Class PruebaClientesController.
 */
@RestController
@RequestMapping("/clientes")
public class PruebaClientesController  {

	/** The prueba clientes service. */
	@Autowired
	private PruebaClientesService pruebaClientesService;

	/**
	 * Read operation crud.
	 *
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/")
	public ResponseEntity<List<Clientes>> readOperationCrud() throws Exception {

		List<Clientes> Clientes = pruebaClientesService.read();
		if (!Clientes.isEmpty()) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(Clientes);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Clientes);
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
	public ResponseEntity<Clientes> readOnlyOneOperationCrud(@PathVariable Long id) throws Exception {

		Clientes Clientes = pruebaClientesService.readOnlyOne(id);
		if (Clientes != null) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(Clientes);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Clientes);
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
	public ResponseEntity<Clientes> createOperationCrud(
			@Valid @RequestBody Clientes register) throws Exception {

		Clientes Clientes = pruebaClientesService.create(register);
		if (Clientes != null) {
			new ResponseMessage("Informacion guardada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Clientes);
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
	public ResponseEntity<Clientes> updateOperationCrud(
			@Valid @RequestBody Clientes register, @PathVariable Long id) throws Exception {

		Clientes Clientes = pruebaClientesService.update(id, register);
		if (Clientes != null) {
			new ResponseMessage("Informacion actualizada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Clientes);
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
	public ResponseEntity<Clientes> deleteOperationCrud(@PathVariable Long id) throws Exception {

		Clientes Clientes = pruebaClientesService.delete(id);
		if (Clientes != null) {
			new ResponseMessage("Informacion eliminada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(Clientes);
	}

}
