
def calculadora(numero1, numero2, operador):
    if operador == "+":
        print(numero1 + numero2)
    elif operador == "-":
        print(numero1 - numero2)
    elif operador == "*":
        print(numero1 * numero2)
    elif operador == "/":
        if numero2 != 0:
            print(numero1 / numero2)
        else:
            print("Error: No se puede dividir por cero.")
    else:
        print("Operador no válido")

numero1 = int(input("Introduce el primer número: "))
numero2 = int(input("Introduce el segundo número: " ))
operador = input("Introduce el operador (+,-,*,/): ")

calculadora(numero1, numero2, operador)
