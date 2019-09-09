package br.com.contmatic.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.caelum.stella.bean.validation.CNPJ;

/**
 * The Class PessoaJuridica.
 */
public class Empresa extends Pessoa {

    /** The cnpj. */
    @NotBlank(message = "CNPJ obrigatório.")
    @CNPJ(message = "O CNPJ deve ser válido.")
    @Size(min = 14, max = 14, message = "CNPJ deve conter exatamente 14 caracteres.")
    private String cnpj;

    /** The razao social. */
    @NotBlank(message = "Razão Social é obrigatório.")
    @Size(min = 10, max = 30, message = "Razão social deve ter no minino 10 caracteres e no maixmo 30 caracteres.")
    @Pattern(regexp = "/[^a-zA-Z 0-9]+/", message = "Razão Social inválida.")
    private String razaoSocial;

    /** The nome fantasia. */
    @NotBlank(message = "Nome fantasia é obrigatório.")
    @Size(min = 10, max = 30, message = "Nome fantasia deve ter no minimo 10 caracteres e no maixmo 30 caracteres.")
    @Pattern(regexp = "/[^a-zA-Z 0-9]+/", message = "Nome Fantasia inválido.")
    private String nomeFantasia;

    /**
     * Instantiates a new Empresa.
     */
    public Empresa() {
        super();
    }

    /**
     * Instantiates a new Empresa.
     *
     * @param cnpj the cnpj
     * @param razaoSocial the razao social
     * @param nomeFantasia the nome fantasia
     */
    public Empresa(String cnpj) {
        super();
        this.cnpj = cnpj;

    }

    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Sets the cnpj.
     *
     * @param cnpj the new cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Gets the razao social.
     *
     * @return the razao social
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * Sets the razao social.
     *
     * @param razaoSocial the new razao social
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * Gets the nome fantasia.
     *
     * @return the nome fantasia
     */
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * Sets the nome fantasia.
     *
     * @param nomeFantasia the new nome fantasia
     */
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.contmatic.models.Pessoa#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnpj).toHashCode();
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
        Empresa empresa = (Empresa) obj;
        return new EqualsBuilder().append(cnpj, empresa.cnpj).isEquals();
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
