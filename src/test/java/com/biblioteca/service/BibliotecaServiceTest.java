package com.biblioteca.service;

import com.biblioteca.api.domain.Book;
import com.biblioteca.repository.BibliotecaRepository;
import com.biblioteca.service.impl.BibliotecaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BibliotecaServiceTest {

    BibliotecaService bibliotecaService;

    @MockBean
    BibliotecaRepository repository;

    @BeforeEach
    public void setUp(){
        this.bibliotecaService = new BibliotecaServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest(){
        Book book = Book.builder().author("teste").title("testando").isbn("125").build();

        Mockito.when(repository.save(book)).thenReturn(Book.builder().id(1l).isbn("125").title("testando").author("teste").build());

        Book bo = bibliotecaService.save(book);

        assertThat(bo.getId()).isNotNull();
        assertThat(bo.getIsbn()).isEqualTo("125");
        assertThat(bo.getAuthor()).isEqualTo("teste");
        assertThat(bo.getTitle()).isEqualTo("testando");
    }


}
