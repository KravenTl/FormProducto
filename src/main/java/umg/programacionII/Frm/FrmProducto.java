package umg.programacionII.Frm;

import umg.programacionII.DataBase.Model.ModelProducto;
import umg.programacionII.DataBase.Service.ServiceProducto;
import umg.programacionII.Reportes.PdfReport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmProducto {
    private JLabel lblTitulo;
    private JPanel JPanelProducto;
    private JTextField textFieldID;
    private JLabel lblId;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblOrigen;
    private JComboBox comboBoxOrigen;
    private JButton buttonBuscar;
    private JButton buttonCrear;
    private JButton buttonEliminar;
    private JButton buttonActualizar;
    private JLabel lblPrecio;
    private JLabel lblExistencia;
    private JTextField textFieldPrecio;
    private JTextField textFieldExistencia;
    private JLabel lblTipoReporte;
    private JComboBox comboBoxReportes;
    private JButton buttonCrarReporte;

    private ServiceProducto serviceProducto;
    public static void main(String[] args) {
        JFrame frame = new JFrame("FrmProducto");
        frame.setContentPane(new FrmProducto().JPanelProducto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    public FrmProducto() {
        //Instanciar la clase Service del producto
        serviceProducto = new ServiceProducto();

        //Contenido del combo box origen
        String[] orígenes = {
                "","Estados Unidos", "Corea del Sur", "Vietnam", "Colombia",
                "Italia", "Japón", "Suiza", "México",
                "España", "Argentina", "Noruega", "Suecia",
                "Francia", "Irlanda", "China", "Alemania", "Escocia"
        };

        for (String org: orígenes ){
            comboBoxOrigen.addItem(org);
        }

        //Contenido del combobox Registro
        // Definir las opciones en un array
        String[] opcionesReportes = {
                "Reporte General",
                "Precio menores a 100",
                "Existencia menor a 30 unidades",
                "Precio entre 200 y 400",
                "Ordenar de mayor a menor",
                "Ordenar de menor a mayor"
        };

// Agregar las opciones al comboBox en un bucle
        for (String opcion : opcionesReportes) {
            comboBoxReportes.addItem(opcion);
        }

        // Para Buscar
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buscar producto por ID
                try {
                    int idProducto = Integer.parseInt(textFieldID.getText());
                    ModelProducto producto = serviceProducto.obtenerProductoPorId(idProducto);

                    if (producto != null) {
                        textFieldNombre.setText(producto.getDescripcion());
                        comboBoxOrigen.setSelectedItem(producto.getOrigen());
                        textFieldPrecio.setText(String.valueOf(producto.getPrecio()));
                        textFieldExistencia.setText(String.valueOf(producto.getExistencia()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código inválido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar el producto: " + ex.getMessage());
                }
            }
        });

        // Para crear
        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guardar producto
                String descripcion = textFieldNombre.getText();
                String origen = comboBoxOrigen.getSelectedItem().toString();

                ModelProducto producto = new ModelProducto();
                producto.setDescripcion(descripcion);
                producto.setOrigen(origen);

                try {
                    serviceProducto.agregarProducto(producto.getDescripcion(),producto.getOrigen());
                    JOptionPane.showMessageDialog(null, "Producto guardado exitosamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + ex.getMessage());
                }
            }
        });

        // Para Eliminar
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProducto = Integer.parseInt(textFieldID.getText());
                    ModelProducto producto = serviceProducto.obtenerProductoPorId(idProducto);
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        if (producto.getPrecio() == 0){
                            serviceProducto.eliminarProducto(idProducto);
                        JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
                        limpiarCampos();
                        }else{
                            JOptionPane.showMessageDialog(null, "No se puede eliminar productos cuya existencia sea mayor a cero");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código inválido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + ex.getMessage());
                }
            }
        });

        // Para Actualizar
        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idProducto = Integer.parseInt(textFieldID.getText());
                    String descripcion = textFieldNombre.getText();
                    String origen = comboBoxOrigen.getSelectedItem().toString();

                    ModelProducto producto = new ModelProducto();
                    producto.setId_producto(idProducto);
                    producto.setDescripcion(descripcion);
                    producto.setOrigen(origen);

                    serviceProducto.actualizarProducto(producto.getId_producto(),producto.getDescripcion(), producto.getOrigen());
                    JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código inválido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + ex.getMessage());
                }
            }
        });

        //Para generar un reporte en pdf
        buttonCrarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int reporteselect= comboBoxReportes.getSelectedIndex();
                    switch (reporteselect){
                     case 0:{
                         List<ModelProducto> prodigio = new ServiceProducto().obtenerTodosLosProductos();
                         new PdfReport().generateProductReport(prodigio,"C:\\Reportes en PDF\\reporte.pdf");

                         //Mostrar un mensaje de que se genero el reporte con el Jpanel
                         JOptionPane.showMessageDialog(null, "Reporte generado en C:\\Reportes en PDF");
                         break;
                     }
                     case 1:{
                         List<ModelProducto> prodigio = new ServiceProducto().obtenerGenericos("precio<100");
                         new PdfReport().generateProductReport(prodigio,"C:\\Reportes en PDF\\reporte.pdf");

                         //Mostrar un mensaje de que se genero el reporte con el Jpanel
                         JOptionPane.showMessageDialog(null, "Reporte generado en C:\\Reportes en PDF");
                         break;
                     }
                     case 2:{
                         List<ModelProducto> prodigio = new ServiceProducto().obtenerGenericos("existencia<30");
                         new PdfReport().generateProductReport(prodigio,"C:\\Reportes en PDF\\reporte.pdf");

                         //Mostrar un mensaje de que se genero el reporte con el Jpanel
                         JOptionPane.showMessageDialog(null, "Reporte generado en C:\\Reportes en PDF");
                         break;
                     }
                     case 3:{
                         List<ModelProducto> prodigio = new ServiceProducto().obtenerGenericos("precio >=200 AND precio<=400");
                         new PdfReport().generateProductReport(prodigio,"C:\\Reportes en PDF\\reporte.pdf");

                         //Mostrar un mensaje de que se genero el reporte con el Jpanel
                         JOptionPane.showMessageDialog(null, "Reporte generado en C:\\Reportes en PDF");
                         break;
                     }
                        case 4:{
                            // Ordenar los productos por precio de mayor a menor
                            List<ModelProducto> prodigio = new ServiceProducto().obtenerGenericos("1 ORDER BY precio DESC");
                            new PdfReport().generateProductReport(prodigio, "C:\\Reportes en PDF\\reporte.pdf");

                            // Mostrar un mensaje de que se generó el reporte con el JPanel
                            JOptionPane.showMessageDialog(null, "Reporte generado en C:\\Reportes en PDF");
                            break;
                        }
                        case 5:{
                            // Ordenar los productos por precio de menor a mayor
                            List<ModelProducto> prodigio = new ServiceProducto().obtenerGenericos("1 ORDER BY precio ASC");
                            new PdfReport().generateProductReport(prodigio, "C:\\Reportes en PDF\\reporte.pdf");

                            // Mostrar un mensaje de que se generó el reporte con el JPanel
                            JOptionPane.showMessageDialog(null, "Reporte generado en C:\\Reportes en PDF");
                            break;
                        }
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al crear el reporte en C:\\Reportes en PDF: " + ex.getMessage());
                }

            }
        });
    }

    private void limpiarCampos() {
        textFieldID.setText("");
        textFieldNombre.setText("");
        comboBoxOrigen.setSelectedIndex(0);
        textFieldPrecio.setText("");
        textFieldExistencia.setText("");
    }
}
