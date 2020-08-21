package ua.com.alevel.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.hibernate.dao.SolutionDao;
import ua.com.alevel.hibernate.entity.Solution;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:21 PM
 */

@Service
public class SolutionDaoImpl implements SolutionDao {

    private final SessionFactory sessionFactory;

    public SolutionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(Solution solution) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(solution);
    }

    @Override
    @Transactional
    public void deleteAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Solution> solutions = findAll();
        for (Solution solution : solutions) {
            session.delete(solution);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Solution> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Solution").list();
    }
}
