package com.example.notebook.notebook.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//Entity 告訴spring 連接實體資料庫
@Entity
@Getter //不用寫getid 以及 setid 等
@Setter // 如果需要setter 的話 setter == getid 以及 setid
@Table(name = "notes")
public class Note {
    @Id  //用來告訴java spring 這是主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userid;
    private String username;
    private String title;
    private String content;


    @Column(name = "create_at", updatable  = false)
    private LocalDateTime createAt;

    @Column(name = "modifier_at", updatable = true)
    private LocalDateTime modifierAt;


    @PrePersist
    protected void onCreate(){
        this.createAt = LocalDateTime.now();
        this.modifierAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.modifierAt = LocalDateTime.now();
    }
}
