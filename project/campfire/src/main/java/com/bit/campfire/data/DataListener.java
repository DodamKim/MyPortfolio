package com.bit.campfire.data;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import com.bit.campfire.dao.TempDao;

@Component
public class DataListener implements HttpSessionListener {

	public DataListener() {
		super();
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {

		HttpSessionListener.super.sessionCreated(se);

		try {

			if (se.getSession().isNew()) {

				TempDao dao = new TempDao();
				int r = dao.insertTempVisit();

				if (r == 1) {
					System.out.println("세션생성: 방문자 +1");

				} else {
					System.out.println("세션생성: error - 방문자 기록 실패");
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
