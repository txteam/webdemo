/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年12月13日
 * <修改描述:>
 */
package com.tx.security.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.tx.security.model.OperatorRememberMeToken;

/**
 * Operator的记住我功能<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年12月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorRememberMeTokenRepository extends JdbcDaoSupport {
    
    /** The default SQL used by the <tt>getTokenBySeries</tt> query */
    public static final String DEF_TOKEN_BY_SERIES_SQL = "select id,username,token,ipAddress,lastUseDate from oper_remember_me_token where id = ?";
    
    /** The default SQL used by <tt>createNewToken</tt> */
    public static final String DEF_INSERT_TOKEN_SQL = "insert into oper_remember_me_token (id,username,token,ipAddress,lastUseDate) values(?,?,?,?,?)";
    
    /** The default SQL used by <tt>updateToken</tt> */
    public static final String DEF_UPDATE_TOKEN_SQL = "update oper_remember_me_token set token = ?,ipAddress=?, lastUseDate = ? where id = ?";
    
    /** The default SQL used by <tt>removeUserTokens</tt> */
    public static final String DEF_REMOVE_USER_TOKENS_SQL = "delete from oper_remember_me_token where username = ?";
    
    /** The default SQL used by <tt>removeUserTokens</tt> */
    public static final String DEF_DELETE_EXPIRED_TOKENS_SQL = "delete from oper_remember_me_token where lastUseDate < ?";
    
    // ~ Instance fields
    // ================================================================================================
    
    private String tokensBySeriesSql = DEF_TOKEN_BY_SERIES_SQL;
    
    private String insertTokenSql = DEF_INSERT_TOKEN_SQL;
    
    private String updateTokenSql = DEF_UPDATE_TOKEN_SQL;
    
    private String removeUserTokensSql = DEF_REMOVE_USER_TOKENS_SQL;
    
    private String removeExpiredTokensSql = DEF_DELETE_EXPIRED_TOKENS_SQL;
    
    private int validitySeconds = 1000 * 60 * 60 * 24 * 7;
    
    public OperatorRememberMeTokenRepository() {
        super();
    }
    
    public OperatorRememberMeTokenRepository(int validitySeconds) {
        super();
        this.validitySeconds = validitySeconds;
    }
    
    @Override
    protected void initDao() {
        Timer timer = new Timer(false);
        DateTime tomorrow = DateTime.now().plusDays(1);
        //第二天凌晨一点执行第一一次
        Date firstExecuteDate = (new DateTime(tomorrow.getYear(),
                tomorrow.getMonthOfYear(), tomorrow.getDayOfMonth(), 1, 0))
                        .toDate();
        //定时任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    getJdbcTemplate().update(removeExpiredTokensSql,
                            new Date((new Date()).getTime() - validitySeconds));
                } catch (Exception e) {
                    logger.error(
                            "delete expired token error:" + e.getMessage());
                }
                System.out.println("设定指定任务task在指定延迟delay后执行");
            }
        }, firstExecuteDate, 1000 * 60 * 60 * 24);
    }
    
    /**
     * 创建新的Token值<br/>
     * <功能详细描述>
     * @param token [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void createNewToken(OperatorRememberMeToken token) {
        getJdbcTemplate().update(insertTokenSql,
                token.getId(),
                token.getUsername(),
                token.getToken(),
                token.getIpAddress(),
                token.getLastUseDate());
    }
    
    /**
     * 更新Token
     * <功能详细描述>
     * @param series
     * @param tokenValue
     * @param lastUsed [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void updateToken(String series, String tokenValue, String ipAddress,
            Date lastUsedDate) {
        getJdbcTemplate().update(updateTokenSql,
                tokenValue,
                ipAddress,
                lastUsedDate,
                series);
    }
    
    /**
     * Loads the token data for the supplied series identifier.
     *
     * If an error occurs, it will be reported and null will be returned (since the result
     * should just be a failed persistent login).
     *
     * @param seriesId
     * @return the token matching the series, or null if no match found or an exception
     * occurred.
     */
    public OperatorRememberMeToken getTokenForSeries(String seriesId) {
        try {
            return getJdbcTemplate().queryForObject(tokensBySeriesSql,
                    new RowMapper<OperatorRememberMeToken>() {
                        @Override
                        public OperatorRememberMeToken mapRow(ResultSet rs,
                                int rowNum) throws SQLException {
                            return new OperatorRememberMeToken(rs.getString(1),
                                    rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getTimestamp(5));
                        }
                    },
                    seriesId);
        } catch (EmptyResultDataAccessException zeroResults) {
            if (logger.isDebugEnabled()) {
                logger.debug(
                        "Querying token for series '" + seriesId
                                + "' returned no results.",
                        zeroResults);
            }
        } catch (IncorrectResultSizeDataAccessException moreThanOne) {
            logger.error("Querying token for series '" + seriesId
                    + "' returned more than one value. Series"
                    + " should be unique");
        } catch (DataAccessException e) {
            logger.error("Failed to load token for series " + seriesId, e);
        }
        
        return null;
    }
    
    /**
     * 根据用户名移除Token<br/>
     * <功能详细描述>
     * @param username [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void removeUserTokens(String username) {
        getJdbcTemplate().update(removeUserTokensSql, username);
    }
}
