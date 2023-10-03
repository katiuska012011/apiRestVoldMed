package tem.rest.apiRest.domain.medico;

import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import tem.rest.apiRest.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,

        @NotNull
        Especialidad especialidad,

        @NotNull
        @Validated
        DatosDireccion direccion) {
}
