package br.ufc.alu.robertcabral.dao;

import br.ufc.alu.robertcabral.entity.Doenca;
import br.ufc.alu.robertcabral.entity.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

	public void save(Paciente entity) {
		Connection c = null;

		try {

			if (new EnderecoDAO().findByID(entity.getEndereco().getCodigo()) == null) {
				System.out.println("Não existe o endereço");
				return;
			}                        

			c = ConnectionFactory.getConnection();
			String insertsql = "insert into paciente(codigo_endereco,nome,cpf,data_nasc,telefone) values (?,?,?,?,?)";
			String updatesql = "update paciente set codigo_endereco=?, nome=?, cpf=?, data_nasc=?, telefone=? where codigo_paciente = ?";
			PreparedStatement ps;

			if(entity.getCodigo() == 0){
				ps = c.prepareStatement(insertsql);
			}else{
				ps = c.prepareStatement(updatesql);
				ps.setInt(6, entity.getCodigo());
			}

			ps.setInt(1, entity.getEndereco().getCodigo());
			ps.setString(2, entity.getNome());
			ps.setString(3, entity.getCpf());
			ps.setDate(4, entity.getData_nascimento());
			ps.setString(5, entity.getTelefone());
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
			String selectsql = "select max(codigo_paciente + 1) from paciente;";
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

	public Paciente findByID(int codigo) {
		Paciente paciente = null;
		Connection c = null;

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from paciente where codigo_paciente = ?";
			PreparedStatement ps = c.prepareStatement(selectsql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				paciente = new Paciente();
				paciente.setCodigo(rs.getInt("codigo_paciente"));
				paciente.setEndereco(new EnderecoDAO().findByID(rs.getInt("codigo_endereco")));
				paciente.setNome(rs.getString("nome"));                
				paciente.setCpf(rs.getString("cpf"));
				paciente.setData_nascimento(rs.getDate("data_nasc"));
				paciente.setTelefone(rs.getString("telefone"));
			}

			String selectDoencas = "select * from doencas(?) as (c1 int, c2 varchar);";
			ps = c.prepareStatement(selectDoencas);
			if(paciente != null) {
				ps.setInt(1, paciente.getCodigo());
				rs = ps.executeQuery();
				while(rs.next()) {
					Doenca doenca = new Doenca();
					doenca.setCodigo(rs.getInt("c1"));
					doenca.setNome(rs.getString("c2"));
					paciente.addDoenca(doenca);
				}            
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
		return paciente;
	}

	public List<Paciente> findByNome(String nome) {
		List<Paciente> list = null;        
		Connection c = null;

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from paciente where upper(nome) like upper(?)";
			PreparedStatement ps = c.prepareStatement(selectsql);
			ps.setString(1, '%' + nome + '%');
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setCodigo(rs.getInt("codigo_paciente"));
				paciente.setEndereco(new EnderecoDAO().findByID(rs.getInt("codigo_endereco")));
				paciente.setNome(rs.getString("nome"));                
				paciente.setCpf(rs.getString("cpf"));
				paciente.setData_nascimento(rs.getDate("data_nasc"));
				paciente.setTelefone(rs.getString("telefone"));
				list.add(paciente);
			}

			for(Paciente paciente : list) {
				String selectDoencas = "select * from doencas(?) as (c1 int, c2 varchar);";
				ps = c.prepareStatement(selectDoencas);
				if(paciente != null) {
					ps.setInt(1, paciente.getCodigo());
					rs = ps.executeQuery();
					while(rs.next()) {
						Doenca doenca = new Doenca();
						doenca.setCodigo(rs.getInt("c1"));
						doenca.setNome(rs.getString("c2"));
						paciente.addDoenca(doenca);
					}
				}
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

	public Paciente findByCPF(String cpf) {
		Paciente paciente = null;
		Connection c = null;

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from paciente where cpf = ?";
			PreparedStatement ps = c.prepareStatement(selectsql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				paciente = new Paciente();
				paciente.setCodigo(rs.getInt("codigo_paciente"));
				paciente.setEndereco(new EnderecoDAO().findByID(rs.getInt("codigo_endereco")));
				paciente.setNome(rs.getString("nome"));                
				paciente.setCpf(rs.getString("cpf"));
				paciente.setData_nascimento(rs.getDate("data_nasc"));
				paciente.setTelefone(rs.getString("telefone"));
			}

			String selectDoencas = "select * from doencas(?) as (c1 int, c2 varchar);";
			ps = c.prepareStatement(selectDoencas);
			if(paciente != null) {
				ps.setInt(1, paciente.getCodigo());
				rs = ps.executeQuery();
				while(rs.next()) {
					Doenca doenca = new Doenca();
					doenca.setCodigo(rs.getInt("c1"));
					doenca.setNome(rs.getString("c2"));
					paciente.addDoenca(doenca);
				} 
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
		return paciente;
	}

	public List<Doenca> findDoencas(int codigo){
		List<Doenca> list = null;        
		Connection c = null;

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from doencas(?) as (c1 int, c2 varchar);";
			PreparedStatement ps = c.prepareStatement(selectsql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				list.add(new DoencaDAO().findByID(rs.getInt("c1")));
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
			String deletesql = "delete from paciente where codigo_paciente = ?";
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
