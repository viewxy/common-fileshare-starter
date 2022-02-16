package com.codecool.fileshare.repository;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component("jdbc")
public class ImageJdbcRepository implements ImageRepository {
    DatabaseConnectionManager dc = new DatabaseConnectionManager();

    /**
     * implement store image in database here
     * content is base64 coded image file
     * generate and return uuid of stored image
     * https://www.base64-image.de/
     * https://codebeautify.org/base64-to-image-converter
     */
    @Override
    public String storeImage(String category, String content) {
        DataSource ds = null;
        try {
            ds = dc.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ds.getConnection()) {
            String sql = "INSERT INTO image (category, content, extension) VALUES (?, ?, ?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, category);
            st.setBytes(2, content.getBytes());
            st.setString(3, getExtensionFromContent(content));
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getUUIDByCategoryAndContent(category, content, ds);
    }

    private String getUUIDByCategoryAndContent(String category, String content, DataSource ds) {
        try (Connection conn = ds.getConnection()) {
            String uuidSelect = "SELECT id FROM image WHERE category = ? AND content = cast(? AS bytea)";
            PreparedStatement st = conn.prepareStatement(uuidSelect);
            st.setString(1, category);
            st.setString(2, content);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getExtensionFromContent(String content) {
        int startIndex = content.indexOf('/') + 1;
        int endIndex = content.indexOf(';');
        return content.substring(startIndex, endIndex);
    }

    /**
     * @param uuid implement readImage from database here
     * @return base64 encoded image
     */
    @Override
    public String readImage(String uuid) {
        DataSource ds = null;
        try {
            ds = dc.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = ds.getConnection()) {
            String sql = "SELECT content FROM image WHERE id::text = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                byte[] bytes = rs.getBytes(1);
                return new String(bytes, StandardCharsets.UTF_8);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}