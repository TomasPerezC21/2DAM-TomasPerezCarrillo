import csv
import random
import os

NOMBRE_ARCHIVO_ENTRADA = "amigo invisible.csv"
NOMBRE_ARCHIVO_SALIDA = "resultados_sorteo.txt"

def crear_csv_ejemplo():
    """
    Crea un archivo CSV de prueba si no existe, para que el programa funcione
    directamente al ejecutarlo.
    """
    if not os.path.exists(NOMBRE_ARCHIVO_ENTRADA):
        print(f"[INFO] Creando archivo de prueba '{NOMBRE_ARCHIVO_ENTRADA}'...")
        datos = [["Nombre"], ["Ana"], ["Carlos"], ["Beatriz"], ["David"], ["Elena"], ["Fernando"]]
        with open(NOMBRE_ARCHIVO_ENTRADA, mode='w', newline='', encoding='utf-8') as f:
            writer = csv.writer(f)
            writer.writerows(datos)

def cargar_participantes(archivo):
    """
    Lee el archivo CSV y carga los nombres en una lista.
    Se salta la cabecera si existe.
    """
    participantes = []
    try:
        with open(archivo, mode='r', encoding='utf-8') as f:
            reader = csv.reader(f)
            # Intentamos saltar la cabecera si la primera fila es "Nombre"
            lista_raw = list(reader)
            
            for fila in lista_raw:
                if fila: # Evitar filas vacías
                    nombre = fila[0].strip()
                    # Evitamos añadir la cabecera "Nombre" como participante
                    if nombre.lower() != "nombre":
                        participantes.append(nombre)
        return participantes
    except FileNotFoundError:
        print(f"[ERROR] No se encuentra el archivo {archivo}")
        return []

def generar_parejas(participantes):
    """
    Mezcla aleatoriamente y asigna parejas de forma circular:
    El 1º regala al 2º, el 2º al 3º... y el último al 1º.
    """
    # 1. Mezclar aleatoriamente (Requisito del PDF)
    random.shuffle(participantes)
    
    parejas = []
    n = len(participantes)
    
    # 2. Generar asignaciones
    for i in range(n):
        regalador = participantes[i]
        # El receptor es el siguiente en la lista.
        # Usamos el operador módulo % para que el último apunte al índice 0
        indice_receptor = (i + 1) % n
        receptor = participantes[indice_receptor]
        
        parejas.append((regalador, receptor))
        
    return parejas

def guardar_resultados(parejas):
    """
    Guarda las parejas generadas en un archivo de texto.
    """
    try:
        with open(NOMBRE_ARCHIVO_SALIDA, 'w', encoding='utf-8') as f:
            f.write("--- RESULTADOS DEL SORTEO ---\n")
            for regalador, receptor in parejas:
                linea = f"{regalador} --> regala a --> {receptor}\n"
                f.write(linea)
        print(f"\n[INFO] Resultados guardados en '{NOMBRE_ARCHIVO_SALIDA}'")
    except Exception as e:
        print(f"[ERROR] Al guardar el archivo: {e}")

def main():
    # 1. Preparar entorno (Crear CSV si no existe)
    crear_csv_ejemplo()
    
    # 2. Cargar datos
    print("--- SORTEO DE AMIGO INVISIBLE ---")
    nombres = cargar_participantes(NOMBRE_ARCHIVO_ENTRADA)
    
    if len(nombres) < 2:
        print("[ERROR] Necesitas al menos 2 participantes para jugar.")
        return

    print(f"Participantes cargados ({len(nombres)}): {', '.join(nombres)}")

    # 3. Realizar sorteo
    input("\nPresiona ENTER para realizar el sorteo...")
    parejas = generar_parejas(nombres)

    # 4. Mostrar y guardar
    print("\n--- ASIGNACIONES ---")
    for regalador, receptor in parejas:
        print(f"{regalador} le regala a {receptor}")
        
    guardar_resultados(parejas)

if __name__ == "__main__":
    main()