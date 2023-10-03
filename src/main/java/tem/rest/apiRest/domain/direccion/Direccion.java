package tem.rest.apiRest.domain.direccion;

import jakarta.persistence.*;
import lombok.*;


@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

 private String calle;
 private String distrito;
 private String ciudad;
 private String numero;
 private String complemento;

 public Direccion(DatosDireccion direccion) {
  this.calle = direccion.calle();
  this.ciudad = direccion.ciudad();
  this.distrito = direccion.distrito();
  this.numero = direccion.numero();
  this.complemento = direccion.complemento();
 }

 public Direccion actualizarDireccion(DatosDireccion direccion) {
  this.calle = direccion.calle();
  this.ciudad = direccion.ciudad();
  this.distrito = direccion.distrito();
  this.numero = direccion.numero();
  this.complemento = direccion.complemento();
  return this;
 }
}
