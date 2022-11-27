use desafioapi;

insert into tb_perfis(nome) values ("admin");

insert into tb_perfis(nome) values ("user");
    
insert into tb_usuario (email, password, username)
	values ("michel@gft.com", "$2a$10$9xvk7rb6tQy.u/aXZlw.F.sj.FIg/YM5tJ2shz9HwbfhOtrLoHkjK", "Michel");
  
insert into tb_usuario_perfil(usuario_id, perfil_id) values (1,1);
	  
insert into tb_usuario(email, password, username)
	values ("natalia@gft.com","$2a$10$i8UQM.w1m.CM.8uVSfH4dO7PTw4LhGXVJnyJRvQJfZGYC8GJqd6Se", "Natalia");
   
insert into tb_usuario_perfil(usuario_id, perfil_id) values (2,2);
	
insert into tb_usuario(email, password, username)
	values("rodrigo@gft.com", "$2a$10$oUr4ilAX2EqlDQ8yit4jY.LdSshUanhq.yA1FGx8PGxl49oOSfk9.", "Rodrigo");

insert into tb_usuario_perfil(usuario_id, perfil_id) values (3,1);

insert into tb_usuario(email, password, username)
	values("leticia@gft.com", "$2a$10$OEpyuMUxJj2LtkASl99rQ.NN.idF/pTsZpTzRfTGWhtwROMxhH7bm", "Leticia");

insert into tb_usuario_perfil(usuario_id, perfil_id) values (4,2);

insert into tb_etiqueta(nome) values ("saoPaulo");
    
insert into tb_etiqueta(nome) values ("futebol");

insert into tb_etiqueta(nome) values ("jornalismo");
    