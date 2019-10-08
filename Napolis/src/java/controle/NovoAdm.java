package controle;

import dao.DAO;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Administrador;

@ManagedBean (name="novoAdm") 
@ViewScoped
public class NovoAdm implements Serializable {
    private Administrador adm;
    private DAO<Administrador> dao; 
    
    public NovoAdm(){
        adm = new Administrador();
        dao = new DAO(Administrador.class);
    }
    
    public void salvar(){
        dao.inserir(adm);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage
          (null, new FacesMessage(FacesMessage.SEVERITY_INFO,
          "Administrador cadastrado", null));
    }
    

    public Administrador getAdm() {
        return adm;
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }

    public DAO<Administrador> getDao() {
        return dao;
    }

    public void setDao(DAO<Administrador> dao) {
        this.dao = dao;
    }
    
    
    
}