import json
import os

# Nombre del archivo donde guardaremos los datos
NOMBRE_ARCHIVO = "catalogo_navidad.json"

def guardar_catalogo(catalogo):
    """
    Guarda la lista de regalos en un archivo JSON.
    """
    try:
        with open(NOMBRE_ARCHIVO, 'w', encoding='utf-8') as f:
            json.dump(catalogo, f, indent=4, ensure_ascii=False)
        print(f"\n[INFO] Catálogo guardado correctamente en {NOMBRE_ARCHIVO}.")
    except Exception as e:
        print(f"[ERROR] No se pudo guardar el archivo: {e}")

def mostrar_catalogo(catalogo):
    """
    Muestra por pantalla todos los regalos y sus precios.
    """
    print("\n" + "="*30)
    print("   CATÁLOGO DE REGALOS")
    print("="*30)
    
    if not catalogo:
        print("El catálogo está vacío.")
    else:
        for i, regalo in enumerate(catalogo, 1):
            print(f"{i}. {regalo['nombre']} - {regalo['precio']}€")
    print("-" * 30)

def solicitar_regalos(catalogo, cantidad_minima=0):
    """
    Pide al usuario que introduzca regalos.
    Si cantidad_minima > 0, obliga a introducir esa cantidad.
    """
    contador = 0
    continuar = True

    print(f"\n--- Introducción de regalos ---")
    
    while continuar:
        # Si hay un mínimo obligatorio (ej. los primeros 6), controlamos el bucle
        if cantidad_minima > 0 and contador < cantidad_minima:
            print(f"\nRegalo {contador + 1} de {cantidad_minima} (Obligatorio):")
        else:
            # Si ya pasamos el mínimo, preguntamos si quiere seguir solo si no estamos en la fase obligatoria
            if cantidad_minima == 0: 
                respuesta = input("¿Quieres añadir un regalo nuevo? (s/n): ").lower()
                if respuesta != 's':
                    break

        nombre = input("Nombre del regalo: ").strip()
        while not nombre: # Validación simple para no dejar nombre vacío
            nombre = input("El nombre no puede estar vacío. Introduce el nombre: ").strip()

        try:
            precio = float(input("Precio del regalo: "))
            
            # Añadimos al catálogo
            nuevo_regalo = {"nombre": nombre, "precio": precio}
            catalogo.append(nuevo_regalo)
            contador += 1

        except ValueError:
            print("[ERROR] Por favor, introduce un precio numérico válido (usa punto para decimales).")

        # Control para salir del bucle obligatorio
        if cantidad_minima > 0 and contador >= cantidad_minima:
            continuar = False

def rectificar_precio(catalogo):
        
    mostrar_catalogo(catalogo)
    nombre_buscado = input("\n¿A qué regalo quieres cambiarle el precio? (Escribe el nombre exacto): ").strip()
    
    encontrado = False
    for regalo in catalogo:
        if regalo['nombre'].lower() == nombre_buscado.lower():
            try:
                nuevo_precio = float(input(f"Introduce el nuevo precio para '{regalo['nombre']}': "))
                regalo['precio'] = nuevo_precio
                print(f"[OK] Precio actualizado a {nuevo_precio}€")
                encontrado = True
                break
            except ValueError:
                print("[ERROR] Precio no válido.")
                return

    if not encontrado:
        print(f"[!] No se encontró ningún regalo con el nombre '{nombre_buscado}'.")

def main():
    """
    Función principal que controla el flujo del programa.
    """
    catalogo = []

    # 1. Pedir mínimo 6 regalos (Requisito del PDF)
    print("Bienvenido. Para comenzar, necesitamos registrar 6 regalos iniciales.")
    solicitar_regalos(catalogo, cantidad_minima=6)

    # 2. Guardar y mostrar
    guardar_catalogo(catalogo)
    mostrar_catalogo(catalogo)

    # 3. Preguntar si quiere añadir más regalos (Requisito del PDF)
    print("\n--- Fase de ampliación ---")
    while True:
        opcion = input("¿Quieres añadir algún regalo más? (s/n): ").lower()
        if opcion == 's':
            solicitar_regalos(catalogo, cantidad_minima=0) # 0 porque ya no hay mínimo
            guardar_catalogo(catalogo) # Guardamos tras añadir
        elif opcion == 'n':
            break
        else:
            print("Por favor, responde 's' o 'n'.")

    # 4. Preguntar si quiere rectificar precios (Requisito del PDF)
    while True:
        opcion = input("\n¿Quieres rectificar el precio de algún regalo? (s/n): ").lower()
        if opcion == 's':
            rectificar_precio(catalogo)
            guardar_catalogo(catalogo) # Guardamos los cambios
            mostrar_catalogo(catalogo) # Mostramos como quedó
        elif opcion == 'n':
            break
        else:
            print("Por favor, responde 's' o 'n'.")

    print("\n¡Gracias! El catálogo final ha sido guardado.")

if __name__ == "__main__":
    main()