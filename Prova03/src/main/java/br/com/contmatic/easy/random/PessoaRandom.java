package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.Pessoa;

public class PessoaRandom {

    public static Pessoa gerarPessoa() {
        EasyRandomParameters parametroPessoa = new EasyRandomParameters();
        
        parametroPessoa.charset(forName("UTF-8"));
        parametroPessoa.collectionSizeRange(2, 5);
        parametroPessoa.stringLengthRange(3, 30);
        parametroPessoa.ignoreRandomizationErrors(true);
        parametroPessoa.randomizationDepth(5);
        parametroPessoa.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroPessoa);
        Pessoa pessoa = easyRandom.nextObject(Pessoa.class);
        return pessoa;
    }
}
