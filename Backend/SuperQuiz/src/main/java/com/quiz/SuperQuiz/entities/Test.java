package com.quiz.SuperQuiz.entities;

import com.quiz.SuperQuiz.dto.TestDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long time;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;

    public TestDTO getDto(){
        TestDTO testDto = new TestDTO();
        testDto.setId(id);
        testDto.setTitle(title);
        testDto.setDescription(description);
        testDto.setTime(time);

        return testDto;
    }


}
