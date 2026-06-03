package com.moroni.agendadortarefas.infrastructure.repository;

import com.moroni.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

}
