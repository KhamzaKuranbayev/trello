package uz.genesis.trello.criterias;

import lombok.*;

import java.io.Serializable;

/**
 * Created by 'javokhir' on 10/06/2019
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericCriteria implements Criterias, Serializable {

    protected Long selfId;

    protected Integer page;

    protected Integer perPage;

    protected String sortBy;

    protected String sortDirection;

    public String getSortDirection() {
        return sortDirection == null || sortDirection.equals("") ? "asc" : sortDirection;
    }

}
