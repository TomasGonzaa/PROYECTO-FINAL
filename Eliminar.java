package proyectofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Eliminar {
  public static void main(String[] args) {
    
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/consultorio";
    String usuario="root";
    String clave="Tomasidolo007.";
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Escriba el DNI del paciente a eliminar:...");
    int DniPac  = sc.nextInt();
    
    String consulta= "DELETE FROM pacientes WHERE DniPac = '"+DniPac+"'";   
    try {
    	Class.forName("com.mysql.jdbc.Driver");     
        conexion=DriverManager.getConnection(url,usuario,clave);    
        Statement sentencia=conexion.createStatement();
        sentencia.execute(consulta);   
        System.out.println("El registro se elimino");
    } catch (Exception e) {  
      e.printStackTrace();
      System.out.println("Error en el borrado del registro");
    }
  }
}