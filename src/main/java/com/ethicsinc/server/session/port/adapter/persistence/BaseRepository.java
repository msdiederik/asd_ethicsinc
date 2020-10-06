package com.ethicsinc.server.session.port.adapter.persistence;

import java.util.List;

public interface BaseRepository<T, ID> {
    public ID nextId();
    public void save(T entity);
    public List<T> getAll();
    public T getById(ID id) throws Exception;
}
