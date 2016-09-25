/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.EspanolBean;
import Bean.InfinitivoBean;
import Bean.PasadoParticipioBean;
import Bean.PasadoSimpleBean;
import Utileria.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class RegistrarVerboDao {

    static String consultaSql;

    public static boolean registrarVerbo(EspanolBean espanol, InfinitivoBean infinitivo, PasadoSimpleBean pasadoSimple,
            PasadoParticipioBean pasadoParticipio) {
        boolean registrado = false;
        try {
            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "INSERT INTO Espanol(nombre) VALUES(?)";
            PreparedStatement ps = conexion.prepareStatement(consultaSql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, espanol.getNombreEspanol());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                espanol.setIdEspanol(rs.getInt(1));
                consultaSql = "INSERT INTO Infinitivo(nombre,id_espanol) VALUES (?,?)";
                ps = conexion.prepareStatement(consultaSql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, infinitivo.getNombreInfinitivo());
                ps.setInt(2, espanol.getIdEspanol());
                ps.execute();

                rs = ps.getGeneratedKeys();

                if (rs.next()) {

                    infinitivo.setIdInfinitivo(rs.getInt(1));
                    consultaSql = "INSERT INTO PasadoSimple(nombre,id_infinitivo)VALUES(?,?)";
                    ps = conexion.prepareStatement(consultaSql, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, pasadoSimple.getNombrePasadoSimple());
                    ps.setInt(2, infinitivo.getIdInfinitivo());
                    ps.execute();

                    rs = ps.getGeneratedKeys();

                    if (rs.next()) {

                        pasadoSimple.setIdPasadoSimple(rs.getInt(1));
                        consultaSql = "INSERT INTO PasadoParticipio(nombre,id_pasado_simple) VALUES(?,?)";
                        ps = conexion.prepareStatement(consultaSql);
                        ps.setString(1, pasadoParticipio.getNombrePasadoParticipio());
                        ps.setInt(2, pasadoSimple.getIdPasadoSimple());
                        ps.execute();

                        registrado = true;
                    }

                }
            }

            rs.close();
            ps.close();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("RegistrarVerboDao.registrarVerbo:" + ex);
        }
        return registrado;
    }

    public static boolean verificarVerbo(String verboEspanol) {
        boolean encontrado = false;
        try {
            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "SELECT ? WHERE ? IN (SELECT nombre FROM Espanol WHERE estado=1)";
            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ps.setString(1, verboEspanol);
            ps.setString(2, verboEspanol);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error ocurrido en RegistrarVerboDao.verificarVerbo");
        }
        return encontrado;
    }
    
    public static boolean buscarPapeleria(String verboEspanol) {
        boolean encontrado = false;
        try {
            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "SELECT ? WHERE ? IN (SELECT nombre FROM Espanol WHERE estado=0)";
            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ps.setString(1, verboEspanol);
            ps.setString(2, verboEspanol);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error ocurrido en RegistrarVerboDao.verificarVerbo");
        }
        return encontrado;
    }
    
    
}
