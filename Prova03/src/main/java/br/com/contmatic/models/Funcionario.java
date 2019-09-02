package br.com.contmatic.models;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Funcionario extends PessoaFisica {
    private Short salario;
    private Short comissao;
    private String departamento;

    public Funcionario() {
        super();
    }

    public Funcionario(Short salario, Short comissao, String departamento) {
        super();
        this.salario = salario;
        this.comissao = comissao;
        this.departamento = departamento;
    }

    @NotNull(message = "Salário é obrigatório.")
    @DecimalMin(value = "1000.00", message = "O salário não pode ser menor que a base de R$: 1000.00.")
    @DecimalMax(value = "9999.99", message = "O salário não pode ser maior que R$: 9999.99.")
    public Short getSalario() {
        return salario;
    }

    public void setSalario(Short salario) {
        this.salario = salario;
    }

    @DecimalMin(value = "0.0", message = "A comissão não pode ser menor que R$: 0.00.")
    @DecimalMax(value = "500.00", message = "A comissão não pode ser maior que R$: 500.00.")
    public Short getComissao() {
        return comissao;
    }

    public void setComissao(Short comissao) {
        this.comissao = comissao;
    }

    @NotNull(message = "Ter um departamento é obrigatório.")
    @Size(min = 1, max = 30)
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
