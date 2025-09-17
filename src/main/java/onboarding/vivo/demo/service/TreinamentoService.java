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
        System.out.println("🔍 Service: Buscando todos os treinamentos...");
        List<Treinamento> result = repo.findAll();
        System.out.println("📊 Service: Encontrados " + result.size() + " treinamentos");
        return result;
    }

    public Treinamento findById(Long id) {
        System.out.println("🔍 Service: Buscando treinamento com ID " + id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    System.out.println("❌ Service: Treinamento com ID " + id + " não encontrado!");
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

    // Métodos para relatório
    public long countTreinamentosConcluidos() {
        System.out.println("📊 Service: Contando treinamentos concluídos...");
        long count = repo.findAll().stream()
                .filter(treinamento -> treinamento.getDataFim() != null)
                .count();
        System.out.println("✅ Service: " + count + " treinamentos concluídos encontrados");
        return count;
    }

    public long countTreinamentosNaoConcluidos() {
        System.out.println("📊 Service: Contando treinamentos não concluídos...");
        long count = repo.findAll().stream()
                .filter(treinamento -> treinamento.getDataFim() == null)
                .count();
        System.out.println("⏳ Service: " + count + " treinamentos não concluídos encontrados");
        return count;
    }

    public long countTotalTreinamentos() {
        System.out.println("📊 Service: Contando total de treinamentos...");
        long count = repo.count();
        System.out.println("📋 Service: " + count + " treinamentos totais");
        return count;
    }
}
