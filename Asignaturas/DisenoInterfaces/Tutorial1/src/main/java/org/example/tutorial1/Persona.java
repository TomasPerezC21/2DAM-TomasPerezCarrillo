package org.example.tutorial1;

import java.time.LocalDate;

public class Persona {

    private String nombre;
    private String apellido;
    private String calle;
    private String ciudad;
    private int codigoPostal;
    private LocalDate fechaNacimiento;

    public Persona(String nombre, int codigoPostal, String ciudad, String apellido, String calle, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.apellido = apellido;
        this.calle = calle;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
