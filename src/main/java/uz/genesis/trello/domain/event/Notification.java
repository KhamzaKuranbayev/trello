package uz.genesis.trello.domain.event;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.utils.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event_notifications")
public class Notification extends Auditable {

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "initiator_id")
    private Long initiatorId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_type", nullable = false, referencedColumnName = "id")
    private Type activityType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_type", nullable = false, referencedColumnName = "id")
    private Type objectType;

    @Column(name = "object_url")
    private String objectUrl;

    @Column(name = "content")
    private String content;

    @Column(name = "seen_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime seenAt;




}
