package luis.spring.aluguel_carros.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import luis.spring.aluguel_carros.models.Carro;
import luis.spring.aluguel_carros.models.dtos.CarroDto;
import luis.spring.aluguel_carros.services.CarrosService;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarrosService carroService;
   
    public CarroController(CarrosService carroService) {
        this.carroService = carroService;
    }
    
    @GetMapping("/carros")
    public ResponseEntity<List<Carro>> listarCarros() {
        List<Carro> carros = carroService.listarTodos();
        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
    return ResponseEntity.ok(carros); 
    }


    @PostMapping
    public ResponseEntity<?> cadastrarCarro(@RequestBody CarroDto carro) {
        try {
            Carro novoCarro = carroService.cadastrarCarro(carro);
            return ResponseEntity.ok(novoCarro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable long id) {
        try {
            carroService.deletarCarro(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
     }
}

}       
