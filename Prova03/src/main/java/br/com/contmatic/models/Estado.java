package br.com.contmatic.models;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

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

public class Estado {
    @NotBlank(message = "Estado não pode ser nulo ou estar vazio.")
    @Size(min = 5, max = 30, message = "Estado não pode conter menos de 5 caracteres e mais de 30.")
    @Pattern(regexp = "[^_\\W\\d][záéíóúàèìòùâêîôûãõ]*", message = "Estado não pode ter números ou caracteres especiais.")
    private String nome;

    @Min(value = 1, message = "O código do estado não pode ser menor que 1.")
    @Max(value = 100, message = "O código do estado não pode ser maior que 100.")
    int codigoEstado;

    @Valid
    @NotNull(message = "Não pode conter cidade nula.")
    Set<Cidade> cidades;

    public Estado() {
        super();
    }

    public Estado(String nome, int codigoEstado, Set<Cidade> cidades) {
        super();
        this.nome = nome;
        this.codigoEstado = codigoEstado;
        this.cidades = cidades;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoEstado() {
        return this.codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Set<Cidade> getCidades() {
        return this.cidades;
    }

    public void setCidades(Set<Cidade> cidade) {
        this.cidades = cidade;
    }

    public boolean adicionarCidade(Cidade cidade) {
        checkNotNull(cidade, "Cidade não pode ser nula.");
        this.cidades.add(cidade);
        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(codigoEstado).toHashCode();
    }

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
        Estado estado = (Estado) obj;
        return new EqualsBuilder().append(codigoEstado, estado.codigoEstado).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
