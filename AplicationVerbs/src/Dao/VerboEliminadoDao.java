/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.VerboBean;
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
public class VerboEliminadoDao {

    static String consultaSql;

    public static List<VerboBean> consultarVerbosEliminados() {

        List<VerboBean> listaVerbos = new ArrayList<>();

        try {

            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "SELECT  Espanol.id_espanol, Espanol.nombre ,Infinitivo.nombre ,PasadoSimple.nombre ,PasadoParticipio.nombre FROM Espanol\n"
                    + "INNER JOIN Infinitivo ON Espanol.id_espanol=Infinitivo.id_espanol\n"
                    + "INNER JOIN PasadoSimple ON Infinitivo.id_infinitivo=PasadoSimple.id_infinitivo\n"
                    + "INNER JOIN PasadoParticipio ON PasadoParticipio.id_pasado_simple=PasadoSimple.id_pasado_simple\n"
                    + "WHERE Espanol.estado = 0";

            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VerboBean verboBean = new VerboBean();
                verboBean.setIdEspanol(rs.getInt(1));
                verboBean.setNombreEspanol(rs.getString(2));
                verboBean.setNombreInfinitivo(rs.getString(3));
                verboBean.setNombrePasadoSimple(rs.getString(4));
                verboBean.setNombrePasadoParticipio(rs.getString(5));
                listaVerbos.add(verboBean);
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException ex) {
            System.out.println("Error en VerboEliminadoDao.consultarVerbosEliminados: " + ex);
        }
        return listaVerbos;
    }

    public static boolean vaciarPapeleria() {
        boolean eliminado = false;

        try {

            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "DELETE FROM PasadoParticipio WHERE estado=0";
            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ps.execute();
            eliminado = true;

            if (eliminado) {
                consultaSql = "DELETE FROM PasadoSimple WHERE estado=0";
                ps = conexion.prepareStatement(consultaSql);
                ps.execute();
                eliminado = true;

                if (eliminado) {
                    consultaSql = "DELETE FROM Infinitivo WHERE estado=0";
                    ps = conexion.prepareStatement(consultaSql);
                    ps.execute();
                    eliminado = true;

                    if (eliminado) {
                        consultaSql = "DELETE FROM Espanol WHERE estado=0";
                        ps = conexion.prepareStatement(consultaSql);
                        ps.execute();
                        eliminado = true;

                    }

                }
            }

            ps.close();
            conexion.close();
        } catch (SQLException ex) {

            System.out.println("Error ocurrido en VerboEliminadoDao.vaciarPapeleria:" + ex);
        }
        return eliminado;
    }

    public static boolean eliminarVerboPapeleria(int[] ids) {
        boolean eliminado = false;

        try {

            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "DELETE FROM PasadoParticipio WHERE id_pasado_simple=?";
            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ps.setInt(1, ids[2]);
            ps.execute();
            eliminado = true;

            if (eliminado) {
                consultaSql = "DELETE FROM PasadoSimple WHERE id_infinitivo=?";
                ps = conexion.prepareStatement(consultaSql);
                ps.setInt(1, ids[1]);
                ps.execute();
                eliminado = true;

                if (eliminado) {
                    consultaSql = "DELETE FROM Infinitivo WHERE id_espanol=?";
                    ps = conexion.prepareStatement(consultaSql);
                    ps.setInt(1, ids[0]);
                    ps.execute();
                    eliminado = true;

                    if (eliminado) {
                        consultaSql = "DELETE FROM Espanol WHERE id_espanol=?";
                        ps = conexion.prepareStatement(consultaSql);
                        ps.setInt(1, ids[0]);
                        ps.execute();
                        eliminado = true;

                    }

                }
            }

            ps.close();
            conexion.close();
        } catch (SQLException ex) {

            System.out.println("Error ocurrido en VerboEliminadoDao.vaciarPapeleria:" + ex);
        }
        return eliminado;
    }

    public static int[] consultarIds(int idEspanol) {
        int[] ids = new int[4];

        try {
            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "SELECT Espanol.id_espanol,Infinitivo.id_infinitivo ,PasadoSimple.id_pasado_simple,PasadoParticipio.id_pasado_participio FROM Espanol\n"
                    + "INNER JOIN Infinitivo ON Espanol.id_espanol=Infinitivo.id_espanol\n"
                    + "INNER JOIN PasadoSimple ON Infinitivo.id_infinitivo=PasadoSimple.id_infinitivo\n"
                    + "INNER JOIN PasadoParticipio ON PasadoParticipio.id_pasado_simple=PasadoSimple.id_pasado_simple\n"
                    + "WHERE Espanol.id_espanol=?";

            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ps.setInt(1, idEspanol);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ids[0] = rs.getInt(1);
                ids[1] = rs.getInt(2);
                ids[2] = rs.getInt(3);
                ids[3] = rs.getInt(4);
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException ex) {
            System.out.println("Error en VerboEliminadoDao.consultarIds:" + ex);
        }
        return ids;
    }
    
    public static boolean recuperarVerboPapeleria(int[] ids) {
        boolean recuperado = false;

        try {

            Connection conexion = ConexionSQL.getConnection();
            consultaSql = "UPDATE PasadoParticipio SET estado=0 WHERE id_pasado_simple=?";
            PreparedStatement ps = conexion.prepareStatement(consultaSql);
            ps.setInt(1, ids[2]);
            ps.execute();
            recuperado = true;

            if (recuperado) {
                consultaSql = "UPDATE PasadoSimple SET estado=1 WHERE id_infinitivo=?";
                ps = conexion.prepareStatement(consultaSql);
                ps.setInt(1, ids[1]);
                ps.execute();
                recuperado = true;

                if (recuperado) {
                    consultaSql = "UPDATE Infinitivo SET estado=1 WHERE id_espanol=?";
                    ps = conexion.prepareStatement(consultaSql);
                    ps.setInt(1, ids[0]);
                    ps.execute();
                    recuperado = true;

                    if (recuperado) {
                        consultaSql = "UPDATE Espanol SET estado=1 WHERE id_espanol=?";
                        ps = conexion.prepareStatement(consultaSql);
                        ps.setInt(1, ids[0]);
                        ps.execute();
                        recuperado = true;

                    }

                }
            }

            ps.close();
            conexion.close();
        } catch (SQLException ex) {

            System.out.println("Error ocurrido en VerboEliminadoDao.vaciarPapeleria:" + ex);
        }
        return recuperado;
    }
}
