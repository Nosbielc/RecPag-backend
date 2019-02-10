package br.com.hackmovile.recpag.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "str_nome", nullable = false)
    private String nome;

    @Column(name = "str_chave", nullable = false)
    private String chave;

    @Column(name = "str_chave_vendedor", nullable = false)
    private String chaveVendedor;

    @Column(name = "str_chave_comprador", nullable = false)
    private String chaveComprador;

    @Override
    public String toString() {
        return new StringJoiner(", ", Conta.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nome='" + nome + "'")
                .add("chave='" + chave + "'")
                .toString();
    }

    public Conta(String nome, String chave) {
        this.nome = nome;
        this.chave = chave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
}
