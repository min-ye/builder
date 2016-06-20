package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Brick;

public class BrickController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Brick brick = (Brick) obj;
         session.delete(brick);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Brick brick = (Brick) obj;
         session.save(brick);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Brick brick = (Brick) obj;
         session.update(brick);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         String hql="from com.lia.lego.Brick as b where b.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Brick> brickList = query.list();
         if (brickList.size() > 0){
            output = brickList.get(0);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         String hql="from com.lia.lego.Brick";
         Query query=session.createQuery(hql);

         List<Brick> brickList = query.list();
         for (Brick brick : brickList) {
            output.add(brick);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
}