package br.com.contmatic.models;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * The Class Funcionario.
 */
public class Funcionario extends PessoaFisica {

	/** The salario. */
	@NotNull(message = "Salário é obrigatório.")
	@DecimalMin(value = "1000.00", message = "O salário não pode ser menor que a base de R$: 1000.00.")
	@DecimalMax(value = "8500.00", message = "O salário não pode ser maior que R$: 8500.00.")
	private Long salario;

	/** The comissao. */
	@DecimalMin(value = "0.0", message = "A comissão não pode ser menor que R$: 0.00.")
	@DecimalMax(value = "500.00", message = "A comissão não pode ser maior que R$: 500.00.")
	private Long comissao;

	/** The departamento. */
	@NotBlank(message = "Ter um departamento é obrigatório.")
	@Size(min = 5, max = 30, message = "O departamento não pode conter menos de 5 e mais de 30 caracteres.")
	@Pattern(regexp = "([A-Z]\\B)([a-zA-Z])+$", message = "A primeira letra deve ser maiuscula e não pode conter acentuação e caracteres especiais.")
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
	 * @param salario      the salario
	 * @param comissao     the comissao
	 * @param departamento the departamento
	 */
	public Funcionario(Long salario, Long comissao, String departamento) {
		super();
		this.comissao = comissao;
		this.salario = salario;
		this.departamento = departamento;
	}

	/**
	 * Gets the salario.
	 *
	 * @return the salario
	 */

	public Long getSalario() {
		return salario;
	}

	/**
	 * Sets the salario.
	 *
	 * @param salario the new salario
	 */
	public void setSalario(Long salario) {
		this.salario = salario;
	}

	/**
	 * Gets the comissao.
	 *
	 * @return the comissao
	 */
	public Long getComissao() {
		return comissao;
	}

	/**
	 * Sets the comissao.
	 *
	 * @param comissao the new comissao
	 */
	public void setComissao(Long comissao) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return new HashCodeBuilder().appendSuper(super.hashCode()).append(departamento).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Funcionario funcionario = (Funcionario) obj;
		return new EqualsBuilder().appendSuper(true).append(departamento, funcionario.departamento).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);

	}

}
