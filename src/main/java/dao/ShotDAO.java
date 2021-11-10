package dao;

import beans.ShotBean;
import org.hibernate.Session;
import models.Shot;
import util.HibernateSessionFactory;

import java.util.List;

public class ShotDAO {
    public List<Shot> findAll() {
        return HibernateSessionFactory.getSessionFactory().openSession().createQuery("FROM Shot ").list();
    }

    public void save (ShotBean shotBean) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.save(new Shot(shotBean));
        session.close();
    }
}
