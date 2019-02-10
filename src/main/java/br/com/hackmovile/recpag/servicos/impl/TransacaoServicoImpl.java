package br.com.hackmovile.recpag.servicos.impl;

import br.com.hackmovile.recpag.entidades.Conta;
import br.com.hackmovile.recpag.entidades.Transacao;
import br.com.hackmovile.recpag.repositorios.ITransacaoRepositorio;
import br.com.hackmovile.recpag.servicos.ITransacaoServico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoServicoImpl implements ITransacaoServico {

    private static final Logger log = LoggerFactory.getLogger(ContaServicoImpl.class);

    @Autowired
    private ITransacaoRepositorio transacaoRepositorio;

    @Override
    public Page<Transacao> findAllPageable(PageRequest pageRequest) {
        return this.transacaoRepositorio.findAll(pageRequest);
    }

    @Override
    public Optional<Transacao> findById(Long id) {
        return this.transacaoRepositorio.findById(id);
    }

    @Override
    public Transacao persist(Transacao transacao) {
        return this.transacaoRepositorio.save(transacao);
    }

    @Override
    public void remove(Transacao transacao) {
        this.transacaoRepositorio.delete(transacao);
    }

    @Override
    public Optional<List<Transacao>> findAll() {
        return Optional.ofNullable(this.transacaoRepositorio.findAll());
    }

    @Override
    public List<Transacao> findAllByOrigemOrDestinoOrderByIdAsc(Conta origem, Conta destino) {
        return this.transacaoRepositorio.findAllByOrigemOrDestinoOrderByIdAsc(origem, destino);
    }

}
