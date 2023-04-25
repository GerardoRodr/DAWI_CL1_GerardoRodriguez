package com.cibertec.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.model.AreaCliente;
import com.cibertec.model.Cliente;
import com.cibertec.repository.IClienteRepository;

@Controller
public class ProyectoController {
	@Autowired
	private IClienteRepository repo;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	//La pagina principal sera la de registro
	@GetMapping("/")
	public String regPag(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "APP-RODRIGUEZMONZON-REGISTRO";
	}
	
	@PostMapping("/grabar")
	public String regcliente(@ModelAttribute Cliente cliente, Model model) {
		System.out.println(cliente);
		repo.save(cliente);
		model.addAttribute("mensaje", "Se registró el cliente correctamente.");
		return "APP-RODRIGUEZMONZON-REGISTRO";
	}
	
	@GetMapping("/listar")
	public String listadoClientes(Model model) {
		model.addAttribute("lstClientes", repo.findAll());
		return "listado";
	}
	
	@GetMapping("/buscarCliente")
	  public String buscarClientePorId(Model model) {
	    model.addAttribute("cliente", new Cliente());
	    return "APP-RODRIGUEZMONZON-CONSULTACLIENTE";
	  }
	
	@PostMapping("/buscarCliente")
	  public String buscarClientePorId(@ModelAttribute Cliente cliente, Model model) {
	    Cliente clienteEncontrado = repo.findById(String.valueOf(cliente.getId_cliente())).orElse(null);
	    if (clienteEncontrado == null) {
	      model.addAttribute("mensaje", "No se encontró ningún cliente con ese ID.");
	    } else {
	      model.addAttribute("clienteEncontrado", clienteEncontrado);
	    }
	    return "APP-RODRIGUEZMONZON-CONSULTACLIENTE";
	  }
	
	@GetMapping("/consultarClienteArea")
    public String areaClienteForm() {
        return "APP-RODRIGUEZMONZON-CONSULTA";
    }

    @PostMapping("/consultarClienteArea")
    public String areaClienteSubmit(@RequestParam("idCliente") int idCliente, Model model) {
    	List<AreaCliente> areaClientes = new ArrayList<>();
    	
    	try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList("CALL areasFromIdCliente(?)", idCliente);
			rows.forEach(row -> {
				AreaCliente areaCliente = new AreaCliente();
				areaCliente.setNombreArea((String) row.get("nom_area"));
				areaCliente.setCantidadClientesArea((Integer) row.get("cant_clien_area"));
				areaCliente.setIdCliente((Integer) row.get("id_cliente"));
				areaCliente.setNombreCliente((String) row.get("nom_cliente"));
				areaClientes.add(areaCliente);
			});
			
			if (areaClientes.isEmpty()) {
	            model.addAttribute("error", "No se encontraron resultados para la ID de cliente proporcionada.");
	        } else {
	            model.addAttribute("areaClientes", areaClientes);
	        }
		} catch (Exception e) {
			model.addAttribute("error", "Error al buscar áreas de cliente: " + e.getMessage());
		}

        return "APP-RODRIGUEZMONZON-CONSULTA";
    }
}
