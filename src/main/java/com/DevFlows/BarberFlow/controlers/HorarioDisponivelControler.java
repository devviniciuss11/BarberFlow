package com.DevFlows.BarberFlow.controlers;
import com.DevFlows.BarberFlow.service.HorarioDisponivelService;
import com.DevFlows.BarberFlow.dto.HorarioRequestDTO;
import com.DevFlows.BarberFlow.dto.HorarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/Horarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HorarioDisponivelControler {
    private final HorarioDisponivelService horarioservice;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioResponseDTO cadastrarHorario(@RequestBody HorarioRequestDTO dto) {
        return horarioservice.cadastrarHorario(dto);
    }

    @GetMapping("/disponiveis")
    public List<HorarioResponseDTO> listarDisponiveis(@RequestParam Long barbeiroId, @RequestParam String data) {
        return horarioservice.listarDisponiveis(barbeiroId, data);
    }
}
