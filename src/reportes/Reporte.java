/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Produccion
 */
public class Reporte {
   Connection ConexionReportes;
    public Reporte(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        ConexionReportes=DriverManager.getConnection("jdbc:mysql://localhost/fru","frucasa","frucasa09");
    }catch(ClassNotFoundException | SQLException e){
    }
    }
    // Metodo que muestra al reporte
    public void mostrarReporte(){
    try{
       
        JasperReport reporte=JasperCompileManager.compileReport("report1.jasper");
      
        //PARAMETROS
        Map parametros = new HashMap();
        parametros.put("LOTE", Long.parseLong("7565"));
        parametros.put("TAM", Long.parseLong("7565"));
        parametros.put("COL",Long.parseLong("7565"));
        
        JasperPrint p;
         
        p = JasperFillManager.fillReport(reporte, parametros,ConexionReportes);
        
        
        JasperViewer view=new JasperViewer(p,false);
        view.setTitle("Reporte de Clasificación");
        view.setExtendedState(Frame.MAXIMIZED_BOTH);
        view.setVisible(true);
    } catch (JRException | NumberFormatException e){
    }
    }  
}
