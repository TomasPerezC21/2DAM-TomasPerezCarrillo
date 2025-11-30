def mostrar_menu():
    print("\n--- MENÚ DE VILLANCICOS ---")
    print("1. Campana sobre Campana")
    print("2. Los Peces en el Río")
    print("3. Mi Burrito Sabanero")
    print("5. SALIR")

while True:
    mostrar_menu()
    opcion = input("\nElige una opción (1-5): ")

    if opcion == "1":
        print("\nCampana sobre campana, y sobre campana una,")
        print("asómate a la ventana, verás al Niño en la cuna.")
    
    elif opcion == "2":
        print("\nPero mira cómo beben los peces en el río,")
        print("pero mira cómo beben por ver al Dios nacido.")
    
    elif opcion == "3":
        print("\nCon mi burrito sabanero voy camino de Belén,")
        print("si me ven, si me ven, voy camino de Belén.")
    
    
    elif opcion == "5":
        print("\n¡Feliz Navidad!")
        break  # Esto rompe el bucle y termina el programa
    
    else:
        print("\n Opción no válida. Por favor, elige un número del 1 al 5.")