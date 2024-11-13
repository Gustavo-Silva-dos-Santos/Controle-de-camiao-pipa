package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PipaIrregularDao {

    // Método para cadastrar uma pipa
    public void cadastrarPipa(PipaIrregular pipa) {
        String sql = "INSERT INTO pipas (nome_cliente, dia, mes, ano, tem_foto, situacao_agua, rua, numero, bairro) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pipa.getCliente());
            stmt.setInt(2, pipa.getDia());
            stmt.setInt(3, pipa.getMes());
            stmt.setInt(4, pipa.getAno());
            stmt.setInt(5, pipa.isTemFoto() ? 1 : 0);
            stmt.setString(6, pipa.getSituacaoAgua());
            stmt.setString(7, pipa.getRua());
            stmt.setInt(8, pipa.getNumero());
            stmt.setString(9, pipa.getBairro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar pipa no banco de dados.");
        }
    }

    // Método para listar todas as pipas cadastradas
    public List<PipaIrregular> listarPipas() {
        List<PipaIrregular> pipas = new ArrayList<>();
        String sql = "SELECT * FROM pipas";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PipaIrregular pipa = new PipaIrregular(
                    rs.getString("nome_cliente"),
                    rs.getInt("dia"),
                    rs.getInt("mes"),
                    rs.getInt("ano"),
                    rs.getBoolean("tem_foto"),
                    rs.getString("situacao_agua"),
                    rs.getString("rua"),
                    rs.getInt("numero"),
                    rs.getString("bairro")
                );
                pipas.add(pipa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar pipas no banco de dados.");
        }
        return pipas;
    }
}
