package br.ufc.alu.robertcabral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.alu.robertcabral.entity.ContraIndicacoes;
import br.ufc.alu.robertcabral.entity.EfeitosColaterais;
import br.ufc.alu.robertcabral.entity.Remedio;

public class RemedioDAO {

    public void save(Remedio entity) {
        Connection c = null;

        try {
            c = ConnectionFactory.getConnection();
            String insertsql = "insert into remedio values (?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(insertsql);
            ps.setInt(1, this.findID());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getNome_original());
            ps.setString(4, entity.getTarja());            
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
            String selectsql = "select max(codigo_remedio + 1) from remedio;";
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
    
    public List<ContraIndicacoes> findContraIndicacoes(String nome) {
        List<ContraIndicacoes> list = null;        
        Connection c = null;

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select nome_contra,codigo_remedio from contra where upper(nome_remedio) like upper(?)";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setString(1, '%' + nome + '%');
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while(rs.next()) {
                ContraIndicacoes ci = new ContraIndicacoes();
                ci.setRemedio(findByID(rs.getInt("codigo_remedio")));
                ci.setNome(rs.getString("nome_contra"));                
                list.add(ci);
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
    
    public List<EfeitosColaterais> findEfeitosColaterais(String nome) {
        List<EfeitosColaterais> list = null;       
        Connection c = null;

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select nome_efeito,codigo_remedio from efeitos where upper(nome_remedio) like upper(?)";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setString(1, '%' + nome + '%');
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while(rs.next()) {
                EfeitosColaterais ci = new EfeitosColaterais();
                ci.setRemedio(findByID(rs.getInt("codigo_remedio")));
                ci.setNome(rs.getString("nome_efeito"));                
                list.add(ci);
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
    
    public List<Remedio> findByNome(String nome) {
        List<Remedio> list = null;
        Remedio remedio = null;
        Connection c = null;
        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from remedio where upper(nome) like upper(?)";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setString(1, '%' + nome + '%');
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                remedio = new Remedio();
                remedio.setCodigo_remedio(rs.getInt("codigo_remedio"));
                remedio.setNome(rs.getString("nome"));
                remedio.setNome_original(rs.getString("nome_original"));
                remedio.setTarja(rs.getString("tarja"));
                list.add(remedio);
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
    
    public List<Remedio> findByTarja(int codigo) {
        List<Remedio> list = null;
        Remedio remedio = null;
        Connection c = null;
        String tarja = null;
        
        switch(codigo){
            case 0:
                tarja = "Sem tarja";
                break;
            case 1:
                tarja = "Amarela";
                break;
            case 2:
                tarja = "Vermelha";
                break;
            case 3:
                tarja = "Preta";
                break;
            default:
                return null;
        }

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from remedio where tarja like ?";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setString(1, tarja);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                remedio = new Remedio();
                remedio.setCodigo_remedio(rs.getInt("codigo_remedio"));
                remedio.setNome(rs.getString("nome"));
                remedio.setNome_original(rs.getString("nome_original"));
                remedio.setTarja(rs.getString("tarja"));
                list.add(remedio);
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
    
    public Remedio findByID(int codigo) {
        Remedio remedio = null;
        Connection c = null;
        
        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from remedio where codigo_remedio = ?";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                remedio = new Remedio();
                remedio.setCodigo_remedio(rs.getInt("codigo_remedio"));
                remedio.setNome(rs.getString("nome"));
                remedio.setNome_original(rs.getString("nome_original"));
                remedio.setTarja(rs.getString("tarja"));
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
        return remedio;
    }
    
    public void delete(int codigo) {
        Connection c = null;

        try {
            c = ConnectionFactory.getConnection();
            String deletesql = "delete from remedio where codigo_remedio = ?";
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
