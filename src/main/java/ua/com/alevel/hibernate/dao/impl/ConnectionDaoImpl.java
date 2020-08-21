package ua.com.alevel.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.hibernate.dao.ConnectionDao;
import ua.com.alevel.hibernate.entity.Connection;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:20 PM
 */

@Repository
public class ConnectionDaoImpl implements ConnectionDao {

    private final SessionFactory sessionFactory;

    public ConnectionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(Connection connection) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(connection);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Connection> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Connection").list();
    }
}
