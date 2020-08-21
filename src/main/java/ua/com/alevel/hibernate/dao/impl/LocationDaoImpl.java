package ua.com.alevel.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.hibernate.dao.LocationDao;
import ua.com.alevel.hibernate.entity.Location;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:10 PM
 */

@Repository
public class LocationDaoImpl implements LocationDao {

    private final SessionFactory sessionFactory;

    public LocationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(Location location) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(location);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Location").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Location findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Location.class, id);
    }
}
