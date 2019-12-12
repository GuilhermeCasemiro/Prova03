package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.Endereco;

public class EnderecoRandom {

    public static Endereco gerarEndereco() {
        EasyRandomParameters parametroEndereco = new EasyRandomParameters();

        parametroEndereco.charset(forName("UTF-8"));
        parametroEndereco.stringLengthRange(5, 1500);
        parametroEndereco.ignoreRandomizationErrors(true);
        parametroEndereco.randomizationDepth(1);
        parametroEndereco.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroEndereco);
        Endereco endereco = easyRandom.nextObject(Endereco.class);
        return endereco;
    }

}
