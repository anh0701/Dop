package org.dop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.dop.entity.embeded.EmailEmbedded;
import org.dop.entity.state.UserPrimaryStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserPrimary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9-]{4,50}$")
    @Column(unique = true, length = 50)
    private String username;

    private String password;

    @Embedded
    private EmailEmbedded email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserPrimaryStatus status;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;

    /**
     * Relationship
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_primary_role",
            joinColumns = @JoinColumn(name = "user_primary_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = Image.Fields.owner, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Image> images;
}
