package com.example.demo.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//this is a Repository because it is responsible for data access
public interface StudentRepository extends JpaRepository<Student,Long> {



    //one method
    //select * from student  where email=""
//    @Query("SELECT s FROM Student s WHERE s.email=?1")
    //Is a custom query method that Spring automatically implements based on the method name,
    // using the "Spring Data JPA method naming conventions".
    Optional<Student> findStudentByEmail(String email);

}
