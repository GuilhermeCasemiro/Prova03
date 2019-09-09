package br.com.contmatic.testes;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.apache.commons.lang.RandomStringUtils.random;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.fixtures.Fixtures;
import br.com.contmatic.models.Contato;
import br.com.contmatic.models.Endereco;
import br.com.contmatic.models.Pessoa;
import br.com.six2six.fixturefactory.Fixture;

/**
 * The Class PessoaTest.
 */
public class PessoaTest {

    /** The validator. */
    private Validator validator;

    /** The factory. */
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Pessoa pessoa = Fixture.from(Pessoa.class).gimme("PessoaFixture");

    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
        Fixtures.setUp();
    }

    /* Testes do Nome */

    /**
     * Nao deve ter nome nulo.
     */
    @Test
    public void nao_deve_ter_nome_nulo() {

        pessoa.setNome(null);
        assertFalse(isValid(pessoa, "Nome é obrigatório"));
    }

    /**
     * Nao deve ter nome vazio.
     */
    @Test()
    public void nao_deve_ter_nome_vazio() {

        pessoa.setNome(EMPTY);
        assertFalse(isValid(pessoa, "Nome é obrigatório"));
    }

    /**
     * Deve aceitar se conter nome valido.
     */
    @Test
    public void deve_aceitar_se_conter_nome_valido() {

        pessoa.setNome("Guilherme");
        assertTrue(isValid(pessoa, "Guilherme"));
    }

    @Test
    public void pode_aceitar_nome_com_acentos() {
        pessoa.setNome("Márcio");
        assertTrue(isValid(pessoa, "Márcio"));
    }

    /**
     * Nao deve aceitar se conter nome invalido.
     */
    @Test
    public void nao_deve_aceitar_nome_com_a_primeira_letra_miniscula() {

        pessoa.setNome("mÁRCIO");
        assertFalse(isValid(pessoa, "Nome não pode ter números ou caracteres especiais."));
    }

    /**
     * Nao deve aceitar nome com numeros.
     */
    @Test
    public void nao_deve_aceitar_nome_com_numeros() {

        pessoa.setNome("Guilherm3");
        assertFalse(isValid(pessoa, "Nome não pode ter números ou caracteres especiais."));
    }

    /**
     * Nao deve aceitar nome com menos de 2 caracteres.
     */
    @Test
    public void nao_deve_aceitar_nome_com_menos_de_2_caracteres() {

        pessoa.setNome(random(1));
        assertFalse(isValid(pessoa, "Nome não pode ter menos de 3 ou mais de 30 caracteres."));
    }

    /**
     * Nao deve aceitar nome com mais de 30 caracteres.
     */
    @Test()
    public void nao_deve_aceitar_nome_com_mais_de_30_caracteres() {

        pessoa.setNome(random(60));
        assertFalse(isValid(pessoa, "Nome não pode ter menos de 3 ou mais de 30 caracteres."));
    }

    /**
     * Deve aceitar nomes com mais de 2 caracteres.
     */
    @Test
    public void deve_aceitar_nomes_com_mais_de_2_caracteres() {

        pessoa.setNome(random(10));
        assertTrue(isValid(pessoa, pessoa.getNome()));
    }

    /**
     * Deve aceitar nomes com menos de 30 caracteres.
     */
    @Test
    public void deve_aceitar_nomes_com_menos_de_30_caracteres() {

        pessoa.setNome(random(25));
        assertTrue(isValid(pessoa, pessoa.getNome()));
    }

    /* Testes do Email */

    /**
     * Nao deve ter email nulo.
     */
    @Test
    public void nao_deve_ter_email_nulo() {

        pessoa.setEmail(null);
        assertFalse(isValid(pessoa, "E-mail é obrigatório."));
    }

    /**
     * Deve aceitar email valido.
     */
    @Test
    public void deve_aceitar_email_valido() {

        pessoa.setEmail("guilherme@contmatic.com.br");
        assertTrue(isValid(pessoa, "guilherme@contmatic.com.br"));
    }

    /**
     * Nao deve conter email invalido.
     */
    @Test
    public void nao_deve_conter_email_invalido() {

        pessoa.setEmail("teste@@@contmatic.com.br");
        assertFalse(isValid(pessoa, "Deve ser um e-mail válido."));
    }

    /* Testes dos Contatos */

    /**
     * Nao deve ter contato nulo.
     */
    @Test
    public void nao_deve_ter_contato_nulo() {

        pessoa.setContatos(null);
        assertFalse(isValid(pessoa, "Não pode conter lista de contatos vazia."));
    }

    /**
     * Deve retornar verdadeiro se o contato nao for nulo.
     */
    @Test
    public void deve_retornar_verdadeiro_se_o_contato_nao_for_nulo() {

        Contato contato = Fixture.from(Contato.class).gimme("ContatoFixoFixture");
        assertTrue(pessoa.acicionarContato(contato));
    }

    /**
     * Deve dar erro se o contato for nulo.
     */
    @Test(expected = NullPointerException.class)
    public void deve_dar_erro_se_o_contato_for_nulo() {

        assertNull(pessoa.acicionarContato(null));
    }

    /* Testes dos Endereços */

    /**
     * Nao deve conter endereco nulo.
     */
    @Test
    public void nao_deve_conter_endereco_nulo() {

        pessoa.setEndereco(null);
        assertFalse(isValid(pessoa, "Endereço é obrigatório."));
    }

    /**
     * Deve retornar verdadeiro se o endereco nao for nulo.
     */
    @Test
    public void deve_retornar_verdadeiro_se_o_endereco_nao_for_nulo() {

        Endereco endereco = Fixture.from(Endereco.class).gimme("EnderecoFixture");
        assertTrue(pessoa.acicionarEndereco(endereco));
    }

    /**
     * Deve dar erro se o endereco for nulo.
     */
    @Test(expected = NullPointerException.class)
    public void deve_dar_erro_se_o_endereco_for_nulo() {
        pessoa.setEndereco(null);
        assertNull(pessoa.acicionarEndereco(null));
    }

    /*----------------------------------------------------------------------------------*/

    @Test
    public void deve_retornar_falso_se_o_nome_e_o_email_forem_diferentes() {
        Pessoa pessoa2 = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        pessoa2.setNome("Teste");
        pessoa2.setEmail("doyo@rich-mail.net");
        assertFalse(pessoa.equals(pessoa2));
    }

    @Test
    public void deve_retornar_verdadeiro_se_o_nome_e_o_email_forem_iguais() {
        Pessoa pessoa2 = Fixture.from(Pessoa.class).gimme("PessoaFixture");
        pessoa2.setEmail(pessoa.getEmail());
        pessoa2.setNome(pessoa.getNome());
        assertTrue(pessoa.equals(pessoa2));
    }

    /**
     * Deve respeitar o equals.
     */
    @Test
    public void deve_respeitar_o_equals() {
        assertThat(Pessoa.class, hasValidBeanEqualsFor("nome", "email"));
    }

    /**
     * Deve respeitar o hashcode.
     */
    @Test
    public void deve_respeitar_o_hashcode() {
        assertThat(Pessoa.class, hasValidBeanHashCodeFor("nome", "email"));
    }

    /**
     * Deve respeitar os gets e sets.
     */
    @Test
    public void deve_respeitar_os_gets_e_sets() {
        assertThat(Pessoa.class, hasValidGettersAndSetters());
    }

    /**
     * Deve respeitar o to string.
     */
    @Test
    public void deve_respeitar_o_toString() {

        assertTrue(pessoa.toString().contains("contmatic"));
    }

    /**
     * Checks if is valid.
     *
     * @param pessoa the pessoa
     * @param mensagem the mensagem
     * @return true, if is valid
     */
    public boolean isValid(Pessoa pessoa, String mensagem) {
        validator = factory.getValidator();
        boolean valido = true;
        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        for(ConstraintViolation<Pessoa> constraintViolation : restricoes)
            if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
                valido = false;

        return valido;
    }
}
