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

    @Column(name = "str_telefone", nullable = false)
    private String telefone;

    public Conta() {
    }

    public Conta(String nome, String chave, String chaveVendedor, String chaveComprador, String telefone) {
        this.nome = nome;
        this.chave = chave;
        this.chaveVendedor = chaveVendedor;
        this.chaveComprador = chaveComprador;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Conta.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nome='" + nome + "'")
                .add("chave='" + chave + "'")
                .add("chaveVendedor='" + chaveVendedor + "'")
                .add("chaveComprador='" + chaveComprador + "'")
                .add("telefone='" + telefone + "'")
                .toString();
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

    public String getChaveVendedor() {
        return chaveVendedor;
    }

    public void setChaveVendedor(String chaveVendedor) {
        this.chaveVendedor = chaveVendedor;
    }

    public String getChaveComprador() {
        return chaveComprador;
    }

    public void setChaveComprador(String chaveComprador) {
        this.chaveComprador = chaveComprador;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
