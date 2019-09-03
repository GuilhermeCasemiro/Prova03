package br.com.contmatic.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;

// TODO: Auto-generated Javadoc
/**
 * The Class PessoaFisica.
 */
public class PessoaFisica extends Pessoa {

    /** The cpf. */
    @NotNull(message = "CPF obrigatório.")
    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    /** The sexo. */
    @NotNull(message = "Sexo obrigatório.")
    @Size(min = 8, max = 9)
    private String sexo;

    /** The data nascimento. */
    @NotNull(message = "A data de nascimento não pode ser nula.")
    @Past
    private DateTime dataNascimento;

    /**
     * Instantiates a new pessoa fisica.
     */
    public PessoaFisica() {
        super();
    }

    /**
     * Instantiates a new pessoa fisica.
     *
     * @param cpf the cpf
     * @param sexo the sexo
     * @param dataNascimento the data nascimento
     */
    public PessoaFisica(String cpf, String sexo, DateTime dataNascimento) {
        super();
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    /**
     * Gets the data nascimento.
     *
     * @return the data nascimento
     */
    public DateTime getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Sets the data nascimento.
     *
     * @param dataNascimento the new data nascimento
     */
    public void setDataNascimento(DateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets the cpf.
     *
     * @param cpf the new cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Gets the sexo.
     *
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the sexo.
     *
     * @param sexo the new sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.contmatic.models.Pessoa#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.contmatic.models.Pessoa#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.contmatic.models.Pessoa#toString()
     */
    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
