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
public class Chat {
    private Cliente paciente;
    private Cliente saude;

    public Chat(Cliente paciente, Cliente saude) {
        this.paciente = paciente;
        this.saude = saude;
    }

    public Chat() {
    }

    public Cliente getPaciente() {
        return paciente;
    }

    public void setPaciente(Cliente paciente) {
        this.paciente = paciente;
    }

    public Cliente getSaude() {
        return saude;
    }

    public void setSaude(Cliente saude) {
        this.saude = saude;
    }
    
    
    
}
