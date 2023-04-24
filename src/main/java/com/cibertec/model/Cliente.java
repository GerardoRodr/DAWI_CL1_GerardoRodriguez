package com.cibertec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	private int id_cliente;
	private String nom_cliente;
	private int id_area;
	private String dni_cliente;
	private int edad_cliente;
	private String genero_cliente;
	
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nom_cliente=" + nom_cliente + ", id_area=" + id_area + 
				", dni_cliente=" + dni_cliente + ", edad_cliente=" + edad_cliente + ", genero_cliente=" + genero_cliente + "]";
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNom_cliente() {
		return nom_cliente;
	}

	public void setNom_cliente(String nom_cliente) {
		this.nom_cliente = nom_cliente;
	}

	public int getId_area() {
		return id_area;
	}

	public void setId_area(int id_area) {
		this.id_area = id_area;
	}

	public String getDni_cliente() {
		return dni_cliente;
	}

	public void setDni_cliente(String dni_cliente) {
		this.dni_cliente = dni_cliente;
	}

	public int getEdad_cliente() {
		return edad_cliente;
	}

	public void setEdad_cliente(int edad_cliente) {
		this.edad_cliente = edad_cliente;
	}

	public String getGenero_cliente() {
		return genero_cliente;
	}

	public void setGenero_cliente(String genero_cliente) {
		this.genero_cliente = genero_cliente;
	}
}
