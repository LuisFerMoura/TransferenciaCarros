package luis.spring.aluguel_carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import luis.spring.aluguel_carros.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
