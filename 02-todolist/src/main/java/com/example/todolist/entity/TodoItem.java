package com.example.todolist.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "todo_list")
public class TodoItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Null(message = "Id field must be null")
   private Long id;
   @NotBlank(message = "Title must be not null")
   private String title;
   @NotBlank(message = "Description must be not null")
   private String description;

   public TodoItem(){

   }
    public TodoItem(String title, String description) {
        this.title = title;
        this.description =description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                "description=" + description +
                '}';
    }
}
