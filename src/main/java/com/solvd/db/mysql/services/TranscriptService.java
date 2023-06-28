package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.TranscriptDAO;
import com.solvd.db.mysql.mapper.TranscriptMapper;
import com.solvd.db.mysql.model.Transcript;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TranscriptService implements GenericDAO<Transcript>, GetAllInterface<Transcript> {
    private final TranscriptMapper transcriptMapper;

    public TranscriptService(SqlSessionFactory sqlSessionFactory) {
        transcriptMapper = sqlSessionFactory.openSession().getMapper(TranscriptMapper.class);
    }

    @Override
    public List<Transcript> getAll() {
        return transcriptMapper.getAllTranscripts();
    }

    @Override
    public boolean create(Transcript transcript) {
        return transcriptMapper.createTranscript(transcript);
    }

    @Override
    public Transcript getById(long id) {
        return transcriptMapper.getTranscriptById(id);
    }

    public Transcript getTranscriptByStudentId(long id) {
        return transcriptMapper.getTranscriptByStudentId(id);
    }

    @Override
    public boolean update(Transcript transcript) {
        return transcriptMapper.updateTranscript(transcript);
    }

    @Override
    public boolean delete(long id) {
        return transcriptMapper.deleteTranscript(id);
    }
}
