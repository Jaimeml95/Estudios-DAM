# Generador de cuadrados (primeros 10)
cuadrados = [x**2 for x in range(1, 11)]
print(f"Primeros 10 cuadrados: {cuadrados}")

# Generador de impares menores de 20
impares = [x for x in range(20) if x % 2 != 0]
print(f"Números impares menores de 20: {impares}")  