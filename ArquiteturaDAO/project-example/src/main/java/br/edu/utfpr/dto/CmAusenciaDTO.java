package br.edu.utfpr.dto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CmAusenciaDTO {
    private int idCm;
    private Date data;
    private String motivo;
    private ProfessorDTO professor;
}
