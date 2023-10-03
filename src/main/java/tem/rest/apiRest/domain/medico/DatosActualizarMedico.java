package tem.rest.apiRest.domain.medico;

import jakarta.validation.constraints.NotNull;
import tem.rest.apiRest.domain.direccion.DatosDireccion;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, String especialidad, DatosDireccion direccion) {
}
