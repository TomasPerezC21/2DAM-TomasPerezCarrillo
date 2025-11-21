
pares = []

for numero in range(1,21):
    if(numero%2==0):
        pares.append(numero)

print("Números pares del 1 al 20: ")
for numero in pares:
    print(numero, end=" ")