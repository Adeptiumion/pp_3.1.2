package pp_312.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "У вас не может быть пустого имени")
    @Pattern(regexp = "[a-zA-Z а-яА-Я]+", message = "Имя должно содержать только буквы!")
    @Size(min = 2, max = 10, message = "Число символов должно быть от 2 до 10!" )
    private String name;

    public User() {

    }


    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nid is --- " + getId()
                + "\nname is --- " + getName() + "\n";
    }
}
