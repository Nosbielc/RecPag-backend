package br.com.hackmovile.recpag.cliente;

import br.com.hackmovile.recpag.cliente.dto.WavyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient("http://messaging-api.wavy.global:8080/v1/sms")
public interface IClienteWavy {

    @PostMapping("/send")
    Object sendSms(@RequestHeader(value = "Access-key", name = "ek-12548965369") String accessKey,
                 @RequestHeader(value = "Accept",  name = "application/json") String accept,
                 @RequestHeader(value = "Content-Type",  name = "application/json") String contentType,
                 @RequestBody WavyDto wavyDto);
}
