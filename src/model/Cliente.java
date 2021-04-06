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
