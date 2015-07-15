/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piconcorrentedistribuido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class Banco {

    private Connection conexao() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/TrabalhoFinalPCDBD2", usuario = "root", senha = "admin";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return conn;
        }
    }

    public void InserirTipoExecucao(String tipoExecucao) throws SQLException {
        String sql = "INSERT INTO TIPO_EXECUCAO (DES_TIP_EXECUCAO) VALUES (?)";

        try (Connection conn = conexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tipoExecucao);
            ps.executeUpdate();
            ps.close();
        }
    }

    public void InserirExperimento(int codTipoExecucao, int numProcessoThread, double tempoExecucao) throws SQLException {
        String sql = "INSERT INTO EXPERIMENTO (COD_TIP_EXECUCAO, NUM_PROCESSOS_THREADS, VAL_TEMPO_EXECUCAO) VALUES (?, ?, ?)";

        try (Connection conn = conexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codTipoExecucao);
            ps.setInt(2, numProcessoThread);
            ps.setDouble(3, tempoExecucao);
            ps.executeUpdate();
            ps.close();
        }
    }
    
    public ArrayList<String> ListarTipoExecucao() throws SQLException {
        String sql = "SELECT DES_TIP_EXECUCAO FROM TIPO_EXECUCAO";
        ArrayList<String> tipos = new ArrayList<String>();
        Connection con = conexao();

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            tipos.add(rs.getString("DES_TIP_EXECUCAO"));
        }
        rs.close();
        ps.close();
        con.close();

        return tipos;
    }

}
