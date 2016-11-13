package con.jie.answers.yahoo.crawler.bean;

/**
 * Created by jie on 16-8-12.
 */
public class Question {
    //question Id
    private String questionId;
    //title
    private String title;
    //question description
    private String description;
    //question category
    private String category;
    //question follower persons
    private int follower;
    //question the number of answers
    private int answers;


    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", follower=" + follower +
                ", answers=" + answers +
                '}';
    }


    public void PrintMes(){
        System.out.println("---question--- : "+toString());
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }
}
