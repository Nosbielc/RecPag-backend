package br.com.hackmovile.recpag.repositorios;

import br.com.hackmovile.recpag.entidades.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContaRepositorio extends JpaRepository<Conta, Long> {

    Conta findByChave(String chave);
}
