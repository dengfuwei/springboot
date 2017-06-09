package com.itopener.framework.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class BaseDao  {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int delete(String sqlid) {
		return sqlSession.delete(sqlid);
	}

	public int delete(String sqlid, Object object) {
		return sqlSession.delete(sqlid, object);
	}
	
	public int insert(String sqlid) {
		return sqlSession.insert(sqlid);
	}
	
	public int insert(String sqlid, Object object) {
		return sqlSession.insert(sqlid, object);
	}
	
	public void select(String sqlid, ResultHandler<?> resultHandler) {
		sqlSession.select(sqlid, resultHandler);
	}

	public void select(String sqlid, Object object, ResultHandler<?> resultHandler) {
		sqlSession.select(sqlid, object, resultHandler);
	}

	public void select(String sqlid, Object object, RowBounds rowBounds, ResultHandler<?> resultHandler) {
		sqlSession.select(sqlid, object, rowBounds, resultHandler);
	}
	
	public <E> List<E> selectList(String sqlid) {
		return sqlSession.selectList(sqlid);
	}
	
	public <E> List<E> selectList(String sqlid, Object object) {
		return sqlSession.selectList(sqlid, object);
	}

	public <E> List<E> selectList(String sqlid, Object object, RowBounds arg3) {
		return sqlSession.selectList(sqlid, object, arg3);
	}
	
	public <E> List<E> selectPage(String sqlid, BaseCondition condition, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize, false);
		return sqlSession.selectList(sqlid, condition);
	}
	
	public <K, V> Map<K, V> selectMap(String sqlid, String key) {
		return sqlSession.selectMap(sqlid, key);
	}

	public <K, V> Map<K, V> selectMap(String sqlid, Object object, String key) {
		return sqlSession.selectMap(sqlid, object, key);
	}

	public <K, V> Map<K, V> selectMap(String sqlid, Object object, String key, RowBounds rowBounds) {
		return sqlSession.selectMap(sqlid, object, key, rowBounds);
	}
	
	public <T> T selectOne(String sqlid) {
		return sqlSession.selectOne(sqlid);
	}

	public <T> T selectOne(String sqlid, Object object) {
		return sqlSession.selectOne(sqlid, object);
	}
	
	public <T> T selectOnePage(String sqlid, BaseCondition condition) {
		List<T> list = this.selectPage(sqlid, condition, 1, 1);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	
	public int update(String sqlid) {
		return sqlSession.update(sqlid);
	}
	
	public int update(String sqlid, Object object) {
		return sqlSession.update(sqlid, object);
	}
}

