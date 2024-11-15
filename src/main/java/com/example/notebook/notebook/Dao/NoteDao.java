package com.example.notebook.notebook.Dao;

import com.example.notebook.notebook.Model.Note;
import java.util.Optional;
import java.util.List;

public interface NoteDao {
    Note save(Note note);
    void deleteById(int id);
    void deleteByTitle(String title);
    Optional<Note> findByTitle(String title);
    Optional<Note> findById(Long id);
    List<Note> findAll(Long id);
    List<Note> findByTitleContaining(String title);
}
