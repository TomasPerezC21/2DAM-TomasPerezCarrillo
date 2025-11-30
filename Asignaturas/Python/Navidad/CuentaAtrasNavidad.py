from datetime import date

# 1. Obtener la fecha de hoy
hoy = date.today()

# 2. Definir la Navidad de este año
navidad = date(hoy.year, 12, 25)

# 3. Comprobar si ya pasó la Navidad este año
if hoy > navidad:
    # Si hoy es mayor que el 25 de dic, apuntamos a la Navidad del año que viene
    navidad = date(hoy.year + 1, 12, 25)

# 4. Calcular la diferencia
dias_restantes = (navidad - hoy).days

print(f"Faltan {dias_restantes} días para Navidad.")