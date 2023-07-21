package uz.pdp.app.lc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    private String message;

    private Boolean success;

    private T data;

    public ResponseDTO(T data) {
        this.data = data;
    }

    public ResponseDTO(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public ResponseDTO(String message, Boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
}

