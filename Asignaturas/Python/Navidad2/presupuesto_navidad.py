def solicitar_presupuesto():
    """
    Solicita al usuario el presupuesto máximo disponible.
    """
    while True:
        try:
            presupuesto = float(input("Introduce tu presupuesto máximo para estas navidades (€): "))
            if presupuesto < 0:
                print("El presupuesto no puede ser negativo.")
            else:
                return presupuesto
        except ValueError:
            print("[ERROR] Por favor, introduce un número válido.")

def crear_lista_compras():
    """
    Pide al usuario los regalos, precios y cantidades para crear la lista de compras.
    Devuelve una lista de diccionarios.
    """
    lista_compras = []
    print("\n--- Creación de la Lista de Regalos ---")
    
    while True:
        nombre = input("Nombre del regalo: ").strip()
        if not nombre:
            print("El nombre no puede estar vacío.")
            continue
            
        try:
            precio = float(input(f"Precio unitario de '{nombre}': "))
            cantidad = int(input(f"Cantidad de '{nombre}': "))
            
            if precio < 0 or cantidad < 0:
                print("El precio y la cantidad deben ser valores positivos.")
                continue

            # Añadimos a la lista con la estructura requerida 
            lista_compras.append({
                "nombre": nombre,
                "precio": precio,
                "cantidad": cantidad
            })
            
        except ValueError:
            print("[ERROR] Introduce valores numéricos válidos para precio y cantidad.")
            continue

        continuar = input("¿Quieres añadir otro regalo a la lista? (s/n): ").lower()
        if continuar != 's':
            break
            
    return lista_compras

def calcular_y_evaluar(lista_regalos, presupuesto_maximo):
    """
    Calcula el total y compara con el presupuesto.
    """
    total_gasto = 0.0
    
    print("\n" + "="*40)
    print(f"{'PRODUCTO':<20} {'CANT':<5} {'PRECIO':<8} {'SUBTOTAL':<8}")
    print("-" * 40)

    # Calcular el total recorriendo la lista 
    for item in lista_regalos:
        subtotal = item['precio'] * item['cantidad']
        total_gasto += subtotal
        print(f"{item['nombre']:<20} {item['cantidad']:<5} {item['precio']:<8.2f} {subtotal:<8.2f}€")
    
    print("-" * 40)
    print(f"TOTAL GASTO: {total_gasto:.2f}€")
    print(f"PRESUPUESTO: {presupuesto_maximo:.2f}€")
    print("="*40)

    # Evaluar presupuesto [cite: 22]
    diferencia = presupuesto_maximo - total_gasto

    if diferencia > 0:
        # El gasto cabe y sobra dinero [cite: 23]
        print(f"\n✅ ¡Felicidades! Estás dentro del presupuesto.")
        print(f"Te sobran: {diferencia:.2f}€")
    elif diferencia == 0:
        # Presupuesto exacto [cite: 24]
        print(f"\n⚠️ ¡Cuidado! Has gastado exactamente todo tu presupuesto (0€ restantes).")
    else:
        # Se ha superado el presupuesto (diferencia es negativa) [cite: 24]
        print(f"\n❌ PRESUPUESTO EXCEDIDO.")
        print(f"Te has pasado por: {abs(diferencia):.2f}€")

def main():
    """
    Función principal
    """
    print("--- CALCULADORA DE PRESUPUESTO NAVIDEÑO ---")
    
    # 1. Crear la lista de regalos
    lista = crear_lista_compras()
    
    if not lista:
        print("No has introducido ningún regalo. Fin del programa.")
        return

    # 2. Pedir presupuesto
    presupuesto = solicitar_presupuesto()

    # 3. Calcular y mostrar resultados
    calcular_y_evaluar(lista, presupuesto)

if __name__ == "__main__":
    main()