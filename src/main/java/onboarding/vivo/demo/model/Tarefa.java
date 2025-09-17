package onboarding.vivo.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import onboarding.vivo.demo.config.LocalDateDeserializer;

import java.time.LocalDate;

@Entity
@Table(name = "sprint_tarefas")
public class Tarefa {

    @Id
    @Column(name = "id_tarefas")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqta")
    @SequenceGenerator(name = "seqta", sequenceName = "seqta", allocationSize = 1)
    private Long id;

    @Column(name = "descricao", length = 50, nullable = false)
    @NotBlank
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    @NotNull(message = "dataInicio é obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    @Column(name = "id_funcionario")
    private Long idFuncionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
