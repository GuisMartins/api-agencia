package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reservas")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReserva;
	
	@ManyToOne
	private Cliente fkIdCliente;
	
	@ManyToOne
	private Pacote fkIdPacote;
	
	
	public Reserva() {
		
	}


	public Reserva(long idReserva, Cliente fkIdCliente, Pacote fkIdPacote) {
		super();
		this.idReserva = idReserva;
		this.fkIdCliente = fkIdCliente;
		this.fkIdPacote = fkIdPacote;
	}


	public long getIdReserva() {
		return idReserva;
	}


	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
	}


	public Cliente getFkIdCliente() {
		return fkIdCliente;
	}


	public void setFkIdCliente(Cliente fkIdCliente) {
		this.fkIdCliente = fkIdCliente;
	}


	public Pacote getFkIdPacote() {
		return fkIdPacote;
	}


	public void setFkIdPacote(Pacote fkIdPacote) {
		this.fkIdPacote = fkIdPacote;
	}
	
	

}
