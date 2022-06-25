INSERT into Cliente(idCliente,nome,endereco,cpf,usuario,senha) VALUES
    (1,"Pedro Ferreira","Avenida","184.196.967-22","pferreira","1234"),
    (2,"Amir Japones","Avenida","184.196.967-22","aJapones","1234"),
    (3,"Arthur Pedro","Avenida","184.196.967-22","aPedro","1234"),
    (4,"Kethlyn Ortega","Avenida","184.196.967-22","kOrtega","1234");


INSERT into Conta(idConta,saldo,limite,Cliente_idCliente,tipo) VALUES
            (1,1000,500,1,0),
            (2,1000,500,1,1),
            (3,1000,100,4,1);