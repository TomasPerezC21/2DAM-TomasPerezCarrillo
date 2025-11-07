from datetime import date 

anio_actual = date.today().year 


edad_usuario = int(input("Introduce tu edad: "))

nacimiento = (anio_actual - edad_usuario)

nombre_usuario = input("Pon tu nombre: ")

mes = int(input("Introduce el número del mes en el que naciste: "))


meses = {
    1: "Enero",
    2: "Febrero",
    3: "Marzo",
    4: "Abril",
    5: "Mayo",
    6: "Junio",
    7: "Julio",
    8: "Agosto",
    9: "Septiembre",
    10: "Octubre",
    11: "Noviembre",
    12: "Diciembre"
}

mesString = meses.get(mes)


print(f"Hola {nombre_usuario}. Tienes {edad_usuario} años y naciste en el mes {mesString} año {nacimiento}." )