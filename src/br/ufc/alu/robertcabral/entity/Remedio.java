package br.ufc.alu.robertcabral.entity;

public class Remedio {
    int codigo_remedio;
    String nome, nome_original, tarja;

    public int getCodigo_remedio() {
        return codigo_remedio;
    }

    public void setCodigo_remedio(int codigo_remedio) {
        this.codigo_remedio = codigo_remedio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_original() {
        return nome_original;
    }

    public void setNome_original(String nome_original) {
        this.nome_original = nome_original;
    }

    public String getTarja() {
        return tarja;
    }

    public void setTarja(String tarja) {
        this.tarja = tarja;
    }

    @Override
    public String toString() {
        return "Remedio{" + "codigo_remedio=" + codigo_remedio + ", nome=" + nome + ", nome_original=" + nome_original + ", tarja=" + tarja + '}';
    }        
    
}
