package controle;

import dao.AnimalDao;
import dao.ProprietarioDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Animal;
import modelo.Proprietario;

@ManagedBean(name="AnimalControle")
@SessionScoped
public class AnimalControle implements Serializable {
    private Animal anml;
    private Animal aux;
    private List<Animal> lista;
    private AnimalDao anmlDao;
    private ProprietarioDao propDao;
    private Proprietario proprietarioSelecionado;
    private List<Proprietario> proprietarios;
    public AnimalControle(){
        propDao = new ProprietarioDao();
        anmlDao = new AnimalDao();
        lista = anmlDao.listarTodos();
        anml = new Animal();
        proprietarios = propDao.listarTodos();
        proprietarioSelecionado = new Proprietario();
    }
    public void preparaAlterar(Animal anml){
        setAux(anml);
    }
    public void alterar() {
	anmlDao.alterar(getAux());
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Alteração","Animal alterado com sucesso!"));
    }
    public void listarPorNomeParcial() {
        lista = anmlDao.buscarPorNomeParcial(anml.getNome());        
    }
    public void excluir(Animal anml) {
	anmlDao.excluir(anml);
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão","Animal excluído com sucesso!"));
        lista.remove(anml);
        listar();
    }
    public void incluir() {
        anml.setProp(proprietarioSelecionado);
	anmlDao.inserir(anml);
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Cadastro","Animal cadastrado com sucesso!"));
        lista.add(anml);
        limpar();
        listar();
    }
    public void limpar() {
	anml = new Animal();
    }
    public void listar() {
        lista = anmlDao.listarTodos();
    }
    public Animal getAnimal() {
        return anml;
    }
    public List<Animal> getLista() {
	return lista;
    }
    public void setLista(List<Animal> lista) {
        this.lista = lista;
    }
    public Proprietario getProprietarioSelecionado() {
        return proprietarioSelecionado;
    }
    public void setProprietarioSelecionado(Proprietario proprietarioSelecionado) {
        this.proprietarioSelecionado = proprietarioSelecionado;
    }
    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }
    public void setProprietarios(List<Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }
    public Animal getAux() {
        return aux;
    }
    public void setAux(Animal aux) {
        this.aux = aux;
    }
}