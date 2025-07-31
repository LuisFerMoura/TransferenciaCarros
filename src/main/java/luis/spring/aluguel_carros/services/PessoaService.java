package luis.spring.aluguel_carros.services;

import java.util.List;


import org.springframework.stereotype.Service;

import luis.spring.aluguel_carros.exceptions.PessoaNaoEncontradaException;
import luis.spring.aluguel_carros.models.Pessoa;
import luis.spring.aluguel_carros.repository.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        try {
            return pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao cadastrar pessoa: " + e.getMessage());
        }  
    }

    public void deletarPessoa(long id) {
        pessoaRepository.findById(id)
            .orElseThrow(() -> new PessoaNaoEncontradaException(id));
        pessoaRepository.deleteById(id);
            
    }

    public Pessoa buscarPessoaPorId(long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new PessoaNaoEncontradaException(id));
    }
    
}
