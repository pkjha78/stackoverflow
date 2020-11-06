package com.altimetrik.stackoverflow.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.stackoverflow.entity.Posts;
import com.altimetrik.stackoverflow.entity.Tags;
import com.altimetrik.stackoverflow.entity.Users;
import com.altimetrik.stackoverflow.model.request.PostRequest;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.repository.PostsRepository;
import com.altimetrik.stackoverflow.repository.TagsRepository;
import com.altimetrik.stackoverflow.repository.UsersRepository;


@Service
public class PostService {

	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	public Optional<Posts> getPostById(Long postId) {
		return postsRepository.findById(postId);
	}
	
	public List<Posts> getAllPosts(){
		return postsRepository.findAll();
	}
	
	public Response createPostDetails(PostRequest postReq) {
		Response response = null;
		postsRepository.save(getPostEntityFromRequest(postReq));
		response = new Response();
		response.setMessage("Post Added successfully");
		return response;
	}
	
	public Response updatePostDetails(PostRequest postReq) {
		Response response = null;
		Optional<Posts> postObj = postsRepository.findById(postReq.getId());
		if(postObj.isPresent()) {
			postsRepository.save(getPostEntityFromRequest(postReq));
			response = new Response();
			response.setMessage("Post updated successfull!!");
		}
		return response;
	}
	
	public Response deletePostDetails(Long postId) {
		Response response = null;
		Optional<Posts> postObj = postsRepository.findById(postId);
		if(postObj.isPresent()) {
			postsRepository.delete(postObj.get());
			response = new Response();
			response.setMessage("Post deleted successfull!!");
		}
		return response;
	}
	
	private Posts getPostEntityFromRequest(PostRequest req) {
		LocalDateTime now = LocalDateTime.now();
		Posts post = new Posts();
		Optional<Users> user = usersRepository.findById(req.getUserId());
		if(user.isPresent()) {
			post.setOwnerUserReference(user.get());
			post.setTitle(req.getTitle());
			post.setBody(req.getBody());
			post.setOwnerDisplayName(req.getOwnerDisplayName());
			post.setPostType(req.getPostType());
			post.setScore(req.getScore());
			post.setViewCount(req.getViewCount());
			post.setAnswerCount(req.getAnswerCount());
			post.setCommnetCount(req.getCommnetCount());
			post.setFavoriteCount(req.getFavoriteCount());
			post.setParentId(req.getParentId());
			post.setAcceptedAnswerId(req.getAcceptedAnswerId());
			post.setLastEditorUserId(req.getLastEditorUserId());
			post.setLastEditorDisplayName(req.getLastEditorDisplayName());
			post.setLastEditDate(now);
			post.setLastActivityDate(now);
			Set<Tags> tagsSet = new HashSet<>();
			for(Long tagId: req.getTagsId()) {
				Optional<Tags> tagObj = tagsRepository.findById(tagId);
				if(tagObj.isPresent()) {
					tagsSet.add(tagObj.get());
				}
			}
			post.setTags(tagsSet);
		}
		
		return post;
	}
}
