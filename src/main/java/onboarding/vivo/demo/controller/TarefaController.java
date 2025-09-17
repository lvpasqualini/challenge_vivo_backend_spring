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
@CrossOrigin(origins = "*")
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

    @GetMapping(path = {"", "/get-tarefa"})
    public List<Tarefa> getAll() {
        System.out.println("Controller: GET /dashboard/tarefas");
        return service.findAll();
    }

    @GetMapping(path = {"/{id}", "/get-tarefa/{id}"})
    public Tarefa getById(@PathVariable Long id) {
        System.out.println("Controller: GET /dashboard/tarefas/" + id);
        return service.findById(id);
    }

    @PostMapping(path = {"", "/create-tarefa"})
    public ResponseEntity<Tarefa> create(@Valid @RequestBody Tarefa tarefa) {
        System.out.println("Controller: POST /dashboard/tarefas com body=" + tarefa);
        Tarefa created = service.create(tarefa);
        URI location = URI.create("/dashboard/tarefas/" + created.getId());
        System.out.println("Controller: tarefa criada com id=" + created.getId());
        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping(path = {"/{id}", "/update-tarefa/{id}"})
    public Tarefa update(@PathVariable Long id, @Valid @RequestBody Tarefa t) {
        System.out.println("Controller: PUT /dashboard/tarefas/" + id + " com body=" + t);
        return service.update(id, t);
    }

    @DeleteMapping(path = {"/{id}", "/delete-tarefa/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("Controller: DELETE /dashboard/tarefas/" + id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
