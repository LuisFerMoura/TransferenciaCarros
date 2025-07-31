package luis.spring.aluguel_carros.models.dtos;

public record CarroDto(
    String marca,
    String modelo,
    double preco,
    String cor,
    String placa,
    long donoId
) {}
