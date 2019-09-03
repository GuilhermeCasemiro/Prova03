package br.com.contmatic.models;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class Funcionario.
 */
public class Funcionario extends PessoaFisica {

    /** The salario. */
    @NotNull(message = "Salário é obrigatório.")
    @DecimalMin(value = "1000.00", message = "O salário não pode ser menor que a base de R$: 1000.00.")
    @DecimalMax(value = "9999.99", message = "O salário não pode ser maior que R$: 9999.99.")
    private Short salario;

    /** The comissao. */
    @DecimalMin(value = "0.0", message = "A comissão não pode ser menor que R$: 0.00.")
    @DecimalMax(value = "500.00", message = "A comissão não pode ser maior que R$: 500.00.")
    @Pattern(regexp = "^([a-zA-Z])+$")
    private Short comissao;

    /** The departamento. */
    @NotNull(message = "Ter um departamento é obrigatório.")
    @Size(min = 10, max = 30)
    private String departamento;

    /**
     * Instantiates a new funcionario.
     */
    public Funcionario() {
        super();
    }

    /**
     * Instantiates a new funcionario.
     *
     * @param salario the salario
     * @param comissao the comissao
     * @param departamento the departamento
     */
    public Funcionario(Short salario, Short comissao, String departamento) {
        super();
        this.salario = salario;
        this.comissao = comissao;
        this.departamento = departamento;
    }

    /**
     * Gets the salario.
     *
     * @return the salario
     */

    public Short getSalario() {
        return salario;
    }

    /**
     * Sets the salario.
     *
     * @param salario the new salario
     */
    public void setSalario(Short salario) {
        this.salario = salario;
    }

    /**
     * Gets the comissao.
     *
     * @return the comissao
     */
    public Short getComissao() {
        return comissao;
    }

    /**
     * Sets the comissao.
     *
     * @param comissao the new comissao
     */
    public void setComissao(Short comissao) {
        this.comissao = comissao;
    }

    /**
     * Gets the departamento.
     *
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the departamento.
     *
     * @param departamento the new departamento
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
