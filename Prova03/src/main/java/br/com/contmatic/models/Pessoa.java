package br.com.contmatic.models;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.URL;

// TODO: Auto-generated Javadoc
/**
 * The Class Pessoa.
 */
public class Pessoa {

    /** The nome. */
    private String nome;

    /** The email. */
    private String email;

    /** The endereco. */
    private Set<Endereco> endereco;

    /** The contatos. */
    private Set<Contato> contatos;

    /**
     * Instantiates a new pessoa.
     */
    public Pessoa() {
        super();
    }

    /**
     * Instantiates a new pessoa.
     *
     * @param nome the nome
     * @param email the email
     * @param endereco the endereco
     * @param contatos the contatos
     */
    public Pessoa(String nome, String email, Set<Endereco> endereco, Set<Contato> contatos) {
        super();
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.contatos = contatos;
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[[ ]|\\\\p{L}*]+$")
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    @Email
    @NotEmpty(message = "E-mail é obrigatório")
    @URL
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    @Valid
    @NotNull(message = "Endereço é obrigatório.")
    public Set<Endereco> getEndereco() {

        return this.endereco;
    }

    /**
     * Sets the endereco.
     *
     * @param endereco the new endereco
     */
    public void setEndereco(Set<Endereco> endereco) {
        this.endereco = endereco;
    }

    /**
     * Gets the contatos.
     *
     * @return the contatos
     */
    @Valid
    @NotNull(message = "Não pode conter lista de contatos vazia.")
    public Set<Contato> getContatos() {
        return this.contatos;
    }

    /**
     * Sets the contatos.
     *
     * @param contatos the new contatos
     */
    public void setContatos(Set<Contato> contatos) {
        this.contatos = contatos;
    }

    /**
     * Acicionar endereco.
     *
     * @param endereco the endereco
     * @return true, if successful
     */
    public boolean acicionarEndereco(Endereco endereco) {
        checkNotNull(endereco, "Endereço não pode ser nulo.");
        this.endereco.add(endereco);
        return true;
    }

    /**
     * Remover endereco.
     *
     * @param endereco the endereco
     * @return true, if successful
     */
    public boolean removerEndereco(Endereco endereco) {
        checkNotNull(endereco, "Endereço não pode ser nulo.");
        this.endereco.remove(endereco);
        return true;
    }

    /**
     * Acicionar contato.
     *
     * @param contato the contato
     * @return true, if successful
     */
    public boolean acicionarContato(Contato contato) {
        checkNotNull(contato, "Contato não pode ser nulo.");
        this.contatos.add(contato);
        return true;
    }

    /**
     * Remover contato.
     *
     * @param contato the contato
     * @return true, if successful
     */
    public boolean removerContato(Contato contato) {
        checkNotNull(contato, "Contato não pode ser nulo.");
        this.contatos.add(contato);
        return true;
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
