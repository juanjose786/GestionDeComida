package gestion.de.comida;

import gestion.de.comida.BD.MysqlBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juan_
 */
public class Platoprincipal {

    private final String tabla_Ordenes = "ordenes";
    private String ComidaPrincipal;
    private String ComidaSecundaria;
    
    public Platoprincipal() {
        
    }
    
    public String getComidaPrincipal() {
        return ComidaPrincipal;
    }

    public void setComidaPrincipal(String ComidaPrincipal) {
        this.ComidaPrincipal = ComidaPrincipal;
    }

    public String getComidaSecundaria() {
        return ComidaSecundaria;
    }

    public void setComidaSecundaria(String ComidaSecundaria) {
        this.ComidaSecundaria = ComidaSecundaria;
    }
    
    public void SubirOrdenPlato(String PlatoPrincipal, String PlatoSecundario){
        MysqlBD bd = new MysqlBD();
        Connection con = bd.conectar();
        try{
            PreparedStatement consulta;
            
            consulta = con.prepareStatement("INSERT INTO " + this.tabla_Ordenes + "(PlatoPrincipal, PlatoSecundario) VALUES(?, ?)");
            consulta.setString(1, PlatoPrincipal);
            consulta.setString(2, PlatoSecundario);
            consulta.executeUpdate();
            
            System.out.println("Se guardaron los datos correctamente. ");
            
        } 
        catch (SQLException ex){
            System.out.println("Hubo un error al momento de guardar los datos: " + ex.getMessage());
        }
    }
    
    public boolean VerificarOrden(String Platoprincipal, String PlatoSecundario) {
        MysqlBD bd = new MysqlBD();
        Connection con = bd.conectar();
        try {
            PreparedStatement consulta = con.prepareStatement("SELECT PlatoSecundario FROM " + this.tabla_Ordenes + " WHERE PlatoPrincipal = ?");
            consulta.setString(1, Platoprincipal);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                String PlatoSobtenido = resultado.getString("PlatoSecundario");
                return PlatoSecundario.equals(PlatoSobtenido);
            }
        } catch (SQLException ex) {
            // Manejo de excepciones opcionalmente
            System.out.println("Error en la verificación del login: " + ex.getMessage());
        }
        return false;
    }
}
