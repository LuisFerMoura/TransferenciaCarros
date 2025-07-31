package luis.spring.aluguel_carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import luis.spring.aluguel_carros.models.Carro;

public interface CarrosRepository extends JpaRepository<Carro, Long> {
    boolean existsByPlaca(String placa);
}
