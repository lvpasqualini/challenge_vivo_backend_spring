package onboarding.vivo.demo.controller;

import jakarta.validation.Valid;
import onboarding.vivo.demo.model.Tarefa;
import onboarding.vivo.demo.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dashboard/tarefas")
public class TarefaController {
    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
        System.out.println("Controlle tarefa lançado com sucesso!!!!");
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("🧪 Endpoint de teste chamado!");
        return "Controller tarefa funcionando!";
    }

    @GetMapping("/get-tarefa")
    public List<Tarefa> getAll() {
        return service.findAll();
    }

    @GetMapping("/get-tarefa/{id}")
    public Tarefa getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/create-tarefa")
    public ResponseEntity<Tarefa> create(@Valid @RequestBody Tarefa t) {
        Tarefa created = service.create(t);
        return ResponseEntity.created(URI.create("/dashboard/treinamentos/post/" + created.getId())).body(created);
    }

    @PutMapping("/update-tarefa/{id}")
    public Tarefa update(@PathVariable Long id, @Valid @RequestBody Tarefa t) {
        return service.update(id, t);
    }

    @DeleteMapping("/delete-tarefa/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
