package Actividad2;

public class Libro {

    private int cod_libro;
    private String nombre_libro;
    private int numero_paginas;
    private String tema;
    private int autor;
    private int editorial;

    public Libro(int cod_libro, String nombre_libro, int numero_paginas, String tema, int autor, int editorial) {
        this.cod_libro = cod_libro;
        this.nombre_libro = nombre_libro;
        this.numero_paginas = numero_paginas;
        this.tema = tema;
        this.autor = autor;
        this.editorial = editorial;
    }

    public int getCod_libro() {
        return cod_libro;
    }

    public String getNombre_libro() {
        return nombre_libro;
    }

    public int getNumero_paginas() {
        return numero_paginas;
    }

    public String getTema() {
        return tema;
    }

    public int getAutor() {
        return autor;
    }

    public int getEditorial() {
        return editorial;
    }

    public void setNombre_libro(String nombre_libro) {
        this.nombre_libro = nombre_libro;
    }

    public void setNumero_paginas(int numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public void setEditorial(int editorial) {
        this.editorial = editorial;
    }
}
