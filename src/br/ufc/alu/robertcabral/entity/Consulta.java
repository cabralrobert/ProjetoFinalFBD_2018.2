package br.ufc.alu.robertcabral.entity;

import java.sql.Date;

public class Consulta {
    
    int codigo;
    Remedio remedio;
    Paciente paciente;
    Medico medico;
    Date data;
    int qtd_dias, tempo_entre;

    public Consulta() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtd_dias() {
        return qtd_dias;
    }

    public void setQtd_dias(int qtd_dias) {
        this.qtd_dias = qtd_dias;
    }

    public int getTempo_entre() {
        return tempo_entre;
    }

    public void setTempo_entre(int tempo_entre) {
        this.tempo_entre = tempo_entre;
    }

	@Override
	public String toString() {
		return "Consulta [codigo=" + codigo + ", \nremedio=" + remedio + ", \npaciente=" + paciente + ", \nmedico=" + medico
				+ ", \ndata=" + data + ", \nqtd_dias=" + qtd_dias + ", \ntempo_entre=" + tempo_entre + "]\n";
	}        
    
    
    
}
