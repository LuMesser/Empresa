/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Empresa;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class DaoEmpresa {
     public static boolean inserir(Empresa objeto) {
        String sql = "INSERT INTO empresa (nomefantasia, razaosocial, dtfundacao, hrabertura) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNomefantasia());
            ps.setString(2, objeto.getRazaosocial());
            ps.setString(3, objeto.getDtfundacao());
            ps.setString(4, objeto.getHrabertura());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static void main(String[] args) {
        Empresa objeto = new Empresa();
        objeto.setNomefantasia("Brandt Store");
        objeto.setRazaosocial("Brandt Store");
        objeto.setDtfundacao("10/12/2003");
        objeto.setHrabertura("8:00");
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
       public static boolean alterar(Empresa objeto) {
        String sql = "UPDATE empresa SET nomefantasia = ?, razaosocial = ?, dtfundacao = ?, hrabertura = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNomefantasia()); 
            ps.setString(2, objeto.getRazaosocial());
            ps.setString(3, objeto.getDtfundacao());
            ps.setString(4, objeto.getHrabertura());
            ps.setInt(5, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static boolean excluir(Empresa objeto) {
        String sql = "DELETE FROM empresa WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
       public static List<Empresa> consultar() {
        List<Empresa> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nomefantasia, razaosocial, dtfundacao, hrabertura FROM empresa";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empresa objeto = new Empresa();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNomefantasia(rs.getString("nomefantasia"));
                objeto.setRazaosocial(rs.getString("razaosocial"));
                objeto.setDtfundacao(rs.getString("dtfundacao"));
                objeto.setHrabertura(rs.getString("hrabertura"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
       public static Empresa consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nomefantasia, razaosocial, dtfundacao, hrabertura FROM empresa WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empresa objeto = new Empresa();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNomefantasia(rs.getString("nomefantasia"));
                objeto.setRazaosocial(rs.getString("razaosocial"));
                objeto.setDtfundacao(rs.getString("dtfundacao"));
                objeto.setHrabertura(rs.getString("hrabertura"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
