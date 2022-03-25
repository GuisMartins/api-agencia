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
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/cliente")
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	@PostMapping("/cliente")
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long idCliente){
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + idCliente));
			return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/cliente/{idCliente}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long idCliente, @RequestBody Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + idCliente));
		
		cliente.setNome(clienteDetails.getNome());
		cliente.setCpf(clienteDetails.getCpf());
		cliente.setDataNasc(clienteDetails.getDataNasc());
		cliente.setTelefone(clienteDetails.getTelefone());
		cliente.setEmail(clienteDetails.getEmail());
		cliente.setEndereco(clienteDetails.getEndereco());
		
		Cliente updatedCliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(updatedCliente);
		
	}
	
	@DeleteMapping("/cliente/{idCliente}")
	public ResponseEntity<Map<String, Boolean>> deleteCliente(@PathVariable Long idCliente){
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente not exist with id :" + idCliente));
		
		clienteRepository.delete(cliente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	

}
