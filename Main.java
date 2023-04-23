package proyectofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    Connection conexion = null;
    Statement stm = null;
   
    public static void main(String[] args) {
      
        Scanner sc = new Scanner(System.in);
        Main m = new Main();

        m.conectar();    //CONECTO LA BD ANTES DE INICIAR EL MENÚ
        boolean salir = false;
        do {

            switch (menuPrin()) {
                case 1:
                    m.consultaTablaPacientes();    		 //CUANDO PULSE LA OPCIÓN 1 DEL MENÚ ME IMPRIMIRÁ LA TABLA DE LOS PACIENTES.
                    break;
                case 2 :
                    m.consultaTablaMedicos();            //CUANDO PULSE LA OPCIÓN 2 DEL MENÚ ME IMPRIMIRÁ LA TABLA DE LOS MÉDICOS.
                    break;
                case 3 :
                    m.consultaTablaHistorialMedico();    //CUANDO PULSE LA OPCIÓN 3 DEL MENÚ ME IMPRIMIRÁ LA TABLA DE LOS HISTORIALES MÉDICOS.
                    break;
                    
                case 0:
                    System.out.println("Vuelve pronto");
                    m.desconectar();                     //CUANDO PULSO EL 0 CIERRO LA BD Y CIERRO LA APLICACIÓN
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        } while (!salir);

    }

    // MENÚ PRINCIPAL:
    private static int menuPrin() {

        Scanner sc = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println("Conexión de bbdd MySQL");
        System.out.println("--------------------------------");
        System.out.println("1.MOSTRAR EL CONTENIDO DE LA TABLA PACIENTES");
        System.out.println("2.MOSTRAR EL CONTENIDO DE LA TABLA MEDICOS");
        System.out.println("3.MOSTRAR EL CONTENIDO DE LA TABLA HISTORIAL MEDICO");
        System.out.println("0. Salir");
        System.out.println("\n Por favor, escoja una opción.");
        System.out.println("--------------------------------");

        return sc.nextInt();

    }
   
    // CONEXIÓN CON BD
    public void conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/consultorio", "root", "Tomasidolo007.");

            System.out.println("**************************************");
            System.out.println(" * CONEXIÓN REALIZADA CORRECTAMENTE * ");
            System.out.println("**************************************");
           
        } catch (Exception e) {

            System.out.println("*****************************************");
            System.out.println(" * NO SE HA PODIDO REALIZAR LA CONEXIÓN * ");
            System.out.println("******************************************");
        }
    }
    
    // DESCONEXIÓN DE LA BD
    private void desconectar() {
        try {
        		conexion.close();
            	System.out.println("\n************************************************************\n");
            	System.out.println("La conexion a la base de datos se ha terminado");
            	System.out.println("\n************************************************************");
        	} catch (SQLException ex) {
        		System.out.println(ex.getMessage());
        	}
      }
     
     private void consultaTablaPacientes() {
        	ResultSet r = buscar("select DniPac,NomPac,DomPac from pacientes");
       
        	try {
        		System.out.println("\n TODOS LOS REGISTROS DE LA TABLA PACIENTES:\n");
        		while (r.next()) {
        			System.out.println(r.getInt("DniPac") + " | " + r.getString("NomPac") + " | " + r.getString("DomPac") + " | " );
        		}
        	} catch (SQLException ex) {
        		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        	}
     }
        
     private void consultaTablaMedicos() {
        	ResultSet r = buscar("select MatMed,NomMed,EspMed from medicos");
       
        	try {
        		System.out.println("\n TODOS LOS REGISTROS DE LA TABLA MEDICOS:\n");
        		while (r.next()) {
        			System.out.println(r.getInt("MatMed") + " | " + r.getString("NomMed") + " | " + r.getString("EspMed") + " | " );
        		}
        	} catch (SQLException ex) {
        		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        	}
     }
        
        private void consultaTablaHistorialMedico() {
        	ResultSet r = buscar("select NumHc,FecHc,DniPacHc,MatMedHc,DiagHc from historialmedico");
        	
        	try {
        		System.out.println("\n TODOS LOS REGISTROS DE LA TABLA HISTORIAL MEDICO:\n");
        		while (r.next()) {
        			System.out.println(r.getInt("NumHc") + " | " + r.getString("FecHc") + " | " + r.getInt("DniPacHc") + " | " + r.getInt("MatMedHc") + " | " + r.getString("DiagHc") + " | " );
        		}
        	} catch (SQLException ex) {
        		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        	}
        }
   
       
        //Este método lo uso para mostrar los datos de una tabla: (executeQuery)
        ResultSet buscar(String sql) {
        	try {
        		stm = conexion.createStatement();
        		return stm.executeQuery(sql);
        	} catch (SQLException ex) {
        		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        	}
        return null;
        }  
}