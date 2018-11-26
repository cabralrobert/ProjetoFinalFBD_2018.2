package br.ufc.alu.robertcabral.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    
    int codigo;
    Enderecos endereco;
    String nome, cpf, telefone;
    Date data_nascimento;
    List<Doenca> doencas = new ArrayList<>();

    public Paciente() {
        
    }  

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Enderecos getEndereco() {
        return endereco;
    }

    public void setEndereco(Enderecos endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    
    public void addDoenca(Doenca doenca){
        this.doencas.add(doenca);
    }
    
    public List<Doenca> getDoencas(){
        return this.doencas;
    }

	@Override
	public String toString() {
		return "Paciente [codigo=" + codigo + ", \nendereco=" + endereco + ", \nnome=" + nome + ", \ncpf=" + cpf
				+ ", \ntelefone=" + telefone + ", \ndata_nascimento=" + data_nascimento + ", \ndoencas=" + doencas + "]";
	}	
}
