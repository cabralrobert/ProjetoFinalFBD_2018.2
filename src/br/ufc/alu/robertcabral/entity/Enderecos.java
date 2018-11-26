package br.ufc.alu.robertcabral.entity;

public class Enderecos {
    
    String uf, cidade, bairro, rua, cep;
    int codigo, numero;

    public Enderecos() {
    }    

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }    
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

	@Override
	public String toString() {
		return "Enderecos [codigo=" + codigo + ", UF=" + uf + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", cep=" + cep
				 + ", numero=" + numero + "]";
	}       
    
}
