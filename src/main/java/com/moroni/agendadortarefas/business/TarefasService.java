package com.moroni.agendadortarefas.business;

import com.moroni.agendadortarefas.business.dto.TarefasDTO;
import com.moroni.agendadortarefas.business.mapper.TarefasConverter;
import com.moroni.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.moroni.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.moroni.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.moroni.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));

    }
    public List<TarefasDTO>buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){

        return tarefaConverter.paraListTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }
    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return tarefaConverter.paraListTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }
}
