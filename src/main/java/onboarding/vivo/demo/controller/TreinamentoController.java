package onboarding.vivo.demo.controller;

import jakarta.validation.Valid;
import onboarding.vivo.demo.model.Treinamento;
import onboarding.vivo.demo.service.TreinamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dashboard/treinamentos")
public class TreinamentoController {

    private final TreinamentoService service;

    public TreinamentoController(TreinamentoService service) {
        this.service = service;
        System.out.println("🚀 TreinamentoController foi criado!");
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("🧪 Endpoint de teste chamado!");
        return "Controller funcionando!";
    }

    @GetMapping("/debug")
    public String debug() {
        System.out.println("🔍 Debug: Testando conexão com banco...");
        try {
            List<Treinamento> todos = service.findAll();
            return "✅ Conexão OK! Encontrados " + todos.size() + " treinamentos na tabela.";
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
            return "❌ Erro na conexão: " + e.getMessage();
        }
    }

    @GetMapping
    public List<Treinamento> getAll() {
        System.out.println("📋 Controller: GET /dashboard/treinamentos/ chamado!");
        return service.findAll();
    }

    @GetMapping("/get-treinamento/{id}")
    public Treinamento getById(@PathVariable Long id) {
        System.out.println("🔍 Controller: GET /get-treinamento/" + id + " chamado!");
        return service.findById(id);
    }

    @PostMapping ("/create") // Mapeia para POST /dashboard/treinamentos/
    public ResponseEntity<Treinamento> create(@Valid @RequestBody Treinamento t) {
        System.out.println("✍️ POST /dashboard/treinamentos/ chamado com: " + t.getNome());
        Treinamento created = service.create(t);
        return ResponseEntity.created(URI.create("/dashboard/treinamentos/post/" + created.getId())).body(created);
    }

    @PutMapping("/update-treinamento/{id}")
    public Treinamento update(@PathVariable Long id, @Valid @RequestBody Treinamento t) {
        return service.update(id, t);
    }

    @DeleteMapping("/delete-treinamento/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}