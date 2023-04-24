package com.cibertec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.model.Cliente;
import com.cibertec.repository.IClienteRepository;

@Controller
public class ProyectoController {
	@Autowired
	private IClienteRepository repo;
	
	@GetMapping("/")
	public String regPag(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "APP-RODRIGUEZMONZON-REGISTRO";
	}
	
	@PostMapping("/grabar")
	public String regcliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttrs) {
		System.out.println(cliente);
		repo.save(cliente);
		// Mensaje de confirmación para mostrar en la página de destino
	    redirectAttrs.addFlashAttribute("mensaje", "El cliente se ha registrado correctamente");
	    
	    
		return "redirect:/listar";
	}
	
	@GetMapping("/listar")
	public String listadoClientes(Model model) {
		model.addAttribute("lstClientes", repo.findAll());
		return "listado";
	}
	
    @GetMapping("/consulta")
    public String consulta(Model model) {
        model.addAttribute("lstClientes", repo.findAll());
        return "APP-RODRIGUEZMONZON-CONSULTA";
    }
    
    @GetMapping("/consultaId")
	public String consulta(@RequestParam("id_cliente") String id_cliente, Model model) {
	    Optional<Cliente> clienteEncontrado = repo.findById(id_cliente);
	    if (clienteEncontrado.isPresent()) {
	        model.addAttribute("clienteEncontrado", clienteEncontrado.get());
	    } else {
	        model.addAttribute("mensaje", "No se encontró ningún cliente con ese ID.");
	    }
	    model.addAttribute("cliente", new Cliente());
	    return "APP-RODRIGUEZMONZON-CONSULTA";
	}
}
