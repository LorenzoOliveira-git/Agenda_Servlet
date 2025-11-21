package com.agenda.MODEL;

public class JavaBeans {
    private int idcon;
    private String nome;
    private String fone;
    private String email;

//    Construtores
    public JavaBeans(){
        super();
    }
    public JavaBeans(int idcon, String nome, String fone, String email){
        this.idcon = idcon;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
    }

//    Getters e Setters
    public int getIdcon() {
        return idcon;
    }
    public String getNome() {
        return nome;
    }
    public String getFone() {
        return fone;
    }
    public String getEmail() {
        return email;
    }

    public void setIdcon(int idcon) {
        this.idcon = idcon;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "JavaBeans{" +
                "idcon=" + idcon +
                ", nome='" + nome + '\'' +
                ", fone='" + fone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
