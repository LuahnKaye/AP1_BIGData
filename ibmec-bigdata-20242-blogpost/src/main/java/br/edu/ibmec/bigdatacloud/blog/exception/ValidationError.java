package br.edu.ibmec.bigdatacloud.blog.exception;

import lombok.Data;

@Data
public class ValidationError {
    private String field;
    private String message;
}
