package con.jie.answers.yahoo.crawler.parser;

import con.jie.answers.yahoo.crawler.Util.DateUtil;
import con.jie.answers.yahoo.crawler.Util.MatchUtil;
import con.jie.answers.yahoo.crawler.bean.Answer;
import con.jie.answers.yahoo.crawler.bean.Question;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * parser the page of question and answer
 * will get a Question and a list of answer
 *
 */
public class Parser {
    //the question ID
    private String questionId;
    // the question message in this page
    private Question question;
    // the answers message in this page
    private List<Answer> answers;

    public Parser(){
        question = new Question();
        answers =new ArrayList<Answer>();
    }

    public boolean IsCanParse(Document document){
        Element eleMain = document.getElementById("ya-center-rail");
        if (eleMain == null || eleMain.toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * public fun
     * the main of the parer
     */
    public void parseDocument(Document document){
        Element eleMain = document.getElementById("ya-center-rail");
        if (eleMain == null || eleMain.toString().equals("")) {
            return ;
        }

        //get question id
        //<div data-ya-question-id="20160811174309AAN2bJB" id="ya-question-detail"
        questionId = eleMain.select("div[id=\"ya-question-detail\"][data-ya-question-id]").attr("data-ya-question-id");
        question.setQuestionId(questionId);
        //parse question message
        question = parseQuestion(eleMain);

        if (question.getAnswers() > 0) {
            // parse the best answer
            if (eleMain.getElementById("ya-best-answer") != null) {
                Element ele = eleMain.getElementById("ya-best-answer").select("[class=\"Mstart-75 Pos-r\"]").get(0);
                answers.add(paserOneAnswer(ele,true));
            }
            //parse the common answer
            Elements eleCommonAnswer = eleMain.getElementById("ya-qn-answers").select("[class=\"Cf Py-14 ya-other-answer Pend-14  Bdbx-f4 \"][data-ya-type=\"answer\"]");

            for (Element ele:eleCommonAnswer) {
                answers.add(paserOneAnswer(ele,false));
            }
        }
    }

    /**
     *  private fun
     *  Parse the question in this page
     *
     */
    private Question parseQuestion(Element eleQuestion){

        //title
        //<h1 itemprop="name" class="Fz-24 Fw-300 Mb-10"
        String title = eleQuestion.select("h1[itemprop=\"name\"]").text();
        question.setTitle(title);




        //description
        // <div class="Fz-13 Fw-n Mb-10"
        // <span itemprop="text" class="ya-q-text">
        //show more
        //<span class="D-n ya-q-full-text Ol-n" tabindex="-1">
        if (eleQuestion.select("span[class=\"D-n ya-q-full-text Ol-n\"]").toString().equals("")) {
            System.out.println("****** not show more ****");
            String description = eleQuestion.select("div[class=\"Fz-13 Fw-n Mb-10\"]").select("span.ya-q-text").text();
            question.setDescription(description);
        }else {
            String description = eleQuestion.select("div[class=\"Fz-13 Fw-n Mb-10\"]").select("span[class=\"D-n ya-q-full-text Ol-n\"]").text();
            question.setDescription(description);
        }

        //category
        //id="brdCrb"
        String category = "";
        Elements eleCategory = eleQuestion.getElementById("brdCrb").getElementsByTag("a");
        for (Element ele:eleCategory
             ) {
            category += ele.text()+",";
        }
        question.setCategory(category);

        //follower   int
        //<div class="qfollow Mend-10 Fz-13 Fw-n D-ib Cur-p" id="ya-follow-q"
        //<span class="follow-text" data-ya-fc="5"
        String followerStr = eleQuestion.select("div[id=\"ya-follow-q\"]").select("span[class=\"follow-text\"][data-ya-fc]").attr("data-ya-fc");
        question.setFollower(Integer.parseInt(followerStr));


        //answers    int
        //<div class="Mend-10 Fz-13 Fw-n D-ib"
        //<span itemprop="answerCount" class="D-n">17</span>
        String answers = eleQuestion.select("div[class=\"Mend-10 Fz-13 Fw-n D-ib\"]").select("span[itemprop=\"answerCount\"]").text();
        if (MatchUtil.IsMatch("\\d+",answers))
            question.setAnswers(Integer.parseInt(answers));

        return question;
    }

    /**
     * parse one answer
     */
    private Answer paserOneAnswer(Element eleOneAnswer,boolean isBest){
        Answer answer = new Answer();
        answer.setQuestionId(questionId);

        // content text:
        // <div class="answer-detail Fw-n">  or bestAnswer  <div class="Fw-n" id="yui_3_17_2_2_1471005197825_2051">
        //<span itemprop="text" class="ya-q-full-text">
        String content = "";
        content = eleOneAnswer.select("div[class=\"answer-detail Fw-n\"],div[class=\"Fw-n\"]").select("span[itemprop=\"text\"][class=\"ya-q-full-text\"]").text();
        answer.setContent(content);

        //userName
        //<div class="Pt-15" id="yui_3_17_2_3_1470992836240_2156">
        // <a href="/activity/questions?show=6MA3YRIU6G3CECTCK7MYUV6FCM&amp;t=g" class="uname Clr-b" data-ylk="slk:usract" id="yui_3_17_2_3_1470992836240_2155">Frank</a>
        String userName = eleOneAnswer.select("div[class=\"Pt-15\"]").select("a[class=\"uname Clr-b\"]").text();
        answer.setUserName(userName);
        //<span class="Clr-88"> · 3 days ago </span>
        String date = eleOneAnswer.select("div[class=\"Pt-15\"]").select("span[class=\"Clr-88\"]").text();
        String tmp[] = date.split(" ");
        int days=0;
        int num=Integer.parseInt(tmp[1]);
        switch (tmp[2]){
            case "days": days = num;break;
            case "weeks": days = num*7;break;
            case "months": days = num*30;break;
            case "years": days=num*365;break;
            default:days=0;
        }
        answer.setDate(DateUtil.DateMutil(days));

        //thumbsUp
        //<div itemprop="upvoteCount" class="D-ib Mstart-23 count"id="yui_3_17_2_3_1470992836240_2193">1</div>
        String thumbsUpStr = eleOneAnswer.select("div[itemprop=\"upvoteCount\"][class=\"D-ib Mstart-23 count\"]").text();
        answer.setThumbsUp(Integer.parseInt(thumbsUpStr));

        //thumbsDown
        //data-ya-type="thumbsDown"
        //<div class="D-ib Mstart-23 count" id="yui_3_17_2_3_1470992836240_2225">0</div>
        String thumbsDownStr = eleOneAnswer.select("div[data-ya-type=\"thumbsDown\"]").select("div[class=\"D-ib Mstart-23 count\"]").text();
        answer.setThumbsDown(Integer.parseInt(thumbsDownStr));

        //isBest
        answer.setBest(isBest);

        //commentNum

        return answer;
    }


    public Question getQuestion() {
        return question;
    }


    public List<Answer> getAnswers() {
        return answers;
    }

}
