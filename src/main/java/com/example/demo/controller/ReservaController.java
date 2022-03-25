package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;

// get all reserva
	@GetMapping("/reserva")
	public List<Reserva> getAllReservas() {
		return reservaRepository.findAll();
	}

// create reserva rest api
	@PostMapping("/reserva")
	public Reserva createReserva(@RequestBody Reserva reserva) {
		return reservaRepository.save(reserva);
	}

// get reserva by id rest api
	@GetMapping("/reserva/{idReserva}")
	public ResponseEntity<Reserva> getReservaById(@PathVariable Long idReserva) {
		Reserva reserva = reservaRepository.findById(idReserva)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva not exist with id :" + idReserva));
		return ResponseEntity.ok(reserva);
	}

// update reserva rest api

	@PutMapping("/reserva/{idReserva}")
	public ResponseEntity<Reserva> updateReserva(@PathVariable Long idReserva, @RequestBody Reserva reservaDetails) {
		Reserva reserva = reservaRepository.findById(idReserva)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva not exist with id :" + idReserva));

		reserva.setFkIdCliente(reservaDetails.getFkIdCliente());
		reserva.setFkIdPacote(reservaDetails.getFkIdPacote());
		

		Reserva updatedReserva = reservaRepository.save(reserva);
		return ResponseEntity.ok(updatedReserva);
	}

// delete reserva rest api
	@DeleteMapping("/reserva/{idReserva}")
	public ResponseEntity<Map<String, Boolean>> deleteReserva(@PathVariable Long idReserva) {
		Reserva reserva = reservaRepository.findById(idReserva)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva not exist with id :" + idReserva));

		reservaRepository.delete(reserva);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
