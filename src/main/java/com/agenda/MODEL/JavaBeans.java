package com.agenda.MODEL;

public class JavaBeans {
    private String idcon;
    private String nome;
    private String fone;
    private String email;

//    Construtores
    public JavaBeans(){
        super();
    }
    public JavaBeans(String idcon, String nome, String fone, String email){
        this.idcon = idcon;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
    }

//    Getters e Setters
    public String getIdcon() {
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

    public void setIdcon(String idcon) {
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
}
