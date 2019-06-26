package uz.genesis.trello.criterias;

import lombok.*;

/**
 * Created by 'javokhir' on 10/06/2019
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericCriteria implements Criterias {

    protected Long selfId;

    protected Integer page;

    protected Integer perPage;

    protected String sortBy;

    protected String sortDirection;

    public String getSortDirection() {
        return sortDirection == null || sortDirection.equals("") ? "asc" : sortDirection;
    }

}
