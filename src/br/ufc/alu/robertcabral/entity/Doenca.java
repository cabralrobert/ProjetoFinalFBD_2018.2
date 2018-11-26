package br.ufc.alu.robertcabral.entity;

public class Doenca {
    int codigo;
    String nome;

    public Doenca() {
    }   
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	@Override
	public String toString() {
		return "Doenca [codigo=" + codigo + ", nome=" + nome + "]";
	}   
      
}
