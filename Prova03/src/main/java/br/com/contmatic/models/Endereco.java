package br.com.contmatic.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.contmatic.enums.DDD;

// TODO: Auto-generated Javadoc
/**
 * The Class Endereco.
 */
public class Endereco {

    /** The bairro. */
    @Size(min = 5, max = 30)
    @Pattern(regexp = "^([a-zA-Z])*$")
    private String bairro;

    /** The uf. */
    @Size(max = 2)
    private DDD uf;

    /** The cep. */
    @NotNull
    @Pattern(regexp = "[0-9]{5}-[\\\\d]{3}")
    @Max(9)
    private String cep;

    /** The complemento. */
    @Null
    @Size(max = 30)
    private String complemento;

    /**
     * Instantiates a new endereco.
     */
    public Endereco() {
        super();
    }

    /**
     * Instantiates a new endereco.
     *
     * @param bairro the bairro
     * @param uf the uf
     * @param cep the cep
     * @param complemento the complemento
     */
    public Endereco(String bairro, DDD uf, String cep, String complemento) {
        super();
        this.bairro = bairro;
        this.uf = uf;
        this.cep = cep;
        this.complemento = complemento;
    }

    /**
     * Gets the complemento.
     *
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets the complemento.
     *
     * @param complemento the new complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Gets the bairro.
     *
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets the bairro.
     *
     * @param bairro the new bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Gets the uf.
     *
     * @return the uf
     */
    public DDD getUf() {
        return uf;
    }

    /**
     * Sets the uf.
     *
     * @param uf the new uf
     */
    public void setUf(DDD uf) {
        this.uf = uf;
    }

    /**
     * Gets the cep.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the cep.
     *
     * @param cep the new cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
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
