def mostrar_inventario(inventario):
    """
    Muestra los productos disponibles con su precio y stock.
    """
    print("\n--- PRODUCTOS DISPONIBLES ---")
    print(f"{'Producto':<15} {'Precio':<10} {'Stock':<5}")
    print("-" * 35)
    for nombre, datos in inventario.items():
        print(f"{nombre:<15} {datos['precio']:<6.2f}€    {datos['stock']:<5}")
    print("-" * 35)

def realizar_compra(inventario):
    """
    Gestiona el proceso de compra validando el stock.
    """
    cesta = []
    total_compra = 0.0
    continuar = True

    print("Bienvenido a la tienda navideña. Introduce tu pedido.")

    while continuar:
        mostrar_inventario(inventario)
        
        producto = input("\nIntroduce el nombre del producto que quieres: ").strip().capitalize()
        
        # 1. Validar que el producto existe
        if producto not in inventario:
            print(f"Error: El producto '{producto}' no existe en nuestro catálogo.")
        else:
            try:
                cantidad = int(input(f"¿Cuántas unidades de '{producto}' quieres?: "))
                
                stock_actual = inventario[producto]['stock']
                
                # 2. Verificar si hay suficiente stock (Requisito clave)
                if cantidad <= 0:
                    print("Error: La cantidad debe ser mayor que 0.")
                elif cantidad > stock_actual:
                    # Si no hay suficiente, mostrar error
                    print(f"Error: No hay suficiente stock. Solo quedan {stock_actual} unidades.")
                else:
                    # 3. Si hay stock: calcular importe y restar stock
                    precio_unidad = inventario[producto]['precio']
                    subtotal = precio_unidad * cantidad
                    
                    # Actualizamos el inventario (Restamos stock)
                    inventario[producto]['stock'] -= cantidad
                    
                    # Añadimos a la cesta del cliente
                    cesta.append({
                        "producto": producto,
                        "cantidad": cantidad,
                        "subtotal": subtotal
                    })
                    
                    total_compra += subtotal
                    print(f"Añadido: {cantidad}x {producto} ({subtotal:.2f}€)")

            except ValueError:
                print("Error: Por favor, introduce un número válido para la cantidad.")

        # Preguntar si quiere seguir comprando
        opcion = input("\n¿Quieres añadir otro producto? (s/n): ").lower()
        if opcion != 's':
            continuar = False

    return cesta, total_compra

def main():
    # 1. Crear lista con productos, precios y stock inicial
    # Usamos un diccionario para acceder rápido a los datos
    inventario = {
        "Turron":   {"precio": 4.50, "stock": 10},
        "Polvoron": {"precio": 3.20, "stock": 25},
        "Cava":     {"precio": 9.00, "stock": 5},
        "Mazapan":  {"precio": 2.80, "stock": 15},
        "Panettone": {"precio": 12.50, "stock": 4}
    }

    # 2. Proceso de compra
    cesta_final, total = realizar_compra(inventario)

    # 3. Mostrar resultados finales (Requisito del PDF)
    print("\n" + "="*40)
    print("RESUMEN DE LA COMPRA")
    print("="*40)
    
    if not cesta_final:
        print("No has comprado nada.")
    else:
        for item in cesta_final:
            print(f"- {item['cantidad']}x {item['producto']}: {item['subtotal']:.2f}€")
        
        print("-" * 40)
        print(f"PRECIO TOTAL DE LA CESTA: {total:.2f}€")

    # 4. Mostrar stock restante (Requisito del PDF)
    print("\n" + "="*40)
    print("STOCK RESTANTE EN TIENDA")
    print("="*40)
    for nombre, datos in inventario.items():
        print(f"{nombre}: {datos['stock']} unidades")

if __name__ == "__main__":
    main()