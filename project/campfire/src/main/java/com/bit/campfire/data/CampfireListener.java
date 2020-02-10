package com.bit.campfire.data;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bit.campfire.dao.TempDao;

/**
 * Application Lifecycle Listener implementation class CampfireListener
 *
 */
@WebListener
public class CampfireListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public CampfireListener() {

	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		
		try {
			
			if(se.getSession().isNew()){
	           
				TempDao dao = new TempDao();
				int r = dao.insertTempVisit();
				
				if(r == 1) {
					System.out.println("세션생성: 방문자 +1");
					
				} else {
					System.out.println("세션생성: error - 방문자 기록 실패");
				}
				
	        }

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

}
