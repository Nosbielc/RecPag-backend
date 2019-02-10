package br.com.hackmovile.recpag.servicos;

import br.com.hackmovile.recpag.entidades.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface IContaServico {

    Page<Conta> findAllPageable(PageRequest pageRequest);

    Optional<Conta> findById(Long id);

    Conta persist(Conta conta);

    void remove(Conta conta);

    Optional<List<Conta>> findAll();

}
