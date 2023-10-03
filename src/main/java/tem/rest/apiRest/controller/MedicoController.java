package tem.rest.apiRest.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;
import tem.rest.apiRest.domain.direccion.DatosDireccion;
import tem.rest.apiRest.domain.medico.*;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {
       Medico medico =  medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico( medico.getId(), medico.getNombre(),
                medico.getEmail(), medico.getDocumento(), medico.getEspecialidad().toString(), new DatosDireccion(
                medico.getDireccion().getCalle(), medico.getDireccion().getNumero(), medico.getDireccion().getDistrito(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento()));
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return  ResponseEntity.created(uri).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(medicoRepository.findAll(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualiarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos (datosActualizarMedico);
        return ResponseEntity.ok( new DatosRespuestaMedico(medico.getId(), medico.getNombre(),
                medico.getEmail(), medico.getDocumento(), medico.getEspecialidad().toString(), new DatosDireccion(
                        medico.getDireccion().getCalle(), medico.getDireccion().getNumero(), medico.getDireccion().getDistrito(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento()
        )));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable("id") Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> getMedicoById(@PathVariable("id") Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedicos = new DatosRespuestaMedico(medico.getId(), medico.getNombre(),
                medico.getEmail(), medico.getDocumento(), medico.getEspecialidad().toString(), new DatosDireccion(
                medico.getDireccion().getCalle(), medico.getDireccion().getNumero(), medico.getDireccion().getDistrito(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento()
        ));
        return ResponseEntity.ok(datosMedicos);
    }
}
