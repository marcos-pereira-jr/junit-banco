package br.com.cefet.banco.negocio;

import br.com.cefet.banco.exceptions.PromocaoFuncionarioException;
import br.com.cefet.banco.persistencia.bd.FuncionarioDAO;

public class ControleDePromocao {
	
		boolean err = false;
		String errMessage = "Não foram encontrados erros";
	
		public void promoverFuncionario(Funcionario promovente, Funcionario promovido) throws PromocaoFuncionarioException {
			
			int novoCargo = promovido.getCargo()+1;
			
			if(!funcionariosSaoIguais(promovente, promovido)) {
				if(promovente.getCargo() > promovido.getCargo()) {
						promovido.setCargo(novoCargo);
						
						FuncionarioDAO funcDao = new FuncionarioDAO();
						funcDao.altera(promovido);
						
				}else {
					this.err = true;
					this.errMessage = "Não é possível um funcionário promover outro funcionário de cargo maior ou igual";
				}
			}else {
				this.err = true;
				this.errMessage = "Não é possível um funcionário se autopromover";
			}
			
			if( this.err ) {
				throw new PromocaoFuncionarioException(this.errMessage);
			}
			
		}
		
		private boolean funcionariosSaoIguais(Funcionario funcionario1, Funcionario funcionario2) {
			boolean igual = (funcionario1.getId() == funcionario2.getId()) ? true: false; 
			return igual;
		}

}
