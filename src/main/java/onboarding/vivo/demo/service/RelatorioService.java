package onboarding.vivo.demo.service;

import onboarding.vivo.demo.model.RelatorioGeral;
import org.springframework.stereotype.Service;
@Service
public class RelatorioService {
    
    private final TarefaService tarefaService;
    private final TreinamentoService treinamentoService;
    
    public RelatorioService(TarefaService tarefaService, TreinamentoService treinamentoService) {
        this.tarefaService = tarefaService;
        this.treinamentoService = treinamentoService;
    }
    
    public RelatorioGeral gerarRelatorioGeral() {
        System.out.println("📈 RelatorioService: Gerando relatório geral...");
        
        // Estatísticas de tarefas (Ler formulário)
        long tarefasConcluidas = tarefaService.countTarefasConcluidas();
        long tarefasNaoConcluidas = tarefaService.countTarefasNaoConcluidas();
        
        RelatorioGeral.EstatisticasItem lerFormulario = 
            new RelatorioGeral.EstatisticasItem(tarefasConcluidas, tarefasNaoConcluidas);
        
        // Estatísticas de treinamentos (Assistir treinamento)
        long treinamentosConcluidos = treinamentoService.countTreinamentosConcluidos();
        long treinamentosNaoConcluidos = treinamentoService.countTreinamentosNaoConcluidos();
        
        RelatorioGeral.EstatisticasItem assistirTreinamento = 
            new RelatorioGeral.EstatisticasItem(treinamentosConcluidos, treinamentosNaoConcluidos);
        
        RelatorioGeral relatorio = new RelatorioGeral(lerFormulario, assistirTreinamento);
        
        System.out.println("✅ RelatorioService: Relatório gerado com sucesso!");
        System.out.println("📊 Tarefas: " + tarefasConcluidas + " concluídas, " + tarefasNaoConcluidas + " não concluídas (" + lerFormulario.getPercentualConcluidos() + "%)");
        System.out.println("📊 Treinamentos: " + treinamentosConcluidos + " concluídos, " + treinamentosNaoConcluidos + " não concluídos (" + assistirTreinamento.getPercentualConcluidos() + "%)");
        
        return relatorio;
    }
}