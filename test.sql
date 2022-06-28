INSERT into Cliente(idCliente,nome,endereco,cpf,usuario,senha) VALUES
    (1,"Pedro Ferreira","Avenida","184.196.967-22","pferreira","1234"),
    (2,"Amir Japones","Avenida","184.196.967-22","aJapones","1234"),
    (3,"Arthur Pedro","Avenida","184.196.967-22","aPedro","1234"),
    (4,"Kethlyn Ortega","Avenida","184.196.967-22","kOrtega","1234"),
    (5,"Deleted","Avenida","184.196.967-22","dDeleted","1234");


INSERT into Conta(idConta,saldo,limite,Cliente_idCliente,tipo) VALUES
            (1,1000,500,1,0),
            (2,1000,500,1,1),
            (3,1000,100,4,1),
            (5,1000,200,5,1);

insert into Funcionario(`cargo`, `cpf`, `departamento`, `endereco`, `idFuncionario`, `nome`, `salario`, `senha`, `usuario`)
					   values (0,'192.169.90-22', 'Contas', 'Avenida', 2, 'Caixa', 50000, '2566', 'ccaixa'),
                       		  (1,'192.169.90-22', 'Gerencia', 'Avenida', 3, 'Gerente', 50000, '2566', 'ggerente'),
                       		  (2,'192.169.90-22', 'Diretoria', 'Avenida', 1, 'Diretor', 50000, '2566', 'ddiretor'),
                              (0,'192.169.90-22', 'Deleted Caixa', 'Avenida', 4, 'Diretor', 50000, '2566', 'ccaixaD'),
                              (1,'192.169.90-22', 'Deleted Gerencia', 'Avenida', 5, 'Gerente', 50000, '2566', 'ggerenteD'),
                       		  (2,'192.169.90-22', 'Deleted Diretoria', 'Avenida', 6, 'Diretor', 50000, '2566', 'ddiretorD'),
                              (0,'192.168.90-22', 'Login User','Avenida',7,'Diretor',50000,'1234','llogin');
