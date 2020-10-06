package com.ethicsinc.server.session.port.adapter.persistence;

import com.ethicsinc.server.session.domain.model.concern.Concern;
import com.ethicsinc.server.session.domain.model.concern.ConcernId;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcernRepository extends BaseRepository<Concern, ConcernId> {
}
