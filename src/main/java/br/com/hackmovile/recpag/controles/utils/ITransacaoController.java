package br.com.hackmovile.recpag.controles.utils;

import br.com.hackmovile.recpag.dtos.PagamentoDto;
import br.com.hackmovile.recpag.dtos.RecebimentoDto;
import br.com.hackmovile.recpag.dtos.TransacaoDto;
import br.com.hackmovile.recpag.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ITransacaoController {

    @ApiOperation(value = "Pagamento", nickname = "Pagamento", notes = "Realiza um pagamento.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = PagamentoDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<PagamentoDto>> pagamento(@Valid @RequestBody PagamentoDto pagamentoDto) throws Exception;

    @ApiOperation(value = "Recebimento", nickname = "Recebimento", notes = "Realiza um recebimento.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = RecebimentoDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<RecebimentoDto>> recebimento(@Valid @RequestBody RecebimentoDto recebimentoDto)
            throws Exception;

    @ApiOperation(value = "Lista de transacoes", nickname = "Transacaoes", notes = "Lista as transacoes do usuario.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "List", response = TransacaoDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Page<TransacaoDto>>> listar(@RequestParam(value = "pag", defaultValue = "0") Integer pag,
                                                        @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                        @RequestParam(value = "dir", defaultValue = "DESC") String dir,
                                                        @RequestParam(value = "conta", defaultValue = "") String conta)
            throws Exception;

}
