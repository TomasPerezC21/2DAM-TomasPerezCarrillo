vocales = "aeiouAEIOU"

usuario = input("Introduce una palabra: ")

contadorVocales = 0

for letra in usuario:
    if letra in vocales:
        contadorVocales+=1

print(f"La palabra '{usuario}' tiene {contadorVocales} vocales.")