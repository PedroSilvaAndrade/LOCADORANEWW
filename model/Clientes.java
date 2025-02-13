package model;

public class Clientes {
    private String id_cliente;
    private String nome;
    private String numero_cnh;
    private int idade;
    private char sexo;

    public Clientes(String id_cliente, String nome, String numero_cnh, int idade, char sexo){
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.numero_cnh = numero_cnh;
        this.idade = idade;
        this.sexo = sexo;
    }
}
