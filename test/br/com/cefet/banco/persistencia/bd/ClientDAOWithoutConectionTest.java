package br.com.cefet.banco.persistencia.bd;

import br.com.cefet.banco.negocio.Cliente;
import br.com.cefet.banco.negocio.Conta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAOWithoutConectionTest {

    @InjectMocks
    private ClienteDAO clienteDAO;

    @Mock
    private Connection conexao;

    @Mock
    private PreparedStatement stmt;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void adicionarCliente_quandoAConexaoErrar_deveNaoEmitirSQLException() throws SQLException {
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");
        Mockito.when(conexao.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenThrow(SQLException.class);
        this.clienteDAO.adicionaCliente(cliente);

    }

    @Test
    public void getCliente_quandoObterPeloIDErroNaExecucao_deveRetonarNulo() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Cliente cliente = this.clienteDAO.getCliente(1);
        Assert.assertNull(cliente);
    }

    @Test
    public void getCliente_quandoObterPeloUsuarioErroNaExecucao_deveRetonarNulo() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Cliente cliente = this.clienteDAO.getCliente("ddiretor");
        Assert.assertNull(cliente);
    }



}
