package com.moroni.agendadortarefas.business.mapper;

import com.moroni.agendadortarefas.business.dto.TarefasDTO;
import com.moroni.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity (TarefasDTO dto);
    TarefasDTO paraTarefaDTO(TarefasEntity entity);
    List<TarefasDTO> paraListTarefasDTO(List<TarefasEntity> entities);
    List<TarefasEntity> paraListTarefasEntity(List<TarefasDTO> dtos);
}
