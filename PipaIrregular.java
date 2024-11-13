package com.example;

public class PipaIrregular {
    private String cliente;
    private int dia;
    private int mes;
    private int ano;
    private boolean temFoto;
    private String situacaoAgua;
    private String rua;
    private int numero;
    private String bairro;

    public PipaIrregular(String cliente, int dia, int mes, int ano, boolean temFoto, String situacaoAgua, String rua, int numero, String bairro) {
        this.cliente = cliente;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.temFoto = temFoto;
        this.situacaoAgua = situacaoAgua;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }

    public String getCliente() { return cliente; }
    public int getDia() { return dia; }
    public int getMes() { return mes; }
    public int getAno() { return ano; }
    public boolean isTemFoto() { return temFoto; }
    public String getSituacaoAgua() { return situacaoAgua; }
    public String getRua() { return rua; }
    public int getNumero() { return numero; }
    public String getBairro() { return bairro; }

    @Override
    public String toString() {
        return "Cliente: " + cliente + ", Data: " + dia + "/" + mes + "/" + ano +
               ", Tem foto: " + (temFoto ? "Sim" : "Não") +
               ", Situação da água: " + situacaoAgua +
               ", Endereço: " + rua + ", Nº: " + numero + ", Bairro: " + bairro;
    }
}
