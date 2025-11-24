import time

# --- 1. CONFIGURACIÓN INICIAL (Valores definidos en el código) ---
usuario_guardado = "admin"
contrasena_guardada = "1234"
sesion_activa = False # Variable para controlar el acceso

print("--- SISTEMA DE LOGIN ---")

# --- 2. PEDIR DATOS Y VERIFICAR ---
usuario_input = input("Introduce tu usuario: ")
contrasena_input = input("Introduce tu contraseña: ")

# Verificamos si coinciden con los guardados
if usuario_input == usuario_guardado and contrasena_input == contrasena_guardada:
    print("Credenciales correctas. Accediendo...")
    sesion_activa = True
    time.sleep(1) # Pequeña pausa estética
else:
    print("Error: Usuario o contraseña incorrectos.")

# --- 3. MENÚ PRINCIPAL (Bucle While) ---
# Solo entramos aquí si la sesión está activa (login correcto)
while sesion_activa:
    print("\n" + "="*30)
    print("       MENÚ DE USUARIO")
    print("="*30)
    print("1. Ver perfil")
    print("2. Cambiar contraseña")
    print("3. Salir")
    
    opcion = input("Elige una opción (1-3): ")

    # --- CONTROL DE FLUJO (Condicionales) ---
    if opcion == "1":
        # Opción: Ver Perfil
        print(f"\n[PERFIL] Usuario actual: {usuario_guardado}")
        print("Estado: Conectado")
        
    elif opcion == "2":
        # Opción: Cambiar Contraseña
        nueva_pass = input("\nIntroduce la nueva contraseña: ")
        # Actualizamos la variable
        contrasena_guardada = nueva_pass 
        print("¡Contraseña actualizada con éxito!")
        print(f"(Tu nueva contraseña es: {contrasena_guardada})")

    elif opcion == "3":
        # Opción: Salir
        print("\nCerrando sesión... ¡Hasta luego!")
        sesion_activa = False # Esto rompe el bucle while

    else:
        # Opción no válida
        print("Opción desconocida. Por favor, intenta de nuevo.")