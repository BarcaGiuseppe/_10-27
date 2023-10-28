package dao;

import java.io.IOException;
import java.util.List;

public interface Crud <T>{
    void add (T object) throws IOException;

    List<T> findAll();

    void update (String id, T object);

    void delete (String id);
}
