package onboarding.vivo.demo.model;

public class RelatorioGeral {
    private EstatisticasItem lerFormulario;
    private EstatisticasItem assistirTreinamento;
    
    public RelatorioGeral() {
        this.lerFormulario = new EstatisticasItem();
        this.assistirTreinamento = new EstatisticasItem();
    }
    
    public RelatorioGeral(EstatisticasItem lerFormulario, EstatisticasItem assistirTreinamento) {
        this.lerFormulario = lerFormulario;
        this.assistirTreinamento = assistirTreinamento;
    }

    public EstatisticasItem getLerFormulario() {
        return lerFormulario;
    }

    public void setLerFormulario(EstatisticasItem lerFormulario) {
        this.lerFormulario = lerFormulario;
    }

    public EstatisticasItem getAssistirTreinamento() {
        return assistirTreinamento;
    }

    public void setAssistirTreinamento(EstatisticasItem assistirTreinamento) {
        this.assistirTreinamento = assistirTreinamento;
    }

    // Classe interna para representar estatísticas de um item
    public static class EstatisticasItem {
        private long concluidos;
        private long naoConcluidos;
        private double percentualConcluidos;

        public EstatisticasItem() {}

        public EstatisticasItem(long concluidos, long naoConcluidos) {
            this.concluidos = concluidos;
            this.naoConcluidos = naoConcluidos;
            calcularPercentual();
        }

        private void calcularPercentual() {
            long total = concluidos + naoConcluidos;
            if (total > 0) {
                this.percentualConcluidos = Math.round((double) concluidos / total * 100.0);
            } else {
                this.percentualConcluidos = 0.0;
            }
        }

        public long getConcluidos() {
            return concluidos;
        }

        public void setConcluidos(long concluidos) {
            this.concluidos = concluidos;
            calcularPercentual();
        }

        public long getNaoConcluidos() {
            return naoConcluidos;
        }

        public void setNaoConcluidos(long naoConcluidos) {
            this.naoConcluidos = naoConcluidos;
            calcularPercentual();
        }

        public double getPercentualConcluidos() {
            return percentualConcluidos;
        }

        public long getTotal() {
            return concluidos + naoConcluidos;
        }
    }
}