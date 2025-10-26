package com.agenda.MODEL;

import java.sql.*;
import java.util.ArrayList;
import io.github.cdimascio.dotenv.Dotenv;

public class DAO {
    Dotenv dotenv = Dotenv.load();
    public Connection conectar(){
        Connection conn = null;
        try{
            Class.forName(dotenv.get("DRIVER"));
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://"+dotenv.get("HOST")+":"+dotenv.get(
                            "PORT")+"/"+dotenv.get("NOME_BANCO"),dotenv.get(
                                    "USER"),dotenv.get("PASSWORD")
            );
        }catch(ClassNotFoundException | SQLException sql){
            sql.printStackTrace();
        }finally {
            return conn;
        }
    }
    public void desconectar(Connection conn){
        try{
            if(conn!= null && !conn.isClosed()) {
                conn.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }

//    Métodos do CRUD
//    CRUD - inserirContat
    public void inserirContato(JavaBeans contato){
        String sql = "INSERT INTO contatos (nome, fone, email) VALUES (?,?,?)";
        Connection conn = null;
        try{
            conn = conectar();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,contato.getNome());
            pstmt.setString(2,contato.getFone());
            pstmt.setString(3,contato.getEmail());

            pstmt.executeUpdate();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally {
            desconectar(conn);
        }
    }

//    CRUD - listarContatos
    public ArrayList<JavaBeans> listarContatos(){
        ArrayList<JavaBeans> lista = new ArrayList<>();
        Connection conn = null;
        try{
            conn = conectar();

            String sql = "SELECT * FROM contatos ORDER BY nome";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int idcon = rs.getInt("idcon");
                String nome = rs.getString("nome");
                String fone = rs.getString("fone");
                String email = rs.getString("email");

                JavaBeans javaBeans = new JavaBeans(idcon,nome,fone,email);

                lista.add(javaBeans);
            }
            return lista;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return lista;
        }finally {
            desconectar(conn);
        }
    }

    public void selecionarContato(JavaBeans contato){
        Connection conn = null;
        try{
            conn = conectar();
            String sql = "SELECT * FROM contatos WHERE idcon=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,contato.getIdcon());
            ResultSet rs = pstmt.executeQuery(sql);
            while(rs.next()){
                contato.setIdcon(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setFone(rs.getString(3));
                contato.setEmail(rs.getString(4));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally {
            desconectar(conn);
        }
    }

}
