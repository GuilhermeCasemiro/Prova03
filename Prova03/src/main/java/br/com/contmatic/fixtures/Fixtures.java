package br.com.contmatic.fixtures;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import br.com.contmatic.enums.DDD;
import br.com.contmatic.enums.Telefone;
import br.com.contmatic.models.Contato;
import br.com.contmatic.models.Endereco;
import br.com.contmatic.models.Funcionario;
import br.com.contmatic.models.Pessoa;
import br.com.contmatic.models.PessoaFisica;
import br.com.contmatic.models.Empresa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

/**
 * The Class Fixtures.
 */
public class Fixtures {

    /**
     * Sets the up.
     */
    public static void setUp() {
        gerarContatos();
        gerarEndereco();
        gerarPessoa();
    }

    /**
     * Gerar contatos.
     */
    public static void gerarContatos() {
        Fixture.of(Contato.class).addTemplate("ContatoFixoFixture", new Rule() {
            {
                add("telefone", Telefone.FIXO);
                add("ddd", DDD.values()[new Random().nextInt(DDD.values().length - 1)]);
            }
        });

        Fixture.of(Contato.class).addTemplate("ContatoCelularFixture", new Rule() {
            {
                add("telefone", Telefone.CELULAR);
                add("ddd", DDD.values()[new Random().nextInt(DDD.values().length - 1)]);
            }
        });
    }

    /**
     * Gerar endereco.
     */
    public static void gerarEndereco() {
        Fixture.of(Endereco.class).addTemplate("EnderecoFixture", new Rule() {
            {
                add("bairro", random("Itaquera", "Tatuape", "Ipiranga", "Cangaiba"));
                add("estado", DDD.values()[new Random().nextInt(DDD.values().length - 1)]);
                add("numeroResidencia",  random(Integer.class, range(1, 1000)));
                add("cep", random("13183-010", "78075-705", "79822-040", "29175-652"));
                add("complemento", random("Apartamento 33A", "Casa 03"));
            }
        });
    }

    /**
     * Gerar pessoa.
     */
    public static void gerarPessoa() {
        Fixture.of(Pessoa.class).addTemplate("PessoaFixture", new Rule() {
            {
                add("nome", random("Guilherme", "Márcio", "Ênio", "Ângela"));
                add("email", "teste@contmatic.com.br");
                add("endereco", has(2).of(Endereco.class, "EnderecoFixture"));
                add("contatos", has(2).of(Contato.class, "ContatoFixoFixture"));
            }
        });

        Fixture.of(PessoaFisica.class).addTemplate("PFisicaFixtureMasculino", new Rule() {
            {
                add("cpf", random("39255056441", "28085342057", "83583844246", "34462547900"));
                add("sexo", "Masculino");
                add("dataNascimento", new DateTime(DateTimeZone.UTC));
            }
        });

        Fixture.of(PessoaFisica.class).addTemplate("PFisicaFixtureFeminino", new Rule() {
            {
                add("cpf", random("39255056441", "28085342057", "83583844246", "34462547900"));
                add("sexo", "Feminino");
                add("dataNascimento", new DateTime(DateTimeZone.UTC));
            }
        });

        Fixture.of(Empresa.class).addTemplate("EmpresaFixture", new Rule() {
            {
                add("cnpj", random("25885370000184", "34755982000143", "62155568000139", "38684848000150"));
                add("razaoSocial", random("Lara e Enrico Filmagens", "Benedita e Marina Limpeza", "Helena e André Telas"));
                add("nomeFantasia", random("Western Games", "Nautilidae Games", "Games Mob"));
            }
        });
        Fixture.of(Funcionario.class).addTemplate("FuncionarioFixture", new Rule() {
            {
                add("salario", random(Long.class, range(1000, 9999)));
                add("comissao", random(Long.class, range(0, 500)));
                add("departamento", "Desenvolvimento");
            }
        });
    }
}
