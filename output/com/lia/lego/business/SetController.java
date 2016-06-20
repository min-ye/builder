package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Set;

public class SetController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Set set = (Set) obj;
         session.delete(set);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Set set = (Set) obj;
         session.save(set);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Set set = (Set) obj;
         session.update(set);
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
         String hql="from com.lia.lego.Set as s where s.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Set> setList = query.list();
         if (setList.size() > 0){
            output = setList.get(0);
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
         String hql="from com.lia.lego.Set";
         Query query=session.createQuery(hql);

         List<Set> setList = query.list();
         for (Set set : setList) {
            output.add(set);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
}