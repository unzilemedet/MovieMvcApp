package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class ErrorMessage {
    int code;
    String message;

    //fields --> validasyonlardan dönen hataların yakalanarak gösterildiği alan
    List<String> fields;
}
