package br.com.hackmovile.recpag.client;

import br.com.hackmovile.recpag.client.dto.ExemploDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("http://10.53.53.63:8080")
public interface ClientZoop {

    @PostMapping("/transacaoInterna/depositar/{cpf}")
    Object depositar(@PathVariable(value = "cpf") String cpf, @RequestBody ExemploDto exemploDto);

}
