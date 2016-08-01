package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;


/**
 * Calls id generation for newly added books to database. 
 * Runs before save method of BookDao implementation.
 * 
 * @author Pawel Patronik
 *
 */
public class BookDaoIdAdvisor implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {
		((BookTo) objects[0]).setId(((BookDao) o).getNextId());
	}
}
