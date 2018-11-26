package br.ufc.alu.robertcabral.main;

import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufc.alu.robertcabral.dao.ConsultaDAO;
import br.ufc.alu.robertcabral.dao.DoencaDAO;
import br.ufc.alu.robertcabral.dao.EnderecoDAO;
import br.ufc.alu.robertcabral.dao.MedicoDAO;
import br.ufc.alu.robertcabral.dao.PacienteDAO;
import br.ufc.alu.robertcabral.dao.RemedioDAO;
import br.ufc.alu.robertcabral.entity.Consulta;
import br.ufc.alu.robertcabral.entity.ContraIndicacoes;
import br.ufc.alu.robertcabral.entity.Doenca;
import br.ufc.alu.robertcabral.entity.EfeitosColaterais;
import br.ufc.alu.robertcabral.entity.Enderecos;
import br.ufc.alu.robertcabral.entity.Medico;
import br.ufc.alu.robertcabral.entity.Paciente;
import br.ufc.alu.robertcabral.entity.Remedio;

public class Principal {

	public static final int
	CADASTRAR_ENDERECO = 1,
	CADASTRAR_PACIENTE = 2,
	CADASTRAR_MEDICO = 3,
	CADASTRAR_CONSULTA = 4,
	ATUALIZAR_PACIENTE = 5,
	ATUALIZAR_MEDICO = 6,
	ATUALIZAR_ENDERECO = 7,
	BUSCAR_ENDERECO = 8,
	BUSCAR_PACIENTE = 9,
	BUSCAR_MEDICO = 10,
	BUSCAR_CONSULTA	= 11,
	BUSCAR_REMEDIO = 12,
	DELETAR_ENDERECO = 13,
	DELETAR_PACIENTE = 14,
	DELETAR_MEDICO = 15,
	DELETAR_CONSULTA = 16,
	DELETAR_REMEDIO = 17;		

	public static void main(String[] args) {

		int op = 0;

		PacienteDAO pDao = new PacienteDAO();
		MedicoDAO mDao = new MedicoDAO();
		EnderecoDAO eDao = new EnderecoDAO();
		RemedioDAO rDao = new RemedioDAO();
		ConsultaDAO cDao = new ConsultaDAO();

		String menu = "Bem vindo!!\n\n"
				+ "Digite a opção para selecionar:\n"
				+ "1 - Cadastrar endereço;\n"
				+ "2 - Cadastrar paciente;\n"
				+ "3 - Cadastrar medico;\n"
				+ "4 - Cadastrar consulta;\n"
				+ "5 - Atualizar paciente;\n"
				+ "6 - Atualizar medico;\n"
				+ "7 - Atualizar endereço;\n"
				+ "8 - Buscar endereço;\n"
				+ "9 - Buscar paciente;\n"
				+ "10 - Buscar medico;\n"
				+ "11 - Buscar consulta;\n"
				+ "12 - Buscar remedio;\n"
				+ "13 - Deletar endereço;\n"
				+ "14 - Deletar paciente;\n"
				+ "15 - Deletar medico;\n"
				+ "16 - Deletar consulta;\n"
				+ "17 - Deletar remedio;\n"
				+ "0 - Sair";        

		do {
			op = Integer.parseInt((String) JOptionPane.showInputDialog(null,menu,"Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));			
			switch (op) {
			case CADASTRAR_ENDERECO:
				Enderecos enderecos  = cadastrarEndereco();
				eDao.save(enderecos);
				break;
			case CADASTRAR_PACIENTE:
				Paciente paciente = cadastrarPaciente();
				pDao.save(paciente);
				break;
			case CADASTRAR_MEDICO:
				Medico medico = cadastrarMedico();
				mDao.save(medico);
				break;
			case CADASTRAR_CONSULTA:
				Consulta consulta = cadastrarConsulta();
				cDao.save(consulta);
				break;
			case ATUALIZAR_PACIENTE:
				Paciente p = pDao.findByCPF(JOptionPane.showInputDialog("Digite o CPF da pessoa a ser alterada: "));
				if(p == null) {
					JOptionPane.showMessageDialog(null, "Paciente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					atualizarPaciente(p);
					pDao.save(p);
				}
				break;
			case ATUALIZAR_MEDICO:
				Medico m = mDao.findByCRM(JOptionPane.showInputDialog("Digite o CRM do medico a ser alterado: "));
				if(m == null) {
					JOptionPane.showMessageDialog(null, "Medico não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					atualizarMedico(m);
					mDao.save(m);
				}
				break;
			case ATUALIZAR_ENDERECO:
				Enderecos e = eDao.findByID(Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do endereço: ")));
				if(e == null) {
					JOptionPane.showMessageDialog(null, "Endereço não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					atualizarEndereco(e);
					eDao.save(e);
				}
				break;
			case BUSCAR_ENDERECO:
				menuBuscarEndereco();
				break;
			case BUSCAR_PACIENTE:
				menuBuscarPaciente();
				break;
			case BUSCAR_MEDICO:
				menuBuscarMedico();
				break;
			case BUSCAR_CONSULTA:
				menuBuscarConsulta();
				break;
			case BUSCAR_REMEDIO:
				menuBuscarRemedio();
				break;
			case DELETAR_ENDERECO:
				int codEnd = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do endereço: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				eDao.delete(codEnd);
				break;
			case DELETAR_PACIENTE:
				int codPac = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do paciente: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				pDao.delete(codPac);
				break;
			case DELETAR_MEDICO:
				int codMed = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do medico: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				mDao.delete(codMed);
				break;
			case DELETAR_CONSULTA:
				int codCon = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo da consulta: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				cDao.delete(codCon);
				break;
			case DELETAR_REMEDIO:
				int codReme = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do remedio: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				rDao.delete(codReme);
				break;
			default:
				break;
			}

		}while(op != 0);
	}

	public static void menuBuscarRemedio() {
		RemedioDAO rDao = new RemedioDAO();
		List<Remedio> list;
		List<EfeitosColaterais> listEfeitos;
		List<ContraIndicacoes> listContras;
		Remedio remedio = new Remedio();

		int op;
		String menu = "Digite a opção para selecionar:\n\n"
				+ "\n1 - Buscar por codigo;"
				+ "\n2 - Buscar por tarja;"
				+ "\n3 - Buscar por nome;"
				+ "\n4 - Buscar efeitos colaterais por nome;"
				+ "\n5 - Buscar contra indicações por nome;"
				+ "\n0 - Voltar ao menu inicial.";

		do {
			op = Integer.parseInt((String) JOptionPane.showInputDialog(null,menu,"Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
			switch (op) {
			case 1:
				int codigo = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				remedio = rDao.findByID(codigo);
				JOptionPane.showMessageDialog(null, remedio);
				break;
			case 2:
				int tarja = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção desejada: \n\n"
						+ "1 - Sem tarja;\n"
						+ "2 - Tarja amarela;\n"
						+ "3 - Tarja vermelha;\n"
						+ "4 - Tarja preta;\n"
						+ "0 - Voltar ao menu anterior."));
				tarja--;
				list = rDao.findByTarja(tarja);
				listaRemedio(list);
				break;
			case 3:
				String nome = JOptionPane.showInputDialog("Digite o nome do remedio: ");
				list = rDao.findByNome(nome);
				listaRemedio(list);				
				break;
			case 4:
				String nome1 = JOptionPane.showInputDialog("Digite o nome do remedio: ");
				listEfeitos = rDao.findEfeitosColaterais(nome1);
				listaEfeitos(listEfeitos);
				break;
			case 5:
				String nome2 = JOptionPane.showInputDialog("Digite o nome do remedio: ");
				listContras = rDao.findContraIndicacoes(nome2);
				listaContra(listContras);
				break;
			}
		}while(op != 0);

	}

	public static void menuBuscarConsulta() {
		ConsultaDAO cDao = new ConsultaDAO();
		List<Consulta> list;

		int op;
		String menu = "Digite a opção para selecionar:\n\n"
				+ "\n1 - Buscar por paciente;"
				+ "\n2 - Buscar por medico;"
				+ "\n3 - Buscar por medico e paciente;"
				+ "\n0 - Voltar ao menu inicial.";

		do {
			op = Integer.parseInt((String) JOptionPane.showInputDialog(null,menu,"Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
			switch (op) {
			case 1:
				int codigo = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				list  = cDao.findByPaciente(new PacienteDAO().findByID(codigo));
				if(list != null)
					listaConsulta(list);
				break;
			case 2:
				int codigo1 = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				list  = cDao.findByMedico(new MedicoDAO().findByID(codigo1));
				if(list != null)
					listaConsulta(list);
				break;
			case 3:
				int codigo2 = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do medico: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				int codigo3 = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do paciente: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				list  = cDao.findByMedicoAndPaciente(new MedicoDAO().findByID(codigo2), new PacienteDAO().findByID(codigo3));
				if(list != null)
					listaConsulta(list);
				break;

			default:
				break;
			}
		} while (op != 0);
	}

	public static void menuBuscarEndereco() {
		EnderecoDAO eDao = new EnderecoDAO();
		String menu = "Digite a opção para selecionar:\n\n"
				+ "\n1 - Buscar por codigo;"
				+ "\n2 - Buscar por nome de rua;"
				+ "\n0 - Voltar ao menu inicial.";

		int op;

		do {
			op = Integer.parseInt((String) JOptionPane.showInputDialog(null,menu,"Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));		
			switch (op) {
			case 1:
				int codigo = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				Enderecos endereco = eDao.findByID(codigo);
				JOptionPane.showMessageDialog(null, endereco);
				break;

			case 2:
				String rua = (String) JOptionPane.showInputDialog(null,"Digite o nome da rua: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,"");
				List<Enderecos> listEnderecos = eDao.findByNome(rua);
				listaEnderecos(listEnderecos);
				break;

			default:
				break;
			}
		} while (op != 0);
	}

	public static void menuBuscarMedico() {
		MedicoDAO mDao = new MedicoDAO();
		Medico medico;
		List<Medico> listMedicos;
		String menu = "Digite a opção para selecionar:\n\n"
				+ "\n1 - Buscar por codigo;"
				+ "\n2 - Buscar por CRM;"
				+ "\n3 - Buscar por nome;"
				+ "\n0 - Voltar ao menu inicial.";

		int op;

		do {
			op = Integer.parseInt((String) JOptionPane.showInputDialog(null,menu,"Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));		
			switch (op) {
			case 1:
				int codigo = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do medico a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				medico = mDao.findByID(codigo);
				JOptionPane.showMessageDialog(null, medico);
				break;

			case 2:
				String cpf = (String) JOptionPane.showInputDialog(null,"Digite o CRM do medico a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,"");
				medico = mDao.findByCRM(cpf);
				JOptionPane.showMessageDialog(null, medico);
				break;

			case 3:
				String nome = (String) JOptionPane.showInputDialog(null,"Digite o nome do medico a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,"");
				listMedicos = mDao.findByNome(nome);
				listaMedico(listMedicos);
				break;
			default:
				break;
			}
		} while (op != 0);
	}

	public static void menuBuscarPaciente() {
		PacienteDAO pDao = new PacienteDAO();
		Paciente paciente = new Paciente();
		List<Paciente> listPacientes;
		List<Doenca> listDoencas;
		String menu = "Digite a opção para selecionar:\n\n"
				+ "\n1 - Buscar por codigo;"
				+ "\n2 - Buscar por CPF;"
				+ "\n3 - Buscar por nome;"
				+ "\n4 - Buscar doenças de determinado paciente;"
				+ "\n5 - Cadastrar doença a um determinado paciente;"
				+ "\n0 - Voltar ao menu inicial.";

		int op;

		do {
			op = Integer.parseInt((String) JOptionPane.showInputDialog(null,menu,"Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));		
			switch (op) {
			case 1:
				int codigo = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do paciente a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				paciente = pDao.findByID(codigo);
				JOptionPane.showMessageDialog(null, paciente);
				break;

			case 2:
				String cpf = (String) JOptionPane.showInputDialog(null,"Digite o CPF do paciente a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,"");
				paciente = pDao.findByCPF(cpf);
				JOptionPane.showMessageDialog(null, paciente);
				break;
			case 3:
				String nome = (String) JOptionPane.showInputDialog(null,"Digite o nome do paciente a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,"");
				listPacientes = pDao.findByNome(nome);
				listaPacientes(listPacientes);
				break;
			case 4:
				int codigo1 = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo do paciente a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
				listDoencas = pDao.findDoencas(codigo1);
				listaDoencas(listDoencas);
				break;
			case 5:
				DoencaDAO dDao = new DoencaDAO();
				Doenca doenca;
				String m = "Menu doenças:\n\n"
						+  "1 - Buscar doença cadastrada;\n"
						+  "2 - Cadastrar nova doença;\n"
						+  "3 - Mostrar doenças;\n"
						+  "4 - Atualizar doença;\n"
						+  "5 - Adicionar doença a paciente;\n"
						+  "0 - Voltar ao menu anterior. ";
				int op1, cod;
				do {
					op1 = Integer.parseInt((String) JOptionPane.showInputDialog(null,m,"Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));	
					switch (op1) {
					case 1:
						cod = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo da doença a ser buscada: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
						doenca = dDao.findByID(cod);
						JOptionPane.showMessageDialog(null, doenca);
						break;
					case 2:
						doenca = new Doenca();
						doenca.setNome(JOptionPane.showInputDialog("Digite o nome da doença"));
						dDao.save(doenca);
						break;	
					case 3:
						List<Doenca> list = dDao.findAll();
						listaDoencas(list);
						break;
					case 4:
						cod = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo da doença a ser buscada: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
						doenca = dDao.findByID(cod);
						String nomeDoenca = doenca.getNome();						
						doenca.setNome(JOptionPane.showInputDialog("Digite o nome:",nomeDoenca));
						dDao.save(doenca);
						break;
					case 5:
						paciente = pDao.findByCPF((String) JOptionPane.showInputDialog(null,"Digite o CPF do paciente a ser buscado: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,""));
						doenca = dDao.findByID(Integer.parseInt((String) JOptionPane.showInputDialog(null,"Digite o codigo da doença a ser buscada: ","Sistema de consultas e medicamentos",JOptionPane.PLAIN_MESSAGE,null,null,"")));
						dDao.addDoencaToPaciente(doenca, paciente);
						break;
					default:
						break;
					}
				} while (op1 != 0);

				break;
			default:
				break;
			}
		} while (op != 0);
	}

	public static Enderecos cadastrarEndereco() {
		Enderecos enderecos = new Enderecos();
		enderecos.setUf(JOptionPane.showInputDialog(null, "Digite o UF:", "Cadastro de endereço", JOptionPane.PLAIN_MESSAGE));
		enderecos.setCidade(JOptionPane.showInputDialog(null, "Digite a Cidade:", "Cadastro de endereço", JOptionPane.PLAIN_MESSAGE));
		enderecos.setBairro(JOptionPane.showInputDialog(null, "Digite o Bairro:", "Cadastro de endereço", JOptionPane.PLAIN_MESSAGE));
		enderecos.setRua(JOptionPane.showInputDialog(null, "Digite a Rua:", "Cadastro de endereço", JOptionPane.PLAIN_MESSAGE));
		enderecos.setNumero(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o Número:", "Cadastro de endereço", JOptionPane.PLAIN_MESSAGE)));
		enderecos.setCep(JOptionPane.showInputDialog(null, "Digite o CEP:", "Cadastro de endereço", JOptionPane.PLAIN_MESSAGE));
		return enderecos;
	}

	public static void atualizarEndereco(Enderecos enderecos) {
		String uf, cidade, bairro, rua, cep;
		int numero;

		uf = enderecos.getUf();
		cidade = enderecos.getCidade();
		bairro = enderecos.getBairro();
		rua = enderecos.getRua();
		cep = enderecos.getCep();
		numero = enderecos.getNumero();

		enderecos.setUf(JOptionPane.showInputDialog("Digite o UF:", uf));
		enderecos.setCidade(JOptionPane.showInputDialog("Digite a Cidade:", cidade));
		enderecos.setBairro(JOptionPane.showInputDialog("Digite o Bairro:", bairro));
		enderecos.setRua(JOptionPane.showInputDialog("Digite a Rua:", rua));
		enderecos.setNumero(Integer.parseInt(JOptionPane.showInputDialog("Digite o Número:", numero)));
		enderecos.setCep(JOptionPane.showInputDialog("Digite o CEP:", cep));
	}

	public static Paciente cadastrarPaciente() {
		Paciente paciente = new Paciente();
		Enderecos enderecos = cadastrarEndereco();    	
		new EnderecoDAO().save(enderecos);
		enderecos.setCodigo(new EnderecoDAO().findID());

		DateFormat f = DateFormat.getDateInstance();

		paciente.setEndereco(enderecos);
		paciente.setNome(JOptionPane.showInputDialog(null, "Digite o nome:", "Cadastro de paciente", JOptionPane.PLAIN_MESSAGE));
		paciente.setCpf(JOptionPane.showInputDialog(null, "Digite o CPF:", "Cadastro de paciente", JOptionPane.PLAIN_MESSAGE));
		try {
			paciente.setData_nascimento(convertUtilToSql(f.parse(JOptionPane.showInputDialog(null, "Digite a data de nascimento(dd/mm/aaaa):", "Cadastro de paciente", JOptionPane.PLAIN_MESSAGE))));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		paciente.setTelefone(JOptionPane.showInputDialog(null, "Digite o Telefone:", "Cadastro de paciente", JOptionPane.PLAIN_MESSAGE));
		return paciente;
	}

	public static void atualizarPaciente(Paciente paciente) {
		DateFormat f = DateFormat.getDateInstance();

		String nome = JOptionPane.showInputDialog("Digite o nome:",paciente.getNome());    	
		paciente.setNome(nome);
		String cpf = JOptionPane.showInputDialog("Digite o CPF:",paciente.getCpf());
		paciente.setCpf(cpf);
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String data = df.format(paciente.getData_nascimento());
			Date date1 = f.parse(JOptionPane.showInputDialog("Digite a data de nascimento(dd/mm/aaaa):",data));
			paciente.setData_nascimento(convertUtilToSql(date1));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String telefone = JOptionPane.showInputDialog("Digite o Telefone:", paciente.getTelefone());
		paciente.setTelefone(telefone);
	}


	public static Medico cadastrarMedico() {
		Medico medico = new Medico();
		Enderecos enderecos = cadastrarEndereco();    	
		new EnderecoDAO().save(enderecos);
		enderecos.setCodigo(new EnderecoDAO().findID());

		DateFormat f = DateFormat.getDateInstance();

		medico.setEndereco(enderecos);
		medico.setNome(JOptionPane.showInputDialog(null, "Digite o nome:", "Cadastro de Medico", JOptionPane.PLAIN_MESSAGE));
		medico.setCpf(JOptionPane.showInputDialog(null, "Digite o CPF:", "Cadastro de Medico", JOptionPane.PLAIN_MESSAGE));
		try {
			medico.setData_nascimento(convertUtilToSql(f.parse(JOptionPane.showInputDialog(null, "Digite a data de nascimento(dd/mm/aaaa):", "Cadastro de Medico", JOptionPane.PLAIN_MESSAGE))));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		medico.setTelefone(JOptionPane.showInputDialog(null, "Digite o Telefone:", "Cadastro de Medico", JOptionPane.PLAIN_MESSAGE));
		medico.setCrm(JOptionPane.showInputDialog(null, "Digite o CRM:", "Cadastro de Medico", JOptionPane.PLAIN_MESSAGE));
		return medico;
	}

	public static void atualizarMedico(Medico medico) {
		DateFormat f = DateFormat.getDateInstance();

		String nome = JOptionPane.showInputDialog("Digite o nome:",medico.getNome());     
		medico.setNome(nome);
		String cpf = JOptionPane.showInputDialog("Digite o CPF:",medico.getCpf());
		medico.setCpf(cpf);
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String data = df.format(medico.getData_nascimento());
			Date date1 = f.parse(JOptionPane.showInputDialog("Digite a data de nascimento(dd/mm/aaaa):",data));
			medico.setData_nascimento(convertUtilToSql(date1));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String telefone = JOptionPane.showInputDialog("Digite o Telefone:", medico.getTelefone());
		medico.setTelefone(telefone);
		String crm = JOptionPane.showInputDialog("Digite o CRM:", medico.getCrm());
		medico.setCrm(crm);
	}

	public static Consulta cadastrarConsulta() {
		Consulta consulta = new Consulta();
		String crm, cpf;
		int idRemedio;
		DateFormat f = DateFormat.getDateInstance();
		crm = JOptionPane.showInputDialog(null, "Digite o CRM do médico:", "Cadastro de Consulta", JOptionPane.PLAIN_MESSAGE);
		cpf = JOptionPane.showInputDialog(null, "Digite o CPF do paciente:", "Cadastro de Consulta", JOptionPane.PLAIN_MESSAGE);
		idRemedio = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o codigo do remedio:", "Cadastro de Consulta", JOptionPane.PLAIN_MESSAGE));
		Medico medico = new MedicoDAO().findByCRM(crm);
		Paciente paciente = new PacienteDAO().findByCPF(cpf);
		Remedio remedio = new RemedioDAO().findByID(idRemedio);

		if(medico == null) {
			JOptionPane.showMessageDialog(null, "Medico não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}else if(paciente == null) {
			JOptionPane.showMessageDialog(null, "Paciente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}else if(remedio == null) {
			JOptionPane.showMessageDialog(null, "Remedio não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		consulta.setMedico(medico);
		consulta.setPaciente(paciente);
		consulta.setRemedio(remedio);
		try {
			consulta.setData(convertUtilToSql(f.parse(JOptionPane.showInputDialog(null, "Digite a data da consulta(dd/mm/aaaa):", "Cadastro de Consulta", JOptionPane.PLAIN_MESSAGE))));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		consulta.setQtd_dias(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade de dias do medicamento:", "Cadastro de Consulta", JOptionPane.PLAIN_MESSAGE)));
		consulta.setTempo_entre(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o tempo entre cada remedio:", "Cadastro de Consulta", JOptionPane.PLAIN_MESSAGE)));

		return consulta;
	}

	public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	public static void listaEnderecos(List<Enderecos> enderecos) {
		StringBuilder listagem = new StringBuilder();
		for(Enderecos e : enderecos) {
			listagem.append(e).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum endereco encontrado" : listagem);
	}

	public static void listaPacientes(List<Paciente> paciente) {
		StringBuilder listagem = new StringBuilder();
		for(Paciente p : paciente) {
			listagem.append(p).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum paciente encontrado" : listagem);
	}

	public static void listaMedico(List<Medico> medico) {
		StringBuilder listagem = new StringBuilder();
		for(Medico m : medico) {
			listagem.append(m).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum medico encontrado" : listagem);
	}

	public static void listaDoencas(List<Doenca> doenca) {
		StringBuilder listagem = new StringBuilder();
		for(Doenca d : doenca) {
			listagem.append(d).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma doença encontrada" : listagem);
	}

	public static void listaConsulta(List<Consulta> consultas) {
		StringBuilder listagem = new StringBuilder();
		for(Consulta c : consultas) {
			listagem.append("Nome do medico: " + c.getMedico().getNome()).append("\n");
			listagem.append("Nome do paciente: " + c.getPaciente().getNome()).append("\n");
			listagem.append("Nome do remedio: " + c.getRemedio().getNome()).append("\n");
			listagem.append("Data: " + c.getData()).append("\n");
			listagem.append("Quantidade de dias do medicamento: " + c.getQtd_dias()).append("\n");
			listagem.append("Tempo entre cada medicamento: " + c.getTempo_entre()).append("\n\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma consulta encontrada" : listagem);
		System.out.println(listagem);
	}

	public static void listaRemedio(List<Remedio> remedios) {
		StringBuilder listagem = new StringBuilder();
		for(Remedio r : remedios) {
			listagem.append(r).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum remedio encontrado" : listagem);
	}

	public static void listaEfeitos(List<EfeitosColaterais> efeitos_colaterais) {
		StringBuilder listagem = new StringBuilder();
		for(EfeitosColaterais r : efeitos_colaterais) {
			listagem.append(r).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum efeito colateral encontrado" : listagem);
	}

	public static void listaContra(List<ContraIndicacoes> contra_indicacoes) {
		StringBuilder listagem = new StringBuilder();
		for(ContraIndicacoes r : contra_indicacoes) {
			listagem.append(r).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma contra indiação encontrada" : listagem);
	}


}
