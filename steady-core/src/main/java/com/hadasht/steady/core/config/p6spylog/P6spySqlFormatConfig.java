package com.hadasht.steady.core.config.p6spylog;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import lombok.extern.slf4j.Slf4j;

import static com.p6spy.engine.logging.Category.STATEMENT;
import static java.util.Locale.ROOT;
import static org.hibernate.engine.jdbc.internal.FormatStyle.BASIC;
import static org.hibernate.engine.jdbc.internal.FormatStyle.DDL;

@Slf4j
public class P6spySqlFormatConfig implements MessageFormattingStrategy {

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {

		return String.format("%s | %dms | %s | connection %d | %s%s", now, elapsed, category, connectionId, P6Util
				.singleLine(prepared), formatSql(category, sql));
	}

	private String formatSql(String category, String sql) {
		if (sql == null || sql.trim().equals("")) {
			return sql;
		}

		// Only format Statement, distinguish DDL And DML
		if (STATEMENT.getName().equals(category)) {
			String tempSql = sql.trim().toLowerCase(ROOT);
			sql = "|\nHeFormatSql(P6Spy sql,Hibernate format):" + getSql(sql, tempSql);
		}

		return sql;
	}

	private String getSql(String sql, String tempSql) {
		if (isDDL(tempSql)) {
			sql = DDL.getFormatter().format(sql);
		} else {
			sql = BASIC.getFormatter().format(sql);
		}

		return sql;
	}

	private boolean isDDL(String tmpsql) {
		return tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment");
	}

}