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
import com.example.demo.model.Pacote;
import com.example.demo.repository.PacoteRepository;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class PacoteController {
	

	@Autowired
	private PacoteRepository pacoteRepository;

// get all pacote
	@GetMapping("/pacote")
	public List<Pacote> getAllPacotes() {
		return pacoteRepository.findAll();
	}

// create pacote rest api
	@PostMapping("/pacote")
	public Pacote createPacote(@RequestBody Pacote pacote) {
		return pacoteRepository.save(pacote);
	}

// get pacote by id rest api
	@GetMapping("/pacote/{idPacote}")
	public ResponseEntity<Pacote> getPacoteById(@PathVariable Long idPacote) {
		Pacote pacote = pacoteRepository.findById(idPacote)
				.orElseThrow(() -> new ResourceNotFoundException("Pacote not exist with id :" + idPacote));
		return ResponseEntity.ok(pacote);
	}

// update pacote rest api

	@PutMapping("/pacote/{idPacote}")
	public ResponseEntity<Pacote> updatePacote(@PathVariable Long idPacote, @RequestBody Pacote pacoteDetails) {
		Pacote pacote = pacoteRepository.findById(idPacote)
				.orElseThrow(() -> new ResourceNotFoundException("Pacote not exist with id :" + idPacote));

		pacote.setDestino(pacoteDetails.getDestino());
		pacote.setDias(pacoteDetails.getDias());
		pacote.setValor(pacoteDetails.getValor());

		Pacote updatedPacote = pacoteRepository.save(pacote);
		return ResponseEntity.ok(updatedPacote);
	}

// delete pacote rest api
	@DeleteMapping("/pacote/{idPacote}")
	public ResponseEntity<Map<String, Boolean>> deletePacote(@PathVariable Long idPacote) {
		Pacote pacote = pacoteRepository.findById(idPacote)
				.orElseThrow(() -> new ResourceNotFoundException("Pacote not exist with id :" + idPacote));

		pacoteRepository.delete(pacote);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
