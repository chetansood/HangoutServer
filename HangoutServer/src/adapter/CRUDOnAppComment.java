package adapter;

import java.util.ArrayList;

import com.example.supersaiyans.hangout.model.Comment;

public interface CRUDOnAppComment {
	public ArrayList<Comment> getComments(int EventID);
}
