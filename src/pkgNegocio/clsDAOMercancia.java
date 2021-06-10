/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgNegocio;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pkgDatos.clsConecta;

/**
 *
 * @author Payan Duque
 */
public class clsDAOMercancia extends clsMercancia {
    
    pkgDatos.clsConecta con;

    public clsDAOMercancia() {
        con = new clsConecta();
    }
    
    public boolean insertar() {
        String SQL = "INSERT INTO administrador.tbl_mercancia(id, nombre, cantidad) VALUES ('" + super.getId() + "','" + super.getNombre() + "','" + super.getCantidad() + "')";
        return con.procesarSQL(SQL);
    }
    
    public void borrar() {
        String SQL = "DELETE FROM administrador.tbl_mercancia WHERE id='" + super.getId() + "'";
        con.procesarSQL(SQL);
    }

    public boolean modificar(){
        String SQL = "UPDATE administrador.tbl_mercancia SET cantidad='" + super.getNombre() + "' WHERE id=  cantidad='" + super.getId() + super.getCantidad() + "'";
        return con.procesarSQL(SQL);
    }

    public String consultar() {
        String SQL = "SELECT id, nombre, cantidad FROM administrador.tbl_mercancia WHERE id='" + super.getId() + "'";
        java.sql.ResultSet hr = null;
        hr = con.consultarSQL(SQL);
        try {
            if (hr.next()) {
                return hr.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(clsDAOPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "no";
    }

    public javax.swing.table.DefaultTableModel listar(JTable tbl) {
        javax.swing.table.DefaultTableModel modelo;
        modelo = new DefaultTableModel(new Object[]{tbl.getColumnName(0), tbl.getColumnName(1), tbl.getColumnName(2)}, 0);

        String SQL = "SELECT id, nombre, cantidad FROM administrador.tbl_mercancia";
        java.sql.ResultSet res = null;

        res = con.consultarSQL(SQL);

        try {
            while (res.next()) {
                modelo.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3)});
            }
        } catch (SQLException ex) {
            System.out.println("Error consultando para listar");
        }
        return modelo;
    }
    
    public javax.swing.DefaultComboBoxModel listarCombo() {
        javax.swing.DefaultComboBoxModel modelo;
        modelo = new javax.swing.DefaultComboBoxModel();

        String SQL = "SELECT id, nombre, cantidad FROM administrador.tbl_mercancia";
        java.sql.ResultSet res = null;

        res = con.consultarSQL(SQL);

        try {
            while (res.next()) {
                modelo.addElement(res.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("Error consultando para listar en combo");
        }
        return modelo;
    }
    
    
}
