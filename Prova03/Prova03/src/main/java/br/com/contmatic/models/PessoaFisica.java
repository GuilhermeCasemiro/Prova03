package br.com.contmatic.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;

/**
 * The Class PessoaFisica.
 */
public class PessoaFisica extends Pessoa {

    /** The cpf. */
    @NotBlank(message = "CPF obrigatório.")
    @CPF(message = "CPF deve ser válido.")
    @Size(min = 11, max = 11, message = "CPF deve conter exatamente 11 caracteres.")
    private String cpf;

    /** The sexo. */
    @NotNull(message = "Sexo obrigatório.")
    @Size(min = 8, max = 9, message = "Sexo deve ter no mínimo 8 caracteres e no máximo 9.")
    @Pattern(regexp = "[A-Z]\\B\\w\\D*", message = "Não pode conter acentos, caracteres especiais e números no sexo.")
    private String sexo;

    /** The data nascimento. */
    @NotNull(message = "A data de nascimento não pode ser nula.")
    @Past(message = "A data não pode ser mais recente que a atual.")
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
    public PessoaFisica(String cpf) {
        super();
        this.cpf = cpf;
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
        return new HashCodeBuilder().append(cpf).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.contmatic.models.Pessoa#equals(java.lang.Object)
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
        PessoaFisica pessoaF = (PessoaFisica) obj;
        return new EqualsBuilder().append(cpf, pessoaF.cpf).isEquals();
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
