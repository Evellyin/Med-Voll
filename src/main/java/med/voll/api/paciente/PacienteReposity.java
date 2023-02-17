package med.voll.api.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteReposity extends JpaRepository<Paciente, Long> {
}
