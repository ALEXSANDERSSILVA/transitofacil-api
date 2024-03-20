package com.alexdev.transitofacil.domain.repository;

import com.alexdev.transitofacil.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprietarioRepository  extends JpaRepository<Proprietario, Long> {

//    List<Proprietario> findByNomeContainer(String nome);
    Optional<Proprietario> findByEmail(String nome);


}
