package br.com.hackmovile.recpag.repositorios;

import br.com.hackmovile.recpag.entidades.Conta;
import br.com.hackmovile.recpag.entidades.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransacaoRepositorio extends JpaRepository<Transacao, Long> {

    List<Transacao> findAllByOrigemOrDestinoOrderByIdAsc(Conta origem, Conta destino);

}
