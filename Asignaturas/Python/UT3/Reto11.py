oportunidades = 3

usuarioValido = "Tomas"
contrasenaValida = "12345"

condicion1 = False
condicion2 = False

while(oportunidades > 0):
    usuario = input("Introduce el usuario: ")

    if usuario == usuarioValido:
        print("Usuario válido")
        condicion1 = True
    else:
        print("Usuario incorrecto.")
        oportunidades -=1 
            
    if condicion1 == True:
        contraUsuario = input("Introduce la contraseña: ")
        if contraUsuario == contrasenaValida:
            print("Contraseña correcta.")
            condicion2 = True
        else:
            print("Contraseña errónea.")
            oportunidades -=1
    

    if condicion1 and condicion2 == True:
        print("Sesión iniciada con exito")
        break
    
if oportunidades == 0:
    print("Te quedaste sin intentos")
    



    

