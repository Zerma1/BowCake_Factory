package fr.cepn.testspringpo84.services.commons;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class AbstractService<T, ID, R extends JpaRepository<T, ID>> {

    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    public T save(T entity) throws Exception {
        return repository.save(entity);
    }

    public Optional<T> findById(ID id) throws Exception {
        return repository.findById(id);
    }

    public List<T> findAll() throws Exception {
        return repository.findAll();
    }

    public void deleteById(ID id) throws Exception {
        repository.deleteById(id);
    }

    public void delete(T entity) throws Exception {
        repository.delete(entity);
    }

    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    public long count() throws Exception {
        return repository.count();
    }

    public boolean existsById(ID id) throws Exception {
        return repository.existsById(id);
    }
}
