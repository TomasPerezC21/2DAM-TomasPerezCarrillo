
def calculadora(numero1, numero2, operador):
    """Realiza una operación matemática y devuelve el resultado."""
    if operador == "+":
        return numero1 + numero2
    elif operador == "-":
        return numero1 - numero2
    elif operador == "*":
        return numero1 * numero2
    elif operador == "/":
        if numero2 != 0:
            return numero1 / numero2
        else:
            print("Error: No se puede dividir por cero.")
            return None
    else:
        print("Operador no válido")
        return None

while True:
    # Pedimos los datos al usuario
    numero1 = int(input("Introduce el primer número: "))
    numero2 = int(input("Introduce el segundo número: "))
    operador = input("Introduce el operador (+,-,*,/): ")

    # Llamamos a la función y guardamos el resultado
    resultado = calculadora(numero1, numero2, operador)

    # Si el resultado es válido, lo mostramos y guardamos
    if resultado is not None:
        print(f"El resultado es: {resultado}")
        # Guardamos la operación en el historial
        with open("historial.txt", "a") as f:
            f.write(f"{numero1} {operador} {numero2} = {resultado}\n")

    # Preguntamos si quiere continuar
    continuar = input("¿Quieres hacer otra operación? (s/n): ")
    if continuar.lower() != "s":
        break

print("\n----- Historial de operaciones -----")
with open("historial.txt", "r") as f:
    print(f.read())
