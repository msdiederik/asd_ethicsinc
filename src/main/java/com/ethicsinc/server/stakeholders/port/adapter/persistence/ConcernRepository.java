package com.ethicsinc.server.stakeholders.port.adapter.persistence;

import com.ethicsinc.server.session.port.adapter.persistence.BaseRepository;
import com.ethicsinc.server.stakeholders.domain.model.concern.Concern;
import com.ethicsinc.server.stakeholders.domain.model.concern.ConcernId;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcernRepository extends BaseRepository<Concern, ConcernId> {
}
