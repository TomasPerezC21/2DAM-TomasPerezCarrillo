
fraseUsuario = input("Introduce una frase: ")

fraseSinEspacio = fraseUsuario.split()

contadorPalabras = len(fraseSinEspacio)

print(f"En tu frase hay: {contadorPalabras} palabras")