package com.tts.DoneItt.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.DoneItt.Models.Task;


@Repository
public interface TaskRepository extends CrudRepository<Task,Long>{

}
