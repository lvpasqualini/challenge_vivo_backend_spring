package onboarding.vivo.demo.repository;

import onboarding.vivo.demo.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {
}