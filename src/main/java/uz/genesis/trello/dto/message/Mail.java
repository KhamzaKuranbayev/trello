package uz.genesis.trello.dto.message;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    private List<Object> attachments;
    private Map<String, Object> model;

}
