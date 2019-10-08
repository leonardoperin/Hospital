package controle;

import dao.FuncionarioDao;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Funcionario;

@ManagedBean(name="loginControle")
@SessionScoped
public class LoginControle implements Serializable {
    private Funcionario func;    
    public LoginControle(){
        func = new Funcionario();
    }    
    public String autenticar(){
        FuncionarioDao funcDao = new FuncionarioDao();
        Funcionario temp;
        temp = funcDao.autenticar(getFunc());
        if (temp == null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos", null));
            return null;  // fica na página
        } // seta usuario na Sessao
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ectx = context.getExternalContext();
        HttpSession session = (HttpSession) ectx.getSession(true);
        session.setAttribute("usuarioLogado", getFunc());        
        return "principal"; // vai para o menu
    }
    public String actionLogout(){
        Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.clear();
        return "index";
    }    
    public Funcionario getFunc() {
        return func;
    }
    public void setFunc(Funcionario func) {
        this.func = func;
    }
}