package br.com.hackmovile.recpag.entidades;

import br.com.hackmovile.recpag.enums.TransacaoStatus;
import br.com.hackmovile.recpag.enums.TransacaoTipo;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "transacao")
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vlr_transacao", nullable = false, precision = 2)
    private Float valorTransacao;

    @Column(name = "dt_transacao", nullable = false)
    @CreatedBy
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoratransacao;

    @ManyToOne
    private Conta origem;

    @ManyToOne
    private Conta destino;

    @Enumerated(EnumType.ORDINAL)
    private TransacaoStatus transacaoStatus;

    @Enumerated(EnumType.ORDINAL)
    private TransacaoTipo transacaoTipo;

    @Override
    public String toString() {
        return new StringJoiner(", ", Transacao.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("valorTransacao=" + valorTransacao)
                .add("dataHoratransacao=" + dataHoratransacao)
                .add("origem=" + origem)
                .add("destino=" + destino)
                .add("transacaoStatus=" + transacaoStatus)
                .add("transacaoTipo=" + transacaoTipo)
                .toString();
    }

    public Transacao(Float valorTransacao, Date dataHoratransacao, Conta origem, Conta destino,
                     TransacaoStatus transacaoStatus, TransacaoTipo transacaoTipo) {
        this.valorTransacao = valorTransacao;
        this.dataHoratransacao = dataHoratransacao;
        this.origem = origem;
        this.destino = destino;
        this.transacaoStatus = transacaoStatus;
        this.transacaoTipo = transacaoTipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(Float valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public Date getDataHoratransacao() {
        return dataHoratransacao;
    }

    public void setDataHoratransacao(Date dataHoratransacao) {
        this.dataHoratransacao = dataHoratransacao;
    }

    public Conta getOrigem() {
        return origem;
    }

    public void setOrigem(Conta origem) {
        this.origem = origem;
    }

    public Conta getDestino() {
        return destino;
    }

    public void setDestino(Conta destino) {
        this.destino = destino;
    }

    public TransacaoStatus getTransacaoStatus() {
        return transacaoStatus;
    }

    public void setTransacaoStatus(TransacaoStatus transacaoStatus) {
        this.transacaoStatus = transacaoStatus;
    }

    public TransacaoTipo getTransacaoTipo() {
        return transacaoTipo;
    }

    public void setTransacaoTipo(TransacaoTipo transacaoTipo) {
        this.transacaoTipo = transacaoTipo;
    }
}