package br.com.cefet.banco.persistencia.bd;

import br.com.cefet.banco.negocio.Cliente;
import br.com.cefet.banco.negocio.Diretor;
import br.com.cefet.banco.negocio.Funcionario;
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

public class FuncionarioDAOWithoutConectionTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @InjectMocks
    private FuncionarioDAO funcionarioDAO;

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
    public void adicionarFuncionario_quandoExitirErroNaQuery_devePrintarNoLog() throws SQLException {
        Diretor diretor =  new Diretor("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ddiretor", 20000);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Mockito.when(conexao.prepareStatement(Mockito.anyString(), Mockito.anyInt())).thenThrow(SQLException.class);
        this.funcionarioDAO.adicionaFuncionario(diretor);
        Assert.assertTrue( outputStreamCaptor.toString().trim().contains("Erro ao adicionar."));

    }

    @Test
    public void getFuncionario_quandoObterPeloIDErroNaExecucao_deveNaoSalvar() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Funcionario funcionario = this.funcionarioDAO.getFuncionario(1);
        Assert.assertNull(funcionario);
    }

    @Test
    public void getCliente_quandoAExecucaoDaQuery_deveRetonarNulo() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Funcionario funcionario = this.funcionarioDAO.getFuncionario("ddiretor");
        Assert.assertNull(funcionario);
    }

    @Test
    public void altera_quandoExecutarUpdate_devePrintarNoLog() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Diretor diretor =  new Diretor("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ddiretor", 20000);
        diretor.setId(1);
        this.funcionarioDAO.altera(diretor);
        Assert.assertTrue( outputStreamCaptor.toString().trim().contains("Erro ao alterar."));
    }

    @Test
    public void remove_quandoExecutarUpdate_devePrintarNoLog() throws SQLException {
        Mockito.when(conexao.prepareStatement(Mockito.anyString())).thenThrow(SQLException.class);
        Mockito.doThrow(new SQLException()).when(conexao).close();
        Diretor diretor =  new Diretor("Caixa","Avenida","192.169.90-22", "Contas",   "1234", "ddiretor", 20000);
        diretor.setId(1);
        this.funcionarioDAO.remove(diretor);
        Assert.assertTrue( outputStreamCaptor.toString().trim().contains("Erro ao remover."));
    }
}
