package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Funcionario;
import modelo.Pagamento;
import modelo.Cargo;
import modelo.Endereco;
import modelo.Filial;
import service.FuncionarioService;
import service.FilialService;

@ViewScoped
@ManagedBean
public class FuncionarioBean {

	private Cargo cargo;
	private Endereco endereco;
	private Long idFilial = 0L;
	private Long idEndereco = 0L;
	private String filtro;
	private Boolean editando = false;
	private Boolean gravar = true; 
	private Pagamento pagamento;

	@EJB
	private FuncionarioService funcionarioService;

	@EJB
	private FilialService filialService;

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	private Filial filial = new Filial();
	private List<Filial> filiais = new ArrayList<Filial>();
	
	private List<Pagamento> pagamentos;

    public FuncionarioBean() {
    	funcionario = new Funcionario();
	    endereco = new Endereco();
	    filial = new Filial();
	    funcionario.setEndereco(endereco);
	    funcionario.setFilial(filial);
	    pagamento = new Pagamento();
    }
    
    
    public Cargo[] getCargosDisponiveis() {
        return Cargo.values();
    }

    // // Método para pesquisar e fazer a listagem pelo nome do funcionário
	public void pesquisar() {
		funcionarios = funcionarioService.listarFuncionariosPorNome(filtro);
	}

	@PostConstruct //método após a cosntrução da classe
	private void inicializar() {
		listarFuncionarios();
		filiais = filialService.listAll();
		atualizarLista();
	}

	public void carregarFuncionario(Funcionario fun) {
		funcionario = fun;
		idFilial = fun.getFilial().getId();
		editando = true;
		gravar = false;
	}

	public void excluir(Funcionario fun) {
		funcionarioService.remove(fun);
		listarFuncionarios();
	}

	private void atualizarLista() {
		filiais = filialService.listAll();
	}

	public void listarFuncionarios() {
		funcionarios = funcionarioService.listarFuncionarios(); 
	}

	public String formatarCpf(String cpf) {
	    cpf = cpf.replaceAll("[^0-9]", "");

	    if (cpf.length() != 11) {
	        return cpf;
	    }

	    return cpf.substring(0, 3) + "." +
	           cpf.substring(3, 6) + "." +
	           cpf.substring(6, 9) + "-" +
	           cpf.substring(9);
	}

	public void gravar() {
		Filial f = filialService.obtemPorId(idFilial);
		funcionario.setFilial(f);
		
		if(funcionario.getId() != null) {
			funcionarioService.merge(funcionario);
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Funcionario atualizado com sucesso!")); 
		} else if(f == null) {
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Selecione uma filial"));
		} else {
			funcionarioService.create(funcionario);
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Funcionario gravado com sucesso!")); 
		}
		
		funcionario = new Funcionario();
		filial = new Filial();
		endereco = new Endereco();
		funcionario.setEndereco(endereco);
		funcionario.setFilial(filial);
		
		listarFuncionarios();
		idFilial = 0L;
		idEndereco = 0L;
		gravar = true;
		editando = false;
	}

	// Gets e Sets da classe Funcionário
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

	public Long getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Long idFilial) {
		this.idFilial = idFilial;
	}

	public Boolean getGravar() {
		return gravar;
	}

	public void setGravar(Boolean gravar) {
		this.gravar = gravar;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}


	public Pagamento getPagamento() {
		return pagamento;
	}


	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}


	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}


	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}


	public Cargo getCargo() {
		return cargo;
	}


	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}


	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}


	public FilialService getFilialService() {
		return filialService;
	}


	public void setFilialService(FilialService filialService) {
		this.filialService = filialService;
	}



}