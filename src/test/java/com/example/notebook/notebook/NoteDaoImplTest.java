package com.example.notebook.notebook;


import com.example.notebook.notebook.Dao.NoteDao;
import com.example.notebook.notebook.Model.Note;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = {"spring.profiles.active=test"})
//@Transactional
public class NoteDaoImplTest {

    @Autowired
    private NoteDao noteDao;

    @Test
    void testSave(){
        Note note = new Note();

        note.setId(1L);
        note.setUsername("test2");
        note.setTitle("Test Title2");
        note.setContent("This is test 測試2");

        Note savedNote = noteDao.save(note);

        assertNotNull(savedNote.getId());
        Optional<Note> retrievedNote = noteDao.findById(savedNote.getId());
        assertTrue(retrievedNote.isPresent());
        assertEquals("Test Title", retrievedNote.get().getTitle());
    }
}
