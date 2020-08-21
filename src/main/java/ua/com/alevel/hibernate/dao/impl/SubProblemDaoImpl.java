package ua.com.alevel.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.hibernate.dao.SubProblemDao;
import ua.com.alevel.hibernate.entity.SubProblem;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:22 PM
 */

@Repository
public class SubProblemDaoImpl implements SubProblemDao {

    private final SessionFactory sessionFactory;

    public SubProblemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(SubProblem problem) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(problem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubProblem> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from SubProblem").list();
    }

    @Override
    @Transactional(readOnly = true)
    public SubProblem findById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(SubProblem.class, id);
    }
}
