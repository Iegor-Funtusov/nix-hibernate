package ua.com.alevel.hibernate.dao;

import ua.com.alevel.hibernate.entity.Location;

import java.util.List;

/**
 * @author Iehor Funtusov, created 21/08/2020 - 8:09 PM
 */

public interface LocationDao {

    void create(Location location);
    List<Location> findAll();
    Location findById(Integer id);
}
