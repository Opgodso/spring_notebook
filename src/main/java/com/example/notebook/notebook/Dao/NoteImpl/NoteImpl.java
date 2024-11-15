package com.example.notebook.notebook.Dao.NoteImpl;

import com.example.notebook.notebook.Dao.NoteDao;
import com.example.notebook.notebook.Model.Note;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.List;

@Transactional
@Repository
public class NoteImpl implements NoteDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Note save(Note note){
        if(note.getId() == null){
            entityManager.persist(note); //新增
            return note;
        }else{
            return entityManager.merge(note); //合併
        }
    }

    @Override
    public void deleteById(int id){
        Note note = entityManager.find(Note.class,id);
        if(note != null){
            entityManager.remove(note);
        }
    }

    @Override
    public void deleteByTitle(String title){
        Note note = entityManager.find(Note.class,title);
        if(note != null){
            entityManager.remove(note);
        }
    }


    @Override
    public Optional<Note> findByTitle(String title){
        List<Note> result = entityManager
                .createQuery("SELECT n FROM Note n WHERE n.title = :title",Note.class)
                .setParameter("title",title)
                .getResultList();
        return result.isEmpty()? Optional.empty(): Optional.of(result.get(0));
    }

    @Override
    public Optional<Note> findById(Long id){
        Note note = entityManager.find(Note.class,id);
        return Optional.ofNullable(note);
    }

    @Override
    public List<Note> findAll(Long id){
        return entityManager.createQuery("SELECT n FROM Note n",Note.class)
                .getResultList();
    }

    @Override
    public List<Note>findByTitleContaining(String title){
        return entityManager
                .createQuery("SELECT n FROM Note n WHERE n.title LIKE :title", Note.class)
                .setParameter("title","%"+title+"%")
                .getResultList();
    }
}
