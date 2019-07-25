package uz.genesis.trello.domain.files;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "file_backgrounds")
public class Background extends Auditable {

    @Column(unique = true)
    private String name;
}
