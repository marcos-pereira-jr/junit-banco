package br.com.cefet.banco.negocio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void autenticar_quandoASenhaForValida_deveRetornarTrue() {
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");
        Assert.assertTrue(cliente.autenticar("12345"));
    }

    @Test
    public void autenticar_quandoASenhaForInvalida_deveRetornarFalse() {
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");
        Assert.assertFalse(cliente.autenticar("12345s"));
    }
}