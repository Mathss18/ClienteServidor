/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rafae
 */
public class Hospital {
    
    private int id;
    private String nome;
    private String endereco;
    private int vagas;

    public Hospital() {
    }

    public Hospital(String nome, String endereco, int vagas) {
        this.nome = nome;
        this.endereco = endereco;
        this.vagas = vagas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    @Override
    public String toString() {
        return "Hospital{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", vagas=" + vagas + '}';
    }
    
    
}
