package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.ValueGenerator;

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.PessoaFisica;
import br.com.six2six.fixturefactory.Fixture;

// TODO: Auto-generated Javadoc
/**
 * The Class PessoaFisicaTest.
 */
public class PessoaFisicaTest {

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
    public void nao_deve_aceitar_cpf_nulo_ou_em_branco() {
        PessoaFisica Pfisica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertNotNull(Pfisica.getCpf());
    }

    /**
     * Nao deve permitir cpf com mais de 11 caracteres.
     */
    @Test
    public void nao_deve_permitir_cpf_com_mais_de_11_caracteres() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertThat(pfFsica.getCpf().length(), is(not((12))));
    }

    /**
     * Nao deve permitir cpf com menos de 11 caracteres.
     */
    @Test
    public void nao_deve_permitir_cpf_com_menos_de_11_caracteres() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertThat(pfFsica.getCpf().length(), is(not((10))));
    }

    /**
     * So deve aceitar cpf com 11 caracteres.
     */
    @Test
    public void so_deve_aceitar_cpf_com_11_caracteres() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertTrue(pfFsica.getCpf().length() == 11);
    }

    /**
     * Nao deve aceitar data de nascimento nulo.
     */
    @Test
    public void nao_deve_aceitar_data_de_nascimento_nulo() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertNotNull(pfFsica.getDataNascimento());
    }

    /**
     * Nao deve aceitar sexo nulo.
     */
    @Test
    public void nao_deve_aceitar_sexo_nulo() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertNotNull(pfFsica.getSexo());
    }

    /**
     * Nao deve aceitar sexo com menos de 8 caracteres.
     */
    @Test
    public void nao_deve_aceitar_sexo_com_menos_de_8_caracteres() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertThat(pfFsica.getSexo().length(), is(not(7)));
    }

    /**
     * Nao deve aceitar sexo com mais de 9 caracteres.
     */
    @Test
    public void nao_deve_aceitar_sexo_com_mais_de_9_caracteres() {
        PessoaFisica pfFsica = Fixture.from(PessoaFisica.class).gimme("PFisicaFixtureMasculino");
        assertThat(pfFsica.getSexo().length(), is(not(10)));
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
        gerarData();
        assertThat(PessoaFisica.class, hasValidBeanEquals());
    }

    /**
     * Deve aceitar as regras do hashcode.
     */
    @Test
    public void deve_aceitar_as_regras_do_hashcode() {
        gerarData();
        assertThat(PessoaFisica.class, hasValidBeanHashCode());
    }

    /**
     * Deve aceitar as regras do construtor.
     */
    @Test
    public void deve_aceitar_as_regras_do_construtor() {
        assertThat(PessoaFisica.class, hasValidBeanConstructor());
    }

    // @Test
    // public void deve_aceitar_as_regras_do_toString() {
    // assertThat(PessoaFisica.class, hasValidBeanToString());
    // }

}
