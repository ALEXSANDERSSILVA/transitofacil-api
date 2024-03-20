package com.alexdev.transitofacil.domain.service;

import com.alexdev.transitofacil.domain.exception.NegocioException;
import com.alexdev.transitofacil.domain.model.Proprietario;
import com.alexdev.transitofacil.domain.model.StatusVeiculo;
import com.alexdev.transitofacil.domain.model.Veiculo;
import com.alexdev.transitofacil.domain.repository.ProprietarioRepository;
import com.alexdev.transitofacil.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Repository
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veiculo a ser cadastrado não deve possuir um código");

        }
        boolean placaEmUso =  veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo)).isPresent();

        if (placaEmUso) {
            throw new NegocioException("Já Existe um veiculo cadastrado com esta placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }


}
