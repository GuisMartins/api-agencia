package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pacote;

@Repository
public interface PacoteRepository extends JpaRepository<Pacote, Long>{

}
