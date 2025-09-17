package onboarding.vivo.demo.controller;

import onboarding.vivo.demo.model.RelatorioGeral;
import onboarding.vivo.demo.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class RelatorioController {
    
    private final RelatorioService relatorioService;
    
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
        System.out.println("🚀 RelatorioController foi criado!");
    }
    
    @GetMapping("/relatorio-geral")
    public ResponseEntity<RelatorioGeral> getRelatorioGeral() {
        System.out.println("🔍 Controller: GET /dashboard/relatorio-geral chamado!");
        
        try {
            RelatorioGeral relatorio = relatorioService.gerarRelatorioGeral();
            System.out.println("✅ Controller: Relatório gerado com sucesso!");
            return ResponseEntity.ok(relatorio);
            
        } catch (Exception e) {
            System.out.println("❌ Controller: Erro ao gerar relatório - " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}