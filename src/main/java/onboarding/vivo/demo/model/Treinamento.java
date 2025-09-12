package onboarding.vivo.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "sprint_treinamentos")
public class Treinamento {

    @Id
    @Column(name = "id_treinamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqtr")
    @SequenceGenerator(name = "seqtr", sequenceName = "seqtr", allocationSize = 1)
    private Long id;

    @Column(name = "nome", length = 15, nullable = false)
    @NotBlank
    private String nome;

    @Column(name = "descricao", length = 50, nullable = false)
    @NotBlank
    private String descricao;

    @Column(name = "carga_horaria", nullable = false)
    @Min(1)
    private Integer cargaHoraria;

    @Column(name = "categoria", length = 20, nullable = false)
    @NotBlank
    private String categoria;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "id_funcionario")
    private Long idFuncionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
