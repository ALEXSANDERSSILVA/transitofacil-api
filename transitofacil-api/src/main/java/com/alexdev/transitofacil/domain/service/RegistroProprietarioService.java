package com.alexdev.transitofacil.domain.service;

import com.alexdev.transitofacil.domain.exception.NegocioException;
import com.alexdev.transitofacil.domain.model.Proprietario;
import com.alexdev.transitofacil.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean emailEmUso =  proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario)).isPresent();

        if (emailEmUso) {
            throw new NegocioException("Já existe um proprietário cadastrado com este e-mail");
        }

        return proprietarioRepository.save(proprietario);
    }
}
