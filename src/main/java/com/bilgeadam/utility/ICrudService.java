package com.bilgeadam.utility;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T, ID> {

    T save(T t);
    Iterable<T> saveAll(Iterable<T> t);
    T update(T t);
    T delete(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);

}
