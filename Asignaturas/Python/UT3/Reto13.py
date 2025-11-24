
mi_lista = {"hola.txt", "resumen.pdf", "texto.txt", "musica.mp4", "python.py", "prueba.pdf", "kalewi.pdf"}

buscar = "pdf"

for archivo in mi_lista:
    partesDoc = archivo.split(".")
    extension = partesDoc[1]
    if extension == buscar:
        print(archivo)


