package inu.thebite.toryaba.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_image")
@Getter
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_name")
    private String name;

    @Column(name = "image_url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_seq")
    private Category category;

    public static Image createImage(String url, String imageName, Category category) {
        Image image = new Image();
        image.url = url;
        image.name = imageName;
        image.category = category;
        return image;
    }
}
