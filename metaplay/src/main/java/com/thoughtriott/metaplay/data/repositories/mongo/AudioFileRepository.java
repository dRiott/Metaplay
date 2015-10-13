package com.thoughtriott.metaplay.data.repositories.mongo;

import com.thoughtriott.metaplay.data.documents.AudioFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AudioFileRepository extends MongoRepository <AudioFile, String> {

	//by extending MongoRepository, AudioFileRepository inherits:
	//count, delete, deleteAll, exists, findAll, findOne, save...
}
