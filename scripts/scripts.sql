-- Nome: Robert de Almeida Cabral
-- Matricula: 378581
-- FBD - Etapa 3

create database projetofinal;

\c projetofinal

begin;

create table enderecos(
	codigo_endereco serial NOT NULL, 
	uf char(2) NOT NULL,
	cidade varchar(20) NOT NULL,
	bairro varchar(20) NOT NULL,
	rua varchar(100) NOT NULL,
	numero int,
	cep char(8),
	primary key(codigo_endereco)
);

create table medico(
	codigo_medico serial NOT NULL,
	codigo_endereco int NOT NULL,
	nome varchar(30) NOT NULL,
	cpf char(11) NOT NULL,
	data_nasc date,
	telefone varchar(15),
	crm varchar(10) NOT NULL,
	primary key(codigo_medico),
	foreign key(codigo_endereco) references enderecos(codigo_endereco)
);

create table doenca(
	codigo_doenca serial NOT NULL,
	nome varchar(20) NOT NULL,
	primary key(codigo_doenca)
);

create table paciente(
	codigo_paciente serial NOT NULL,
	codigo_endereco int NOT NULL,
	nome varchar(30) NOT NULL,
	cpf char(11) NOT NULL,
	data_nasc date,
	telefone varchar(15),
	primary key(codigo_paciente),
	foreign key(codigo_endereco) references enderecos(codigo_endereco)
);

create table aux_doenca(
	codigo_doenca int NOT NULL,
	codigo_paciente int NOT NULL,
	primary key(codigo_doenca,codigo_paciente),
	foreign key(codigo_doenca) references doenca(codigo_doenca),
	foreign key(codigo_paciente) references paciente(codigo_paciente)
);

create table remedio(
	codigo_remedio serial NOT NULL,
	nome varchar(40) NOT NULL,
	nome_original varchar(40),
	tarja varchar(10),
	primary key(codigo_remedio)	
);

create table efeitos_colaterais(
	nome varchar(40),
	id_remedio int,
	foreign key(id_remedio) references remedio(codigo_remedio)
);

create table contra_indicacoes(
	nome varchar(40),
	id_remedio int,
	foreign key(id_remedio) references remedio(codigo_remedio)
);

create table consulta(
	codigo_consulta serial NOT NULL,
	codigo_medico int NOT NULL,
	codigo_paciente int NOT NULL,
	codigo_remedio int ,
	data date NOT NULL,
	qtd_dias int NOT NULL,
	tempo_entre int NOT NULL,
	primary key(codigo_consulta),
	foreign key(codigo_remedio) references remedio(codigo_remedio)
);

-- Insersão
-- endereços medicos
insert into enderecos values(default,'CE','Quixadá','Alto São Francisco','Rua Benigno Bezerra',495,'63900000');
insert into enderecos values(default,'SP','São Paulo','Artur Alvim','Av. Miguel Ignácio Curi',111,'08220000');
insert into enderecos values(default,'BA','Salvador','Nazaré','Ladeira da Fonte das Pedras',NULL,'40050565');
insert into enderecos values(default,'CE','Fortaleza','Castelão','Av. Alberto Craveiro',2901,'60861211');
insert into enderecos values(default,'PE','Recife','Penedo','Rua Belo Jardim',NULL,'54710010');

-- enderecos pacientes
insert into enderecos values(default,'CE','Quixadá','Triângulo','Beco 3 Manoel Pereira de Santana',528,'63908818');
insert into enderecos values(default,'CE','Quixadá','Campo Velho','Travessa Tenente Cravo',621,'63907027');
insert into enderecos values(default,'CE','Quixadá','Centro','Beco Autran Moreno',125,'63900269');
insert into enderecos values(default,'CE','Quixadá','Planalto Renascer','Travessa Santa Luzia',749,'63901020');
insert into enderecos values(default,'CE','Quixadá','Alto São Francisco','Rua Aurenice Bessa de Queiroz Silveira',790,'63908180');


-- pacientes
insert into doenca values(default,'Pneumonia');
insert into doenca values(default,'Rinite');
insert into doenca values(default,'Diabetes');
insert into doenca values(default,'Pressão alta');
insert into doenca values(default,'Gastrite Crônica');

insert into paciente values(default,6,'Eduarda Heloise Cardoso','41810552303','07-07-1967','88982169945');
insert into paciente values(default,7,'Tiago Victor Enzo Santos','98882742369','09-12-1969','88991160353');
insert into paciente values(default,8,'César Nelson das Neves','37902022314','09-11-1954','88981214842');
insert into paciente values(default,9,'Sueli Eduarda Moura','69487416307','25-04-1979','88984641844');
insert into paciente values(default,10,'Felipe Arthur Edson Brito','52719314390','21-11-1999','88985075663');

insert into aux_doenca values(1, 2);
insert into aux_doenca values(3, 4);
insert into aux_doenca values(2, 3);
insert into aux_doenca values(2, 1);
insert into aux_doenca values(4, 5);
insert into aux_doenca values(4, 1);

-- medicos
insert into medico values(default,1,'Robert de Almeida Cabral','03532351307','06-07-1998','88998659031','00000000');
insert into medico values(default,2,'Henrique Rodrigo da Rosa','11409638308','06-12-1996','88984845354','11111111');
insert into medico values(default,3,'Matheus Manuel Assis','07731707318','27-10-1996','88997499466','22222222');
insert into medico values(default,4,'César José Nicolas Lima','12557391344','03-07-1983','88988213739','33333333');
insert into medico values(default,5,'Rafaela Liz dos Santos','96257054303','13-11-1953','88985274153','44444444');

-- tarja amarela
insert into remedio values(default,'Tamiflu','Oseltamivir','Amarela');
insert into remedio values(default,'Triancil',NULL,'Vermelha');

-- tarja vermelha
insert into efeitos_colaterais values('Retenção de líquidos',2);
insert into efeitos_colaterais values('Fraqueza muscular',2);
insert into efeitos_colaterais values('Perda de massa muscular',2);

insert into contra_indicacoes values('Pacientes com tuberculose',2);
insert into contra_indicacoes values('Pacientes com micoses sistêmicas',2);
insert into contra_indicacoes values('Pacientes com problemas psiquiátricos',2);

insert into remedio values(default,'Furosemida',NULL,'Sem tarja');
insert into remedio values(default,'Diazepam',NULL,'Preta');

-- consulta
insert into consulta values(default,1,5,1,'29-10-2018',10,480);
insert into consulta values(default,1,4,3,'29-10-2018',3,600);
insert into consulta values(default,3,1,4,'29-10-2018',15,720);
insert into consulta values(default,2,2,2,'19-07-2017',30,720);
insert into consulta values(default,4,5,4,'19-05-2019',10,360);
insert into consulta values(default,3,2,4,'09-06-2014',10,360);

commit;

create or replace view contra as (
select r.nome nome_remedio ,ec.nome nome_contra,r.codigo_remedio
from remedio r right outer join contra_indicacoes ec 
on (r.codigo_remedio = ec.id_remedio)
);

create or replace view efeitos as(           
select r.nome nome_remedio,ec.nome nome_efeito, r.codigo_remedio
from remedio r right outer join efeitos_colaterais ec 
on (r.codigo_remedio = ec.id_remedio)
);

-- endereco
create or replace function remove_end() returns trigger as $$
	Begin
		delete from medico where codigo_endereco=OLD.codigo_endereco;
		delete from paciente where codigo_endereco=OLD.codigo_endereco;
		return OLD;
	end;
$$ language plpgsql;

create trigger remove_endereco
before delete on enderecos
for each row execute procedure remove_end();

-- paciente
create or replace function remove_pac() returns trigger as $$
	Begin
		delete from aux_doenca where codigo_paciente=OLD.codigo_paciente;
		delete from consulta where codigo_paciente=OLD.codigo_paciente;
		return OLD;
	end;
$$ language plpgsql;

create trigger remove_paciente
before delete on paciente
for each row execute procedure remove_pac();

-- medico
create or replace function remove_med() returns trigger as $$
	Begin	
		delete from consulta where codigo_medico=OLD.codigo_medico;
		return OLD;
	end;
$$ language plpgsql;

create trigger remove_medico
before delete on medico
for each row execute procedure remove_med();

--remedio
create or replace function remove_rem() returns trigger as $$
	Begin	
		delete from efeitos_colaterais where id_remedio=OLD.codigo_remedio;
		delete from contra_indicacoes where id_remedio=OLD.codigo_remedio;
		delete from consulta where codigo_remedio=OLD.codigo_remedio;
		return OLD;
	end;
$$ language plpgsql;

create trigger remove_remedio
before delete on remedio
for each row execute procedure remove_rem();


CREATE FUNCTION  doencas(codigo_paciente integer) RETURNS setof record AS $$
  DECLARE
  --  declaraçõe
  BEGIN        
  	return query select ax.codigo_doenca,d.nome from paciente p, doenca d, aux_doenca ax where p.codigo_paciente = ax.codigo_paciente and d.codigo_doenca = ax.codigo_doenca and p.codigo_paciente = $1;
  	return;
  END;
$$  LANGUAGE plpgsql;

-- consulta pesquisa normal
CREATE FUNCTION  consulta_paciente(codigo_paciente integer) RETURNS setof record AS $$
  DECLARE
  --  declaraçõe
  BEGIN        
  	return query select distinct c.codigo_consulta, m.codigo_medico,p.codigo_paciente,r.codigo_remedio,c.tempo_entre,c.qtd_dias,c.data
				from medico m left join consulta c on c.codigo_medico = m.codigo_medico
				left join paciente p on c.codigo_paciente = p.codigo_paciente and p.codigo_paciente = $1
				left join aux_doenca ad on ad.codigo_paciente = p.codigo_paciente
				right join doenca d on ad.codigo_doenca = d.codigo_doenca
				left join remedio r on c.codigo_remedio = r.codigo_remedio
				order by c.codigo_consulta;
  	return;
  END;
$$  LANGUAGE plpgsql;

-- consulta paciente e medico
CREATE FUNCTION  consulta_paciente_medico(codigo_paciente integer, codigo_medico integer) RETURNS setof record AS $$
  DECLARE
  --  declaraçõe
  BEGIN        
  	return query select distinct c.codigo_consulta, m.codigo_medico,p.codigo_paciente,r.codigo_remedio,c.tempo_entre,c.qtd_dias,c.data
				from medico m left join consulta c on c.codigo_medico = m.codigo_medico and m.codigo_medico = $2
				left join paciente p on c.codigo_paciente = p.codigo_paciente and p.codigo_paciente = $1
				left join aux_doenca ad on ad.codigo_paciente = p.codigo_paciente
				right join doenca d on ad.codigo_doenca = d.codigo_doenca
				left join remedio r on c.codigo_remedio = r.codigo_remedio
				order by c.codigo_consulta;
  	return;
  END;
$$  LANGUAGE plpgsql;


CREATE FUNCTION  consulta_medico(codigo_medico integer) RETURNS setof record AS $$
  DECLARE
  --  declaraçõe
  BEGIN        
  	return query select distinct c.codigo_consulta, m.codigo_medico,p.codigo_paciente,r.codigo_remedio,c.tempo_entre,c.qtd_dias,c.data
				from medico m left join consulta c on c.codigo_medico = m.codigo_medico and m.codigo_medico = $1
				left join paciente p on c.codigo_paciente = p.codigo_paciente
				left join aux_doenca ad on ad.codigo_paciente = p.codigo_paciente
				right join doenca d on ad.codigo_doenca = d.codigo_doenca
				left join remedio r on c.codigo_remedio = r.codigo_remedio
				order by c.codigo_consulta;
  	return;
  END;
$$  LANGUAGE plpgsql;


-- usuarios permissões

create user robert with password 'fbd2018';
create role admin;

grant all privileges on database projetofinal to admin;
grant usage on schema public to admin;
grant all privileges on all tables in schema public to admin;
grant all privileges on all sequences in schema public to admin;
grant admin to robert;

create user joao with password 'fbd2018';
create role reader;

grant select on all tables in schema public to reader;
grant select on all sequences in schema public to reader;
grant reader to joao;
