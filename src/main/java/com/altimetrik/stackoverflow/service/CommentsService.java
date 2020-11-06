package com.altimetrik.stackoverflow.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.stackoverflow.entity.Comments;
import com.altimetrik.stackoverflow.entity.Posts;
import com.altimetrik.stackoverflow.entity.Users;
import com.altimetrik.stackoverflow.model.request.CommentsRequest;
import com.altimetrik.stackoverflow.model.response.CommentsResponse;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.repository.CommentsRepository;
import com.altimetrik.stackoverflow.repository.PostsRepository;
import com.altimetrik.stackoverflow.repository.UsersRepository;

@Service
public class CommentsService {
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Optional<CommentsResponse> getCommentById(Long commentId) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		CommentsResponse commentRes = new CommentsResponse();
		Optional<CommentsResponse> response = Optional.ofNullable(commentRes);
		Optional<Comments> comments = commentsRepository.findById(commentId);
		if(comments.isPresent()) {
			Comments comment = comments.get();
			commentRes.setId(comment.getId());
			commentRes.setUserDisplayName(comment.getUserDisplayName());
			commentRes.setPostId(comment.getPostsReference().getId());
			commentRes.setScore(comment.getScore());
			commentRes.setText(comment.getText());
			commentRes.setCreationDate(comment.getCreationDate().format(format));
		}
		return response;
	}
	
	public List<Comments> getAllComments(){
		return commentsRepository.findAll();
	}
	
	public Response createCommentDetails(CommentsRequest commentReq) {
		Response response = null;
		commentsRepository.save(getCommentsEntityFromRequest(commentReq));
		response = new Response();
		response.setMessage("Comment Added successfully");
		return response;
	}
	
	public Response updateCommentDetails(CommentsRequest commentReq) {
		Response response = null;
		Optional<Comments> postObj = commentsRepository.findById(commentReq.getId());
		if(postObj.isPresent()) {
			commentsRepository.save(getCommentsEntityFromRequest(commentReq));
			response = new Response();
			response.setMessage("Comment updated successfull!!");
		}
		return response;
	}
	
	public Response deleteCommentDetails(Long commentId) {
		Response response = null;
		Optional<Comments> commentObj = commentsRepository.findById(commentId);
		if(commentObj.isPresent()) {
			commentsRepository.delete(commentObj.get());
			response = new Response();
			response.setMessage("Comments deleted successfull!!");
		}
		return response;
	}
	
	private Comments getCommentsEntityFromRequest(CommentsRequest commentReq) {
		
		Comments comment = new Comments();
		Optional<Users> user = usersRepository.findById(commentReq.getUserId());
		Optional<Posts> post = postsRepository.findById(commentReq.getPostId());
		if(user.isPresent()) {
			comment.setUsersReference(user.get());
			comment.setUserDisplayName(user.get().getDisplayName());
			if(post.isPresent()) {
				comment.setPostsReference(post.get());
				comment.setText(commentReq.getText());
				comment.setScore(commentReq.getScore());
			}
		}
		
		return comment;
	}
}
