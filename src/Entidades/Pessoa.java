package Entidades;

import DataObjects.Endereco;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pessoa implements Serializable {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private Endereco endereco;

    public Pessoa(String cpf, String nome, Endereco endereco, Date dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }
    private String cpfError() {
        return "CPF Error!";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    private static final int[] WeightCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public static boolean isValidCPF(String cpf) {
        cpf = cpf.trim().replace(".", "").replace("-", "");
        if (cpf.length() != 11) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        int Digit01 = calculateDigit(cpf.substring(0,9));
        int Digit02 = calculateDigit(cpf.substring(0,9) + Digit01);
        return cpf.equals(cpf.substring(0,9) + Digit01 + Digit02);
    }

    private static int calculateDigit(String str) {
        int sum = 0;
        for (int index =str.length()-1, digit; index >= 0; index-- ) {
            digit = Integer.parseInt(str.substring(index,index+1));
            sum += digit* WeightCPF[ WeightCPF.length-str.length()+index];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    private static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    protected String getFullEndereco() {
        return "endereco: " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getEstado() + '}';
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", endereco=" + endereco +
                '}';
    }
}
