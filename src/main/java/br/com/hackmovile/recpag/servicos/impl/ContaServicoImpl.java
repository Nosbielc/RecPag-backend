package br.com.hackmovile.recpag.servicos.impl;

import br.com.hackmovile.recpag.entidades.Conta;
import br.com.hackmovile.recpag.repositorios.IContaRepositorio;
import br.com.hackmovile.recpag.servicos.IContaServico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaServicoImpl implements IContaServico {

    private static final Logger log = LoggerFactory.getLogger(ContaServicoImpl.class);

    @Autowired
    private IContaRepositorio contaRepositorio;

    @Override
    public Page<Conta> findAllPageable(PageRequest pageRequest) {
        return this.contaRepositorio.findAll(pageRequest);
    }

    @Override
    public Optional<Conta> findById(Long id) {
        return this.contaRepositorio.findById(id);
    }

    @Override
    public Conta persist(Conta conta) {
        return this.contaRepositorio.save(conta);
    }

    @Override
    public void remove(Conta conta) {
        this.contaRepositorio.delete(conta);
    }

    @Override
    public Optional<List<Conta>> findAll() {
        return Optional.ofNullable(this.contaRepositorio.findAll());
    }
}
