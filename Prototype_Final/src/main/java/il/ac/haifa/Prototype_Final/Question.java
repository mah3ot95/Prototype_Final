package il.ac.haifa.Prototype_Final;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "questions")
public class Question implements Serializable {

	 public Question(String question, String ans_a, String ans_b, String ans_c, String ans_d, String correct_ans) {
		super();
		this.question = question;
		this.ans_a = ans_a;
		this.ans_b = ans_b;
		this.ans_c = ans_c;
		this.ans_d = ans_d;
		this.correct_ans = correct_ans;
	}
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 String question;
	 String ans_a;
	 String ans_b;
	 String ans_c;
	 String ans_d;
	 String correct_ans;
	public String getQuestion() {
		return question;
	}
	public void  setQuestion(String question) {
		this.question = question;
	}
	public String getAns_a() {
		return ans_a;
	}
	public void setAns_a(String ans_a) {
		this.ans_a = ans_a;
	}
	public String getAns_b() {
		return ans_b;
	}
	public void setAns_b(String ans_b) {
		this.ans_b = ans_b;
	}
	public String getAns_c() {
		return ans_c;
	}
	public void setAns_c(String ans_c) {
		this.ans_c = ans_c;
	}
	public String getAns_d() {
		return ans_d;
	}
	public void setAns_d(String ans_d) {
		this.ans_d = ans_d;
	}
	public int getId() {
		return id;
	}
	public String getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}

}
