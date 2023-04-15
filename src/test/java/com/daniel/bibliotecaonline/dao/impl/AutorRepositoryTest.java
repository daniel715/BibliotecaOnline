package com.daniel.bibliotecaonline.dao.impl;

import com.daniel.bibliotecaonline.dto.Autor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
@DataJdbcTest
class AutorRepositoryTest {

    private AutorRepository autorRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AutorRepositoryTest(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        autorRepository  = new AutorRepository(jdbcTemplate);
    }

    @Test
    public void listAutor(){
        Iterable<Autor> salida = this.autorRepository.findAll();
        assertNotNull(salida);
    }

    @Test
    public void canFindById(){
        Autor autor = new Autor("001", "Daniel");
        Optional<Autor> response = autorRepository.save(Optional.of(autor));

        Optional<Autor> salida = autorRepository.findById("001");

        assertNotNull(salida.get().getId());
        //verify(jdbcTemplate).query("select id, nombre from Autor where id=?", this::mapRowToAutor,"001");
    }

    private Autor mapRowToAutor(ResultSet row, int rowNum)
            throws SQLException {
        return new Autor(
                row.getString("id"),
                row.getString("nombre")
        );
    }

    @Test
    public void canSave() {
        Autor autor = new Autor("001", "Daniel");
        Optional<Autor> response = autorRepository.save(Optional.of(autor));

        assertNotNull(response.get().getId());
//        verify(jdbcTemplate).update("insert into autor (id, nombre) values (?,?)",
//                "001",
//                "Daniel");
    }

    @Test
    @Disabled
    public void testRespondeCodefindAll() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet("http://localhost:8080/autor/list");

        // When
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    @Disabled
    public void testResponseMediaType() throws IOException {
        // Given
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet("http://localhost:8080/autor/list");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }

}