# Solicitamos el tamaño del árbol
altura = int(input("Introduce la altura del árbol (número entero positivo): "))

print("\n") # Un salto de línea para separar

# 1. Bucle para las ramas 
for i in range(altura):
    # Calculamos los espacios para centrar el árbol
    espacios = " " * (altura - i - 1)
    # Calculamos el número de asteriscos (impares: 1, 3, 5...)
    hojas = "*" * (2 * i + 1)
    
    print(espacios + hojas)

# 2. Bucle para el tronco
# El tronco siempre debe estar debajo de la punta, que tiene (altura - 1) espacios
espacios_tronco = " " * (altura - 1)

# Dibujamos un tronco de 2 líneas de alto
for i in range(2):
    print(espacios_tronco + "|")

# Mensaje final
print("\n ¡Feliz Navidad! Que lo pases en grande.")