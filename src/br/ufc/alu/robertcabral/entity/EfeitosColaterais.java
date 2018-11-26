package br.ufc.alu.robertcabral.entity;

public class EfeitosColaterais {
    Remedio remedio;
    String nome;

    public EfeitosColaterais() {
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
		return "EfeitosColaterais [remedio=" + remedio + ", nome=" + nome + "]";
	}
    
    
}
