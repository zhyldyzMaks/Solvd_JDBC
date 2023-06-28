package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Transcript;
import java.util.List;

public interface TranscriptMapper {

    Transcript getTranscriptById(long id);

    Transcript getTranscriptByStudentId(long studentId);

    List<Transcript> getAllTranscripts();

    boolean createTranscript(Transcript transcript);

    boolean updateTranscript(Transcript transcript);

    boolean deleteTranscript(long id);
}
