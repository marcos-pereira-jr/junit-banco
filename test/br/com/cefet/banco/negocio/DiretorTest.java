package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiretorTest {

    @Test
    public void autenticar_quandoSenhaForInteiroIgual_deveRetornarTrue() {
        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        Assert.assertTrue(diretor.autenticar(1234));
    }

    @Test
    public void autenticar_quandoSenhaNaoForInteiroIgual_deveRetornarFalse() {
        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        Assert.assertFalse(diretor.autenticar(123));
    }
    @Test
    public void autenticar_quandoSenhaForStringIgual_deveRetornarTrue() {
        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        Assert.assertTrue(diretor.autenticar("1234"));
    }

    @Test
    public void autenticar_quandoSenhaNaoForStringIgual_deveRetornarFalse() {
        Diretor diretor =  new Diretor("Gerente","Avenida","192.169.90-22", "Contas",   "1234", "ddiretorD", 10000);
        Assert.assertFalse(diretor.autenticar("123"));
    }
}