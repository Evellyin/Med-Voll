package med.voll.api.medico;

import med.voll.api.endereço.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
