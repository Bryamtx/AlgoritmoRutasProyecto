package com.Uptc.Proyecto20.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Uptc.Proyecto20.Service.AlimentoService;
import com.Uptc.Proyecto20.Entities.*;

@Controller
	@RequestMapping("/alimentos")
	public class AlimentoController {

	    @Autowired
	    private AlimentoService alimentoService;

	    @GetMapping("/api/alimentos")
	    @ResponseBody
	    public List<Alimento> obtenerAlimentosApi() {
	        return alimentoService.obtenerTodosLosAlimentos();
	    }

	    @GetMapping("/api/alimento/{id}")
	    @ResponseBody
	    public ResponseEntity<Alimento> obtenerAlimentoPorId(@PathVariable Long id) {
	        return alimentoService.obtenerAlimentoPorId(id)
	                .map(alimento -> ResponseEntity.ok().body(alimento))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @GetMapping("/api/buscar")
	    @ResponseBody
	    public List<Alimento> buscarAlimentos(@RequestParam String nombre) {
	        return alimentoService.buscarAlimentosPorNombre(nombre);
	    }

	    @GetMapping("/api/precio-rango")
	    @ResponseBody
	    public List<Alimento> obtenerAlimentosPorRangoPrecio(
	            @RequestParam Double min, 
	            @RequestParam Double max) {
	        return alimentoService.obtenerAlimentosPorRangoPrecio(min, max);
	    }
	}
