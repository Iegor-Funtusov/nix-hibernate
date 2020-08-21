package ua.com.alevel.hibernate.dao;

import ua.com.alevel.hibernate.entity.Solution;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:09 PM
 */

public interface SolutionDao {

    void create(Solution solution);
    void deleteAll();
    List<Solution> findAll();
}
