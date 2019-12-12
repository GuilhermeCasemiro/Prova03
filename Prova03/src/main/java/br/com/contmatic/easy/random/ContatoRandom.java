package br.com.contmatic.easy.random;

import static java.nio.charset.Charset.forName;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.models.Contato;

public class ContatoRandom {
    
    public static Contato gerarContato() {
        EasyRandomParameters parametroContato = new EasyRandomParameters();
        
        parametroContato.charset(forName("UTF-8"));
        parametroContato.ignoreRandomizationErrors(true);
        parametroContato.overrideDefaultInitialization(false);

        EasyRandom easyRandom = new EasyRandom(parametroContato);
        Contato contato = easyRandom.nextObject(Contato.class);
        return contato;
    }
}
