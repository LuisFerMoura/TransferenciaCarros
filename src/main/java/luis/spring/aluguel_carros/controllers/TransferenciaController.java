package luis.spring.aluguel_carros.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import luis.spring.aluguel_carros.models.dtos.TransferenciaDto;
import luis.spring.aluguel_carros.services.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<String> transferir(@RequestBody TransferenciaDto transferenciaDto) {
        try {
            transferenciaService.transferirCarro(transferenciaDto.getNovoDonoId(), transferenciaDto.getCarroId());
            return ResponseEntity.ok("Transferência realizada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
