package com.example.notebook.notebook.Dao.NoteImpl;

import com.example.notebook.notebook.Dao.NoteDao;
import com.example.notebook.notebook.Model.Note;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class NoteImpl implements NoteDao {

    private static final Logger logger = LoggerFactory.getLogger(NoteImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Note save(Note note){
        try{
            if(note.getId() == null){
                entityManager.persist(note); //新增
                entityManager.flush();
                return note;
            }else{
                return entityManager.merge(note); //合併
            }
        }catch (Exception e){
            logger.error("Error occurred while saving note: {}", note, e);
            throw e;
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
    public void deleteByTitle(String title) {
        List<Note> notes = entityManager
                .createQuery("SELECT n FROM Note n WHERE n.title = :title", Note.class)
                .setParameter("title", title)
                .getResultList();
        for (Note note : notes) {
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
