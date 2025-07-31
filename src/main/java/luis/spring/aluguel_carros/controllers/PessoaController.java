package luis.spring.aluguel_carros.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import luis.spring.aluguel_carros.models.Carro;
import luis.spring.aluguel_carros.models.Pessoa;
import luis.spring.aluguel_carros.services.PessoaService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity <List<Pessoa>> listarTodos() {
        return ResponseEntity.ok(pessoaService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.cadastrarPessoa(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable long id) {
        return ResponseEntity.ok(pessoaService.buscarPessoaPorId(id));
    }

    @GetMapping("/{id}/carros")
    public ResponseEntity<List<Carro>> listarCarrosPorPessoa(@PathVariable long id) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        List<Carro> carros = pessoa.getCarros();
        return ResponseEntity.ok(carros);
    }
}
