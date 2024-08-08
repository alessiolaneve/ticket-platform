package it.alessiolaneve.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.alessiolaneve.platform.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{

}
