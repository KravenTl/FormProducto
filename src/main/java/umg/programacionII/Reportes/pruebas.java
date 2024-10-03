package umg.programacionII.Reportes;

import com.itextpdf.text.pdf.qrcode.Mode;
import umg.programacionII.DataBase.Model.ModelProducto;
import umg.programacionII.DataBase.Service.ServiceProducto;

import javax.swing.*;
import java.util.List;

public class pruebas {

    public static void main(String[] args) {

        try{
            //List<ProductoModel> prod = new ProductoService().obtenerTodosLosProductos();
            //Precio
            /*List<ModelProducto> prod = new ServiceProducto().obtenerGenericos("precio < 30");
            new PdfReport().generateProductReport(prod,"C:\\Reportes en PDF\\reporte.pdf");*/

            List<ModelProducto> prodigio = new ServiceProducto().obtenerGenericos("precio >=200 AND precio <=400 ");
            new PdfReport().generateProductReport(prodigio,"C:\\Reportes en PDF\\reporte.pdf");

            //Mostrar un mensaje de que se genero el reporte con el Jpanel
            JOptionPane.showMessageDialog(null, "Reporte generado en C:\\Reportes en PDF");
        }catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
    }



}
