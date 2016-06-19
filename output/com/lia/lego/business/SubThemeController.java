package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.SubTheme;

public class SubThemeController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            SubTheme subTheme = (SubTheme) obj;
            session.delete(subTheme);
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      
   }

   public void create(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            SubTheme subTheme = (SubTheme) obj;
            session.save(subTheme);
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      
   }

   public void update(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            SubTheme subTheme = (SubTheme) obj;
            session.update(subTheme);
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            String hql="from com.lia.lego.SubTheme as s where s.Key=:key";
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<SubTheme> subThemeList = query.list();
            if (subThemeList.size() > 0){
               output = subThemeList.get(0);
            }
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            String hql="from com.lia.lego.SubTheme";
            Query query=session.createQuery(hql);
            
            List<SubTheme> subThemeList = query.list();
            for (SubTheme subTheme : subThemeList) {
               output.add(subTheme);
            }
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return output;
   }

   public void delete(Session session, CommonObject obj) {
      SubTheme subTheme = (SubTheme) obj;
      session.delete(subTheme);
   }

   public void create(Session session, CommonObject obj) {
      SubTheme subTheme = (SubTheme) obj;
      session.save(subTheme);
      
   }

   public void update(Session session, CommonObject obj) {

      SubTheme subTheme = (SubTheme) obj;
      session.update(subTheme);
      
   }

   public CommonObject retrieveAccordingKey(Session session, UUID key) {
      CommonObject output = null;
      String hql="from com.lia.lego.SubTheme as s where s.Key=:key";
      Query query=session.createQuery(hql);
      query.setString("key", key.toString());
      
      List<SubTheme> subThemeList = query.list();
      if (subThemeList.size() > 0){
         output = subThemeList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve(Session session) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql="from com.lia.lego.SubTheme";
      Query query=session.createQuery(hql);

      List<SubTheme> subThemeList = query.list();
      for (SubTheme subTheme : subThemeList) {
         output.add(subTheme);
      }
      return output;
   }

}