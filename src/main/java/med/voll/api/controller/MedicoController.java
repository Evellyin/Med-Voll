package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@Valid @RequestBody DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    //Método de listagem COM paginação.
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    //Método de listagem SEM paginação.
    //public List<DadosListagemMedico> listar() {
    //    return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    //}

    @PutMapping
    @Transactional
    public void atualizar (@Valid @RequestBody DadosAtualizarMedicos dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

    //Exclusão SEM lógica
    //public void excluir (@PathVariable Long id) {
    //    repository.deleteById(id);
    //}
}
