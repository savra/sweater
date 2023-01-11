package com.example.sweater;


import com.example.sweater.domain.*;
import com.example.sweater.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Tuple;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SomeTest {
    //  private static final Logger LOGGER = LoggerFactory.getLogger(SomeTest.class);
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    EntityManager entityManager;
    @Autowired
    PostDetailsRepository postDetailsRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TableWithClobRepository tableWithClobRepository;

    private enum CalcPeriod {
        M,
        Q
    }

    private static final int ITERATIONS = 20;

    @Test
    @Transactional
            //  @Rollback(value = false)
    public void getPostByIdTest() {

        /*PostDetails p = postDetailsRepository.getOne(1L);
        Post p2 = postRepository.findPostById(1L);
        p2.getPostDetails();
        p.getPost();
        checkQueryCount(1);*/

      /*  User user = new User();
        user.setActive(true);
        user.setUsername("");
        userRepo.saveAndFlush(user);*/
       // Arrays.stream(Post.ImgFormat.values()).forEach(System.out::println);
        //Post p = new Post();
       // postRepository.saveAndFlush(p);
        Post p2 = postRepository.findPostById(1L);

        //Optional<TableWithClob> p2 = tableWithClobRepository.findById(1L);
    }


    @Test
    @Transactional(readOnly = true)
    public void test3() {
        long startTime;
        List<Long> interfaceProjections = new ArrayList<>();
        List<Long> tupleProjections = new ArrayList<>();

        for (int i = 0; i < ITERATIONS; i++)  {
            startTime = System.nanoTime();
            List<TestHugeSelect> hugeSelect = postRepository.getHugeSelect();
            interfaceProjections.add(System.nanoTime() - startTime);

            startTime = System.nanoTime();
            List<Tuple> projections = postRepository.findAllNameOnlyTupleProjection();
            tupleProjections.add(System.nanoTime() - startTime);
        }

        log.info("Interface projections took " +
                interfaceProjections.stream().mapToDouble(Long::doubleValue).average().orElseThrow(IllegalArgumentException::new) +
                "second on average out of " + ITERATIONS + " iterations");

        log.info("Tuple projections took " +
                tupleProjections.stream().mapToDouble(Long::doubleValue).average().orElseThrow(IllegalArgumentException::new) +
                "second on average out of " + ITERATIONS + " iterations");
    }

    @Transactional
    public void test4() {
        Post p = postRepository.findPostById(1L);
    }

    @Test
    public void test5() {
        for(int i = 1000000; i < 1000221; i++) {
            System.out.print(i);
            fff(i);
            System.out.println();
        }
    }


    private void fff(int num) {
        if (num % 100 > 10 && num % 100 < 15) {
            System.out.print(" пользователей согласны");
        } else if (num % 10 == 1) { //21 31 41 51 101 121 131
            System.out.print(" пользователь согласен");
        } else if (num % 10 == 2 || num % 10 == 3 || num % 10 == 4) {//22 23 24 32 33 34 102 103 104
            System.out.print(" пользователя согласны");
        } else {//0 25 26 27 28 29 30 35 36 37 38 39 40
            System.out.print(" пользователей согласны");
        }
    }

  /*  @Test
    public void test() {


        if ((a & (1 << 2)) != 0) {

        }
     pdf   setVisible ((a & (1 << 0)))
     xlsx   setVisible ((a & (1 << 1)) != 0)
     html  setVisible ((a & (1 << 2)) != 0)

        setVisible ((a & 1) != 0)
        setVisible ((a & 2) != 0)
        setVisible ((a & 4) != 0)



        System.out.println();




        boolean isShowMySubordinates = true;
        CalcPeriod calcPeriod = CalcPeriod.M;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> rootPost = criteriaQuery.from(Post.class);
        Root<PostDetails> rootPostDetails = criteriaQuery.from(PostDetails.class);
        //criteriaQuery.select(rootPost);

        //EntityGraph<Post> entityGraph = entityManager.createEntityGraph(Post.class);
        //entityGraph.addSubgraph("postDetails");

        Join<Post, PostDetails> join;

        if (isShowMySubordinates) {
            if (calcPeriod.equals(CalcPeriod.M)) {
                join = rootPost.join("postDetails", JoinType.LEFT);
                join.on(criteriaBuilder.equal(rootPost.get("id"), rootPostDetails.get("post")));
            }
        }



        List<Post> result = entityManager.createQuery(criteriaQuery).getResultList();
    }*/

    private void checkQueryCount(long expected) {
        long actual = entityManagerFactory.unwrap(SessionFactory.class).getStatistics().getPrepareStatementCount();

        System.out.println(String.format("Query actual count: %s. expected count: %s", actual, expected));
    }

    @Test
    public void test2() {

    }
}
