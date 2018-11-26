package br.ufc.alu.robertcabral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.alu.robertcabral.entity.Medico;

public class MedicoDAO {

    public void save(Medico entity) {
        Connection c = null;

        try {

            if (new EnderecoDAO().findByID(entity.getEndereco().getCodigo()) == null) {
                System.out.println("Não existe o endereço");
                return;
            }

            c = ConnectionFactory.getConnection();
            String insertsql = "insert into medico(codigo_endereco,nome,cpf,data_nasc,telefone,crm) values (?,?,?,?,?,?)";
            String updatesql = "update medico set codigo_endereco=?, nome=?, cpf=?, data_nasc=?, telefone=?, crm=? where codigo_medico = ?";
            PreparedStatement ps;
            
            if(entity.getCodigo() == 0){
                ps = c.prepareStatement(insertsql);
            }else{
                ps = c.prepareStatement(updatesql);
                ps.setInt(7, entity.getCodigo());
            }
            
            ps.setInt(1, entity.getEndereco().getCodigo());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getCpf());
            ps.setDate(4, entity.getData_nascimento());
            ps.setString(5, entity.getTelefone());
            ps.setString(6, entity.getCrm());
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
            String selectsql = "select max(codigo_medico + 1) from medico;";
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
    
    public Medico findByID(int codigo) {
        Medico medico = null;
        Connection c = null;

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from medico where codigo_medico = ?";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medico = new Medico();
                medico.setCodigo(rs.getInt("codigo_medico"));
                medico.setEndereco(new EnderecoDAO().findByID(rs.getInt("codigo_endereco")));
                medico.setNome(rs.getString("nome"));                
                medico.setCpf(rs.getString("cpf"));
                medico.setData_nascimento(rs.getDate("data_nasc"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setCrm(rs.getString("crm"));
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
        return medico;
    }
    
    public Medico findByCRM(String crm) {
        Medico medico = null;
        Connection c = null;

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from medico where crm = ?";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setString(1, crm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medico = new Medico();
                medico.setCodigo(rs.getInt("codigo_medico"));
                medico.setEndereco(new EnderecoDAO().findByID(rs.getInt("codigo_endereco")));
                medico.setNome(rs.getString("nome"));                
                medico.setCpf(rs.getString("cpf"));
                medico.setData_nascimento(rs.getDate("data_nasc"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setCrm(rs.getString("crm"));
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
        return medico;
    }
    
    public Medico findByCPF(String cpf) {
        Medico medico = null;
        Connection c = null;

        try {            
            c = ConnectionFactory.getConnection();
            String selectsql = "select * from medico where cpf = ?";
            PreparedStatement ps = c.prepareStatement(selectsql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medico = new Medico();
                medico.setCodigo(rs.getInt("codigo_medico"));
                medico.setEndereco(new EnderecoDAO().findByID(rs.getInt("codigo_endereco")));
                medico.setNome(rs.getString("nome"));                
                medico.setCpf(rs.getString("cpf"));
                medico.setData_nascimento(rs.getDate("data_nasc"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setCrm(rs.getString("crm"));
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
        return medico;
    }
    
    public List<Medico> findByNome(String nome) {
		List<Medico> list = null;        
		Connection c = null;

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from medico where upper(nome) like upper(?)";
			PreparedStatement ps = c.prepareStatement(selectsql);
			ps.setString(1, '%' + nome + '%');
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Medico medico = new Medico();
				medico.setCodigo(rs.getInt("codigo_medico"));
				medico.setEndereco(new EnderecoDAO().findByID(rs.getInt("codigo_endereco")));
				medico.setNome(rs.getString("nome"));                
				medico.setCpf(rs.getString("cpf"));
				medico.setData_nascimento(rs.getDate("data_nasc"));
				medico.setTelefone(rs.getString("telefone"));
				medico.setCrm(rs.getString("crm"));
				list.add(medico);
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
            String deletesql = "delete from medico where codigo_medico = ?";
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
