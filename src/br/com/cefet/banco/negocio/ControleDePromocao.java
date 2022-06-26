package br.com.cefet.banco.negocio;

import br.com.cefet.banco.exceptions.PromocaoFuncionarioException;

public class ControleDePromocao {
	
		boolean err = false;
		String errMessage = "Não foram encontrados erros";
	
		public void promoverFuncionario(Funcionario promovente, Funcionario promovido, int novoCargo) throws PromocaoFuncionarioException {
			
			if(!funcionariosSaoIguais(promovente, promovido)) {
				if(promovente.getCargo() > promovido.getCargo()) {
					if(promovente.getCargo() > novoCargo) {
						
						promovido.setCargo(novoCargo);
						
					}else {
						this.err = true;
						this.errMessage = "Não é possível um funcionário promover outro funcionário para um cargo maior ou igual ao seu";
					}
				}else {
					this.err = true;
					this.errMessage = "Não é possível um funcionário promover outro funcionário de cargo maior ou igual";
				}
			}else {
				this.err = true;
				this.errMessage = "Não é possível um funcionário se autopromover";
			}
			
			if( this.err ) {
				declararErro();
			}
			
		}
		
		public void declararErro() throws PromocaoFuncionarioException {
			throw new PromocaoFuncionarioException(this.errMessage);
		}
		
		public boolean funcionariosSaoIguais(Funcionario funcionario1, Funcionario funcionario2) {
			boolean igual = (funcionario1.getId() == funcionario2.getId()) ? true: false; 
			return igual;
		}

}
