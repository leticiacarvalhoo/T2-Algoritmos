import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Documento {
    private String nomeArquivo;
    private String nomeUsuario;
    private LocalDateTime horarioSolicitacao;
    
    public Documento(String nomeArquivo, String nomeUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.nomeUsuario = nomeUsuario;
        this.horarioSolicitacao = LocalDateTime.now();
    }
    
    public String getNomeArquivo() {
        return nomeArquivo;
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    
    public LocalDateTime getHorarioSolicitacao() {
        return horarioSolicitacao;
    }
    
    public long calcularTempoEspera() {
        return ChronoUnit.SECONDS.between(horarioSolicitacao, LocalDateTime.now());
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "Documento[" + nomeArquivo + ", usuário: " + nomeUsuario + 
               ", solicitado: " + horarioSolicitacao.format(formatter) + "]";
    }
}