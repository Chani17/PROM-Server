package inu.thebite.toryaba.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_image")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "category_seq")
    private Category category;

    public static Image createImage(String url, Category category) {
        Image image = new Image();
        image.url = url;
        image.category = category;
        return image;
    }
}
