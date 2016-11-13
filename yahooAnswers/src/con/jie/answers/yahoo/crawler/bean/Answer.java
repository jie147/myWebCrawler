package con.jie.answers.yahoo.crawler.bean;

/**
 * Created by jie on 16-8-12.
 */
public class Answer {
    //question id
    private String questionId;
    //answer content
    private String content;
    //answer user name
    private String userName;
    //answer date
    private String date;
    //answer thumbs Up
    private int thumbsUp = 0;
    //answer thumbs down
    private int thumbsDown = 0;
    //is best answer
    private boolean isBest = false;
    //number of comments
    private int commentNum = 0;

    @Override
    public String toString() {
        return "Answer{" +
                "content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", thumbsUp=" + thumbsUp +
                ", thumbsDown=" + thumbsDown +
                ", isBest=" + isBest +
                ", commentNum=" + commentNum +
                '}';
    }

    public void PrintMes(){
        System.out.println("+++answer+++ : "+toString());
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(int thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public boolean isBest() {
        return isBest;
    }

    public void setBest(boolean best) {
        isBest = best;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
