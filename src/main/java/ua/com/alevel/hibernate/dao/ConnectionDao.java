package ua.com.alevel.hibernate.dao;

import ua.com.alevel.hibernate.entity.Connection;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:09 PM
 */

public interface ConnectionDao {

    void create(Connection connection);
    List<Connection> findAll();
}
