package com.alexdev.transitofacil.domain.repository;

import com.alexdev.transitofacil.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository  extends JpaRepository<Proprietario, Long> {
}
