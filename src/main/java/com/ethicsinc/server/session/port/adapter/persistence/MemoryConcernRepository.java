package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.concern.Concern;
import com.ethicsinc.server.session.domain.model.concern.ConcernId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryConcernRepository implements ConcernRepository {
    private long id;
    private List<Concern> concerns;

    MemoryConcernRepository() {
        this.id = 0;
        this.concerns = new ArrayList<>();
    }


    @Override
    public ConcernId nextId() {
        this.id++;
        return new ConcernId(this.id);
    }

    @Override
    public void save(Concern concern) {
        int concernIndex = this.concerns.indexOf(concern);

        if(concernIndex == -1) {
            this.concerns.add(concern);
        } else {
            this.concerns.set(concernIndex, concern);
        }
    }

    @Override
    public List<Concern> getAll() {
        return this.concerns;
    }

    @Override
    public Concern getById(ConcernId concernId) throws Exception {

        for (Concern concern : this.concerns) {
            if (concern.getId().equals(concernId)) {
                return concern;
            }
        }

        throw new Exception("No concern found with id: " + concernId);
    }
}
