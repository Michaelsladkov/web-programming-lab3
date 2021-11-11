package dao;

import beans.ShotBean;
import org.hibernate.Session;
import models.Shot;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class ShotDAO {
    public List<Shot> findAll() {
        List<Shot> ret = (List<Shot>) HibernateSessionFactory.getSessionFactory().openSession().
                createQuery("FROM Shot").list();
        ret.sort((o1, o2) -> o2.compareTo(o1));
        System.out.println(ret.size());
        return ret;
    }

    public void save (ShotBean shotBean) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Shot toSave = new Shot(shotBean);
        System.out.println(toSave);
        session.save(toSave);
        transaction.commit();
        session.close();
    }

    public void clear() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("DELETE FROM Shot ").executeUpdate();
        tx1.commit();
        session.close();
    }
}
