package com.agenda.MODEL;

public class Usuario {
    private int id;
    private String email;
    private String senha;

    public Usuario(){super();}
    public Usuario(int id, String email, String senha){
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {return this.id;}
    public String getEmail() {return this.email;}
    public String getSenha() {return this.senha;}

    public void setId(int id) {this.id = id;}
    public void setEmail(String email) {this.email = email;}
    public void setSenha(String senha) {this.senha = senha;}
}
