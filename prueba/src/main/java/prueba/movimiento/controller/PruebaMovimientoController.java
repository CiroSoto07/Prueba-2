/*
 * prueba
 * 15/07/2021
 * ciro.soto@ada.co
 */
package prueba.movimiento.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import prueba.message.ResponseMessage;
import prueba.models.Movimiento;
import prueba.movimiento.service.PruebaMovimientoService;

/**
 * The Class PruebaMovimientoController.
 */
@RestController
@RequestMapping("/movimientos")
public class PruebaMovimientoController  {

	/** The prueba movimiento service. */
	@Autowired
	private PruebaMovimientoService pruebaMovimientoService;

	/**
	 * Read operation crud.
	 *
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/")
	public ResponseEntity<List<Movimiento>> readOperationCrud() throws Exception {

		List<Movimiento> movimiento = pruebaMovimientoService.read();
		if (!movimiento.isEmpty()) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(movimiento);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(movimiento);
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
	public ResponseEntity<Movimiento> readOnlyOneOperationCrud(@PathVariable Long id) throws Exception {

		Movimiento movimiento = pruebaMovimientoService.readOnlyOne(id);
		if (movimiento != null) {
			new ResponseMessage("Informacion entontrada con exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(movimiento);
		} else {
			new ResponseMessage("No se encontro informacion");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(movimiento);
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
	public ResponseEntity<Movimiento> createOperationCrud(
			@Valid @RequestBody Movimiento register) throws Exception {

		Movimiento movimiento = pruebaMovimientoService.create(register);
		if (movimiento != null) {
			new ResponseMessage("Informacion guardada con exito");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(movimiento);
	}


}
