package com.application.blog.repository;

import com.application.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/*@Repository - Not required as it is extending JpaRepository interface.
This interface is implemented by SimpleJpaRepository class.
This class has @Repository and @Transactional annotations*/

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comments where post_id = :postId", nativeQuery = true)
    List<Comment> getAllCommentsForPost(@Param("postId") String postId);

    @Query(value = "select * from comments where post_id = :postId and id = :commentId", nativeQuery = true)
    Comment getByCommentIdAndPostId(@Param("postId")String postId, @Param("commentId") String commentId);

    /*When custom queries are written using @Query annotation, spring will expect a resultset.
    If the return type is void, it will throw 'the statement did not return a result set' error.
    To avoid the above error message, we should be using @Transactional at interface level and @Modifying at method level as below.
    If @Transactional isn't provided, it will throw Transaction not found kind of exception again
     */
    @Modifying
    @Query(value = "delete from comments where post_id = :postId and id = :commentId", nativeQuery = true)
    void deleteCommentByPostIdAndCommentId(@Param("postId")String postId, @Param("commentId") String commentId);
}
