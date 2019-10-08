package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.Proprietario;
import util.JPAUtil;

public class ProprietarioDao implements Serializable {
    EntityManager manager;
    public boolean alterar(Proprietario prop){
        manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(prop);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }    
    public Proprietario buscarPorCodigo(int cod){
        manager = JPAUtil.getEntityManager();
        Proprietario prop = manager.find(Proprietario.class, cod);
        manager.close();
        return prop;
    }        
    public Proprietario buscarPorNome(String nome){
        Proprietario temp;
        manager = JPAUtil.getEntityManager();
        String consulta = "SELECT c FROM Proprietario c WHERE c.Nome = :nome";
        TypedQuery<Proprietario> query = manager.createQuery(consulta, Proprietario.class);
        query.setParameter("nome", nome);
        temp = query.getSingleResult();
        manager.close();
        return temp;
    }
    public List<Proprietario> buscarPorNomeParcial(String nome) {
        List<Proprietario> list;
        manager = JPAUtil.getEntityManager();
        String consulta = "SELECT c FROM Proprietario c WHERE c.nome LIKE CONCAT('%',:nome,'%')";
        TypedQuery<Proprietario> query = manager.createQuery(consulta, Proprietario.class);
        query.setParameter("nome", nome);
        list = query.getResultList();
        manager.close();
        return list;
    }    
    public boolean excluir(Proprietario prop){
        manager = JPAUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        Proprietario temp = manager.find(Proprietario.class, prop.getCodigo());
        manager.remove(temp);
        tx.commit();
        manager.close();
        return true;
    }    
    public boolean inserir(Proprietario prop){
        manager = JPAUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(prop);
        tx.commit();
        manager.close();
        return true;
    }    
    public List<Proprietario> listarTodos(){
        manager = JPAUtil.getEntityManager();
        CriteriaQuery<Proprietario> query = manager.getCriteriaBuilder().createQuery(Proprietario.class);
        query.select(query.from(Proprietario.class));
        List<Proprietario> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }
}
