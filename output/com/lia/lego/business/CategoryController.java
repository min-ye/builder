package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Category;

public class CategoryController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Category category = (Category) obj;
         session.delete(category);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Category category = (Category) obj;
         session.save(category);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Category category = (Category) obj;
         session.update(category);
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
         String hql="from com.lia.lego.Category as c where c.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Category> categoryList = query.list();
         if (categoryList.size() > 0){
            output = categoryList.get(0);
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
         String hql="from com.lia.lego.Category";
         Query query=session.createQuery(hql);

         List<Category> categoryList = query.list();
         for (Category category : categoryList) {
            output.add(category);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
}