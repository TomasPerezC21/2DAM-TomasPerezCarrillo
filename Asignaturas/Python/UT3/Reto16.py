
numeroSecreto = 21

acierto = False

while(acierto==False):
    numeroUsu = int(input("Introduce un número del 1 al 100: "))
    if numeroSecreto == numeroUsu:
        print("Has acertado.")
        acierto = True