package controle;

import dao.DAO;
import dao.AdministradorDao;
import dao.ExcecaoObjetoNaoEncontrado;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Administrador;

@ManagedBean 
@ViewScoped
public class IndexControle {

    private Administrador adm;
    private AdministradorDao dao;

    public IndexControle() {
        adm = new Administrador();
        dao = new AdministradorDao();
    }

    public String autenticar() {
        Administrador temp; // temp é o objeto retornado do arquivo
        // Método buscarPorLogin dispara exceção, então é obrigatório tratar
        try {
            temp = (Administrador) dao.buscaPorLogin(adm.getLogin());
        } catch (ExcecaoObjetoNaoEncontrado e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            return null;  //fica na página
        }
        // Adminstrador existe mas a senha não confere
        if (! temp.getSenha().equals(adm.getSenha())){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta", null));
            return null; 
        }
        return "menu";    // menu.xhtml
    }

    public Administrador getAdm() {
        return adm;
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }

    public AdministradorDao getDao() {
        return dao;
    }

    public void setDao(AdministradorDao dao) {
        this.dao = dao;
    }

}