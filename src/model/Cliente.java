/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Maths
 */
public class Cliente {
    private String nome;
    private String tipo;
    private String ipcliente;
    private int porta;

    @Override
    public String toString() {
        return "Cliente{" + "nome: " + nome + ", tipo: " + tipo + ", ipcliente: " + ipcliente + ", porta: " + porta + '}';
    }

    public Cliente(String nome, String tipo, String ipcliente, int porta) {
        this.nome = nome;
        this.tipo = tipo;
        this.ipcliente = ipcliente;
        this.porta = porta;
    }

    public Cliente() {
        
    }
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIpcliente() {
		return ipcliente;
	}
	public void setIpcliente(String ipcliente) {
		this.ipcliente = ipcliente;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
    
}
