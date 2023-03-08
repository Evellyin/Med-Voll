package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class ValidadorHorarioFuncionamentoClinica {

    public void validar (DadosAgendamentoConsulta dados){

        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisFechamento = dataConsulta.getHour() > 18;
        if (domingo || antesAbertura || depoisFechamento){
            throw new ValidacaoException("Consulta fora do horário de funcionamento.");
        }

    }

}
