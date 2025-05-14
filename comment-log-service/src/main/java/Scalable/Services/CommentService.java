package Scalable.Services;

import Scalable.Models.Comment;
import Scalable.Repositories.CommentRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    MongoClient mongoClient;
    @Autowired
    public CommentService(CommentRepository commentRepository, MongoClient mongoClient) {
        this.commentRepository = commentRepository;
        this.mongoClient = mongoClient;
    }

//    public Comment save(Comment comment) {
//        return CommentRepository.save(comment);
//    }

    public Comment createComment(Comment comment) {
        comment.setCreatedAt(java.time.Instant.now());
        //comment.setTaggedUserIds(extractTaggedUserIds(comment.getContent()));
        return commentRepository.save(comment);
    }


    public Comment getCommentById(String id) {
        return commentRepository.findById(id).orElse(null);
    }


    public List<Comment> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskIdAndParentCommentIdIsNull(taskId);
    }


    public Comment updateComment(String id, Comment updatedComment) {
        Optional<Comment> existing = commentRepository.findById(id);
        if (existing.isPresent()) {
            Comment comment = existing.get();
            comment.setContent(updatedComment.getContent());
            //comment.setPinned(updatedComment.isPinned());
            return commentRepository.save(comment);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
    }

    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }

    public Comment pinComment(String id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setPinned(true);
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment unpinComment(String id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setPinned(false);
            return commentRepository.save(comment);
        }
        return null;
    }

//    private List<Long> extractTaggedUserIds(String content) {
//        Pattern pattern = Pattern.compile("@(\\w+)");
//        Matcher matcher = pattern.matcher(content);
//        List<Long> userIds = new ArrayList<>();
//
//        while (matcher.find()) {
//            String username = matcher.group(1);
//            // TODO: Look up user by username
//            User user = userRepository.findByUsername(username);
//            if (user != null) {
//                userIds.add(user.getId());
//            }
//        }
//        return userIds;
//    }

    public List<Comment> getThreadedComments(Long taskId) {
        List<Comment> topLevel = commentRepository.findByTaskIdAndParentCommentIdIsNull(taskId);
        for (Comment comment : topLevel) {
            comment.setReplies(loadReplies(comment.getId()));
        }
        return topLevel;
    }

    private List<Comment> loadReplies(String parentId) {
        List<Comment> replies = commentRepository.findByParentCommentId(parentId);
        for (Comment reply : replies) {
            reply.setReplies(loadReplies(reply.getId())); // recursively build thread
        }
        return replies;
    }


}

