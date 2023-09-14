package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Filial;
import modelo.Funcionario;
import modelo.Pagamento;
import service.FuncionarioService;
import service.PagamentoService;

@ViewScoped
@ManagedBean
public class PagamentoBean {
    
    private Long funcionarioId = 0L;
    private Boolean editando = false;
	private Boolean gravar = true; 
    
    @EJB
	private FuncionarioService funcionarioService;
    
    @EJB
	private PagamentoService pagamentoService;
    
    private Pagamento pagamento = new Pagamento();
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();
    
	private Funcionario funcionario = new Funcionario();
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    
    public PagamentoBean() {
    	funcionario = new Funcionario();
        pagamento = new Pagamento();
        pagamento.setFuncionario(funcionario);
        
    }
    
    @PostConstruct //método após a cosntrução da classe
	private void inicializar() {
		listarPagamentos();
		funcionarios = funcionarioService.listAll();
		atualizarLista();
	}

	public void carregarPagamento(Pagamento pag) {
		pagamento = pag;
		funcionarioId = pag.getFuncionario().getId();
		editando = true;
		gravar = false;
	}
	
	public void listarPagamentos() {
		pagamentos = pagamentoService.listarPagamentos(); 
	}

	public void excluir(Pagamento pag) {
		pagamentoService.remove(pag);
		listarPagamentos();
	}

	private void atualizarLista() {
		pagamentos = pagamentoService.listAll();
	}

	public void gravar() {
	
		Funcionario func = funcionarioService.obtemPorId(funcionarioId);
		Filial fil = func.getFilial();
		pagamento.setFuncionario(func);
		pagamento.setFilial(fil);
		
		if(pagamento.getId() != null) {
			pagamentoService.merge(pagamento);
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Pagamento atualizado com sucesso!")); 
		} else if(func == null) {
			FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Selecione um nome"));
		} else {
			if (pagamentoService.procurarPagamentoPorMes(pagamento) != null) {
				FacesContext.getCurrentInstance().
				addMessage("msg1", new FacesMessage("Mês e ano já cadastrados"));
			} else {
				pagamentoService.create(pagamento);
				FacesContext.getCurrentInstance().
				addMessage("msg1", new FacesMessage("Pagamento gravado com sucesso!")); 
				atualizarLista();
				listarPagamentos();
			}
		}
		
		pagamento = new Pagamento();
		funcionario = new Funcionario();
		
		pagamento.setFuncionario(funcionario);
	
		listarPagamentos();
		funcionarioId = 0L;
		
		gravar = true;
		editando = false;
	}
   
    public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}

	public Boolean getGravar() {
		return gravar;
	}

	public void setGravar(Boolean gravar) {
		this.gravar = gravar;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}


	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public void listarFuncionarios() {
		funcionarios = funcionarioService.listarFuncionarios(); 
	}

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Long getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public void funcionarioSelecionado() {
        if (funcionarioId != null && funcionarioId > 0) {
            // Buscar o funcionário correspondente ao ID selecionado
            Funcionario selectedFuncionario = funcionarioService.obtemPorId(funcionarioId);
            if (selectedFuncionario != null) {
                // Preencher o valor do pagamento com o salário do funcionário
                pagamento.setValor(selectedFuncionario.getSalario());
                pagamento.setFuncionario(selectedFuncionario);
            } else {
                pagamento.setValor(null);
            }
        } else {
            pagamento.setValor(null);
        }
    }
}