package Funcionario;

import DataObjects.Pessoa;

import java.util.Date;
import DataObjects.*;


public class Funcionario extends Pessoa {
    private int rg;
    private int numeroCTPS;
    private char sexo;//M ou F
    private String estadoCivil;
    private String cargo;
    private double salario;
    private String dataDeIngresso;

    public Funcionario(String cpf, String nome, int rg, Date dataNascimento, Endereco endereco, int numeroCTPS, char sexo, String estadoCivil, String cargo, double salario) {
        super(cpf, nome, endereco, dataNascimento);
        this.rg = rg;
        this.numeroCTPS = numeroCTPS;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.cargo = cargo;
        this.salario = salario;
        this.dataDeIngresso = getCurrentDate();
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public int getNumeroCTPS() {
        return numeroCTPS;
    }

    public void setNumeroCTPS(int numeroCTPS) {
        this.numeroCTPS = numeroCTPS;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDataDeIngresso() {
        return dataDeIngresso;
    }

    public void setDataDeIngresso(String dataDeIngresso) {
        this.dataDeIngresso = dataDeIngresso;
    }


}
