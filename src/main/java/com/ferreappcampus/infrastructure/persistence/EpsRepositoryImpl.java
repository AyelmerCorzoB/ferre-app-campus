package com.ferreappcampus.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ferreappcampus.domain.entities.Eps;
import com.ferreappcampus.domain.repositories.EpsRepository;
import com.ferreappcampus.infrastructure.database.ConnectionDb;

public class EpsRepositoryImpl implements EpsRepository{

    private final ConnectionDb connectionDb;

    public EpsRepositoryImpl(ConnectionDb connectionDb) {
        this.connectionDb = connectionDb;
    }

    @Override
    public void save(Eps Eps) {
        String sql = "INSERT INTO eps ( name) VALUES (?, ?, ?)";
        try (Connection conn = connectionDb.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(2, Eps.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Eps findById(int id) {
        String sql = "SELECT * FROM eps WHERE id = ?";
        try (Connection conn = connectionDb.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("----------------------------------");
                System.out.println("|"+"ID: "+rs.getInt("id"));
                System.out.println("|"+"Nombre: "+rs.getString("name"));
                System.out.println("|"+"Email: "+rs.getString("email"));
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Eps> findAl() {
        List<Eps> Eps = new ArrayList<>();
        String sql = "SELECT id,name FROM eps";
        try (Connection conn = connectionDb.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Eps.add(new Eps(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Eps eps : Eps) {
            System.out.println("|-------------------------------------------------------------------------|");
            System.out.println(
                    "|ID: " + eps.getId() + " Name: " + eps.getName()  + " |");
            System.out.println("|-------------------------------------------------------------------------|");
        }
        return Eps;
    }

    @Override
    public void update(Eps eps) {
        String sql = "UPDATE eps SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = connectionDb.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eps.getName());
            stmt.setInt(3, eps.getId());
            stmt.executeUpdate();
            System.out.println("eps Actualizada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM eps WHERE id = ?";
        try (Connection conn = connectionDb.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Eliminacion exitosa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
