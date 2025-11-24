import random
import string

listaContra = []

tamanioContra = 8

letras = string.ascii_letters + string.digits

for contra in range(0,5):
    contra = "".join(random.choices(letras, k=tamanioContra)) 
    listaContra.append(contra)

print("Lista de 5 contraseñas: ")
for contrasenias in listaContra:
    print(contrasenias)

    
