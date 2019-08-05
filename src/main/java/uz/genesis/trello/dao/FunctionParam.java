package uz.genesis.trello.dao;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FunctionParam {

    private Object param;
    private int paramType;

}
