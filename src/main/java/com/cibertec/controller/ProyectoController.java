package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.model.Cliente;
import com.cibertec.repository.IClienteRepository;

@Controller
public class ProyectoController {
	@Autowired
	private IClienteRepository repo;
	
	@GetMapping("/registrar")
	public String registroCliente(@RequestParam(name="name", required=false, defaultValue = "World") String name, Model model) {
		
		Cliente c = new Cliente();
		c.setNom_cliente("Maria Rodriguez");
		c.setId_area(1);
		c.setEdad_cliente(12);
		c.setDni_cliente("18196784");
		c.setGenero_cliente("F");
		System.out.println(c);
		
		c = repo.save(c);
		
		model.addAttribute("name", c);
		
		
		return "greeting";
	}
	
	public String listadoClientes(Model model) {
		model.addAttribute("lstClientes", repo.findAll());
		return "listado";
	}
}
