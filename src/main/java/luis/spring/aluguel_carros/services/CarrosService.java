package luis.spring.aluguel_carros.services;

import java.util.List;
import org.springframework.stereotype.Service;

import luis.spring.aluguel_carros.exceptions.CarroNaoEncontradoException;
import luis.spring.aluguel_carros.exceptions.PessoaNaoEncontradaException;
import luis.spring.aluguel_carros.models.Carro;
import luis.spring.aluguel_carros.models.Pessoa;
import luis.spring.aluguel_carros.models.dtos.CarroDto;
import luis.spring.aluguel_carros.repository.CarrosRepository;
import luis.spring.aluguel_carros.repository.PessoaRepository;

@Service
public class CarrosService {

    private CarrosRepository carrosRepository;  
    private PessoaRepository pessoaRepository;

    public CarrosService(CarrosRepository carrosRepository, PessoaRepository pessoaRepository) {
        this.carrosRepository = carrosRepository;
        this.pessoaRepository = pessoaRepository;
    }
    public List<Carro> listarTodos() {
        return carrosRepository.findAll();
    }
    public void deletarCarro(long id) {
        carrosRepository.findById(id)
            .orElseThrow(() -> new CarroNaoEncontradoException(id));
        carrosRepository.deleteById(id);
    }
    public Carro cadastrarCarro(CarroDto carro) {
         
        if (carrosRepository.existsByPlaca(carro.placa())) {
            throw new RuntimeException("Já existe um carro cadastrado com esta placa.");
        }

        Pessoa dono = pessoaRepository.findById(carro.donoId())
            .orElseThrow(() -> new PessoaNaoEncontradaException(carro.donoId()));
        Carro novoCarro = new Carro();

        novoCarro.setMarca(carro.marca());
        novoCarro.setModelo(carro.modelo());
        novoCarro.setPreco(carro.preco());
        novoCarro.setCor(carro.cor());
        novoCarro.setPlaca(carro.placa());
        novoCarro.setDono(dono);

        dono.getCarros().add(novoCarro);
        
        novoCarro = carrosRepository.save(novoCarro);
        pessoaRepository.save(dono);
        return novoCarro;
    }
}
