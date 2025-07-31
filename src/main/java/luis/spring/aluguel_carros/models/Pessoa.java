package luis.spring.aluguel_carros.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;    

    @PositiveOrZero(message = "Saldo não pode ser negativo")
    @Column(nullable = false)
    private double saldo;
    @NotBlank(message = "Nome não pode ser vazio")
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Carro> carros = new ArrayList<>();
    
    public Pessoa() {
    }

    public Pessoa(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }
    //getters and setters   
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Carro> getCarros() {
        return carros;
    }
    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public void adicionarCarro(Carro carro) {
    carros.add(carro);
    carro.setDono(this);  
    }
    public void removerCarro(Carro carro) {
    carros.remove(carro);
    carro.setDono(null);
    }
}
