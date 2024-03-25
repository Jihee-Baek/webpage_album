package com.prj.sbforsvc.Photo;

import java.time.LocalDateTime;

import com.prj.sbforsvc.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "phototbl")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_idx")
    private Long idx;

    @NotEmpty
    private String origin_file_name;

    @NotEmpty
    private String stored_file_path;

    private Long file_size;

    private LocalDateTime create_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;
}
