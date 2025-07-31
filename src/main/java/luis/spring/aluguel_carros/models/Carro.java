package luis.spring.aluguel_carros.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "carros")
public class Carro {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;    
    private double preco;
    private String modelo;
    private String marca;
    private String cor;
    @Column(unique = true)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_dono")
    @JsonBackReference
    private Pessoa dono;

    public Carro() {
    }

    public Carro(String modelo, String marca, String cor, String placa, Pessoa dono, double preco) {
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.placa = placa;
        this.dono = dono;
        this.preco = preco; 
    }
// Getters and Setters

    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getPlaca() {
        return placa;
    }     
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Pessoa getDono() {
        return dono;
    }
    public void setDono(Pessoa dono) {
        this.dono = dono;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }   
} 
