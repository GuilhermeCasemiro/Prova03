package br.com.contmatic.models;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.contmatic.enums.DDD;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The bairro. */
    @NotBlank(message = "Bairro não pode ser nulo e nem estar vazio.")
    @Size(min = 5, max = 30, message = "Bairro não pode conter menos de 5 caracteres e mais 30 de caracteres.")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$", message = "Bairro não pode conter números e caracteres especiais.")
    private String bairro;

    /** The estado. */
    @NotNull(message = "DDD não pode ser nulo.")
    @Valid
    private DDD estado;

    /** The numero residencia. */
    @Min(value = 1, message = "O número da residência não ser negativo.")
    @Max(value = 1500, message = "O número da residência não pode ser maior que 1500.")
    private int numeroResidencia;

    /** The cep. */
    @NotBlank(message = "CEP não pode ser nulo e nem vazio.")
    @Pattern(regexp = "[0-9]{5}-[\\\\d]{3}", message = "CEP inválido.")
    @Size(min = 9, max = 9, message = "CEP não pode ter mais de 9 caracteres.")
    private String cep;

    /** The complemento. */
    @Size(max = 30, message = "Complemento não pode ter mais de 30 caracteres.")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ0-9 ]+$", message = "Deve inserir um complemento válido.")
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
     * @param cep the cep
     * @param numeroResidencia the numeroResidencia
     * @param complemento the complemento
     */
    public Endereco(String cep, Integer numeroResidencia, String complemento) {
        super();
        this.cep = cep;
        this.numeroResidencia = numeroResidencia;
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
     * Gets the estado.
     *
     * @return the estado
     */
    public DDD getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(DDD estado) {
        this.estado = estado;
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

    /**
     * Gets the numero residencia.
     *
     * @return the numero residencia
     */
    public int getNumeroResidencia() {
        return numeroResidencia;
    }

    /**
     * Sets the numero residencia.
     *
     * @param numeroResidencia the new numero residencia
     */
    public void setNumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cep).append(numeroResidencia).append(estado).hashCode();
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
        Endereco endereco = (Endereco) obj;
        return new EqualsBuilder().append(cep, endereco.cep).append(numeroResidencia, endereco.numeroResidencia).append(estado, endereco.estado).isEquals();
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
