package tem.rest.apiRest.domain.medico;

import tem.rest.apiRest.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id, String nombre, String email, String espepcialidad, String documento, DatosDireccion direccion) {
}
