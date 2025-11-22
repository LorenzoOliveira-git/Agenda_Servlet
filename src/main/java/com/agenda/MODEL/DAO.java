package com.agenda.MODEL;

import java.sql.*;
import java.util.ArrayList;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpSession;

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

    public void verificarLogin(Usuario usuario){
        Connection conn = null;
        String senha = usuario.getSenha();
        String email = usuario.getEmail();
        try{
            conn = conectar();

            String sql = "SELECT * FROM usuarios";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getString("email").equals(email) && rs.getString("senha").equals(senha)){
                    usuario.setId(rs.getInt("id"));
                }
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally {
            desconectar(conn);
        }
    }

//    Métodos do CRUD
//    CRUD - adicionar conta
    public void inserirConta(Usuario usuario){
        Connection conn = null;
        String email = usuario.getEmail();
        String senha = usuario.getSenha();
        try{
            conn = conectar();

            String inserir = "INSERT INTO usuarios(email, senha) VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(inserir);
            pstmt.setString(1,email);
            pstmt.setString(2,senha);
            pstmt.execute();

            String pegarID = "SELECT * FROM usuarios";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(pegarID);
            while(rs.next()){
                if(rs.getString("email").equals(email) && rs.getString("senha").equals(senha)){
                    usuario.setId(rs.getInt("id"));
                }
            }
        }catch(SQLException sql){
            sql.printStackTrace();
        }finally {
            desconectar(conn);
        }
    }

//    CRUD - inserirContato
    public void inserirContato(Contato contato){
        String sql = "INSERT INTO contatos (id_usuario,nome, fone, email) " +
                "VALUES (?,?,?,?)";
        Connection conn = null;
        try{
            conn = conectar();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,contato.getIdusuario());
            pstmt.setString(2,contato.getNome());
            pstmt.setString(3,contato.getFone());
            pstmt.setString(4,contato.getEmail());

            pstmt.executeUpdate();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally {
            desconectar(conn);
        }
    }

//    CRUD - listarContatos
    public ArrayList<Contato> listarContatos(Contato contato){
        ArrayList<Contato> lista = new ArrayList<>();
        Connection conn = null;
        int idusuario = contato.getIdusuario();
        try{
            conn = conectar();

            String sql = "SELECT * FROM contatos WHERE id_usuario = ? ORDER " +
                    "BY nome ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idusuario);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int idcon = rs.getInt("idcon");
                String nome = rs.getString("nome");
                String fone = rs.getString("fone");
                String email = rs.getString("email");

                Contato javaBeans = new Contato(idcon,nome,fone,
                        email);

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

    public void selecionarContato(Contato contato){
        Connection conn = null;
        try{
            conn = conectar();
            String sql = "SELECT * FROM contatos WHERE idcon = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,contato.getIdcon());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                contato.setIdcon(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setFone(rs.getString(3));
                contato.setEmail(rs.getString(4));
            }
            System.out.println(contato);
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally {
            desconectar(conn);
        }
    }

    public void modificarContato(Contato contato){
        Connection conn = null;
        String sql = "UPDATE contatos SET nome=?, fone=?, email=? WHERE " +
                "idcon=?";
        try{
            conn = conectar();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,contato.getNome());
            pstmt.setString(2,contato.getFone());
            pstmt.setString(3,contato.getEmail());
            pstmt.setInt(4,contato.getIdcon());

            pstmt.executeUpdate();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally {
            desconectar(conn);
        }
    }

    public void deletarContato(Contato contato){
        Connection conn = null;
        String sql = "DELETE FROM contatos WHERE idcon=?";
        try{
            conn = conectar();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,contato.getIdcon());

            pstmt.executeUpdate();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            desconectar(conn);
        }
    }
}
