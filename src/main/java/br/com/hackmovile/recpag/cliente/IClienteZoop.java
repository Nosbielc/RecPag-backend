package br.com.hackmovile.recpag.cliente;

import br.com.hackmovile.recpag.cliente.dto.TransacaoZoop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@FeignClient("https://api.zoop.ws/v1")
public interface IClienteZoop {

    @PostMapping("/marketplaces/3249465a7753536b62545a6a684b0000/....")
    Object sacar(@RequestHeader("Authorization") String token, @RequestBody TransacaoZoop transacaoZoop);

}
