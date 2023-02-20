package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteReposity reposity;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@Valid @RequestBody DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        reposity.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body((new DadosDetalhamentoPaciente(paciente)));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar (@PageableDefault (size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = reposity.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@Valid @RequestBody DadosAtualizarPaciente dados) {
        var paciente = reposity.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id) {
        var paciente = reposity.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }
}
