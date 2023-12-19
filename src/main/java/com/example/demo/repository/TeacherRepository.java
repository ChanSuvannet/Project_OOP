package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Teacher;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;



@Repository
public interface TeacherRepository extends JpaRepository <Teacher, Long>{
    
    List<Teacher> findByGender(String string);
    Optional<Teacher> findById(String id);
    
}
