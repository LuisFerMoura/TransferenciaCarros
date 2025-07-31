package luis.spring.aluguel_carros.services;

import org.springframework.stereotype.Service;

import luis.spring.aluguel_carros.exceptions.CarroNaoEncontradoException;
import luis.spring.aluguel_carros.exceptions.PessoaNaoEncontradaException;
import luis.spring.aluguel_carros.exceptions.SaldoInsuficienteException;
import luis.spring.aluguel_carros.models.Carro;
import luis.spring.aluguel_carros.models.Pessoa;
import luis.spring.aluguel_carros.repository.CarrosRepository;
import luis.spring.aluguel_carros.repository.PessoaRepository;

@Service
public class TransferenciaService {
    
    private final CarrosRepository carrosRepository;
    private final PessoaRepository pessoaRepository;

    public TransferenciaService(CarrosRepository carrosRepository, PessoaRepository pessoaRepository) {
        this.carrosRepository = carrosRepository;
        this.pessoaRepository = pessoaRepository;
    }
   
    public void transferirCarro(long novoDonoId, long carroId) {
        Carro carro = carrosRepository.findById(carroId)
            .orElseThrow(() -> new CarroNaoEncontradoException(carroId));
        Pessoa novoDono = pessoaRepository.findById(novoDonoId)
            .orElseThrow(() -> new PessoaNaoEncontradaException(novoDonoId));

        long antigoDonoId = carro.getDono().getId();  
        if(antigoDonoId == novoDonoId) {
            throw new IllegalArgumentException("Não é possível transferir o carro para o mesmo dono.");
        }
        // Busca o antigo dono
        Pessoa antigoDono = pessoaRepository.findById(antigoDonoId)
            .orElseThrow(() -> new PessoaNaoEncontradaException(antigoDonoId));
       
        // Verifica se o novo dono tem saldo suficiente para a transferência
        double precoCarro = carro.getPreco();
        double saldoNovoDono = novoDono.getSaldo();
        double saldoAntigoDono = antigoDono.getSaldo();
        String nomeNovoDono = novoDono.getNome();
        if (saldoNovoDono < precoCarro) { 
            throw new SaldoInsuficienteException(nomeNovoDono, precoCarro);
        }
        if (antigoDono.getCarros() != null) {
        antigoDono.getCarros().remove(carro);
        novoDono.getCarros().add(carro);
        }
      
        // Atualiza o saldo dos donos
        novoDono.setSaldo(saldoNovoDono - precoCarro);
        antigoDono.setSaldo(saldoAntigoDono + precoCarro);
        // Atualiza o dono do carro
        carro.setDono(novoDono);    
        // Salva as alterações
        carrosRepository.save(carro);   
        pessoaRepository.save(antigoDono);
        pessoaRepository.save(novoDono);
    }
}
