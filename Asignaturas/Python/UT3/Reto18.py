
importe = int(input("Introduce un importe para desglosarlo en billetes: "))

billetes = [500, 200, 100, 50, 20, 10, 5]

print(f"Desglose para {importe} euros:")

for valor in billetes:
    
    cantidad = importe // valor
    
    if cantidad > 0:
        print(f"{cantidad} billete(s) de {valor} €")
    
    importe = importe % valor

if importe > 0:
    print(f"Sobran {importe} € en monedas.")

