print("   ºC   |    ºF")
print("----------------") 


for c in range(0, 101, 10):
    
    # Fórmula: (C * 1.8) + 32
    f = (c * 1.8) + 32
    
    print(f"   {c}\t|   {f}")