package onboarding.vivo.demo.service;

import onboarding.vivo.demo.exception.ResourceNotFoundException;
import onboarding.vivo.demo.model.Tarefa;
import onboarding.vivo.demo.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository repo;

    public TarefaService(TarefaRepository repo) {
        this.repo = repo;
    }

    public List<Tarefa> findAll() {
        return repo.findAll();
    }

    public Tarefa findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> {
                    System.out.println("Service: Tarefa com ID " + id + " não encontrado!");
                    return new ResourceNotFoundException("Tarefa não encontrado com id " + id);
                });
    }

    public Tarefa create(Tarefa t) {
        t.setId(null);
        return repo.save(t);
    }

    public Tarefa update(Long id, Tarefa updated) {
        Tarefa existing = findById(id);
        existing.setDescricao(updated.getDescricao());
        existing.setDataInicio(updated.getDataInicio());
        existing.setDataFim(updated.getDataFim());
        existing.setIdFuncionario(updated.getIdFuncionario());
        return repo.save(existing);
    }

    public void delete(Long id) {
        Tarefa existing = findById(id);
        repo.delete(existing);
    }

    public long countTarefasConcluidas() {
        long count = repo.findAll().stream()
                .filter(tarefa -> tarefa.getDataFim() != null)
                .count();
        return count;
    }

    public long countTarefasNaoConcluidas() {
        long count = repo.findAll().stream()
                .filter(tarefa -> tarefa.getDataFim() == null)
                .count();
        return count;
    }

    public long countTotalTarefas() {
        return repo.count();
    }
}
