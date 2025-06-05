package com.Uptc.Proyecto20.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Uptc.Proyecto20.Entities.Asiento;
import com.Uptc.Proyecto20.Management.AsientoManagement;
import com.Uptc.Proyecto20.Service.AsientoService;

@Controller
@RequestMapping("/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    @GetMapping
    public String mostrarAsientos(Model model) {
        List<Asiento> asientos = asientoService.obtenerTodosLosAsientos();
        model.addAttribute("asientos", asientos);
        return "asientos";
    }

    @PostMapping("/cambiar-estado/{id}")
    @ResponseBody
    public Asiento cambiarEstadoAsiento(@PathVariable Long id) {
        return asientoService.cambiarEstadoAsiento(id);
    }

    @GetMapping("/api/asientos")
    @ResponseBody
    public List<Asiento> obtenerAsientosApi() {
        return asientoService.obtenerTodosLosAsientos();
    }

    @PostMapping("/reservar")
    @ResponseBody
    public ResponseEntity<?> reservarAsientos(@RequestBody List<Long> idsAsientos) {
        try {
            List<Asiento> asientosReservados = asientoService.reservarMultiplesAsientos(idsAsientos);
            return ResponseEntity.ok().body(asientosReservados);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/api/liberar/{id}")
    @ResponseBody
    public ResponseEntity<?> liberarAsiento(@PathVariable Long id) {
        try {
            Asiento asientoLiberado = asientoService.liberarAsiento(id);
            return ResponseEntity.ok().body(asientoLiberado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/disponibles")
    @ResponseBody
    public List<Asiento> obtenerAsientosDisponibles() {
        return asientoService.obtenerAsientosDisponibles();
    }

    @GetMapping("/ocupados")
    @ResponseBody
    public List<Asiento> obtenerAsientosOcupados() {
        return asientoService.obtenerAsientosOcupados();
    }
}