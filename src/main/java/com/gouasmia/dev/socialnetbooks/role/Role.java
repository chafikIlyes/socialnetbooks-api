package com.gouasmia.dev.socialnetbooks.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gouasmia.dev.socialnetbooks.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(EntityListeners.class)
public class Role {

    @Id
    @GeneratedValue
    private  String id ;
    @Column(unique = true)
    private  String name ;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    @Column(nullable = false,updatable = false)
    private LocalDate createDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;

    public String getName() {
        return name;
    }

}
