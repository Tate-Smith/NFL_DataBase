package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {
    @Id
    private int id;
    @Column()
    private String name;
    @Column()
    private Position position;
    @Column
    private Status status;
    @Column()
    private int number;
}