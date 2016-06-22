package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Color;

public class ColorController implements Controller{

   public void delete(CommonObject obj) {
      Color color = (Color) obj;
      HibernateHelper.currentSession();.delete(color);
   }

   public void create(CommonObject obj) {
      Color color = (Color) obj;
      HibernateHelper.currentSession().save(color);
   }

   public void update(CommonObject obj) {
      Color color = (Color) obj;
      HibernateHelper.currentSession().update(color);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Color as c where c.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Color> colorList = query.list();
      if (colorList.size() > 0){
         output = colorList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Color";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Color> colorList = query.list();
      for (Color color : colorList) {
         output.add(color);
      }
      return output;
   }
}