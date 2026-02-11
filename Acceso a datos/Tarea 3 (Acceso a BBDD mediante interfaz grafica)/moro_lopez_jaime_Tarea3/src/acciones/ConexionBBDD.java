/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ConexionBBDD {

    private static final String URL = "jdbc:mysql://localhost:3306/pedidos?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = ""; // Dejamos la contraseña en blanco ya que no tenemos configurada ninguna

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void crearTablas() {
        // Sentencias SQL para la creación de las tablas.
        String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes ("
                + "NUM_CLI INT(11) PRIMARY KEY, "
                + "NOMBRE VARCHAR(30), "
                + "LIMITE_CREDITO DECIMAL(10,2))";

        String sqlProductos = "CREATE TABLE IF NOT EXISTS productos ("
                + "ID_PRODUCTO INT(11) PRIMARY KEY, "
                + "ID_FAB INT(11), "
                + "DESCRIPCION VARCHAR(20), "
                + "PRECIO DECIMAL(10,2), "
                + "EXISTENCIAS INT(11))";

        String sqlPedidos = "CREATE TABLE IF NOT EXISTS pedidos ("
                + "COD_PEDIDO INT(11) PRIMARY KEY, "
                + "NUM_PEDIDO INT(11), "
                + "FECHA_PEDIDO DATE, "
                + "CLIENTE INT(11), "
                + "FABRI INT(11), "
                + "PRODUCTO INT(11), "
                + "CANT INT(11), "
                + "IMPORTE DECIMAL(10,2))";

        Connection con = null;
        Statement st = null;

        try {
            con = conectar();
            st = con.createStatement();
            st.executeUpdate(sqlClientes);
            st.executeUpdate(sqlProductos);
            st.executeUpdate(sqlPedidos);

            System.out.println("Tablas creadas correctamente en la base de datos 'pedidos'.");

        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
            e.printStackTrace();
        } finally {

            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void insertarRegistros() {
        Connection con = null;
        Statement st = null;
        try {
            con = conectar();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO clientes VALUES (2101, 'Juan Pérez', 5000)");
            st.executeUpdate("INSERT INTO clientes VALUES (2102, 'María López', 1000)");
            st.executeUpdate("INSERT INTO clientes VALUES (2103, 'Carlos García', 7500)");

            st.executeUpdate("INSERT INTO productos VALUES (1, 101, 'Teclado', 25.50, 100)");
            st.executeUpdate("INSERT INTO productos VALUES (2, 102, 'Ratón', 15.75, 200)");
            st.executeUpdate("INSERT INTO productos VALUES (3, 103, 'Monitor', 150, 50)");

            st.executeUpdate("INSERT INTO pedidos VALUES (1, 1001, '2025-01-01', 2101, 1, 1, 10, 51)");
            st.executeUpdate("INSERT INTO pedidos VALUES (2, 1002, '2025-04-05', 2102, 2, 1, 5, 150)");
            st.executeUpdate("INSERT INTO pedidos VALUES (3, 1003, '2025-09-09', 2103, 3, 2, 7, 47.25)");

            System.out.println("Los 9 registros se han insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error en la inserción: " + e.getMessage());
        } finally {

            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String obtenerDatosTabla(String tabla) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- LISTADO DE ").append(tabla.toUpperCase()).append(" ---\n\n");

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conectar();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM " + tabla);

            if (tabla.equalsIgnoreCase("clientes")) {
                while (rs.next()) {
                    sb.append("ID: ").append(rs.getInt("NUM_CLI"))
                            .append(" | Nombre: ").append(rs.getString("NOMBRE"))
                            .append(" | Crédito: ").append(rs.getDouble("LIMITE_CREDITO")).append("\n");
                }
            } else if (tabla.equalsIgnoreCase("productos")) {
                while (rs.next()) {
                    sb.append("ID: ").append(rs.getInt("ID_PRODUCTO"))
                            .append(" | ").append(rs.getString("DESCRIPCION"))
                            .append(" | Precio: ").append(rs.getDouble("PRECIO"))
                            .append(" | Stock: ").append(rs.getInt("EXISTENCIAS")).append("\n");
                }
            } else if (tabla.equalsIgnoreCase("pedidos")) {
                while (rs.next()) {
                    sb.append("Cod: ").append(rs.getInt("COD_PEDIDO"))
                            .append(" | Num: ").append(rs.getInt("NUM_PEDIDO"))
                            .append(" | Fecha: ").append(rs.getDate("FECHA_PEDIDO"))
                            .append(" | Importe: ").append(rs.getDouble("IMPORTE")).append("\n");
                }
            }
        } catch (SQLException e) {
            sb.append("Error al recuperar datos: ").append(e.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String actualizarPrecioProducto(int id, double nuevoPrecio) {
        String infoProducto = "";
        Connection con = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psSelect = null;
        ResultSet rs = rs = null;

        try {
            con = conectar();

            String sqlUpdate = "UPDATE productos SET PRECIO =? WHERE ID_PRODUCTO =?";
            psUpdate = con.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, nuevoPrecio);
            psUpdate.setInt(2, id);

            psUpdate.executeUpdate();

            String sqlSelect = "SELECT * FROM productos WHERE ID_PRODUCTO =?";
            psSelect = con.prepareStatement(sqlSelect);
            psSelect.setInt(1, id);
            rs = psSelect.executeQuery();

            if (rs.next()) {
                infoProducto = "Producto Actualizado:\n"
                        + "ID: " + rs.getInt("ID_PRODUCTO") + "\n"
                        + "Descripción: " + rs.getString("DESCRIPCION") + "\n"
                        + "Nuevo Precio: " + rs.getDouble("PRECIO");
            }

        } catch (SQLException e) {
            infoProducto = "Error al actualizar: " + e.getMessage();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (psSelect != null) {
                    psSelect.close();
                }
                if (psUpdate != null) {
                    psUpdate.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return infoProducto;
    }

    public String actualizarImportePedido(int codPedido, double nuevoImporte) {
        String infoPedido = "";
        Connection con = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;

        try {
            con = conectar();

            String sqlUpdate = "UPDATE pedidos SET IMPORTE =? WHERE COD_PEDIDO =?";
            psUpdate = con.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, nuevoImporte);
            psUpdate.setInt(2, codPedido);
            psUpdate.executeUpdate();

            String sqlSelect = "SELECT * FROM pedidos WHERE COD_PEDIDO =?";
            psSelect = con.prepareStatement(sqlSelect);
            psSelect.setInt(1, codPedido);
            rs = psSelect.executeQuery();

            if (rs.next()) {
                infoPedido = "Pedido Actualizado:\n"
                        + "Código: " + rs.getInt("COD_PEDIDO") + "\n"
                        + "Número: " + rs.getInt("NUM_PEDIDO") + "\n"
                        + "Fecha: " + rs.getDate("FECHA_PEDIDO") + "\n"
                        + "Nuevo Importe: " + rs.getDouble("IMPORTE");
            }

        } catch (SQLException e) {
            infoPedido = "Error al actualizar pedido: " + e.getMessage();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (psSelect != null) {
                    psSelect.close();
                }
                if (psUpdate != null) {
                    psUpdate.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return infoPedido;
    }

    public String actualizarNombreYCredito(int numCli, String nuevoNombre, double nuevoLimite) {
        String infoCliente = "";
        Connection con = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;

        try {
            con = conectar();

            String sqlUpdate = "UPDATE clientes SET NOMBRE =?, LIMITE_CREDITO =? WHERE NUM_CLI =?";
            psUpdate = con.prepareStatement(sqlUpdate);

            psUpdate.setString(1, nuevoNombre);
            psUpdate.setDouble(2, nuevoLimite);
            psUpdate.setInt(3, numCli);

            psUpdate.executeUpdate();

            String sqlSelect = "SELECT * FROM clientes WHERE NUM_CLI =?";
            psSelect = con.prepareStatement(sqlSelect);
            psSelect.setInt(1, numCli);
            rs = psSelect.executeQuery();

            if (rs.next()) {
                infoCliente = "Cliente Actualizado (Punto 11):\n"
                        + "ID: " + rs.getInt("NUM_CLI") + "\n"
                        + "Nuevo Nombre: " + rs.getString("NOMBRE") + "\n"
                        + "Nuevo Límite: " + rs.getDouble("LIMITE_CREDITO");
            } else {
                infoCliente = "Atención: El cliente " + numCli + " no existe en la tabla.";
            }

        } catch (SQLException e) {
            infoCliente = "Error en la actualización múltiple: " + e.getMessage();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (psSelect != null) {
                    psSelect.close();
                }
                if (psUpdate != null) {
                    psUpdate.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return infoCliente;
    }

    public String consultaFabricante1() {
        StringBuilder sb = new StringBuilder();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conectar();
            st = con.createStatement();

            sb.append("--- PRODUCTOS DEL FABRICANTE 1 ---\n");
            rs = st.executeQuery("SELECT DESCRIPCION FROM productos WHERE ID_FAB = 1");
            while (rs.next()) {
                sb.append("- ").append(rs.getString("DESCRIPCION")).append("\n");
            }
            sb.append("\n");

            sb.append("--- DETALLE DE PEDIDOS (FABRICANTE 1) ---\n");
            String sqlJoin = "SELECT p.COD_PEDIDO, p.NUM_PEDIDO, c.NOMBRE, pr.DESCRIPCION, p.CANT "
                    + "FROM pedidos p "
                    + "INNER JOIN clientes c ON p.CLIENTE = c.NUM_CLI "
                    + "INNER JOIN productos pr ON p.PRODUCTO = pr.ID_PRODUCTO "
                    + "WHERE p.FABRI = 1";

            rs = st.executeQuery(sqlJoin);
            while (rs.next()) {
                sb.append("Pedido: ").append(rs.getInt("COD_PEDIDO"))
                        .append(" | Num: ").append(rs.getInt("NUM_PEDIDO"))
                        .append(" | Cliente: ").append(rs.getString("NOMBRE"))
                        .append(" | Art: ").append(rs.getString("DESCRIPCION"))
                        .append(" | Cant: ").append(rs.getInt("CANT")).append("\n");
            }

        } catch (SQLException e) {
            sb.append("Error en la consulta compleja: ").append(e.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }
}
