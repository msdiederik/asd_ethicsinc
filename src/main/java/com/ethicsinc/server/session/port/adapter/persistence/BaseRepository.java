package com.ethicsinc.server.session.port.adapter.persistence;

import java.util.List;

public interface BaseRepository<T, ID> {
    ID nextId();
    void save(T entity);
    List<T> getAll();
    T getById(ID id) throws Exception;
}
