package br.com.contmatic.models;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import br.com.contmatic.enums.DDD;
import br.com.contmatic.enums.Telefone;

// TODO: Auto-generated Javadoc
/**
 * The Class Contato.
 */
public class Contato {

    /** The telefone. */
    @Valid
    @Max(9)
    Telefone telefone;

    /** The ddd. */
    @Valid
    @Length(min = 2, max = 2)
    DDD ddd;

    /**
     * Instantiates a new contato.
     */
    public Contato() {
        super();
    }

    /**
     * Instantiates a new contato.
     *
     * @param telefone the telefone
     * @param ddd the ddd
     */
    public Contato(Telefone telefone, DDD ddd) {
        super();
        this.telefone = telefone;
        this.ddd = ddd;
    }

    /**
     * Gets the telefone.
     *
     * @return the telefone
     */
    public Telefone getTelefone() {
        return telefone;
    }

    /**
     * Sets the telefone.
     *
     * @param telefone the new telefone
     */
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    /**
     * Gets the ddd.
     *
     * @return the ddd
     */
    public DDD getDdd() {
        return ddd;
    }

    /**
     * Sets the ddd.
     *
     * @param ddd the new ddd
     */
    public void setDdd(DDD ddd) {
        this.ddd = ddd;
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
