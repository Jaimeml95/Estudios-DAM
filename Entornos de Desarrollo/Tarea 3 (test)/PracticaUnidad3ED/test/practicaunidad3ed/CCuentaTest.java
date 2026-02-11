/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaunidad3ed;

import org.junit.*;
import static org.junit.Assert.*;

public class CCuentaTest {

    private CCuenta cuenta;

    @BeforeClass
    public static void initAll() {
        // Si necesitas inicializar algo a nivel global, va aquí.
    }

    @AfterClass
    public static void tearDownAll() {
    }

    @Before
    public void setUp() {
        // saldo inicial 1000.0, nombre y cuenta conocidas:
        cuenta = new CCuenta("Juan López", "1000-2365-85-123456789", 1000.0, 0.0);
    }

    @After
    public void tearDown() {
        cuenta = null;
    }
    //Casos de prueba para verificar el método ingresar
    @Test
    public void testIngresarPositivo() throws Exception {
        cuenta.ingresar(500.0);
        assertEquals(1500.0, cuenta.estado(), 0.0);
    }

    @Test(expected = Exception.class)
    public void testIngresarNegativoDebeFallar() throws Exception {
        cuenta.ingresar(-100.0);
    }
    //Casos de prueba para verificar la funcion retirar
    @Test
    public void testRetirarPositivo() throws Exception {
        cuenta.retirar(200.0);
        assertEquals(800.0, cuenta.estado(), 0.0);
    }

    @Test(expected = Exception.class)
    public void testRetirarCeroODebeFallar() throws Exception {
        cuenta.retirar(0.0);
    }

    @Test(expected = Exception.class)
    public void testRetirarMasQueSaldoDebeFallar() throws Exception {
        cuenta.retirar(5000.0);
    }

    /**
     * Test que ingresa y retira en la misma operación. Empezamos en 1000.0,
     * ingresamos 300.0 y retiramos 150.0, el saldo final debe ser 1150.0.
     */
    @Test
    public void testIngresarYRetirar_EnLaMismaPrueba() throws Exception {
        // Ingreso
        cuenta.ingresar(300.0);
        // Retiro
        cuenta.retirar(150.0);
        // Comprobamos saldo final: 1000 + 300 − 150 = 1150
        assertEquals(1150.0, cuenta.estado(), 0.0);
    }

}
