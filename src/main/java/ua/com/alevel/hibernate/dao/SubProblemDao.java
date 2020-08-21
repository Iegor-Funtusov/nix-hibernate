package ua.com.alevel.hibernate.dao;

import ua.com.alevel.hibernate.entity.SubProblem;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:09 PM
 */

public interface SubProblemDao {

    void create(SubProblem problem);
    List<SubProblem> findAll();
    SubProblem findById(Integer id);
}
