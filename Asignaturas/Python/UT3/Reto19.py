import time

barraCarga = ""

print("Cargando...")
for i in range(40):
    barraCarga += "#"
    
    print(f"\r[{barraCarga}]", end="")
    
    time.sleep(0.2)

print("Completado.")
    
