package repositories;

import java.util.List;

public interface GenericDAO<T> {
	T findById(String id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    
    
}
