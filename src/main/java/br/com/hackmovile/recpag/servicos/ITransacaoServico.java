package br.com.hackmovile.recpag.servicos;

import br.com.hackmovile.recpag.entidades.Conta;
import br.com.hackmovile.recpag.entidades.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ITransacaoServico {

    Page<Transacao> findAllPageable(PageRequest pageRequest);

    Optional<Transacao> findById(Long id);

    Transacao persist(Transacao transacao);

    void remove(Transacao transacao);

    Optional<List<Transacao>> findAll();

    List<Transacao> findAllByOrigemOrDestinoOrderByIdAsc(Conta origem, Conta destino);

}
