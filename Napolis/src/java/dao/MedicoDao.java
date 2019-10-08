package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import modelo.Medico;
import util.JPAUtil;

public class MedicoDao implements Serializable {
    EntityManager manager;    
    public boolean alterar(Medico med){
        manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(med);
        manager.getTransaction().commit();
        manager.close();
        return true;
    }    
    public Medico buscarPorCodigo(int cod){
        manager = JPAUtil.getEntityManager();
        Medico med = manager.find(Medico.class, cod);
        manager.close();
        return med;
    } 
    public List<Medico> buscarPorNomeParcial(String nome) {
        List<Medico> list;
        manager = JPAUtil.getEntityManager();
        String consulta = "SELECT c FROM Medico c WHERE c.nome LIKE CONCAT('%',:nome,'%')";
        TypedQuery<Medico> query = manager.createQuery(consulta, Medico.class);
        query.setParameter("nome", nome);
        list = query.getResultList();
        manager.close();
        return list;
    }    
    public boolean excluir(Medico med){
        manager = JPAUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction(); 
        tx.begin();
        Medico temp = manager.find(Medico.class, med.getCodigo());
        manager.remove(temp);
        tx.commit();
        manager.close();
        return true;
    }    
    public boolean inserir(Medico med){
        manager = JPAUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(med);
        tx.commit();
        manager.close();
        return true;
    }    
    public List<Medico> listarTodos(){
        manager = JPAUtil.getEntityManager();
        CriteriaQuery<Medico> query = manager.getCriteriaBuilder().createQuery(Medico.class);
        query.select(query.from(Medico.class));
        List<Medico> lista = manager.createQuery(query).getResultList();
        manager.close();
        return lista;
    }
}