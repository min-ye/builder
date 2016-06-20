package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Color;

public class ColorController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Color color = (Color) obj;
         session.delete(color);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Color color = (Color) obj;
         session.save(color);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Color color = (Color) obj;
         session.update(color);
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
         String hql="from com.lia.lego.Color as c where c.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Color> colorList = query.list();
         if (colorList.size() > 0){
            output = colorList.get(0);
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
         String hql="from com.lia.lego.Color";
         Query query=session.createQuery(hql);

         List<Color> colorList = query.list();
         for (Color color : colorList) {
            output.add(color);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
}