package onboarding.vivo.demo.service;

import onboarding.vivo.demo.exception.ResourceNotFoundException;
import onboarding.vivo.demo.model.Treinamento;
import onboarding.vivo.demo.repository.TreinamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinamentoService {
    private final TreinamentoRepository repo;

    public TreinamentoService(TreinamentoRepository repo) {
        this.repo = repo;
    }

    public List<Treinamento> findAll() {
        return repo.findAll();
    }

    public Treinamento findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> {
                    System.out.println("Service: Treinamento com ID " + id + " não encontrado!");
                    return new ResourceNotFoundException("Treinamento não encontrado com id " + id);
                });
    }

    public Treinamento create(Treinamento t) {
        t.setId(null);
        return repo.save(t);
    }

    public Treinamento update(Long id, Treinamento updated) {
        Treinamento existing = findById(id);
        existing.setNome(updated.getNome());
        existing.setDescricao(updated.getDescricao());
        existing.setCargaHoraria(updated.getCargaHoraria());
        existing.setCategoria(updated.getCategoria());
        existing.setDataInicio(updated.getDataInicio());
        existing.setDataFim(updated.getDataFim());
        existing.setIdFuncionario(updated.getIdFuncionario());
        return repo.save(existing);
    }

    public void delete(Long id) {
        Treinamento existing = findById(id);
        repo.delete(existing);
    }

    public long countTreinamentosConcluidos() {
        long count = repo.findAll().stream()
                .filter(treinamento -> treinamento.getDataFim() != null)
                .count();
        return count;
    }

    public long countTreinamentosNaoConcluidos() {
        long count = repo.findAll().stream()
                .filter(treinamento -> treinamento.getDataFim() == null)
                .count();
        return count;
    }

    public long countTotalTreinamentos() {
        return repo.count();
    }
}
