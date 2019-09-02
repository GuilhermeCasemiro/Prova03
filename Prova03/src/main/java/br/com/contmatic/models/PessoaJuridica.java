package br.com.contmatic.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.caelum.stella.bean.validation.CNPJ;

// TODO: Auto-generated Javadoc
/**
 * The Class PessoaJuridica.
 */
public class PessoaJuridica extends Pessoa {

    /** The cnpj. */
    private String cnpj;

    /** The razao social. */
    private String razaoSocial;

    /**
     * Instantiates a new pessoa juridica.
     */
    public PessoaJuridica() {
        super();
    }

    /**
     * Instantiates a new pessoa juridica.
     *
     * @param cnpj the cnpj
     * @param razaoSocial the razao social
     */
    public PessoaJuridica(String cnpj, String razaoSocial) {
        super();
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    @NotBlank(message = "CNPJ obrigatório")
    @CNPJ
    @Size(min = 14, max = 14)
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
    @NotBlank(message = "Razão Social é obrigatório")
    @Size(min = 10, max = 30)
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
