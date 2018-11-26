package br.ufc.alu.robertcabral.entity;

import java.sql.Date;

public class Medico {
    
    int codigo;
    Enderecos endereco;
    String nome, cpf, telefone, crm;
    Date data_nascimento;
    
    public Medico(){
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
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

	@Override
	public String toString() {
		return "Medico [codigo=" + codigo + ", \nendereco=" + endereco + ", \nnome=" + nome + ", \ncpf=" + cpf + ", \ntelefone="
				+ telefone + ", \ncrm=" + crm + ", \ndata_nascimento=" + data_nascimento + "]";
	}       
    
}
