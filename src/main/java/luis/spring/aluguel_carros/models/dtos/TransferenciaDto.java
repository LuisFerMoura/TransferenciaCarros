package luis.spring.aluguel_carros.models.dtos;

public class TransferenciaDto {
    
    private long carroId;
    private long novoDonoId;

    public long getCarroId() {
        return carroId;
    }

    public void setCarroId(long carroId) {
        this.carroId = carroId;
    }

    public long getNovoDonoId() {
        return novoDonoId;
    }

    public void setNovoDonoId(long novoDonoId) {
        this.novoDonoId = novoDonoId;
    }
}
