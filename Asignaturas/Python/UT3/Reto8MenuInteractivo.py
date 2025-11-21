def saludar():
    print("Saludos, portate bien.")

def sumar(numero1, numero2):
    return numero1 + numero2

seguir = True 

while(seguir):
    usuario = int(input("Bienvenido al menu interactivo. Qué desea:\n1. Saludar  \n2. Sumar \n3. Salir\n----------------\n"))
    
    if usuario==1:
        saludar()
    elif usuario==2:
        num1 = int(input("Introduce numero 1: "))
        num2 = int(input("Introduce numero 2: "))
        print(f"Resultado de la suma:  {int(sumar(num1, num2))}")
    elif usuario == 3:
        print("Hasta luego.")
        seguir = False
    else:
        print("Error. Introduce una opción válida.")
    
        
    
    



