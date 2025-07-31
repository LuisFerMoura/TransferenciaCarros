package luis.spring.aluguel_carros.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.Collections;
import luis.spring.aluguel_carros.models.Carro;
import luis.spring.aluguel_carros.models.Pessoa;
import luis.spring.aluguel_carros.repository.CarrosRepository;
import luis.spring.aluguel_carros.repository.PessoaRepository;

@Component
public class DataLoader {

    private final PessoaRepository pessoaRepository;
    private final CarrosRepository carrosRepository;

    public DataLoader(PessoaRepository pessoaRepository, CarrosRepository carrosRepository) {
        this.pessoaRepository = pessoaRepository;
        this.carrosRepository = carrosRepository;
    }

    @PostConstruct
    public void carregarDadosIniciais() {
        if (pessoaRepository.count() == 0 && carrosRepository.count() == 0) {
            Pessoa pessoa1 = new Pessoa();
            pessoa1.setNome("João");
            pessoa1.setSaldo(50000.00);

            Pessoa pessoa2 = new Pessoa();
            pessoa2.setNome("Maria");
            pessoa2.setSaldo(60000.00);

            pessoaRepository.save(pessoa1);
            pessoaRepository.save(pessoa2);

            Carro carro = new Carro();
            carro.setModelo("Civic");
            carro.setPreco(70000.00);
            carro.setDono(pessoa1);

            carrosRepository.save(carro);

            pessoa1.setCarros(Collections.singletonList(carro));
            pessoaRepository.save(pessoa1);

            System.out.println(">>> Dados iniciais populados com sucesso.");
        }
    }
}
