package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pacotes")
public class Pacote {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPacote;

	@Column(name = "Destino")
	private String destino;

	@Column(name = "Dias")
	private String dias;
	
	@Column(name = "Valor")
	private String valor;
	
	public Pacote() {
		
	}
		

	public Pacote(long idPacote, String destino, String dias, String valor) {
		super();
		this.idPacote = idPacote;
		this.destino = destino;
		this.dias = dias;
		this.valor = valor;
	}



	public long getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(long idPacote) {
		this.idPacote = idPacote;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}



}
