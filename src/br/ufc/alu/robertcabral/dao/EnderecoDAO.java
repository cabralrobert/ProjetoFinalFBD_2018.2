package br.ufc.alu.robertcabral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.alu.robertcabral.entity.Enderecos;

public class EnderecoDAO {

    public void save(Enderecos entity) {
        Connection c = null;

        try {
            c = ConnectionFactory.getConnection();
            String insertsql = "insert into enderecos(uf,cidade,bairro,rua,numero,cep) values (?,?,?,?,?,?)";
            String updatesql = "update enderecos set uf=?, cidade=?, bairro=?, rua=?, numero=?, cep=? where codigo_endereco= ?";
            PreparedStatement ps;            
            
            if(entity.getCodigo() == 0){
                ps = c.prepareStatement(insertsql);
            }else{
                ps = c.prepareStatement(updatesql);
                ps.setInt(7, entity.getCodigo());
            }
            
            ps.setString(1, entity.getUf());
            ps.setString(2, entity.getCidade());
            ps.setString(3, entity.getBairro());
            ps.setString(4, entity.getRua());
            ps.setInt(5, entity.getNumero());
            ps.setString(6, entity.getCep());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int findID() {
        int max = 0;
        Connection c = null;

        try {
            c = ConnectionFactory.getConnection();
            String selectsql = "select max(codigo_endereco) from enderecos;";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt("max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return max;
    }

    public Enderecos findByID(int codigo) {
        Enderecos endereco = null;
        Connection c = null;

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from enderecos where codigo_endereco = ?";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                endereco = new Enderecos();
                endereco.setCodigo(rs.getInt("codigo_endereco"));
                endereco.setUf(rs.getString("uf"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setCep(rs.getString("cep"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return endereco;
    }
    
    public List<Enderecos> findByNome(String rua) {
        List<Enderecos> list = null;        
        Connection c = null;

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from enderecos where upper(rua) like upper(?)";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setString(1, '%' + rua + '%');
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while(rs.next()) {
                Enderecos enderecos = new Enderecos();
                enderecos.setCodigo(rs.getInt("codigo_endereco"));
                enderecos.setUf(rs.getString("uf"));
                enderecos.setCidade(rs.getString("cidade"));
                enderecos.setBairro(rs.getString("bairro"));
                enderecos.setRua(rs.getString("rua"));
                enderecos.setNumero(rs.getInt("numero"));
                enderecos.setCep(rs.getString("cep"));
                list.add(enderecos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public void delete(int codigo) {
        Connection c = null;

        try {
            c = ConnectionFactory.getConnection();
            String deletesql = "delete from enderecos where codigo_endereco = ?";
            PreparedStatement ps = c.prepareStatement(deletesql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
