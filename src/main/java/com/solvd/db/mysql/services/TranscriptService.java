package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.TranscriptDAO;
import com.solvd.db.mysql.model.Transcript;
import com.solvd.db.utils.GenericDAO;
import java.util.List;

public class TranscriptService implements GenericDAO<Transcript>, GetAllInterface<Transcript> {
    private TranscriptDAO transcriptDAO;

    public TranscriptService(){
        transcriptDAO = new TranscriptDAO();
    }

    @Override
    public List<Transcript> getAll() {
        return transcriptDAO.getAll();
    }

    @Override
    public boolean create(Transcript transcript) {
        return transcriptDAO.create(transcript);
    }

    @Override
    public Transcript getById(long id) {
        return transcriptDAO.getById(id);
    }

    @Override
    public boolean update(Transcript transcript) {
        return transcriptDAO.update(transcript);
    }

    @Override
    public boolean delete(long id) {
        return transcriptDAO.delete(id);
    }
}
