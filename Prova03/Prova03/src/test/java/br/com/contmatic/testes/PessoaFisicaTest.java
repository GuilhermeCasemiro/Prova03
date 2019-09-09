package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.ValueGenerator;

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.PessoaFisica;
import br.com.six2six.fixturefactory.Fixture;

/**
 * The Class PessoaFisicaTest.
 */
public class PessoaFisicaTest {

    /** The validator. */
    private Validator validator;

    /** The factory. */
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    PessoaFisica pessoaF = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }

    /**
     * Gerar data.
     */
    public void gerarData() {
        registerValueGenerator(new ValueGenerator<DateTime>() {
            public DateTime generate() {
                return new DateTime(new Random().nextLong()).withMillisOfSecond(0);
            }
        }, DateTime.class);
    }

    /**
     * Nao deve aceitar cpf nulo ou em branco.
     */
    @Test
    public void nao_deve_aceitar_cpf_nulo() {
        pessoaF.setCpf(null);
        assertFalse(isValid(pessoaF, "CPF obrigatório."));
    }

    @Test
    public void nao_deve_aceitar_cpf_em_branco() {
        pessoaF.setCpf(EMPTY);
        assertFalse(isValid(pessoaF, "CPF obrigatório."));
    }

    @Test
    public void deve_aceitar_um_cpf_valido() {
        pessoaF.setCpf("40128625023");
        assertTrue(isValid(pessoaF, pessoaF.getCpf()));
    }

    /**
     * Nao deve permitir cpf com mais de 11 caracteres.
     */
    @Test
    public void nao_deve_permitir_cpf_com_mais_de_11_caracteres() {
        pessoaF.setCpf(random(12, false, true));
        assertFalse(isValid(pessoaF, "CPF deve conter exatamente 11 caracteres."));
    }

    /**
     * Nao deve permitir cpf com menos de 11 caracteres.
     */
    @Test
    public void nao_deve_permitir_cpf_com_menos_de_11_caracteres() {
        pessoaF.setCpf(random(9, false, true));
        assertFalse(isValid(pessoaF, "CPF deve conter exatamente 11 caracteres."));
    }

    /**
     * So deve aceitar cpf com 11 caracteres.
     */
    @Test
    public void so_deve_aceitar_cpf_com_11_caracteres() {
        pessoaF.setCpf(random(11, false, true));
        assertTrue(isValid(pessoaF, pessoaF.getCpf()));
    }

    /**
     * Nao deve aceitar data de nascimento nulo.
     */
    @Test
    public void nao_deve_aceitar_data_de_nascimento_nulo() {
        pessoaF.setDataNascimento(null);
        assertFalse(isValid(pessoaF, "A data de nascimento não pode ser nula."));
    }

    @Test
    public void nao_deve_aceitar_data_de_nascimento_maior_que_a_atual() {
        pessoaF.setDataNascimento(new DateTime(2019, 10, 31, 15, 17));
        assertFalse(isValid(pessoaF, "A data não pode ser mais recente que a atual."));
    }

    /**
     * Nao deve aceitar sexo nulo.
     */
    @Test
    public void nao_deve_aceitar_sexo_nulo() {
        pessoaF.setSexo(null);
        assertFalse(isValid(pessoaF, "Sexo obrigatório."));
    }

    @Test
    public void nao_deve_aceitar_sexo_com_numeros() {
        pessoaF.setSexo(random(8, false, true));
        assertFalse(isValid(pessoaF, "Não pode conter acentos, caracteres especiais e números no sexo."));
    }

    @Test
    public void nao_deve_aceitar_sexo_com_acentos() {
        pessoaF.setSexo("Fêminino");
        assertFalse(isValid(pessoaF, "Não pode conter acentos, caracteres especiais e números no sexo."));
    }

    @Test
    public void nao_deve_aceitar_sexo_com_caracteres_especiais() {
        pessoaF.setSexo("F#####o");
        assertFalse(isValid(pessoaF, "Não pode conter acentos, caracteres especiais e números no sexo."));
    }

    /**
     * Nao deve aceitar sexo com menos de 8 caracteres.
     */
    @Test
    public void nao_deve_aceitar_sexo_com_menos_de_8_caracteres() {
        pessoaF.setSexo(random(7, true, false));
        assertFalse(isValid(pessoaF, "Sexo deve ter no mínimo 8 caracteres e no máximo 9."));
    }

    /**
     * Nao deve aceitar sexo com mais de 9 caracteres.
     */
    @Test
    public void nao_deve_aceitar_sexo_com_mais_de_9_caracteres() {
        pessoaF.setSexo(random(10, true, false));
        assertFalse(isValid(pessoaF, "Sexo deve ter no mínimo 8 caracteres e no máximo 9."));
    }

    @Test
    public void nao_deve_aceitar_sexo_com_a_primeira_letra_miniscula() {
        pessoaF.setSexo("mASCULINO");
        assertFalse(isValid(pessoaF, "Não pode conter acentos, caracteres especiais e números no sexo."));
    }

    @Test
    public void deve_aceitar_um_sexo_valido() {
        pessoaF.setSexo("Masculino");
        assertTrue(isValid(pessoaF, "Masculino"));
    }

    @Test
    public void deve_retornar_falso_se_os_cpfs_forem_diferentes() {
        pessoaF.setCpf("02656650003");
        PessoaFisica pessoaFisica = new PessoaFisica("15448873022");
        assertFalse(pessoaF.equals(pessoaFisica));
    }

    /**
     * Validar gets e sets.
     */
    @Test
    public void validar_gets_e_sets() {
        gerarData();
        assertThat(PessoaFisica.class, hasValidGettersAndSetters());
    }

    /**
     * Deve aceitar as regras do equals.
     */
    @Test
    public void deve_aceitar_as_regras_do_equals() {
        assertThat(PessoaFisica.class, hasValidBeanEqualsFor("cpf"));
    }

    /**
     * Deve aceitar as regras do hashcode.
     */
    @Test
    public void deve_aceitar_as_regras_do_hashcode() {
        assertThat(PessoaFisica.class, hasValidBeanHashCodeFor("cpf"));
    }

    /**
     * Deve aceitar as regras do construtor.
     */
    @Test
    public void deve_aceitar_as_regras_do_construtor() {
        assertThat(PessoaFisica.class, hasValidBeanConstructor());
    }

    /**
     * Deve aceitar as regras do to string.
     */
    @Test
    public void deve_aceitar_as_regras_do_toString() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertTrue(pfFsica.toString().contains("Masculino"));
    }

    public boolean isValid(PessoaFisica pessoaF, String mensagem) {
        validator = factory.getValidator();
        boolean valido = true;
        Set<ConstraintViolation<PessoaFisica>> restricoes = validator.validate(pessoaF);
        for(ConstraintViolation<PessoaFisica> constraintViolation : restricoes)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        return valido;
    }

}
