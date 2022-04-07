package com.login.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.jwt.entity.Formatter;



@Repository
public interface FormatterDao extends JpaRepository<Formatter, Long>{

}
