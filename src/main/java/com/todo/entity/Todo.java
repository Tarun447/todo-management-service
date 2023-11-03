package com.todo.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "Todo Model")
@Entity
@Table(name = "todos")
public class Todo {

    @Schema(description = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Schema(description = "Title")
    @Column(nullable = false)
    private String title;

    @Schema(description = "Description")
    @Column(nullable = false)
    private String description;

    @Schema(description = "Completed")
    private boolean completed;

    public Todo() {
    }

    public Todo(int id, String title, String description, boolean completed) {
        Id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TODO{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
