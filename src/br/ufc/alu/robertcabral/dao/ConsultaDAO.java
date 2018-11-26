package br.ufc.alu.robertcabral.dao;

import br.ufc.alu.robertcabral.entity.Consulta;
import br.ufc.alu.robertcabral.entity.Medico;
import br.ufc.alu.robertcabral.entity.Paciente;
import br.ufc.alu.robertcabral.entity.Remedio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void save(Consulta entity) {
        Connection c = null;

        try {
            c = ConnectionFactory.getConnection();
            String insertsql = "insert into consulta values (?,?,?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(insertsql);
            ps.setInt(1, this.findID());
            ps.setInt(2, entity.getMedico().getCodigo());
            ps.setInt(3, entity.getPaciente().getCodigo());
            ps.setInt(4, entity.getRemedio().getCodigo_remedio());
            ps.setDate(5, entity.getData());
            ps.setInt(6, entity.getQtd_dias());
            ps.setInt(7, entity.getTempo_entre());
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
            String selectsql = "select max(codigo_consulta + 1) from consulta;";
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
    
    public List<Consulta> findByPaciente(Paciente entity){
    	List<Consulta> list = null;        
		Connection c = null;
		RemedioDAO rDao = new RemedioDAO();
		MedicoDAO mDao = new MedicoDAO();
		PacienteDAO pDao = new PacienteDAO();

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from consulta_paciente(?) as (codigo_consulta int, codigo_medico int, codigo_paciente int, codigo_remedio int, tempo_entre int, qtd_dias int, data date)";					
			PreparedStatement ps = c.prepareStatement(selectsql);
			
			if(entity == null)
				return null;
			
			ps.setInt(1, entity.getCodigo());
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Consulta consulta = new Consulta();
				Medico medico = new Medico();
				Paciente paciente = new Paciente();
				Remedio remedio = new Remedio();
				
				medico = mDao.findByID(rs.getInt("codigo_medico"));
				paciente = pDao.findByID(rs.getInt("codigo_paciente"));
				remedio = rDao.findByID(rs.getInt("codigo_remedio"));
				consulta.setTempo_entre(rs.getInt("tempo_entre"));
				consulta.setQtd_dias(rs.getInt("qtd_dias"));
				consulta.setMedico(medico);
				consulta.setPaciente(paciente);
				consulta.setRemedio(remedio);	
				consulta.setData(rs.getDate("data"));
				consulta.setCodigo(rs.getInt("codigo_consulta"));
				list.add(consulta);
				
			}
			
			list.remove(list.size() - 1);

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
    
    public List<Consulta> findByMedico(Medico entity){
    	List<Consulta> list = null;        
		Connection c = null;
		RemedioDAO rDao = new RemedioDAO();
		MedicoDAO mDao = new MedicoDAO();
		PacienteDAO pDao = new PacienteDAO();

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from consulta_medico(?) as (codigo_consulta int, codigo_medico int, codigo_paciente int, codigo_remedio int, tempo_entre int, qtd_dias int, data date)";					
			PreparedStatement ps = c.prepareStatement(selectsql);
			
			if(entity == null)
				return null;
			
			ps.setInt(1, entity.getCodigo());
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Consulta consulta = new Consulta();
				Medico medico = new Medico();
				Paciente paciente = new Paciente();
				Remedio remedio = new Remedio();
				
				medico = mDao.findByID(rs.getInt("codigo_medico"));
				paciente = pDao.findByID(rs.getInt("codigo_paciente"));
				remedio = rDao.findByID(rs.getInt("codigo_remedio"));
				consulta.setTempo_entre(rs.getInt("tempo_entre"));
				consulta.setQtd_dias(rs.getInt("qtd_dias"));
				consulta.setMedico(medico);
				consulta.setPaciente(paciente);
				consulta.setRemedio(remedio);	
				consulta.setData(rs.getDate("data"));
				consulta.setCodigo(rs.getInt("codigo_consulta"));
				list.add(consulta);
				
			}
			
			list.remove(list.size() - 1);

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
    
    public List<Consulta> findByMedicoAndPaciente(Medico entity, Paciente entity2){
    	List<Consulta> list = null;        
		Connection c = null;
		RemedioDAO rDao = new RemedioDAO();
		MedicoDAO mDao = new MedicoDAO();
		PacienteDAO pDao = new PacienteDAO();

		try {            
			c = ConnectionFactory.getConnection();
			String selectsql = "select * from consulta_paciente_medico(?,?) as (codigo_consulta int, codigo_medico int, codigo_paciente int, codigo_remedio int, tempo_entre int, qtd_dias int, data date);";					
			PreparedStatement ps = c.prepareStatement(selectsql);
			
			if(entity == null || entity2 == null)
				return null;
			
			ps.setInt(1, entity2.getCodigo());
			ps.setInt(2, entity.getCodigo());
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Consulta consulta = new Consulta();
				Medico medico = new Medico();
				Paciente paciente = new Paciente();
				Remedio remedio = new Remedio();
				
				medico = mDao.findByID(rs.getInt("codigo_medico"));
				paciente = pDao.findByID(rs.getInt("codigo_paciente"));
				remedio = rDao.findByID(rs.getInt("codigo_remedio"));
				consulta.setTempo_entre(rs.getInt("tempo_entre"));
				consulta.setQtd_dias(rs.getInt("qtd_dias"));
				consulta.setMedico(medico);
				consulta.setPaciente(paciente);
				consulta.setRemedio(remedio);	
				consulta.setData(rs.getDate("data"));
				consulta.setCodigo(rs.getInt("codigo_consulta"));
				list.add(consulta);
				
			}
			
			list.remove(list.size() - 1);

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
            String deletesql = "delete from consulta where codigo_consulta = ?";
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
