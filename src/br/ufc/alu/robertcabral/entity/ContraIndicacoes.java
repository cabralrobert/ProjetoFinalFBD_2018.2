package br.ufc.alu.robertcabral.entity;

public class ContraIndicacoes {
    Remedio remedio;
    String nome;

    public ContraIndicacoes() {
    }        

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	@Override
	public String toString() {
		return "ContraIndicacoes [remedio=" + remedio + ", nome=" + nome + "]";
	}  
    
    
    
}
