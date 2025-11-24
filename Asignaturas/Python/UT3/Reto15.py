
numero = int(input("Introduce un número: "))

es_primo = True

if numero < 2:
    es_primo = False
else:
    for i in range(2, numero):
        if numero % i == 0:  
            es_primo = False
            break 

if es_primo:
    print(f"El {numero} ES PRIMO")
else:
    print(f"El {numero} NO es primo")

