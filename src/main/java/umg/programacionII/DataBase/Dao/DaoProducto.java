package umg.programacionII.DataBase.Dao;


import umg.programacionII.DataBase.Conexion.Conexion;
import umg.programacionII.DataBase.Model.ModelProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoProducto {

    public void agregarProducto(String descripcion, String origen) throws Exception {
        Connection connection = Conexion.getConnection();
        String query = "INSERT INTO tb_producto (descripcion, origen, precio, existencia) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, descripcion);
        ps.setString(2, origen);

        ps.executeUpdate();
        connection.close();
    }

    public ModelProducto obtenerProductoPorId(int idProducto) throws Exception {
        Connection connection = Conexion.getConnection();
        String query = "SELECT * FROM tb_producto WHERE id_producto = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idProducto);

        ResultSet rs = ps.executeQuery();
        ModelProducto producto = null;
        if (rs.next()) {
            producto = new ModelProducto();
            producto.setId_producto(rs.getInt("id_producto"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setOrigen(rs.getString("origen"));
            producto.setPrecio(rs.getInt("precio"));
            producto.setExistencia(rs.getInt("existencia"));
        }
        connection.close();
        return producto;
    }

    public void actualizarProducto(int idProducto, String descripcion, String origen) throws Exception {
        Connection connection = Conexion.getConnection();
        String query = "UPDATE tb_producto SET descripcion = ?, origen = ? WHERE id_producto = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, descripcion);
        ps.setString(2, origen);
        ps.setInt(3, idProducto);

        ps.executeUpdate();
        connection.close();
    }

    public void eliminarProducto(int idProducto) throws Exception {
        Connection connection = Conexion.getConnection();
        String query = "DELETE FROM tb_producto WHERE id_producto = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idProducto);

        ps.executeUpdate();
        connection.close();
    }

    public List<ModelProducto> obtenerTodosLosProductos() throws Exception {
        Connection connection = Conexion.getConnection();
        String query = "SELECT * FROM tb_producto ORDER BY origen";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<ModelProducto> productos = new ArrayList<>();

        while (rs.next()) {
            ModelProducto producto = new ModelProducto();
            producto.setId_producto(rs.getInt("id_producto"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setOrigen(rs.getString("origen"));
            producto.setPrecio(rs.getInt("precio"));
            producto.setExistencia(rs.getInt("existencia"));
            productos.add(producto);
        }

        connection.close();
        return productos;
    }

    public List<ModelProducto> obtenerGenericos(String condicion) throws Exception {
        Connection connection = Conexion.getConnection();
        String query = "SELECT * FROM tb_producto where " + condicion;

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<ModelProducto> productos = new ArrayList<>();

        while (rs.next()) {
            ModelProducto producto = new ModelProducto();
            producto.setId_producto(rs.getInt("id_producto"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setOrigen(rs.getString("origen"));
            producto.setPrecio(rs.getInt("precio"));
            producto.setExistencia(rs.getInt("existencia"));
            productos.add(producto);
        }

        connection.close();
        return productos;
    }
}