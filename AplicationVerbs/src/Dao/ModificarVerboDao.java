/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.VerboBean;
import static Dao.ListaVerboDao.consultaSql;
import Utileria.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ModificarVerboDao {

    public static VerboBean consultarVerbos(int idVerboEspanol) {

        VerboBean verboBean = new VerboBean();

        try {

            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "SELECT Espanol.nombre ,Infinitivo.nombre ,PasadoSimple.nombre,PasadoParticipio.nombre FROM Espanol\n"
                    + "INNER JOIN Infinitivo ON Espanol.id_espanol=Infinitivo.id_espanol\n"
                    + "INNER JOIN PasadoSimple ON Infinitivo.id_infinitivo=PasadoSimple.id_infinitivo\n"
                    + "INNER JOIN PasadoParticipio ON PasadoParticipio.id_pasado_simple=PasadoSimple.id_pasado_simple\n"
                    + "WHERE Espanol.estado=1";

            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                verboBean.setNombreEspanol(rs.getString(1));
                verboBean.setNombreInfinitivo(rs.getString(2));
                verboBean.setNombrePasadoSimple(rs.getString(3));
                verboBean.setNombrePasadoParticipio(rs.getString(4));
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException ex) {
            System.out.println("Error en ModificarEliminadoDao.consultarVerbos: " + ex);
        }
        return verboBean;
    }

    public static boolean modificarVerbo(VerboBean verbo) {
        boolean actualizado = false;

        try {

            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "UPDATE Espanol SET nombre=? WHERE id_espanol=?";
            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ps.setString(1, verbo.getNombreEspanol());
            ps.setInt(2, verbo.getIdEspanol());
            ps.execute();
            actualizado = true;

            if (actualizado) {
                consultaSql = "UPDATE Infinitivo SET nombre=? WHERE id_infinitivo=?";
                ps = conexion.prepareStatement(consultaSql);
                ps.setString(1, verbo.getNombreInfinitivo());
                ps.setInt(2, verbo.getIdEspanol());
                ps.execute();
                actualizado = true;

                if (actualizado) {
                    consultaSql = "UPDATE PasadoSimple SET nombre=? WHERE id_pasado_simple=?";
                    ps = conexion.prepareStatement(consultaSql);
                    ps.setString(1, verbo.getNombrePasadoSimple());
                    ps.setInt(2, verbo.getIdEspanol());
                    ps.execute();
                    actualizado = true;

                    if (actualizado) {
                        consultaSql = "UPDATE PasadoParticipio SET nombre=? WHERE id_pasado_participio=?";
                        ps = conexion.prepareStatement(consultaSql);
                        ps.setString(1, verbo.getNombrePasadoParticipio());
                        ps.setInt(2, verbo.getIdEspanol());
                        ps.execute();
                        actualizado = true;

                    }

                }
            }

            ps.close();
            conexion.close();
        } catch (SQLException ex) {

            System.out.println("Error ocurrido en ModificarEliminadoDao.modificarVerbo:" + ex);
        }
        return actualizado;
    }
}
