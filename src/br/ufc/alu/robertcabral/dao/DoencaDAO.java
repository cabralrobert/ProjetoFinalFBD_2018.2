package br.ufc.alu.robertcabral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.alu.robertcabral.entity.Doenca;
import br.ufc.alu.robertcabral.entity.Paciente;

public class DoencaDAO {

	public void save(Doenca entity) {
		Connection c = null;

        try {

            c = ConnectionFactory.getConnection();
            String insertsql = "insert into doenca(nome) values (?)";
            String updatesql = "update doenca set nome=? where codigo_doenca = ?";
            PreparedStatement ps;
            
            if(entity.getCodigo() == 0){
                ps = c.prepareStatement(insertsql);
            }else{
                ps = c.prepareStatement(updatesql);
                ps.setInt(2, entity.getCodigo());
            }
            
            ps.setString(1, entity.getNome());            
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
	
	public void addDoencaToPaciente(Doenca entity, Paciente paciente) {
		Connection c = null;

        try {

            c = ConnectionFactory.getConnection();
            String insertsql = "insert into aux_doenca(codigo_doenca,codigo_paciente) values (?,?)";
            PreparedStatement ps;
            ps = c.prepareStatement(insertsql);            
            ps.setInt(1, entity.getCodigo());
            ps.setInt(2, paciente.getCodigo());
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
	
	public Doenca findByID(int codigo) {
		Doenca doenca = null;
		Connection c = null;

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from doenca where codigo_doenca = ?";
			PreparedStatement ps = c.prepareStatement(selectsql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				doenca = new Doenca();
				doenca.setCodigo(rs.getInt("codigo_doenca"));
				doenca.setNome(rs.getString("nome"));                
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
		return doenca;
	}

	public List<Doenca> findAll(){
		Connection c = null;
		List<Doenca> list = null;

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from doenca";
			PreparedStatement ps = c.prepareStatement(selectsql);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Doenca doenca = new Doenca();
				doenca.setCodigo(rs.getInt("codigo_doenca"));
				doenca.setNome(rs.getString("nome"));
				list.add(doenca);
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
			String deletesql = "delete from doenca where codigo_doenca = ?";
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
