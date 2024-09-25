package pe.edu.cibertec.patitas_front_end_a.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginResponseDTO(String codigo, String mensaje, String nombreUsuario, String correoUsuario) {
}
