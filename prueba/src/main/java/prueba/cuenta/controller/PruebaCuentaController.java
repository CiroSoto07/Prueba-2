/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.cuenta.controller;

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

import prueba.cuenta.service.PruebaCuentaService;
import prueba.message.ResponseMessage;
import prueba.models.Cuenta;

/**
 * The Class PruebaCuentaController.
 */
@RestController
@RequestMapping("/cuentas")
public class PruebaCuentaController  {

	/** The prueba cuenta service. */
	@Autowired
	private PruebaCuentaService pruebaCuentaService;

	/**
	 * Read operation crud.
	 *
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/")
	public ResponseEntity<List<Cuenta>> readOperationCrud() throws Exception {

		List<Cuenta> cuenta = pruebaCuentaService.read();
		if (!cuenta.isEmpty()) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cuenta);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cuenta);
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
	public ResponseEntity<Cuenta> readOnlyOneOperationCrud(@PathVariable Long id) throws Exception {

		Cuenta cuenta = pruebaCuentaService.readOnlyOne(id);
		if (cuenta != null) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cuenta);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cuenta);
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
	public ResponseEntity<Cuenta> createOperationCrud(
			@Valid @RequestBody Cuenta register) throws Exception {

		Cuenta cuenta = pruebaCuentaService.create(register);
		if (cuenta != null) {
			new ResponseMessage("Informacion guardada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cuenta);
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
	public ResponseEntity<Cuenta> updateOperationCrud(
			@Valid @RequestBody Cuenta register, @PathVariable Long id) throws Exception {

		Cuenta cuenta = pruebaCuentaService.update(id, register);
		if (cuenta != null) {
			new ResponseMessage("Informacion actualizada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cuenta);
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
	public ResponseEntity<Cuenta> deleteOperationCrud(@PathVariable Long id) throws Exception {

		Cuenta cuenta = pruebaCuentaService.delete(id);
		if (cuenta != null) {
			new ResponseMessage("Informacion eliminada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cuenta);
	}

}
