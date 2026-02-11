# --- Conjuntos ---
c1 = set(range(1, 11))
c2 = {2, 4, 6, 8, 10}

interseccion = c1.intersection(c2)
diferencia = c1.difference(c2)

print(f"Intersección (están en ambos): {interseccion}")
print(f"Diferencia (en c1 pero no en c2): {diferencia}")

# --- Diccionarios ---
precios_frutas = {"Manzana": 2.5, "Pera": 1.8, "Plátano": 1.2}
busqueda = "Manzana"

# 1. Mostrar precio o mensaje
precio = precios_frutas.get(busqueda, "Fruta no encontrada")
print(f"Precio de {busqueda}: {precio}")

# 2. Verificar existencia
existe = busqueda in precios_frutas
print(f"¿Está {busqueda} en el diccionario?: {existe}")