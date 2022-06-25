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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAOWithoutConectionTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @InjectMocks
    private ClienteDAO clienteDAO;

    @Mock
    private Connection conexao;

    @Mock
    private PreparedStatement stmt;

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void adicionarCliente_quandoExistirErroNaQuery_devePrintarNoLog() throws SQLException {
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Mockito.when(conexao.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenThrow(SQLException.class);
        this.clienteDAO.adicionaCliente(cliente);
        Assert.assertTrue( outputStreamCaptor.toString().trim().contains("Erro ao adicionar."));

    }

    @Test
    public void getCliente_quandoObterPeloIDErroNaExecucao_deveNaoSalvar() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Cliente cliente = this.clienteDAO.getCliente(1);
        Assert.assertNull(cliente);
    }

    @Test
    public void getCliente_quandoAExecucaoDaQuery_deveRetonarNulo() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Cliente cliente = this.clienteDAO.getCliente("ddiretor");
        Assert.assertNull(cliente);
    }

    @Test
    public void getCliente_quandoExecucaoDaQuery_deveRetornarNull() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        List<Cliente> clientes = this.clienteDAO.getListaClientes();
        Assert.assertNull(clientes);
    }
    @Test
    public void altera_quandoExecutarUpdate_devePrintarNoLog() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");
        this.clienteDAO.altera(cliente);
        Assert.assertTrue( outputStreamCaptor.toString().trim().contains("Erro ao alterar."));
    }

    @Test
    public void remove_quandoExecutarUpdate_devePrintarNoLog() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Cliente cliente = new Cliente("Marcos Pereira","184.196.967-22","Avenida Tancredo","mpereira","12345");
        this.clienteDAO.remove(cliente);
        Assert.assertTrue( outputStreamCaptor.toString().trim().contains("Erro ao remover."));
    }



    }
