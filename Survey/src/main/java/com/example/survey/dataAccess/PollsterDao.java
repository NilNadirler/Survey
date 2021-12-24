package com.example.survey.dataAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.entities.Pollster;

@Repository
public interface PollsterDao extends JpaRepository<Pollster, Integer> {

}
