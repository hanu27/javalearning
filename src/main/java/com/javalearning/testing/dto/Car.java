package com.javalearning.testing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Document(collection = "cars")
public class Car {
    @Id
    private String rowId;
    private Long id;
    @NotEmpty
    @Schema(name = "Model name is mandatory")
    private String modelName;
    private Long length;
    private Long width;
    private String companyName;
}
