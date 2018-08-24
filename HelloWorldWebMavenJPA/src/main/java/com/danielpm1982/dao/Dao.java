package com.danielpm1982.dao;
import java.util.Arrays;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.danielpm1982.entity.EntityModelClass;

public class Dao {
	private SessionFactory sessionFactory;
	private Session session;
	public Dao() {
		sessionFactory = new Configuration().configure().addAnnotatedClass(EntityModelClass.class).buildSessionFactory();
		truncateDBTable();
		populate();
	}
	public List<EntityModelClass> searchAllEntities() { 
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			TypedQuery<EntityModelClass> query = session.createQuery("from EntityModelClass", EntityModelClass.class);
			List<EntityModelClass> itemList = query.getResultList();
			session.getTransaction().commit();
			return itemList;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return null;
		} finally {
			if(session!=null&&session.isOpen()) {
				session.close();
			}
		}
	}
	public boolean insertEntity(EntityModelClass entity) {
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return false;
		} finally {
			if(session!=null&&session.isOpen()) {
				session.close();
			}
		}
	}
	private boolean truncateDBTable() {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			session.createNativeQuery("truncate table "+"scheme1.EntityModelClass").executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return false;
		} finally {
			if(session!=null&&session.isOpen()) {
				session.close();
			}
		}
	}
	private boolean populate() {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			List<String> descriptionList = Arrays.asList("desc1", "desc2", "desc3", "desc4", "desc5");
			descriptionList.forEach(x->session.save(new EntityModelClass(x)));
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return false;
		} finally {
			if(session!=null&&session.isOpen()) {
				session.close();
			}
		}
	}
}
