package proyectofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insertar {
  public static void main(String[] args) {
    
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/consultorio";
    String usuario="root";
    String clave="Tomasidolo007."; 
    String consulta1 = "insert into pacientes(DniPac, NomPac, DomPac) values (43512210 ,'Pepe Pardel', 'Av. Los Quilmes')";
   // String consulta1 = "insert into pacientes(DniPac, NomPac, DomPac) values (123456789 ,'Juli De Maio', 'Av. Avellaneda')";
   // String consulta1 = "insert into pacientes(DniPac, NomPac, DomPac) values (42008784 ,'Tomas Gonzalez', 'Av. Salta')";
    //String consulta2 = "insert into medicos(MatMed, NomMed, EspMed) values (25648, 'Carlos Gutierrez', 'Odontologo')";
   // String consulta3 = "insert into historialmedico(NumHC, FecHC, DniPacHC, MatMedHC, DiagHC) values (2, '2015-04-04', 43512210 , 20832,'Fractura')";
   
        
    try {
    	Class.forName("com.mysql.jdbc.Driver");     
        conexion=DriverManager.getConnection(url,usuario,clave);    
        Statement sentencia=conexion.createStatement();
       sentencia.execute(consulta1);   
      //sentencia.execute(consulta2); 
      //sentencia.execute(consulta3); 
        System.out.println("Los nuevos datos se agregaron a la tabla");
    } catch (Exception e) {  
      e.printStackTrace();
      System.out.println("Error en la insercion de datos");
    }
  }
}