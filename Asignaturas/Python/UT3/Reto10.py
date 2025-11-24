lista_Alumnos = {"Juan", "Pepe", "Ander", "Julio", "Amador", "Eduardo", "Ulises"}
vocales = "aeiouAEIOU"

for alumno in lista_Alumnos:
    if alumno[0] in vocales:
        print(alumno)
    