package connectionPool04;

public class Students {
    private long id;
    private String name;
    private String gender;
    private int score;
    private long class_id;


    public Students(long id, String name, String gender, int score, long class_id) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.score = score;
        this.class_id = class_id;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getScore() {
        return score;
    }

    public long getClass_id() {
        return class_id;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", score=" + score +
                ", class_id=" + class_id +
                '}';
    }
}
