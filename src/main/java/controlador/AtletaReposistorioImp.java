/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Atleta;
import util.ConexionBaseDatos;

/**
 *
 * @author JDANIEL
 */
public class AtletaReposistorioImp implements Repositorio<Atleta> {

    /**
     * *
     * crea una coneccion a la base de datos llamando
     * basculaapp.util.ConexionBaseDatos
     *
     * @return una coneccion a la base de datos
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Atleta> listar() {

        List<Atleta> lista = new ArrayList<>();
        try (Connection conn = ConexionBaseDatos.getInstance(); Statement pst = conn.createStatement();
                ResultSet rs = pst.executeQuery("SELECT * from participantes");) {

            while (rs.next()) {

                lista.add(crearAtleta(rs));

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;

    }

    @Override
    public Atleta porId(Long id) {

        Atleta at = null;

        try (Connection con = ConexionBaseDatos.getInstance(); PreparedStatement pst = con.prepareStatement("select * from participantes where id = ?")) {

            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                   at = crearAtleta(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return at;
    }

    @Override
    public void guardar(Atleta at) {

        String sql;

        // se comprueba que el id no sea nulo y sea > 0
        if (at.getId()!= null && at.getId()> 0) {

            //si se cumple hacemos un update
            sql = "UPDATE participantes SET nombre = ?,numero= ?,tiempo = ? where id=?";
        } else {
            //si no se cumple hacemos un create
            sql = "INSERT INTO participantes(nombre,numero,tiempo) VALUES (?,?,?)";
        }
        //try catch con recursos -- obtiene la conexion y cuando no se ocupe la cerramos
        try (Connection conn = getConnection(); //creamos un objeto de tipo prepareStatement consulta preparada
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

            //pasamos los parametros que solicita la consulta preparada

            
            stmt.setString(1, at.getNombre());
            stmt.setInt(2, at.getNumeroAtleta());
            stmt.setTime(3, Time.valueOf(at.getTiempo()));

            //encaso de ser un update se pasa el id
            if (at.getId()!= null && at.getId()> 0) {
                stmt.setLong(4, at.getId());
            }
            //se ejecuta la consulta
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Guardado");
         

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void eliminar(Long id) {

        try (Connection con = ConexionBaseDatos.getInstance(); 
                PreparedStatement pst = con.prepareStatement("delete from participantes where id = ?")) {

            pst.setLong(1, id);
            pst.executeLargeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Atleta crearAtleta(ResultSet rs) throws SQLException {
        Atleta at = new Atleta();
        at.setId(rs.getLong("id"));
        at.setNombre(rs.getString("nombre"));
        at.setNumeroAtleta(rs.getInt("numero"));
        at.setTiempo( rs.getTime("tiempo").toLocalTime());
     

        return at;
    }

    @Override
    public Atleta buscarGanador() {

        
        Atleta at = null;
        try (Connection conn = ConexionBaseDatos.getInstance(); Statement pst = conn.createStatement();
                ResultSet rs = pst.executeQuery("SELECT * from participantes ORDER BY tiempo ASC LIMIT 1 ");) {

      
            while (rs.next()) {
                
               at = crearAtleta(rs);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return at;



    }

}
