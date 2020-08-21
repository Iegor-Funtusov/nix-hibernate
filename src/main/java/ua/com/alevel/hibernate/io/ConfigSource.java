package ua.com.alevel.hibernate.io;

import ua.com.alevel.hibernate.io.data.Problem;

public interface ConfigSource {

    Problem load();
}
