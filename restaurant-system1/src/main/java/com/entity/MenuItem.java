package com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private Double price;
    
    private String category;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "image_width")
    private Integer imageWidth;
    
    @Column(name = "image_height")
    private Integer imageHeight;
    
    @Column(name = "file_size")
    private String fileSize;
    
    @Column(name = "dpi")
    private Integer dpi;
    
    @Column(name = "bit_depth")
    private Integer bitDepth;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}