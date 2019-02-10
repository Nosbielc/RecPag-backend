package br.com.hackmovile.recpag.repositorios;

import br.com.hackmovile.recpag.entidades.Conta;
import br.com.hackmovile.recpag.entidades.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITransacaoRepositorio extends JpaRepository<Transacao, Long> {

    List<Transacao> findAllByOrigemOrDestinoOrderByIdAsc(Conta origem, Conta destino);

    Page<Transacao> findAllByOrigemOrDestinoOrderByIdAsc(Conta origem, Conta destino, Pageable pageable);

    @Query(value = "SELECT (SELECT SUM(VLR_TRANSACAO) FROM TRANSACAO WHERE DESTINO_ID = ?1 AND TRANSACAO_STATUS = 3 AND TRANSACAO_TIPO = 0) - (SELECT SUM(VLR_TRANSACAO) FROM TRANSACAO WHERE ORIGEM_ID = ?1 AND TRANSACAO_STATUS = 3 AND TRANSACAO_TIPO = 0) FROM DUAL", nativeQuery = true)
    Float saldoConta(Long idConta);

}
