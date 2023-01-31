package Entidades.Funcionario;

import Entidades.Pessoa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import DataObjects.*;


public class Funcionario extends Pessoa implements Serializable, CalculoSalarial, CurrentDate {
    private String rg;
    private String numeroCTPS;
    private char sexo;//M ou F
    private String estadoCivil;
    private String cargo;
    private double salario;
    private Date dataDeIngresso;

    public Funcionario(String cpf, String nome, String rg, Date dataNascimento, Endereco endereco, String numeroCTPS, char sexo, String estadoCivil, String cargo, double salario) {
        super(cpf, nome, endereco, dataNascimento);
        this.rg = rg;
        this.numeroCTPS = numeroCTPS;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.cargo = cargo;
        this.salario = salario;
        this.dataDeIngresso = CurrentDate.getCurrentDate();
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNumeroCTPS() {
        return numeroCTPS;
    }

    public void setNumeroCTPS(String numeroCTPS) {
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

    public Date getDataDeIngresso() {
        return dataDeIngresso;
    }

    public void setDataDeIngresso(Date dataDeIngresso) {
        this.dataDeIngresso = dataDeIngresso;
    }

    @Override
    public void calculaSalario(double salario) {
        String dataExtenso = CurrentDate.showDate(dataDeIngresso);
        String []data = new String[3];
        data =  dataExtenso.split("/");
        int anoAtual = LocalDate.now().getYear();
        int anoDeIngresso = Integer.parseInt(data[2]);

        if(anoAtual - anoDeIngresso >= 15){
            setSalario(salario * 1.10);
        }else{
            setSalario(salario);
        }
    }
}
